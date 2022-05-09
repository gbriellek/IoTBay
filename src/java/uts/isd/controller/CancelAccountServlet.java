package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
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
 * @author Raunak K
 */

public class CancelAccountServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();
        //check which user is logged in
        DBAccessLogManager accessLogManager = (DBAccessLogManager) session.getAttribute("accessLogManager");
        DBCustomerManager customerManager = (DBCustomerManager) session.getAttribute("customerManager");
        DBStaffManager staffManager = (DBStaffManager) session.getAttribute("staffManager");
        String userType = (String) session.getAttribute("userType");

        try {
            // add logout log
            if (userType.equals("staff")) {
                Staff staff = (Staff) session.getAttribute("user");
                accessLogManager.addAccessLog(staff.getStaffID(), new Timestamp(System.currentTimeMillis()), "Logut");
                // then delete account
                staffManager.deleteStaff(staff.getStaffID());
                //set the message
                request.setAttribute("logoutMessage", "Your account has been cancelled!");
            }
            else if (userType.equals("customer")) {
                Customer customer = (Customer) session.getAttribute("user");
                accessLogManager.addAccessLog(customer.getCustomerID(), new Timestamp(System.currentTimeMillis()), "Logut");
                // then delete account
                customerManager.deleteCustomerByID(customer.getCustomerID());
                // set the message
                request.setAttribute("logoutMessage", "Your account has been cancelled!");
            }
            //Invalidate the user session
            session.invalidate();
            //redirect the user to the logout page
            request.getRequestDispatcher("logout.jsp").include(request, response);
            return;
        } catch (SQLException ex) { 
            Logger.getLogger(CancelAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            //Invalidate the user session
            session.invalidate();
            //Sending user back to logout page
            request.getRequestDispatcher("logout.jsp").include(request, response);
            return;
        }
    }
}
