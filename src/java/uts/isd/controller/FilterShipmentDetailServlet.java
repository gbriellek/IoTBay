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

public class FilterShipmentDetailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               //1- retrieve the current session
        HttpSession session = request.getSession();
        // get the search terms from the request
        String shipmentID = request.getParameter("shipmentID").trim();
        String shipmentDate = request.getParameter("shipmentDate").trim();
        // get instance of validator
        Validator validator = new Validator();
        // validate request parameters
        if (shipmentID.length() > 0 && !validator.validateID(shipmentID)) {
            //set the session attribute to order id error
            request.setAttribute("shipmentDetailError", "Please enter a valid shipment detail ID");
            //Sending user back to login page
            request.getRequestDispatcher("shipmentHistory.jsp").include(request, response);
            return;
        }
        if (shipmentDate.length() > 0 && !validator.validateDate(shipmentDate)) {
            //set the session attribute to order id error
            request.setAttribute("shipmentDetailError", "Please enter a valid shipment date (yyyy-mm-dd)");
            //Sending user back to login page
            request.getRequestDispatcher("shipmentHistory.jsp").include(request, response);
            return;
        }
        //convert date

        //5- retrieve the manager instance from session      
        DBShipmentDetailManager shipmentDetailManager = (DBShipmentDetailManager) session.getAttribute("shipmentDetailManager");
        DBAddressManager addressManager = (DBAddressManager) session.getAttribute("addressManager");
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        // retrieve the customer stored in session
        Customer customer = (Customer) session.getAttribute("user");
        try {
            // convert String to date
            Date convertedDate = Date.valueOf(LocalDate.now());
            if (shipmentDate.length() > 0) {
                convertedDate = Date.valueOf(shipmentDate);
            }
            //convert string to int
            int convertedShipmentID = 0;
            if (shipmentID.length() > 0) {
                convertedShipmentID = Integer.parseInt(shipmentID);
            }

            //get user id from customer object
            int userID = customer.getCustomerID();

            ArrayList<ShipmentDetail> filterShipmentDetail = new ArrayList<ShipmentDetail>();
            // find past orders via filter in db for the customer
            if (shipmentID.length() == 0 && shipmentDate.length() != 0) {
                //filter by order date
                filterShipmentDetail = shipmentDetailManager.findPastShipmentDetailByUserIDDate(userID, convertedDate);
            } else if (shipmentID.length() != 0 && shipmentDate.length() == 0) {
                //filter by order id
                filterShipmentDetail = shipmentDetailManager.findPastShipmentDetailByUserIDShipmentID(userID, convertedShipmentID);
            } else if (shipmentID.length() != 0 && shipmentDate.length() != 0) {
                // filter by both
                filterShipmentDetail = shipmentDetailManager.findPastShipmentDetailByUserIDDateOrderID(userID, convertedDate, convertedShipmentID);
            }
            // store the orders in a session attribute
            request.setAttribute("filterShipmentDetail", filterShipmentDetail);
            // spit back out the orderid and orderdate field values
            request.setAttribute("shipmentID", shipmentID);
            request.setAttribute("shipmentDate", shipmentDate);
            
            //get all address associated with each shipment detail
            ArrayList<Address> addresses = new ArrayList<>();
            for (ShipmentDetail x: filterShipmentDetail) {
                addresses.add(addressManager.findAddressByID(x.getAddressID()));
            }
            request.setAttribute("filterAddress", addresses);
            
            //get all orders associated with each shipment detail
            ArrayList<Order> orders = new ArrayList<>();
            for (ShipmentDetail x: filterShipmentDetail) {
                System.out.println(x.getShipmentDetailID());
                orders.add(orderManager.findPastOrderByShipmentID(x.getShipmentDetailID()));
            }
            request.setAttribute("filterOrder", orders);

            // redirect customer to order history page
            request.getRequestDispatcher("shipmentHistory.jsp").include(request, response);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("orderError", ex.getMessage());
            // redirect customer to order history page
            request.getRequestDispatcher("shipmentHistory.jsp").include(request, response);
            return;
        }
    }
}
