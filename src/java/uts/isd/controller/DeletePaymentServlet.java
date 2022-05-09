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

public class DeletePaymentServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();   
        // get instance of validator
        Validator validator = new Validator();
        

        // saved order and paymentinfo must be in session so retrieve it
        Order savedOrder = (Order) session.getAttribute("savedOrder");
        PaymentInformation savedPayment = (PaymentInformation) session.getAttribute("savedPayment");
        
        
        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBPaymentInformationManager paymentInformationManager = (DBPaymentInformationManager) session.getAttribute("paymentInformationManager");
        try {   
            // clear saved payment session object
            savedPayment.setPaymentInformationID(0);
            savedPayment.setCardNumber("");
            savedPayment.setCardType("");
            savedPayment.setExpiryDate("");
            savedPayment.setCVV(0);
            
            // then update order's payment id in db and object
            int orderID = savedOrder.getOrderID();
            orderManager.updateOrderPaymentID(orderID, 0);
            savedOrder.setPaymentInformationID(0);
            
            request.setAttribute("updatedSavedPayment", "Successfully Deleted Payment Information");
            
            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(DeletePaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        }
    }
}
