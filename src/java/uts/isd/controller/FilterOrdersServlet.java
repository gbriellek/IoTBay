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
import uts.isd.model.*;
import uts.isd.model.dao.*;

public class FilterOrdersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               //1- retrieve the current session
        HttpSession session = request.getSession();
        // get the search terms from the request
        String orderID = request.getParameter("orderID").trim();
        String orderDate = request.getParameter("orderDate").trim();
        // get instance of validator
        Validator validator = new Validator();
        // validate request parameters
        if (orderID.length() > 0 && !validator.validateID(orderID)) {
            //set the session attribute to order id error
            request.setAttribute("orderError", "Please enter a valid order ID");
            //Sending user back to order history page
            request.getRequestDispatcher("orderHistory.jsp").include(request, response);
            return;
        }
        if (orderDate.length() > 0 && !validator.validateDate(orderDate)) {
            //set the session attribute to order id error
            request.setAttribute("orderError", "Please enter a valid order date (yyyy-mm-dd)");
            //Sending user back to order history page
            request.getRequestDispatcher("orderHistory.jsp").include(request, response);
            return;
        }
        //convert date

        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBOrderLineManager orderLineManager = (DBOrderLineManager) session.getAttribute("orderLineManager");
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        // retrieve the customer stored in session
        Customer customer = (Customer) session.getAttribute("user");
        try {
            // convert String to date
            Date convertedDate = Date.valueOf(LocalDate.now());
            if (orderDate.length() > 0) {
                convertedDate = Date.valueOf(orderDate);
            }
            //convert string to int
            int convertedOrderID = 0;
            if (orderID.length() > 0) {
                convertedOrderID = Integer.parseInt(orderID);
            }

            //get user id from customer object
            int userID = customer.getCustomerID();

            ArrayList<Order> filterOrders = new ArrayList<Order>();
            // find past orders via filter in db for the customer
            if (orderID.length() == 0 && orderDate.length() != 0) {
                //filter by order date
                filterOrders = orderManager.findPastOrdersByUserIDDate(userID, convertedDate);
            } else if (orderID.length() != 0 && orderDate.length() == 0) {
                //filter by order id
                filterOrders = orderManager.findPastOrdersByUserIDAndOrderID(userID, convertedOrderID);
            } else if (orderID.length() != 0 && orderDate.length() != 0) {
                // filter by both
                filterOrders = orderManager.findPastOrdersByUserIDAndDateAndOrderID(userID, convertedDate, convertedOrderID);
            }
            // store the orders in a session attribute
            request.setAttribute("filterOrders", filterOrders);

            // find orderline for each order id
            ArrayList<ArrayList<OrderLine>> orderLines = new ArrayList<ArrayList<OrderLine>>();
            for (Order o : filterOrders) {
                orderLines.add(orderLineManager.findOrderLineByOrderID(o.getOrderID()));
            }
            // store the orders in a session attribute
            request.setAttribute("filterOrderLines", orderLines);

            // get product names for each orderline
            ArrayList<ArrayList<String>> productNames = new ArrayList<ArrayList<String>>();
            ArrayList<String> innerProductNames;
            for (ArrayList<OrderLine> oList : orderLines) {
                innerProductNames = new ArrayList<String>();
                for (OrderLine ol : oList) {
                    innerProductNames.add(productManager.findProductNameByID(ol.getProductID()));
                }
                productNames.add(innerProductNames);
            }
            request.setAttribute("filterProductNames", productNames);
            
            // spit back out the orderid and orderdate field values
            request.setAttribute("orderID", orderID);
            request.setAttribute("orderDate", orderDate);

            // redirect customer to order history page
            request.getRequestDispatcher("orderHistory.jsp").include(request, response);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("orderError", ex.getMessage());
            // redirect customer to order history page
            request.getRequestDispatcher("orderHistory.jsp").include(request, response);
            return;
        }
    }
}
