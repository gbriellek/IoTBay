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

        //get user type
        String userType = (String) session.getAttribute("userType");
        
        int userID = 0;
        // set userID to id customer or userid
        if (userType.equals("customer")) {
            //get customer object from session
            Customer customer = (Customer) session.getAttribute("user");
            userID = customer.getCustomerID();
        } else{
            //get user object from session
            User user = (User) session.getAttribute("user");
            userID = user.getUserID();
        }
        
        //5- retrieve the manager instance from session      
        DBShipmentDetailManager shipmentDetailManager = (DBShipmentDetailManager) session.getAttribute("shipmentDetailManager");
        DBAddressManager addressManager = (DBAddressManager) session.getAttribute("addressManager");
        try {    
            Order savedOrder = (Order) session.getAttribute("savedOrder");
            // looks for saved shipment detail in database
            ShipmentDetail savedShipmentDetail = shipmentDetailManager.findShipmentDetail(savedOrder.getShipmentDetailID());
            // update order date in db and object
            shipmentDetailManager.updateShipmentDate(savedShipmentDetail.getShipmentDetailID(), Date.valueOf(LocalDate.now()));
            savedOrder.setOrderDate(Date.valueOf(LocalDate.now()));
            //add the saved order to session
            session.setAttribute("savedShipmentDetail", savedShipmentDetail);
            
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(AddToOrderServlet.class.getName()).log(Level.SEVERE, null, ex);            
            // show error being like add please browse for products
            request.setAttribute("noSavedShipment", "You have no saved shipment details.");
            request.getRequestDispatcher("savedShipment.jsp").include(request, response);
            return;
        }
    }
}
