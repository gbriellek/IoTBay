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

public class SavedShipmentDetailServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();        
        
        //5- retrieve the manager instance from session      
        DBShipmentDetailManager shipmentDetailManager = (DBShipmentDetailManager) session.getAttribute("shipmentDetailManager");
        DBAddressManager addressManager = (DBAddressManager) session.getAttribute("addressManager");
        try {    
            Order savedOrder = (Order) session.getAttribute("savedOrder");
            // looks for saved shipment detail in database
            ShipmentDetail savedShipmentDetail = shipmentDetailManager.findShipmentDetail(savedOrder.getShipmentDetailID());
            //calculate date            
            Date date = Date.valueOf(savedOrder.getOrderDate().toLocalDate().plusWeeks(1));
            if (savedShipmentDetail.getDeliveryMethod().equals("express")) {
                
                date = Date.valueOf(savedOrder.getOrderDate().toLocalDate().plusWeeks(2));
            }
            // update order date in db and object
            shipmentDetailManager.updateShipmentDate(savedShipmentDetail.getShipmentDetailID(), date);
            savedShipmentDetail.setDeliveryDate(date);
            //add the saved order to session
            session.setAttribute("savedShipmentDetail", savedShipmentDetail);
            
            //get the address by id 
            Address savedAddress = addressManager.findAddressByID(savedShipmentDetail.getAddressID());
            session.setAttribute("savedAddress", savedAddress);
            
            
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(AddToOrderServlet.class.getName()).log(Level.SEVERE, null, ex);            
            // if no shipment details make a new one for the session
            session.setAttribute("savedShipmentDetail", new ShipmentDetail(0,0,0,null,Date.valueOf(LocalDate.now()), ""));
            session.setAttribute("savedAddress", new Address(0,null,"","","",0,""));
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
    }
}
