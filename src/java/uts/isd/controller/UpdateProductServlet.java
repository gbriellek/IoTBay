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
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1- retrieve the current session
        HttpSession session = request.getSession();
        // get request parameters
        String productID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
        String category = request.getParameter("category");

        Validator validator = new Validator();
        if (!validator.validateProductName(productName)) {
            request.setAttribute("productError", "Please enter a valid product name");
            DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        } else if (!validator.validateDescription(description)) {
            request.setAttribute("productError", "Please enter a valid description");
            DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        } else if (!validator.validatePrice(price)) {
            request.setAttribute("productError", "Please enter a valid price");
            DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        } else if (!validator.validateStock(stock)) {
            request.setAttribute("productError", "Please enter a valid stock");
            DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        } else if (!validator.validateCategory(category)) {
            request.setAttribute("productError", "Please enter a valid category");
            DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        }

        //2- retrieve the manager instance from session      
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        try {
            double convertedPrice = Double.parseDouble(price);
            int convertedStock = Integer.parseInt(stock);
            int convertedProductID = Integer.parseInt(productID);
            productManager.updateProduct(convertedProductID, productName, description, convertedPrice, convertedStock, category, true);
            //getting all products from database
            ArrayList<Product> product = productManager.findAllProduct();
            request.setAttribute("products", product);
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("productError", ex.getMessage());
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        }
    }
}
