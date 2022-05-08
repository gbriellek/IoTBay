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

public class FilterPaymentInformationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               //1- retrieve the current session
        HttpSession session = request.getSession();
        // get the search terms from the request
        String payID = request.getParameter("paymentID").trim();
        String payDate = request.getParameter("paymentDate").trim();
        // get instance of validator
        Validator validator = new Validator();
        // validate request parameters
        if (payID.length() > 0 && !validator.validateID(payID)) {
            //set the session attribute to order id error
            request.setAttribute("paymentError", "Please enter a valid payment information ID");
            //Sending user back to payment history page
            request.getRequestDispatcher("paymentHistory.jsp").include(request, response);
            return;
        }
        if (payDate.length() > 0 && !validator.validateDate(payDate)) {
            //set the session attribute to order id error
            request.setAttribute("paymentError", "Please enter a valid payment information date (yyyy-mm-dd)");
            //Sending user back to payment history page
            request.getRequestDispatcher("paymentHistory.jsp").include(request, response);
            return;
        }
        //convert date

        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBPaymentInformationManager paymentInformationManager = (DBPaymentInformationManager) session.getAttribute("paymentInformationManager");
        DBShipmentDetailManager shipmentDetailManager = (DBShipmentDetailManager) session.getAttribute("shipmentDetailManager");;
        // retrieve the customer stored in session
        Customer customer = (Customer) session.getAttribute("user");
        try {
            // convert String to date
            Date convertedDate = Date.valueOf(LocalDate.now());
            if (payDate.length() > 0) {
                convertedDate = Date.valueOf(payDate);
            }
            //convert string to int
            int convertedOrderID = 0;
            if (payID.length() > 0) {
                convertedOrderID = Integer.parseInt(payID);
            }

            //get user id from customer object
            int userID = customer.getCustomerID();

            ArrayList<Order> filterOrders = new ArrayList<Order>();
            // find past orders via filter in db for the customer
            if (payID.length() == 0 && payDate.length() != 0) {
                //filter by order date
                filterOrders = orderManager.findPastOrderPaymentsByUserIDDate(userID, convertedDate);
            } else if (payID.length() != 0 && payDate.length() == 0) {
                //filter by order id
                filterOrders = orderManager.findPastOrderPaymentsByUserIDAndPaymentID(userID, convertedOrderID);
            } else if (payID.length() != 0 && payDate.length() != 0) {
                // filter by both
                filterOrders = orderManager.findPastOrderPaymentsByUserIDAndDateAndPaymentID(userID, convertedDate, convertedOrderID);
            }
            // store the orders in a session attribute
            request.setAttribute("filterOrders", filterOrders);

            // get past payment info and shipment for each past order
            ArrayList<PaymentInformation> pastPaymentInfo = new ArrayList<PaymentInformation>();
            ArrayList<ShipmentDetail> pastShips = new ArrayList<ShipmentDetail>();
            for (Order o: filterOrders) {
                pastPaymentInfo.add(paymentInformationManager.findPaymentInformationByID(o.getPaymentInformationID()));
                pastShips.add(shipmentDetailManager.findShipmentDetail(o.getShipmentDetailID()));
            }
            // store the payment infos in a session attribute
            request.setAttribute("filterPayments", pastPaymentInfo);
            request.setAttribute("filterShips", pastShips);
            
            // spit back out the orderid and orderdate field values
            request.setAttribute("payID", payID);
            request.setAttribute("payDate", payDate);

            // redirect customer to order history page
            request.getRequestDispatcher("paymentHistory.jsp").include(request, response);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(FilterPaymentInformationServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("paymentError", ex.getMessage());
            // redirect customer to order history page
            request.getRequestDispatcher("paymentHistory.jsp").include(request, response);
            return;
        }
    }
}
