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

public class SavedPaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1- retrieve the current session
        HttpSession session = request.getSession();

        //5- retrieve the manager instance from session      
        DBPaymentInformationManager paymentInformationManager = (DBPaymentInformationManager) session.getAttribute("paymentInformationManager");
        DBShipmentDetailManager shipmentDetailManager = (DBShipmentDetailManager) session.getAttribute("shipmentDetailManager");
        try {
            // get the order from the session
            Order savedOrder = (Order) session.getAttribute("savedOrder");
            //look for saved payment info in the session that matches the savedOrder paymentinfo id
            PaymentInformation savedPayment = (PaymentInformation) session.getAttribute("savedPayment");
            if (savedPayment != null) {
                if (savedPayment.getPaymentInformationID() == savedOrder.getPaymentInformationID()) {
                    //get shipment detail if saved order has it
                    if (savedOrder.getShipmentDetailID() != 0) {
                        ShipmentDetail s = shipmentDetailManager.findShipmentDetail(savedOrder.getShipmentDetailID());
                        session.setAttribute("savedShipmentDetail", s);
                        System.out.println("b3" + s);
                    }

                    // if match go to savedPayment page
                    request.getRequestDispatcher("savedPayment.jsp").include(request, response);
                    return;
                } else {
                    //check if the order has payment associated with it
                    if (savedOrder.getPaymentInformationID() != 0) {
                        //get the correct payment info from db
                        PaymentInformation paymentInfo = paymentInformationManager.findPaymentInformationByID(savedOrder.getPaymentInformationID());
                        session.setAttribute("savedPayment", paymentInfo);
                    } else {
                        // add a new saved payment to the session
                        session.setAttribute("savedPayment", new PaymentInformation(0, "", "", "", 0));
                    }
                }
            } else {
                //then look for payment in db
                PaymentInformation paymentInfo = paymentInformationManager.findPaymentInformationByID(savedOrder.getPaymentInformationID());
                session.setAttribute("savedPayment", paymentInfo);
            }

            //get shipment detail if saved order has it
            if (savedOrder.getShipmentDetailID() != 0) {
                ShipmentDetail s = shipmentDetailManager.findShipmentDetail(savedOrder.getShipmentDetailID());
                session.setAttribute("savedShipmentDetail", s);
                System.out.println("b3" + s);
            }

            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(SavedPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            // if no saved payment then pass in empty saved payment object
            session.setAttribute("savedPayment", new PaymentInformation(0, "", "", "", 0));

            request.getRequestDispatcher("savedPayment.jsp").include(request, response);
            return;
        }
    }
}
