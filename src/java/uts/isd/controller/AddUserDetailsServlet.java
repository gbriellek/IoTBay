package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.dao.*;
import uts.isd.model.*;

/*
 * Author:  Sarah F
 * Created: 7 May 2022
 */

public class AddUserDetailsServlet extends HttpServlet {
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();        
        
        //2- create an instance of the Validator class
        Validator validator = new Validator();
        //Get fname
        String fName = request.getParameter("fname");
        //Get lname
        String lName = request.getParameter("lname");
        //3- capture the posted email      
        String email = request.getParameter("email");
        //Get phone
        String phoneNo = request.getParameter("phone");
        
        if (!validator.validateName(fName)) {
            //set the session attribute to first name error
            request.setAttribute("addUserDetailsError", "Please enter a first name (must contain only letters)");
            //Sending user back to register page
            request.getRequestDispatcher("addUserDetails.jsp").include(request, response);
            return;
        }
        
        if (!validator.validateName(lName)) {
            //set the session attribute to last name error
            request.setAttribute("addUserDetailsError", "Please enter a last name (must contain only letters)");
            //Sending user back to register page
            request.getRequestDispatcher("addUserDetails.jsp").include(request, response);
            return;
        }
        
        if (!validator.validateEmail(email)) {
            //set the session attribute to email error
            request.setAttribute("addUserDetailsError", "Incorrect email format");
            //Sending user back to register page
            request.getRequestDispatcher("addUserDetails.jsp").include(request, response);
            return;
        }
        
        if (!validator.validatePhone(phoneNo)) {
            //set the session attribute to phone error
            request.setAttribute("addUserDetailsError", "Enter a mobile phone number e.g. 0478418342");
            //Sending user back to register page
            request.getRequestDispatcher("addUserDetails.jsp").include(request, response);
            return;
        }
        
        //5- retrieve the manager instance from session      
        DBUserManager userManager = (DBUserManager) session.getAttribute("userManager");
        try {    
            //add new customer to database
            userManager.addUser(email, fName, lName, phoneNo);
            //6- find user by email
            User user = userManager.findUser(email);
            // add user to session
            session.setAttribute("user", user);
            request.getRequestDispatcher("savedOrder.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(AddUserDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("addUserDetailsError", ex.getMessage());
            //Sending user back to register page
            request.getRequestDispatcher("addUserDetails.jsp").include(request, response);
            return;
        }
    }
}
