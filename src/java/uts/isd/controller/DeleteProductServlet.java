package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Order;
import uts.isd.model.OrderLine;
import uts.isd.model.Product;
import uts.isd.model.dao.*;

/**
 *
 * @author Jemma S
 */
public class DeleteProductServlet extends HttpServlet {
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {               
        //1- retrieve the current session
        HttpSession session = request.getSession();   

        // get product id from request
        String productID = request.getParameter("productID");
        
        //convert productid to int
        int convertedProductID = Integer.parseInt(productID);
   
        //5- retrieve the manager instance from session      
        DBProductManager productManager = (DBProductManager) session.getAttribute("productManager");
        try {    
            Product p = productManager.findProductByID(convertedProductID);
            // delete product from db
            productManager.deleteProduct(convertedProductID);
            
            // re-get all products from the db
            ArrayList<Product> product = productManager.findAllProduct();
            session.setAttribute("products", product);
            
            request.setAttribute("productAdd", p.getName() + " Successfully Removed");
            
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
           
        } catch (SQLException ex) {    
            Logger.getLogger(DeleteProductServlet.class.getName()).log(Level.SEVERE, null, ex);                                 session.setAttribute("noProductsError", "No Products Available");
            //redirect to page
            request.getRequestDispatcher("products.jsp").include(request, response);
            return;
        }
    }
}
