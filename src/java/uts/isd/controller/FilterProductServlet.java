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
        // 1 - Retrieve the current session
        HttpSession session = request.getSession();
        // Get request parameters
        String productName = request.getParameter("productName").trim();
        String category = request.getParameter("category").trim();
        Validator validator = new Validator();
        if (productName.length() > 0 && !validator.validateProductName(productName)) {
            request.setAttribute("productError", "Please enter a valid product name");
            // Redirect to page
            DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        } else if (category.length() > 0 && !validator.validateCategory(category)) {
            request.setAttribute("productError", "Please enter a valid category");
            // Redirect to page
            DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        }

        // 2 - Retrieve the manager instance from session      
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        try {
            if (productName.length() == 0 && category.length() != 0) {
                ArrayList<Product> filterCategory = productManager.findProductByCategory(category);
                request.setAttribute("filterProducts", filterCategory);
            } else if (productName.length() != 0 && category.length() == 0) {
                ArrayList<Product> filterProductName = productManager.findProductByName(productName);
                request.setAttribute("filterProducts", filterProductName);
            } else if (productName.length() != 0 && category.length() != 0) {
                ArrayList<Product> filterProductNameAndCategory = productManager.findProductByNameAndCategory(productName, category);
                request.setAttribute("filterProducts", filterProductNameAndCategory);
            }
           
            // Required to keep product name and category in the textfields
            request.setAttribute("requestName", productName);
            request.setAttribute("requestCategory", category);

            
            // Redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(FilterProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("productError", ex.getMessage());
            // Redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        }
    }
}
