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
 * @author Gabrielle K
 */

public class UpdateShipmentDetailServlet extends HttpServlet {
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        //1- retrieve the current session
        HttpSession session = request.getSession();   
        // get instance of validator
        Validator validator = new Validator();
        
        //get the request params
        String shipmentID = request.getParameter("shipmentID");
        String unitNo = request.getParameter("unitNo").trim();
        String streetNo = request.getParameter("streetNo").trim();
        String streetName = request.getParameter("streetName").trim();
        String city = request.getParameter("city").trim();
        String postcode = request.getParameter("postcode").trim();
        String state = request.getParameter("state").trim();
        String deliveryMethod = request.getParameter("deliveryMethod").trim();
        String deliveryInstructions = request.getParameter("deliveryInstructions").trim();
        
        //validate the request parameters
        if (unitNo.length() > 0 && !validator.validateAddressNo(unitNo)) {
            //set the session attribute to unit no error
            request.setAttribute("savedShipmentError", "Please enter a valid unit number");
            //Sending user back to saved order page
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
        if (!validator.validateAddressNo(streetNo)) {
            //set the session attribute to street no error
            request.setAttribute("savedShipmentError", "Please enter a valid street number");
            //Sending user back to saved order page
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
        if (!validator.validateStreet(streetName)) {
            //set the session attribute to street error
            request.setAttribute("savedShipmentError", "Please enter a valid street name");
            //Sending user back to saved order page
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
        if (!validator.validateStreet(city)) {
            //set the session attribute to city error
            request.setAttribute("savedShipmentError", "Please enter a valid city");
            //Sending user back to saved order page
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
        if (!validator.validatePostcode(postcode)) {
            //set the session attribute to postcode error
            request.setAttribute("savedShipmentError", "Please enter a valid postcode");
            //Sending user back to saved order page
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
        if (!validator.validateState(state)) {
            //set the session attribute to state error
            request.setAttribute("savedShipmentError", "Please enter a valid state (ACT, NSW, NT, QLD, SA, TAS, VIC, WA)");
            //Sending user back to saved order page
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
        if (!validator.validateDeliveryMethod(deliveryMethod)) {
            //set the session attribute to deliveryMethod error
            request.setAttribute("savedShipmentError", "Please enter a valid delivery method (express/standard)");
            //Sending user back to saved order page
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
        if (deliveryInstructions.length() > 0 && !validator.validateDeliveryInstructions(deliveryInstructions)) {
            //set the session attribute to deliveryInstructions error
            request.setAttribute("savedShipmentError", "Please enter valid delivery instructions");
            //Sending user back to saved order page
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
        //convert strings to numbers
        int convertedPostcode = Integer.parseInt(postcode);
        int convertedShipID = Integer.parseInt(shipmentID);
        // convert optionals to nulls
        if (unitNo.length() == 0) {
            unitNo = null;
        }
        if (deliveryInstructions.length() == 0) {
            deliveryInstructions = null;
        }

        // saved order and shipment and address must be in session so retrieve it
        Order savedOrder = (Order) session.getAttribute("savedOrder");
        ShipmentDetail savedShipmentDetail = (ShipmentDetail) session.getAttribute("savedShipmentDetail");
        Address savedAddress = (Address) session.getAttribute("savedAddress");
   
        
        //calculate fee and date
        double fee = 10;
        Date date = Date.valueOf(savedOrder.getOrderDate().toLocalDate().plusWeeks(1));
        if (deliveryMethod.equals("express")) {
            fee = 20;
            date = Date.valueOf(savedOrder.getOrderDate().toLocalDate().plusWeeks(2));
        }
        
        String feedbackMessage = "Successfully Updated Shipment Details!";
        if (savedShipmentDetail.getShipmentDetailID() == 0) {
            feedbackMessage = "Successfully Added Shipment Details!";
        }
        
        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBShipmentDetailManager shipmentDetailManager = (DBShipmentDetailManager) session.getAttribute("shipmentDetailManager");
        DBAddressManager addressManager = (DBAddressManager) session.getAttribute("addressManager");
        
        try {
            // check if address exists
            int addressID = addressManager.findAddressID(unitNo, streetNo, streetName, city, convertedPostcode, state);
            //update address object in session
            savedAddress.setAddressID(addressID);
            savedAddress.setUnitNo(unitNo);
            savedAddress.setStreetNo(streetNo);
            savedAddress.setStreetName(streetName);
            savedAddress.setCity(city);
            savedAddress.setPostcode(convertedPostcode);
            savedAddress.setState(state);
            
            int shipID = savedShipmentDetail.getShipmentDetailID();
            
            //check whether or not shipment detail is new
            if (savedShipmentDetail.getShipmentDetailID() == 0) {
                //add shipment in db
                shipID = shipmentDetailManager.addShipmentDetail(addressID, fee, deliveryInstructions, date, deliveryMethod);
            }
            else {
                //update shipment details (inlcuding address id in object and db)
                shipmentDetailManager.updateShipmentDetail(savedShipmentDetail.getShipmentDetailID(), addressID, fee, deliveryInstructions, date, deliveryMethod);
            }
            //update shipment object
            savedShipmentDetail.setShipmentDetailID(shipID);
            savedShipmentDetail.setAddressID(addressID);
            savedShipmentDetail.setDeliveryFee(fee);
            savedShipmentDetail.setDeliveryInstructions(deliveryInstructions);
            savedShipmentDetail.setDeliveryDate(date);
            savedShipmentDetail.setDeliveryMethod(deliveryMethod);
            //update order shipment id in object and db
            orderManager.updateOrderShipmentDetailID(savedOrder.getOrderID(), shipID);
            savedOrder.setShipmentDetailID(shipID);
            
            //set feedback message
            request.setAttribute("updatedSavedShipment", feedbackMessage);
            
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;            
        }catch (SQLException ex) {    
            Logger.getLogger(UpdateShipmentDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // if address doesn't exist
        try {    
            int orderID = savedOrder.getOrderID();
            
            // add address
            int addressID = addressManager.addAddress(unitNo, streetNo, streetName, city, convertedPostcode, state);
            
            //update address object in session
            savedAddress.setAddressID(addressID);
            savedAddress.setUnitNo(unitNo);
            savedAddress.setStreetNo(streetNo);
            savedAddress.setStreetName(streetName);
            savedAddress.setCity(city);
            savedAddress.setPostcode(convertedPostcode);
            savedAddress.setState(state);
            
            int shipID = savedShipmentDetail.getShipmentDetailID();
            
            //check whether or not shipment detail is new
            if (savedShipmentDetail.getShipmentDetailID() == 0) {
                //add shipment in db
                shipID = shipmentDetailManager.addShipmentDetail(addressID, fee, deliveryInstructions, date, deliveryMethod);                
            }
            else {
                //update shipment details (inlcuding address id in object and db)
                shipmentDetailManager.updateShipmentDetail(savedShipmentDetail.getShipmentDetailID(), addressID, fee, deliveryInstructions, date, deliveryMethod);
            }
            //update shipment object
            savedShipmentDetail.setShipmentDetailID(shipID);
            savedShipmentDetail.setAddressID(addressID);
            savedShipmentDetail.setDeliveryFee(fee);
            savedShipmentDetail.setDeliveryInstructions(deliveryInstructions);
            savedShipmentDetail.setDeliveryDate(date);
            savedShipmentDetail.setDeliveryMethod(deliveryMethod);
            //update order shipment id in object and db
            orderManager.updateOrderShipmentDetailID(savedOrder.getOrderID(), shipID);
            savedOrder.setShipmentDetailID(shipID);
            
            //set feedback message
            request.setAttribute("updatedSavedShipment", feedbackMessage);
            
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(UpdateShipmentDetailServlet.class.getName()).log(Level.SEVERE, null, ex);            
            // show error being like add please browse for products
            request.setAttribute("savedShipmentError", ex.getMessage());
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
    }
}
