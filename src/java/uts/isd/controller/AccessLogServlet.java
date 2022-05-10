package uts.isd.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
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

public class AccessLogServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {     
        //1- retrieve the current session
        HttpSession session = request.getSession(); 
        // get the access log manager from session
        DBAccessLogManager accessLogManager = (DBAccessLogManager) session.getAttribute("accessLogManager");
        try {
            //get access logs based on current user
            String userType = (String) session.getAttribute("userType");
             if (userType.equals("staff")) {
                Staff staff = (Staff) session.getAttribute("user");
                ArrayList<AccessLog> logs = accessLogManager.findAccessLogByUserID(staff.getStaffID());
                session.setAttribute("accessLog", logs);
            }
            else if (userType.equals("customer")) {
                Customer customer = (Customer) session.getAttribute("user");
                ArrayList<AccessLog> logs = accessLogManager.findAccessLogByUserID(customer.getCustomerID());
                session.setAttribute("accessLog", logs);
            }
            else if (userType.equals("admin")) {
                ArrayList<AccessLog> logs = accessLogManager.findAllAccessLog();
                session.setAttribute("accessLog", logs);
            }
            request.getRequestDispatcher("accessLogs.jsp").include(request, response);
            return;
        } catch (SQLException ex) { 
            Logger.getLogger(AccessLogServlet.class.getName()).log(Level.SEVERE, null, ex);
            // create new order
            request.setAttribute("accessLogError", ex.getMessage());
            //Sending user back to register page
            request.getRequestDispatcher("accessLogs.jsp").include(request, response);
            return;
        }
    }
}
