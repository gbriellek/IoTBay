package uts.isd.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.dao.*;
import uts.isd.model.*;

/*
 * Author:  Sarah F
 * Created: 7 May 2022
 */

public class DeleteSavedItemServlet extends HttpServlet {
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();   
        
        //get the quantity and productID
        String productID = request.getParameter("productID");
        
        //convert strings to numbers
        int convertedProductID = Integer.parseInt(productID);

        // saved order and orderline must be in session so retrieve it
        Order savedOrder = (Order) session.getAttribute("savedOrder");
        ArrayList<OrderLine> savedOrderLines = (ArrayList<OrderLine>) session.getAttribute("savedOrderLines");
        //also need to get productsname
        ArrayList<String> productNames = (ArrayList<String>) session.getAttribute("savedProductNames");
   
        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBOrderLineManager orderLineManager = (DBOrderLineManager) session.getAttribute("orderLineManager");   
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        try {    
            int orderID = savedOrder.getOrderID();
            // store the items original quantity, original cost and grab orderline
            int originalQuantity = 0;
            double originalCost = 0;
            OrderLine originalOrderLine = savedOrderLines.get(0);
            boolean found = false;
            for (OrderLine ol: savedOrderLines) {
                if (ol.getProductID() == convertedProductID && ol.getOrderID() == orderID) {
                    originalQuantity = ol.getQuantity();
                    originalCost = ol.getPrice();
                    originalOrderLine = ol;
                    found = true;
                }
            }
            // if product not found do nothing
            if (!found) {
                request.getRequestDispatcher("savedOrder.jsp").include(request, response);
                return;
            }
            // try get product object
            Product product = productManager.findProductByID(convertedProductID);
            //get the product price and stock
            int stock = product.getStock();
            double price = product.getPrice();
            
            // check if it is the only item left in order - if so cancel order
            if (savedOrderLines.size() == 1) {
                orderManager.deleteOrder(orderID);
                //remove orderline object from list
                savedOrderLines.remove(originalOrderLine);                    
                // remove productname from list
                productNames.remove(product.getName());  
                //update session to remove saved order
                session.removeAttribute("savedOrder");
                session.removeAttribute("savedOrderLine");
                session.removeAttribute("savedProductNames");
            }
            // otherwise just delete ol from db and object
            else {
                orderLineManager.deleteOrderLine(orderID, convertedProductID);                    
                //remove orderline object from list
                savedOrderLines.remove(originalOrderLine);                    
                // remove productname from list
                productNames.remove(product.getName());                    
                //them update cost in order db and object
                double updatedCost = savedOrder.getTotalCost() - originalCost;
                Date updatedDate = Date.valueOf(LocalDate.now());
                orderManager.updateOrderDateAndCost(orderID, updatedDate, updatedCost);
                savedOrder.setTotalCost(updatedCost);
                savedOrder.setOrderDate(updatedDate);
            }
            //then return stock in db                
            int updatedStock = stock + originalQuantity;
            productManager.updateProductStock(convertedProductID, updatedStock);
            // if order was cancelled (list size should now be 0) show error being like add please browse for products
            if (savedOrderLines.size() == 0)
                request.setAttribute("noSavedOrderError", "You have no saved orders. Please browse for products.");
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
           
        } catch (SQLException ex) {    
            Logger.getLogger(UpdateSavedOrderServlet.class.getName()).log(Level.SEVERE, null, ex);            
            // show error being like add please browse for products
            request.setAttribute("savedOrderError", "You have no saved orders. Please browse for products.");
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
        }
    }
}
