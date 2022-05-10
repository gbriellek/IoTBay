package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.dao.*;

/**
 * Author:  Sarah F
 * 
 */

public class ConnServlet extends HttpServlet {

    private DBConnector db;
    private DBAccessLogManager accessLogManager;
    private DBAddressManager addressManager;
    private DBCustomerManager customerManager;
    private DBOrderManager orderManager;
    private DBOrderLineManager orderLineManager;
    private DBPaymentInformationManager paymentInformationManager;
    private DBProductManager productManager;
    private DBShipmentDetailManager shipmentDetailManager;
    private DBStaffManager staffManager;
    private DBUserManager userManager;   
    private Connection conn;

    @Override //Create and instance of DBConnector for the deployment session
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override //Add the DBConnector, DBManager, Connection instances to the session
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        conn = db.openConnection();
        try {
            // create a new DBmanager for each of the tables
            accessLogManager = new DBAccessLogManager(conn);
            addressManager = new DBAddressManager(conn);
            customerManager = new DBCustomerManager(conn);
            orderManager = new DBOrderManager(conn);
            orderLineManager = new DBOrderLineManager(conn);
            paymentInformationManager = new DBPaymentInformationManager(conn);
            productManager = new DBProductManager(conn);
            shipmentDetailManager = new DBShipmentDetailManager(conn);
            staffManager = new DBStaffManager(conn);
            userManager = new DBUserManager(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //export the DB manager to the view-session (JSPs)
        session.setAttribute("accessLogManager", accessLogManager);
        session.setAttribute("addressManager", addressManager);
        session.setAttribute("customerManager", customerManager);
        session.setAttribute("orderManager", orderManager);
        session.setAttribute("orderLineManager", orderLineManager);
        session.setAttribute("paymentInformationManager", paymentInformationManager);
        session.setAttribute("productManager", productManager);
        session.setAttribute("shipmentDetailManager", shipmentDetailManager);
        session.setAttribute("staffManager", staffManager);
        session.setAttribute("userManager", userManager);
    }

    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
