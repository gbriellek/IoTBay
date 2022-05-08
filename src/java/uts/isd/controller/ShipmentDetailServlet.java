package uts.isd.controller;

import java.io.IOException;
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

public class ShipmentDetailServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               //1- retrieve the current session
        HttpSession session = request.getSession();
        //5- retrieve the manager instance from session      
        DBShipmentDetailManager shipmentDetailManager = (DBShipmentDetailManager) session.getAttribute("shipmentDetailManager");
        DBAddressManager addressManager = (DBAddressManager) session.getAttribute("addressManager");
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        // retrieve the customer stored in session
        Customer customer = (Customer) session.getAttribute("user");
        try {
            // find past shipment details in db for the customer
            ArrayList<ShipmentDetail> pastShipmentDetails = shipmentDetailManager.findShipmentDetailByUserIDSubmitted(customer.getCustomerID());
            // store the orders in a session attribute
            session.setAttribute("shipmentDetails", pastShipmentDetails);
            
            //get all address associated with each shipment detail
            ArrayList<Address> addresses = new ArrayList<>();
            for (ShipmentDetail x: pastShipmentDetails) {
                addresses.add(addressManager.findAddressByID(x.getAddressID()));
            }
            session.setAttribute("addresses", addresses);
            
            //get all orders associated with each shipment detail
            ArrayList<Order> orders = new ArrayList<>();
            for (ShipmentDetail x: pastShipmentDetails) {
                System.out.println(x.getShipmentDetailID());
                orders.add(orderManager.findPastOrderByShipmentID(x.getShipmentDetailID()));
            }
            session.setAttribute("orders", orders);
            
            // redirect customer to shipment history page
            request.getRequestDispatcher("shipmentHistory.jsp").include(request, response);
            return;
        } catch (SQLException ex) {           
            Logger.getLogger(ShipmentDetailServlet.class.getName()).log(Level.SEVERE, null, ex);       
            request.setAttribute("shipmentDetailError", ex.getMessage());
            // redirect customer to shipment history page
            request.getRequestDispatcher("shipmentHistory.jsp").include(request, response);
            return;
        }        
    }
}