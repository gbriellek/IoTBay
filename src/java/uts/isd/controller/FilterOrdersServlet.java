package uts.isd.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.*;
import uts.isd.model.dao.*;

public class FilterOrdersServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               //1- retrieve the current session
        HttpSession session = request.getSession();
        // get the search terms from the request
        String orderID = request.getParameter("orderID");
        String  orderDate = request.getParameter("orderDate");
        // get instance of validator
        Validator validator = new Validator();
        // validate request parameters
        if (!validator.validateID(orderID)) {
            //set the session attribute to order id error
            request.setAttribute("orderError", "Incorrect order id");
            //Sending user back to login page
            request.getRequestDispatcher("orderHistory.jsp").include(request, response);
            return;
        }
        if (!validator.validateDate(orderDate)) {
            
        }
        //convert date
        
        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBOrderLineManager orderLineManager = (DBOrderLineManager) session.getAttribute("orderLineManager");
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        // retrieve the customer stored in session
        Customer customer = (Customer) session.getAttribute("user");
        try {       
            // find past orders via filter in db for the customer
            if ()
            ArrayList<Order> pastOrders = orderManager.findPastOrdersByUserID(customer.getCustomerID());
            // store the orders in a session attribute
            request.setAttribute("orders", pastOrders);
            
            // find orderline for each order id
            ArrayList<ArrayList<OrderLine>> orderLines = new ArrayList<ArrayList<OrderLine>>();
            for (Order o: pastOrders) {
                orderLines.add(orderLineManager.findOrderLineByOrderID(o.getOrderID()));
            }
            // store the orders in a session attribute
            request.setAttribute("orderLines", orderLines);
            
            // get product names for each orderline
            ArrayList<ArrayList<String>> productNames = new ArrayList<ArrayList<String>>();
            ArrayList<String> innerProductNames;
            for (ArrayList<OrderLine> oList: orderLines) {
                innerProductNames = new ArrayList<String>();
                for (OrderLine ol: oList) {
                    innerProductNames.add(productManager.findProductNameByID(ol.getProductID()));
                }
                productNames.add(innerProductNames);
            }
            request.setAttribute("productNames", productNames);
//            System.out.println(productNames.size());
//            System.out.println(productNames.get(0).size());
            
            // redirect customer to order history page
            request.getRequestDispatcher("orderHistory.jsp").include(request, response);
            return;
        } catch (SQLException ex) {           
            Logger.getLogger(OrderHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);       
            request.setAttribute("orderError", ex.getMessage());
        }        
    }
}