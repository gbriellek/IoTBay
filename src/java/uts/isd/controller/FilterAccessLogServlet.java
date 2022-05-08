package uts.isd.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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

public class FilterAccessLogServlet extends HttpServlet {
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();
        //get request paramters
        String date = request.getParameter("accessDate").trim();
        //Make new instance of validator class
        Validator validator = new Validator();
        if (date.length() > 0 && !validator.validateDate(date)) {
            //set the session attribute to order id error
            request.setAttribute("accessLogError", "Please enter a valid date (yyyy-mm-dd)");
            //Sending user back to accessLogs page
            request.getRequestDispatcher("accessLogs.jsp").include(request, response);
            return;
        }
         Date convertedDate = Date.valueOf(LocalDate.now());
         if (date.length() > 0) {
                convertedDate = Date.valueOf(date);
        } else {
             //Sending user back to accessLogs page
            request.getRequestDispatcher("accessLogs.jsp").include(request, response);
            return;
         }
        //get manager from session
        DBAccessLogManager accessLogManager = (DBAccessLogManager) session.getAttribute("accessLogManager");
        String userType = (String) session.getAttribute("userType");

        try {
            if (userType.equals("staff")) {
                Staff staff = (Staff) session.getAttribute("user");
                ArrayList<AccessLog> logs = accessLogManager.findAccessLogByUserIDDate(staff.getStaffID(), convertedDate);
                request.setAttribute("filterAccessLog", logs);
            }
            else if (userType.equals("customer")) {
                Customer customer = (Customer) session.getAttribute("user");
                ArrayList<AccessLog> logs = accessLogManager.findAccessLogByUserIDDate(customer.getCustomerID(), convertedDate);
                request.setAttribute("filterAccessLog", logs);
            } 
            else if (userType.equals("admin")) {
                ArrayList<AccessLog> logs = accessLogManager.findAllAccessLogByDate(convertedDate);
                request.setAttribute("filterAccessLog", logs);
            }
            request.setAttribute("requestDate", date);
            request.getRequestDispatcher("accessLogs.jsp").include(request, response);
            return;
        } catch (SQLException ex) { 
            Logger.getLogger(FilterAccessLogServlet.class.getName()).log(Level.SEVERE, null, ex);
            //Sending user back to register page
            request.getRequestDispatcher("accessLogs.jsp").include(request, response);
            return;
        }
    }
}