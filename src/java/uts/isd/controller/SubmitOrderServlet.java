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

public class SubmitOrderServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();        

        //get user type
        String userType = (String) session.getAttribute("userType");
        
        // if user check for user details
        if (userType.equals("user")) {
            //get user object from session
            User user = (User) session.getAttribute("user");
            if (user.getFirstName().length() == 0) {
                // if empty user then set error telling them to add their details
                request.setAttribute("savedOrderError", "Please Add Your Details to the Order");
                request.getRequestDispatcher("savedOrder.jsp").include(request, response);
                return;
            }
        }
        // get the saved order from session
        Order savedOrder = (Order) session.getAttribute("savedOrder");
        // check for shipment and payment details
        if (savedOrder.getShipmentDetailID() == 0) {
            // set error telling them to add payment information
            request.setAttribute("savedOrderError", "Please Add Shipment Details to the Order");
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
        }
        if (savedOrder.getPaymentInformationID() == 0) {
            // set error telling them to add payment information
            request.setAttribute("savedOrderError", "Please Add Your Payment Information to the Order");
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
        }       

        
        
        //5- retrieve the manager instance from session      
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        DBOrderLineManager orderLineManager = (DBOrderLineManager) session.getAttribute("orderLineManager");
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        try {    
            //update order status to dispatched
            orderManager.updateOrderStatus(savedOrder.getOrderID());
            // clear session details for saved order
            session.removeAttribute("savedOrder");
            session.removeAttribute("savedOrderLines");
            session.removeAttribute("savedProductNames");
            session.removeAttribute("savedPayment");
            
            request.setAttribute("orderSubmitted", "Order Submitted!");
            
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(SubmitOrderServlet.class.getName()).log(Level.SEVERE, null, ex);            
            // show error being like add please browse for products
            request.setAttribute("noSavedOrderError", "You have no saved orders. Please browse for products.");
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
        }
    }
}
