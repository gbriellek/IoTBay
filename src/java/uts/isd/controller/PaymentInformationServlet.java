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

public class PaymentInformationServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               //1- retrieve the current session
        HttpSession session = request.getSession();
        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBPaymentInformationManager paymentInformationManager = (DBPaymentInformationManager) session.getAttribute("paymentInformationManager");
        DBShipmentDetailManager shipmentDetailManager = (DBShipmentDetailManager) session.getAttribute("shipmentDetailManager");
        // retrieve the customer stored in session
        Customer customer = (Customer) session.getAttribute("user");
        try {       
            // find past orders in db for the customer
            ArrayList<Order> pastOrders = orderManager.findPastOrdersByUserID(customer.getCustomerID());
            // store the orders in a session attribute
            session.setAttribute("orders", pastOrders);
            
            // get past payment info and shipment for each past order
            ArrayList<PaymentInformation> pastPaymentInfo = new ArrayList<PaymentInformation>();
            ArrayList<ShipmentDetail> pastShips = new ArrayList<ShipmentDetail>();
            for (Order o: pastOrders) {
                pastPaymentInfo.add(paymentInformationManager.findPaymentInformationByID(o.getPaymentInformationID()));
                pastShips.add(shipmentDetailManager.findShipmentDetail(o.getShipmentDetailID()));
            }
            // store the payment infos in a session attribute
            session.setAttribute("paymentInfo", pastPaymentInfo);
            session.setAttribute("ships", pastShips);
            
            // redirect customer to payment history page
            request.getRequestDispatcher("paymentHistory.jsp").include(request, response);
            return;
        } catch (SQLException ex) {           
            Logger.getLogger(PaymentInformationServlet.class.getName()).log(Level.SEVERE, null, ex);       
            request.setAttribute("paymentError", ex.getMessage());
            // redirect customer to payment history page
            request.getRequestDispatcher("paymentHistory.jsp").include(request, response);
            return;
        }        
    }
}