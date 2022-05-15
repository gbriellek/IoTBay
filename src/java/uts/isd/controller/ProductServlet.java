package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
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

/*
 * Author:  Jemma S
 * Created: 6 May 2022
 */

public class ProductServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();        
  
        //2- retrieve the manager instance from session      
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        try {    
            //getting all products from database
            ArrayList<Product> product = productManager.findAllProduct();
            session.setAttribute("products", product);
            
            session.removeAttribute("noProductsError");
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("noProductsError", "No Products Available");
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        }
    }
}
