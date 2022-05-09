/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.controller;

/**
 *
 * @author Gabrielle K
 */
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.logging.*;
import uts.isd.model.dao.*;
import uts.isd.model.*;

 

public class TestDB {

    public static void main(String[] args) {          
        DBConnector connector;
        Connection conn;
        try {
            //test out customerManager functions
            //set up connections
            connector  = new DBConnector();
            conn = connector.openConnection();
            DBCustomerManager customerManager = new DBCustomerManager(conn);
            Customer result;
            // Find customer by email
            System.out.println("findCustomerByEmail");
            result = customerManager.findCustomerByEmail("ahmed.bender@gmail.com");
            System.out.println(result);
            // Add customer
            System.out.println("addCustomer");
            customerManager.addCustomer("hello@gmail.com", "Hello", "World", "0412345678", "helloworld", true);
            result = customerManager.findCustomerByEmail("hello@gmail.com");
            System.out.println(result);
            // Update customer
            System.out.println("updateCustomerByID");
            customerManager.updateCustomerByID(44, "hello@gmail.com", "Goodbye", "World", "0412345678", "byeworld", true);
            result = customerManager.findCustomerByEmail("hello@gmail.com");
            System.out.println(result);
            // Delete customer
            System.out.println("deleteCustomerByID");
            customerManager.deleteCustomerByID(44);
            result = customerManager.findCustomerByEmail("hello@gmail.com");
            System.out.println(result);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        try {
            //test out orderLineManagement functions
            //set up connections
            connector = new DBConnector();
            conn = connector.openConnection();
            DBOrderLineManager orderLineManager = new DBOrderLineManager(conn);
            OrderLine result;
            ArrayList<OrderLine> resultList = new ArrayList<>();
            // Find orderLine by orderID
            System.out.println("findOrderLineByOrderID");
            resultList = orderLineManager.findOrderLineByOrderID(1);
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i));
            }
            // Add orderLine
            System.out.println("addOrderLine");
            orderLineManager.addOrderLine(2, 2, 3, 15);
            resultList = orderLineManager.findOrderLineByOrderID(2);
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i));
            }
            // Update orderLine
            System.out.println("updateOrderLine");
            orderLineManager.updateOrderLine(16, 13, 10, 30);
            resultList = orderLineManager.findOrderLineByOrderID(16);
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i));
            }
            // Delete orderLine
            System.out.println("deleteOrderLine");
            orderLineManager.deleteOrderLine(3, 3);
            resultList = orderLineManager.findOrderLineByOrderID(3);
        }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //test out accessLogManager functions
            //set up connections
            connector  = new DBConnector();
            conn = connector.openConnection();
            DBAccessLogManager accessLogManager = new DBAccessLogManager(conn);
            AccessLog result;
            ArrayList<AccessLog> resultList = new ArrayList<>();
            
            // Find access log by userID
            System.out.println("findAccessLogByUserID");
            resultList = accessLogManager.findAccessLogByUserID(1);
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i));
            }
            // Find access log by userID and date
            System.out.println("findAccessLogByUserIDDate");
            resultList = accessLogManager.findAccessLogByUserIDDate(1,Timestamp.valueOf("2022-2-5 09:10:35"));
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i));
            }
            // Add access log
            System.out.println("addAccessLog");
            accessLogManager.addAccessLog(18,Timestamp.valueOf("2022-2-4 10:34:26"),"login");
            resultList = accessLogManager.findAccessLogByUserID(18);
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //test out productManager functions
            //set up connections
            connector  = new DBConnector();
            conn = connector.openConnection();
            DBProductManager productManager = new DBProductManager(conn);
            ArrayList<Product> resultList = new ArrayList<>();
            // Find product by name
            System.out.println("findProductByName");
            resultList = productManager.findProductByName("Moisture Sensor");
            for (Product p: resultList) {
                System.out.println(p);
            }
            // Find product by category
            
            System.out.println("findProductByCategory");
            resultList = productManager.findProductByCategory("Navigation Modules");
            for (Product p: resultList) {
                System.out.println(p);
            }
            // Find all product
            System.out.println("findAllProduct");
            System.out.println(productManager.findAllProduct());
            // Find product by ID
            System.out.println("findProductID");
            int resultID;
            resultID = productManager.findProductID("PIR Motion Sensor");
            System.out.println(resultID);
            // Add product
            System.out.println("addProduct");
            productManager.addProduct("Hello - Test", "Hi this is a test product", 6, 4, "Test category", true);
            resultList = productManager.findProductByName("Hello - Test");
            for (Product p: resultList) {
                System.out.println(p);
            }
            // Update product
            System.out.println("updateProduct");
            productManager.updateProduct(20, "Joystick", "TEST UPDATED: One of these analog modules is a 2-axis joystick. Two potentiometers (see below) for X and Y axes are installed, which allow more or less voltage to pass through the movement. If one converts the analog value into a digital, one gets numbers between 0 (no voltage) and 1023 (full voltage). In the center, a digital value of approx. 512 is returned on both axes.", 6, 4, "Analogous Raspberry Pi Sensors", true);
            resultList = productManager.findProductByName("Joystick");
            for (Product p: resultList) {
                System.out.println(p);
            }
            // Delete product
            System.out.println("deleteProduct");
            productManager.deleteProduct(1);
//            result = productManager.findProductByName("USB GPS Receiver");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //test out staffManager functions
            //set up connections
            connector  = new DBConnector();
            conn = connector.openConnection();
            DBStaffManager staffManager = new DBStaffManager(conn);
            Staff result;
            // Find staff by email
            System.out.println("findStaff");
            result = staffManager.findStaff("paul.lim@iotbay.com.au");
            System.out.println(result);
            // Add staff
            System.out.println("addStaff");
            staffManager.addStaff("hello@gmail.com", "Hello", "World", "0412345678", "helloworld", "S21", true);
            result = staffManager.findStaff("hello@gmail.com");
            System.out.println(result);
            // Update staff
            System.out.println("updateStaff");
            staffManager.updateStaff(52, "hello@gmail.com", "Hello", "World", "0412345678", "helloworld", "S21", true);
            result = staffManager.findStaff("hello@gmail.com");
            System.out.println(result);
            // Delete staff
            System.out.println("deleteStaff");
            staffManager.deleteStaff(52);
            result = staffManager.findStaff("hello@gmail.com");
            System.out.println(result);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //test out userManager functions
            //set up connections
            connector  = new DBConnector();
            conn = connector.openConnection();
            DBUserManager userManager = new DBUserManager(conn);
            User result;
            // Find user by email
            System.out.println("findUser");
            result = userManager.findUser("hope.bar91@yahoo.com");
            System.out.println(result);
            // Add user
            System.out.println("addUser");
            userManager.addUser("hello@gmail.com", "Hello", "World", "0412345678");
            result = userManager.findUser("hello@gmail.com");
            System.out.println(result);
            // Update user
            System.out.println("updateUser");
            userManager.updateUser("hello@gmail.com", "Goodbye", "World", "0412345678");
            result = userManager.findUser("hello@gmail.com");
            System.out.println(result);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //test out customerManager functions
            //set up connections
            connector  = new DBConnector();
            conn = connector.openConnection();
            DBPaymentInformationManager paymentInformationManager = new DBPaymentInformationManager(conn);
            PaymentInformation result;
            ArrayList<PaymentInformation> resultList = new ArrayList<>();
            // Find payment information by id
            System.out.println("findPaymentInformationByID");
            result = paymentInformationManager.findPaymentInformationByID(1);
            System.out.println(result);
            // Find payment information by card number
            System.out.println("findPaymentInformationByCardNumber");
            result = paymentInformationManager.findPaymentInformationByCardNumber("4603651184490994");
            System.out.println(result);
            // Find payment information by id and date
            System.out.println("findPaymentInformationByIDDate");
            resultList = paymentInformationManager.findPaymentInformationByIDDate(1,Date.valueOf("2022-02-09"));
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i));
            }
            // Add customer
            System.out.println("addPaymentInformation");
            paymentInformationManager.addPaymentInformation("1234567890123456","AMEX","2025-03");
            result = paymentInformationManager.findPaymentInformationByID(21);
            System.out.println(result);
            // Update customer
            System.out.println("updatePaymentInformation");
            paymentInformationManager.updatePaymentInformation(21, "AMEX","2025-03");
            result = paymentInformationManager.findPaymentInformationByID(21);
            System.out.println(result);
            // Delete customer
            System.out.println("deletePaymentInformation");
            paymentInformationManager.deletePaymentInformation(21);
            result = paymentInformationManager.findPaymentInformationByID(21);
            System.out.println(result);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        try {
            //test out orderManager functions
            //set up connections
            connector  = new DBConnector();
            conn = connector.openConnection();
            DBOrderManager orderManager = new DBOrderManager(conn);
            Order result;
            ArrayList<Order> resultList = new ArrayList<>();
            // Find order by userID
            System.out.println("findOrderByUserID");
            result = orderManager.findOrderByUserID(41);
            System.out.println(result);
            // Find past orders by userID
            System.out.println("findPastOrdersByUserID");
            resultList = orderManager.findPastOrdersByUserID(41);
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i));
            }
            // Find past orders by userID and date
            System.out.println("findPastOrdersByUserIDDate");
            resultList = orderManager.findPastOrdersByUserIDDate(10, new Date(2022-4-27));
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i));
            }
            // Add order
            System.out.println("addOrder");
            orderManager.addOrder(41, new Date(2022, 2, 11), 24.50);
            result = orderManager.findOrderByUserID(41);
            System.out.println(result);
            // Update order
            System.out.println("updateOrder");
            orderManager.updateOrder(9, 22, 5, 9, new Date(2022, 5, 13), 202.02, "Delivered");
            result = orderManager.findOrderByUserID(22);
            System.out.println(result);
            // Delete order
            System.out.println("deleteOrder");
            orderManager.deleteOrder(1);
            result = orderManager.findOrderByUserID(21);
         } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            connector  = new DBConnector();
            conn = connector.openConnection();
            DBAddressManager addressManager = new DBAddressManager(conn);
            Address result;
            int id;
            // Find addressID
            System.out.println("findAddressID");
            id = addressManager.findAddressID(null, "48", "Patton Street", "Beenak", 3139, "VIC");
            System.out.println(id);
            // Find address by ID
            System.out.println("findAddressByID");
            result = addressManager.findAddressByID(1);
            System.out.println(result);
            // Add address
            System.out.println("addAddress");
            addressManager.addAddress(null, "26", "First Street", "Sydney", 2000, "NSW");
            id = addressManager.findAddressID(null, "26", "First Street", "Sydney", 2000, "NSW");
            System.out.println(id);
            // Update address
            System.out.println("updateCustomerByID");
            addressManager.updateAddress(1, null, "26", "First Street", "Sydney", 2000, "NSW");
            result = addressManager.findAddressByID(1);
            System.out.println(result);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //test out customerManager functions
            //set up connections
            connector  = new DBConnector();
            conn = connector.openConnection();
            DBShipmentDetailManager shipmentDetailManager = new DBShipmentDetailManager(conn);
            ShipmentDetail result;
            // Find shipment detail
            System.out.println("findShipmentDetail");
            result = shipmentDetailManager.findShipmentDetail(1);
            System.out.println(result);
            // Add shipment detail
            System.out.println("addShipmentDetail");
            shipmentDetailManager.addShipmentDetail(2,10,null,Date.valueOf("2022-7-23"),"standard");
            result = shipmentDetailManager.findShipmentDetail(26);
            System.out.println(result);
            // Update shipment detail
            System.out.println("updateShipmentDetail");
            shipmentDetailManager.updateShipmentDetail(2, 26,20,null,Date.valueOf("2022-7-23"),"express");
            result = shipmentDetailManager.findShipmentDetail(26);
            System.out.println(result);
            // Delete shipment detail
            System.out.println("deleteShipmentDetail");
            shipmentDetailManager.deleteShipmentDetail(3);
            result = shipmentDetailManager.findShipmentDetail(3);
            System.out.println(result);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}