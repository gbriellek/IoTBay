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

public class AddToOrderServlet extends HttpServlet {
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();        
        
        //2- create an instance of the Validator class
        Validator validator = new Validator();
        //Get productID and quantity and stock and price
        String productID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String quantity = request.getParameter("quantity");
        String productStock = request.getParameter("productStock");
        String productPrice = request.getParameter("productPrice");
        // validate quantity
        if (!validator.validateStock(quantity)) {
            //set the session attribute to quantity error
            request.setAttribute("productError", "Please enter a number for quantity");
            //Sending user back to products page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        }
        //convert strings to numbers
        int convertedProductID = Integer.parseInt(productID);
        int convertedQuantity = Integer.parseInt(quantity);
        int convertedStock = Integer.parseInt(productStock);
        double convertedPrice = Double.parseDouble(productPrice);
        // error if quantity is more than stock
        if (convertedQuantity > convertedStock) {
            //set the session attribute to quantity error
            request.setAttribute("productError", "Not enough stock for " + productName);
            //Sending user back to products page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        }
        // check quantity isn't 0
        if (convertedQuantity <= 0) {
            //set the session attribute to quantity error
            request.setAttribute("productError", "Please entity a non zero quantity for " + productName);
            //Sending user back to products page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        }
        // calculate orderline cost
        double olCost = convertedQuantity * convertedPrice;

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
            // looks looks for saved order in database
            Order savedOrder = orderManager.findOrderByUserID(userID);
            //add the saved order to session
            session.setAttribute("savedOrder", savedOrder);
            // add product to orderline            
            int orderID = savedOrder.getOrderID();
            // first check if product is in the ol yet
            OrderLine existingOrderLine = orderLineManager.findOrderLineByOrderIDAndProductID(orderID, convertedProductID);
            if (existingOrderLine != null) {
                // update existing ol
                int updatedQuantity = existingOrderLine.getQuantity() + convertedQuantity;
                double updatedCost = existingOrderLine.getPrice() + olCost;
                orderLineManager.updateOrderLine(orderID, convertedProductID, updatedQuantity, updatedCost);
            }
            //if not make a new orderline
            else{
                orderLineManager.addOrderLine(orderID, convertedProductID, convertedQuantity, olCost);
            }
            
            // update costs in order in db and object            
            double totalCost = savedOrder.getTotalCost() + olCost;
            orderManager.addToOrder(orderID, totalCost);
            savedOrder.setTotalCost(totalCost);
            
            //decrease stock in db
            int updatedStock = convertedStock-convertedQuantity;
            productManager.updateProductStock(convertedProductID, updatedStock);
            // update product stock in product list in session
            ArrayList<Product> list_products = (ArrayList<Product>) session.getAttribute("products");
            for (Product p : list_products) {
                if (p.getProductID() == convertedProductID) {
                    p.setStock(updatedStock);
                }
            }
            
            // set error to say added to order
            request.setAttribute("productAdd", quantity + "x " + productName + " Added to Order");
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(AddToOrderServlet.class.getName()).log(Level.SEVERE, null, ex);            
            try {
                // create new order
                Date date = Date.valueOf(LocalDate.now());
                int orderID = orderManager.addOrder(userID, date, olCost);
                // add product to orderline
                orderLineManager.addOrderLine(orderID, convertedProductID, convertedQuantity, olCost);
                
                // set error to say added to order
                request.setAttribute("productAdd", quantity + "x " + productName + " Added to Order");
                
                //decrease stock in db
                int updatedStock = convertedStock-convertedQuantity;
                productManager.updateProductStock(convertedProductID, updatedStock);
                // update product stock in product list in session
                ArrayList<Product> list_products = (ArrayList<Product>) session.getAttribute("products");
                for (Product p : list_products) {
                    if (p.getProductID() == convertedProductID) {
                        p.setStock(updatedStock);
                    }
                }                
                
                //Sending user back to products page
                request.getRequestDispatcher("products.jsp").include(request, response);
                return;
            } catch (SQLException exception) {    
                Logger.getLogger(AddToOrderServlet.class.getName()).log(Level.SEVERE, null, exception);
                // set error to say unable to add to order
                request.setAttribute("productError", "Unable to Add " +  productName + " to Order");
                request.getRequestDispatcher("products.jsp").include(request, response);
                return;
            }
        }
    }
}
