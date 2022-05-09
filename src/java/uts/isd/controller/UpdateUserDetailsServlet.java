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

public class UpdateUserDetailsServlet extends HttpServlet {
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
        //Get phone
        String phoneNo = request.getParameter("phone");
        //4- capture the posted password  
        String password = request.getParameter("password");
        
        // validate user details
        if (!validator.validateName(fName)) {
            //set the session attribute to first name error
            request.setAttribute("updatedDetails", "Please enter a first name (must contain only letters)");
            //Sending user back to profile page
            request.getRequestDispatcher("main.jsp").include(request, response);
            return;
        }
        
        if (!validator.validateName(lName)) {
            //set the session attribute to last name error
            request.setAttribute("updatedDetails", "Please enter a last name (must contain only letters)");
            //Sending user back to profile page
            request.getRequestDispatcher("main.jsp").include(request, response);
            return;
        }
        
        if (!validator.validatePhone(phoneNo)) {
            //set the session attribute to phone error
            request.setAttribute("updatedDetails", "Enter a mobile phone number e.g. 0478418342");
            //Sending user back to profile page
            request.getRequestDispatcher("main.jsp").include(request, response);
            return;
        }
        if (password.length() > 0 && !validator.validatePassword(password)) {
            //set the session attribute to password error
            request.setAttribute("updatedDetails", "Incorrect password format (must be longer than 4 characters)");
            //Sending user back to profile page
            request.getRequestDispatcher("main.jsp").include(request, response);
            return;
        }
        
        //check which user is logged in
        DBCustomerManager customerManager = (DBCustomerManager) session.getAttribute("customerManager");
        DBStaffManager staffManager = (DBStaffManager) session.getAttribute("staffManager");
        String userType = (String) session.getAttribute("userType");

        try {
            // add logout log
            if (userType.equals("staff")) {
                Staff staff = (Staff) session.getAttribute("user");
                // only update password if they enter it
                if (password.length() > 0) {
                    // update staff in db
                    staffManager.updateStaff(staff.getStaffID(), fName, lName, phoneNo, password, staff.getStaffNumber());
                    //update staff object in session
                    staff.setFirstName(fName);
                    staff.setLastName(lName);
                    staff.setPhoneNumber(phoneNo);
                    staff.setPassword(password);
                }
                else {
                    // update staff in db
                    staffManager.updateStaff(staff.getStaffID(), fName, lName, phoneNo, staff.getPassword(), staff.getStaffNumber());
                    //update staff object in session
                    staff.setFirstName(fName);
                    staff.setLastName(lName);
                    staff.setPhoneNumber(phoneNo);
                }
            }
            else if (userType.equals("customer")) {
                Customer customer = (Customer) session.getAttribute("user");
                // only update password if they enter it
                if (password.length() > 0) {
                    // update staff in db
                    customerManager.updateCustomerByID(customer.getCustomerID(), fName, lName, phoneNo, password);
                    //update staff object in session
                    customer.setFirstName(fName);
                    customer.setLastName(lName);
                    customer.setPhoneNumber(phoneNo);
                    customer.setPassword(password);
                }
                else {
                    // update staff in db
                    customerManager.updateCustomerByID(customer.getCustomerID(), fName, lName, phoneNo, customer.getPassword());
                    //update staff object in session
                    customer.setFirstName(fName);
                    customer.setLastName(lName);
                    customer.setPhoneNumber(phoneNo);
                }
            }
            // set feedback text
            request.setAttribute("updatedDetails", "User Details Updated!");
            
            //redirect the user to the logout page
            request.getRequestDispatcher("main.jsp").include(request, response);
            return;
        } catch (SQLException ex) { 
            Logger.getLogger(UpdateUserDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
            //Sending user back to register page
            request.getRequestDispatcher("main.jsp").include(request, response);
            return;
        }
    }
}
