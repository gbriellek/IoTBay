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

public class SavedOrderServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();        

        //get user type
        String userType = (String) session.getAttribute("userType");
        
        int userID = 0;
        // set userID to id customer or userid
        if (userType.equals("customer")) {
            //get customer object from session
            Customer customer = (Customer) session.getAttribute("user");
            userID = customer.getCustomerID();
        } else{
            //get user object from session
            User user = (User) session.getAttribute("user");
            userID = user.getUserID();
        }
        
        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBOrderLineManager orderLineManager = (DBOrderLineManager) session.getAttribute("orderLineManager");
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        try {    
            
            // looks for saved order in database
            Order savedOrder = orderManager.findOrderByUserID(userID);
            // update order date in db and object
            orderManager.updateOrderDateAndCost(savedOrder.getOrderID(), (java.sql.Date) Date.valueOf(LocalDate.now()), savedOrder.getTotalCost());
            savedOrder.setOrderDate(Date.valueOf(LocalDate.now()));
            //add the saved order to session
            session.setAttribute("savedOrder", savedOrder);            
            
            // find orderline for the saved order
            ArrayList<OrderLine> orderLines = orderLineManager.findOrderLineByOrderID(savedOrder.getOrderID());
            // store the orders in a session attribute
            session.setAttribute("savedOrderLines", orderLines);
            
            // get product names for each orderline
            ArrayList<String> productNames = new ArrayList<String>();
            for (OrderLine ol: orderLines) {
                productNames.add(productManager.findProductNameByID(ol.getProductID()));
            }
            session.setAttribute("savedProductNames", productNames);
            
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(AddToOrderServlet.class.getName()).log(Level.SEVERE, null, ex);            
            // show error being like add please browse for products
            request.setAttribute("noSavedOrderError", "You have no saved orders. Please browse for products.");
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
        }
    }
}
