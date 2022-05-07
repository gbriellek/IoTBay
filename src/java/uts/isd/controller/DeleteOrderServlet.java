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

public class DeleteOrderServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();   

        // saved order and orderline must be in session so retrieve it
        Order savedOrder = (Order) session.getAttribute("savedOrder");
        ArrayList<OrderLine> savedOrderLines = (ArrayList<OrderLine>) session.getAttribute("savedOrderLines");
   
        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBOrderLineManager orderLineManager = (DBOrderLineManager) session.getAttribute("orderLineManager");   
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        try {    
            int orderID = savedOrder.getOrderID();
            
            
            // cancel the order 
            orderManager.deleteOrder(orderID);      
            //update session to remove saved order
            session.removeAttribute("savedOrder");
            session.removeAttribute("savedOrderLine");
            session.removeAttribute("savedProductNames");
            
            //then return stock in db
            // loop through orderlines and return stock
            for (OrderLine ol: savedOrderLines) {
                Product product = productManager.findProductByID(ol.getProductID());
                int updatedQuantity = product.getStock() + ol.getQuantity();
                productManager.updateProductStock(ol.getProductID(), updatedQuantity);
            }
            request.setAttribute("noSavedOrderError", "You have no saved orders. Please browse for products.");
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
           
        } catch (SQLException ex) {    
            Logger.getLogger(DeleteOrderServlet.class.getName()).log(Level.SEVERE, null, ex);            
            // show error being like add please browse for products
            request.setAttribute("savedOrderError", "You have no saved orders. Please browse for products.");
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
        }
    }
}
