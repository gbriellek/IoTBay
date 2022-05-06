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
public class FilterProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1- retrieve the current session
        HttpSession session = request.getSession();
        // get request parameters
        String productName = request.getParameter("productName");
        String category = request.getParameter("category");
        Validator validator = new Validator();
        if (productName.length() > 0 && !validator.validateProductName(productName)) {
            request.setAttribute("productError", "Please enter a valid product name");
            //redirect to page
            DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
            try {
                //getting all products from database
                ArrayList<Product> product = productManager.findAllProduct();
                request.setAttribute("products", product);
                //redirect to page
                request.getRequestDispatcher("products.jsp").include(request, response);
                return;
            } catch (SQLException ex) {
                Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("productError", ex.getMessage());
                //redirect to page
                request.getRequestDispatcher("products.jsp").include(request, response);
                return;
            }
        } else if (category.length() > 0 && !validator.validateCategory(category)) {
            request.setAttribute("productError", "Please enter a valid category");
            //redirect to page
            DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
            try {
                //getting all products from database
                ArrayList<Product> product = productManager.findAllProduct();
                request.setAttribute("products", product);
                //redirect to page
                request.getRequestDispatcher("products.jsp").include(request, response);
                return;
            } catch (SQLException ex) {
                Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("productError", ex.getMessage());
                //redirect to page
                request.getRequestDispatcher("products.jsp").include(request, response);
                return;
            }
        }

        //2- retrieve the manager instance from session      
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        try {
            if (productName.length() == 0 && category.length() != 0) {
                ArrayList<Product> filterCategory = productManager.findProductByCategory(category);
                request.setAttribute("filterCategory", filterCategory);
            } else if (productName.length() != 0 && category.length() == 0) {
                Product filterProductName = productManager.findProductByName(productName);
                request.setAttribute("filterName", filterProductName);
            } else if (productName.length() != 0 && category.length() != 0) {
                Product filterProductNameAndCategory = productManager.findProductByNameAndCategory(productName, category);
                request.setAttribute("filterName", filterProductNameAndCategory);
            } else {
                 ArrayList<Product> product = productManager.findAllProduct();
                request.setAttribute("products", product);
            }
           
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(FilterProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("productError", ex.getMessage());
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        }
    }
}
