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

/**
 *
 * @author Mia Z
 */

public class UpdateSavedPaymentServlet extends HttpServlet {
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();   
        // get instance of validator
        Validator validator = new Validator();
        
        //get the request parameters
        String cardNo = request.getParameter("cardNo").trim();
        String cardType = request.getParameter("cardType").trim();
        String expiryDate = request.getParameter("expiryDate").trim();
        String cvv = request.getParameter("cvv").trim();
        
        
        //validate the user inputs
        if (!validator.validateCardNo(cardNo)) {
            //set the session attribute to cardNo error
            request.setAttribute("savedPaymentError", "Please enter a 16 digit number for card number");
            //Sending user back to saved payment page
            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        }
        if (!validator.validateCardType(cardType)) {
            //set the session attribute to cardType error
            request.setAttribute("savedPaymentError", "Please enter one of the following card types: AMEX, Mastercard, Visa");
            //Sending user back to saved payment page
            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        }
        if (!validator.validateExpiryDate(expiryDate)) {
            //set the session attribute to expiryDate error
            request.setAttribute("savedPaymentError", "Please enter an expiry date in the format yyyy-mm");
            //Sending user back to saved payment page
            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        }
         if (!validator.validateCVV(cvv)) {
            //set the session attribute to cvv error
            request.setAttribute("savedPaymentError", "Please enter a 3 or 4 digit number for cvv");
            //Sending user back to saved payment page
            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        }
        
        //convert strings to numbers
        int convertedCVV = Integer.parseInt(cvv);

        // saved order and paymentinfo must be in session so retrieve it
        Order savedOrder = (Order) session.getAttribute("savedOrder");
        PaymentInformation savedPayment = (PaymentInformation) session.getAttribute("savedPayment");            
        
        // check whether or not this is new details
        String feedbackMessage = "Successfully Updated Payment Details!";
        if (savedPayment.getPaymentInformationID() == 0) {
            feedbackMessage = "Successfully Added Payment Details!";
        }
        
        
        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBPaymentInformationManager paymentInformationManager = (DBPaymentInformationManager) session.getAttribute("paymentInformationManager");
        try {             
            // first check if card number exists
            PaymentInformation payment = paymentInformationManager.findPaymentInformationByCardNumber(cardNo);
            // if so check if the other details match - if not then update it in db
            if (payment.getCardType() != cardType || payment.getExpiryDate() != expiryDate) {
                paymentInformationManager.updatePaymentInformation(payment.getPaymentInformationID(), cardType, expiryDate);
            }
            int payID = payment.getPaymentInformationID();
            // update saved payment session object
            savedPayment.setPaymentInformationID(payID);
            savedPayment.setCardNumber(cardNo);
            savedPayment.setCardType(cardType);
            savedPayment.setExpiryDate(expiryDate);
            savedPayment.setCVV(convertedCVV);
            
            // then update order's payment id in db and object
            int orderID = savedOrder.getOrderID();
            orderManager.updateOrderPaymentID(orderID, payID);
            savedOrder.setPaymentInformationID(payID);
            
            request.setAttribute("updatedSavedPayment", feedbackMessage);
            
            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(UpdateSavedPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);   
        }
        // if card number doesn't exist
        try {
            // add to db and update object in session
            int payID = paymentInformationManager.addPaymentInformation(cardNo, cardType, expiryDate);
            savedPayment.setPaymentInformationID(payID);
            savedPayment.setCardNumber(cardNo);
            savedPayment.setCardType(cardType);
            savedPayment.setExpiryDate(expiryDate);
            savedPayment.setCVV(convertedCVV);
            // update order's payment id in db and object
            int orderID = savedOrder.getOrderID();
            orderManager.updateOrderPaymentID(orderID, payID);
            savedOrder.setPaymentInformationID(payID);
            
            
            request.setAttribute("updatedSavedPayment", feedbackMessage);
            
            
            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        }catch (SQLException ex) { 
            Logger.getLogger(UpdateSavedPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);            
            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        }
    }
}
