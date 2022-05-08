package uts.isd.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.sql.Timestamp;
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

public class LogoutServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();
        //check which user is logged in
        DBAccessLogManager accessLogManager = (DBAccessLogManager) session.getAttribute("accessLogManager");
        String userType = (String) session.getAttribute("userType");

        try {
            if (userType.equals("staff")) {
                Staff staff = (Staff) session.getAttribute("user");
                accessLogManager.addAccessLog(staff.getStaffID(), new Timestamp(System.currentTimeMillis()), "Logut");
            }
            else if (userType.equals("customer")) {
                Customer customer = (Customer) session.getAttribute("user");
                accessLogManager.addAccessLog(customer.getCustomerID(), new Timestamp(System.currentTimeMillis()), "Logut");
            }
            //Invalidate the user session
            session.invalidate();
            request.getRequestDispatcher("logout.jsp").include(request, response);
            return;
        } catch (SQLException ex) { 
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            //Invalidate the user session
            session.invalidate();
            //Sending user back to register page
            request.getRequestDispatcher("logout.jsp").include(request, response);
            return;
        }
    }
}
