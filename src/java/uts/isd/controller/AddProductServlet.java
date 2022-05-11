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
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1- retrieve the current session
        HttpSession session = request.getSession();
        // get request parameters
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
        String category = request.getParameter("category");

        Validator validator = new Validator();
        
        if (productName.length() ==0 || !validator.validateProductName(productName)) {
            request.setAttribute("productError", "Please enter a valid product name");
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        } else if (description.length() == 0 ||  !validator.validateDescription(description)) {
            request.setAttribute("productError", "Please enter a valid description");
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        } else if (price.length() ==0 ||  !validator.validatePrice(price)) {
            request.setAttribute("productError", "Please enter a valid price");
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        } else if (stock.length() ==0 ||  !validator.validateStock(stock)) {
            request.setAttribute("productError", "Please enter a valid stock");
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        } else if (category.length() ==0 ||  !validator.validateCategory(category)) {
            request.setAttribute("productError", "Please enter a valid category");
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        }

        //2- retrieve the manager instance from session      
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        try {
            double convertedPrice = Double.parseDouble(price);
            int convertedStock = Integer.parseInt(stock);
            productManager.addProduct(productName, description, convertedPrice, convertedStock, category, true);
            //getting all products from database
            ArrayList<Product> product = productManager.findAllProduct();
            request.setAttribute("products", product);
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("productError", ex.getMessage());
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        }
    }
}
