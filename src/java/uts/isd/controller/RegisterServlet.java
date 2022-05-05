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

public class RegisterServlet extends HttpServlet {
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();
        
        //Clear error in the session
        session.removeAttribute("registerError");
        
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
        //4- capture the posted password  
        String password = request.getParameter("password");
        //Get TOS
        String tos = request.getParameter("tos");
        
        if (!validator.validateName(fName)) {
            //set the session attribute to email error
            session.setAttribute("registerError", "Please enter a first name");
            //Sending user back to login page
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        
        if (!validator.validateName(lName)) {
            //set the session attribute to email error
            session.setAttribute("registerError", "Please enter a last name");
            //Sending user back to login page
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        
        if (!validator.validateEmail(email)) {
            //set the session attribute to email error
            session.setAttribute("registerError", "Incorrect email format");
            //Sending user back to login page
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        if (!validator.validatePassword(password)) {
            //set the session attribute to email error
            session.setAttribute("registerError", "Incorrect password format");
            //Sending user back to login page
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        
        if (!validator.validatePhone(phoneNo)) {
            //set the session attribute to email error
            session.setAttribute("registerError", "Enter a mobile phone number e.g. 0478418342");
            //Sending user back to login page
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        
        //Test is they acceoted TOS
        if (tos == null) {
            session.setAttribute("registerError", "You cannot proceed without accepting TOS");
             //Sending user back to register page
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        
        //5- retrieve the manager instance from session      
        DBCustomerManager customerManager = (DBCustomerManager) session.getAttribute("customerManager");
        try {    
            //add new customer to databse
            customerManager.addCustomer(email, fName, lName, phoneNo, password, true);
            //6- find user by email
            Customer customer = customerManager.findCustomerByEmail(email);
            // add customer to session
            session.setAttribute("user", customer);
            session.setAttribute("requestType", "register");
            session.setAttribute("userType", "customer");
            request.getRequestDispatcher("welcome.jsp").include(request, response);
            return;
        } catch (SQLException ex) {    
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("registerError", "Customer not found");
            //Sending user back to register page
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
    }
}
