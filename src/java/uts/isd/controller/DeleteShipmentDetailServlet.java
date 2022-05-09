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

public class DeleteShipmentDetailServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();           

        // saved order and paymentinfo must be in session so retrieve it
        Order savedOrder = (Order) session.getAttribute("savedOrder");
        ShipmentDetail savedShipmentDetail = (ShipmentDetail) session.getAttribute("savedShipmentDetail");
        Address savedAddress = (Address) session.getAttribute("savedAddress");
        
        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBShipmentDetailManager shipmentDetailManager = (DBShipmentDetailManager) session.getAttribute("shipmentDetailManager");
        try {   
            int originalShipID = savedShipmentDetail.getShipmentDetailID();
            // clear saved shipment session object
            savedShipmentDetail.setShipmentDetailID(0);
            savedShipmentDetail.setAddressID(0);
            savedShipmentDetail.setDeliveryFee(0);
            savedShipmentDetail.setDeliveryInstructions(null);
            savedShipmentDetail.setDeliveryDate(Date.valueOf(LocalDate.now()));
            savedShipmentDetail.setDeliveryMethod("");
            
            // clear saved address session object
            savedAddress.setAddressID(0);
            savedAddress.setUnitNo(null);
            savedAddress.setStreetNo("");
            savedAddress.setStreetName("");
            savedAddress.setCity("");
            savedAddress.setPostcode(0);
            savedAddress.setState("");
            
            // then update order's payment id in db and object
            int orderID = savedOrder.getOrderID();
            orderManager.updateOrderShipmentDetailID(orderID, 0);
            savedOrder.setShipmentDetailID(0);
            
            // delete the shipment from db
            shipmentDetailManager.deleteShipmentDetail(originalShipID);
            
            request.setAttribute("updatedSavedShipment", "Successfully Deleted Shipment Details");
            
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(DeleteShipmentDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
    }
}
