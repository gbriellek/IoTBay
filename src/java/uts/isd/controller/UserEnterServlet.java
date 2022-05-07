package uts.isd.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
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

public class UserEnterServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession(); 
        // get the user manager from session
        DBUserManager userManager = (DBUserManager) session.getAttribute("userManager");
        try {
        //insert new user into db
        byte[] array = new byte[40]; // length is bounded by 7
        new Random().nextBytes(array);
        String tempEmail = new String(array, Charset.forName("UTF-8"));
        userManager.addUser(tempEmail, "","","");
        // get the insert user
        User user = userManager.findUser(tempEmail);
        // then set session attributes
        session.setAttribute("user", user);
        session.setAttribute("userType", "user");
        request.getRequestDispatcher("ProductServlet").include(request, response);
        return;
        } catch (SQLException ex) { 
            Logger.getLogger(AddToOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            // create new order
            request.setAttribute("registerError", ex.getMessage());
            //Sending user back to register page
            request.getRequestDispatcher("index.jsp").include(request, response);
            return;
        }
    }
}
