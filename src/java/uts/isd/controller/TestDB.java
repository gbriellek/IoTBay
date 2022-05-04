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

import java.util.logging.*;
import uts.isd.model.dao.*;
import uts.isd.model.*;

 

public class TestDB {

    public static void main(String[] args) {          
//        try {
//            //test out customerManager functions
//            //set up connections
//            DBConnector connector  = new DBConnector();
//            Connection conn = connector.openConnection();
//            DBCustomerManager customerManager = new DBCustomerManager(conn);
//            Customer result;
//            // Find customer by email
//            System.out.println("findCustomerByEmail");
//            result = customerManager.findCustomerByEmail("ahmed.bender@gmail.com");
//            System.out.println(result);
//            // Add customer
//            System.out.println("addCustomer");
//            customerManager.addCustomer("hello@gmail.com", "Hello", "World", "0412345678", "helloworld", true);
//            result = customerManager.findCustomerByEmail("hello@gmail.com");
//            System.out.println(result);
//            // Update customer
//            System.out.println("updateCustomerByID");
//            customerManager.updateCustomerByID(44, "hello@gmail.com", "Goodbye", "World", "0412345678", "byeworld", true);
//            result = customerManager.findCustomerByEmail("hello@gmail.com");
//            System.out.println(result);
//            // Delete customer
//            System.out.println("deleteCustomerByID");
//            customerManager.deleteCustomerByID(44);
//            result = customerManager.findCustomerByEmail("hello@gmail.com");
//            System.out.println(result);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            //test out orderLineManagement functions
//            //set up connections
//            DBConnector connector  = new DBConnector();
//            Connection conn = connector.openConnection();
//            DBOrderLineManager orderLineManager = new DBOrderLineManager(conn);
//            OrderLine result;
//            ArrayList<OrderLine> resultList = new ArrayList<>();
//            // Find orderLine by orderID
//            System.out.println("findOrderLineByOrderID");
//            resultList = orderLineManager.findOrderLineByOrderID(1);
//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.println(resultList.get(i));
//            }
//            // Add orderLine
//            System.out.println("addOrderLine");
//            orderLineManager.addOrderLine(2, 2, 3, 15);
//            resultList = orderLineManager.findOrderLineByOrderID(2);
//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.println(resultList.get(i));
//            }
//            // Update orderLine
//            System.out.println("updateOrderLine");
//            orderLineManager.updateOrderLine(16, 13, 10, 30);
//            resultList = orderLineManager.findOrderLineByOrderID(16);
//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.println(resultList.get(i));
//            }
//            // Delete orderLine
//            System.out.println("deleteOrderLine");
//            orderLineManager.deleteOrderLine(3);
//            resultList = orderLineManager.findOrderLineByOrderID(3);
//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.println(resultList.get(i));
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            //test out productManager functions
//            //set up connections
//            DBConnector connector  = new DBConnector();
//            Connection conn = connector.openConnection();
//            DBProductManager productManager = new DBProductManager(conn);
//            Product result;
//            // Find product by name
//            System.out.println("findProductByName");
//            result = productManager.findProductByName("Moisture Sensor");
//            System.out.println(result);
//            // Find product by category
//            System.out.println("findProductByCategory");
//            result = productManager.findProductByCategory("Navigation Modules");
//            System.out.println(result);
//            // Find all product
//            System.out.println("findAllProduct");
//            System.out.println(productManager.findAllProduct());
////            // Find product by ID
////            System.out.println("findProductID");
////            result = productManager.findProductID("PIR Motion Sensor");
////            System.out.println(result);
//            // Add product
//            System.out.println("addProduct");
//            productManager.addProduct("Hello - Test", "Hi this is a test product", 6, 4, "Test category", true);
//            result = productManager.findProductByName("Hello - Test");
//            System.out.println(result);
//            // Update product
//            System.out.println("updateProduct");
//            productManager.updateProduct(20, "Joystick", "TEST UPDATED: One of these analog modules is a 2-axis joystick. Two potentiometers (see below) for X and Y axes are installed, which allow more or less voltage to pass through the movement. If one converts the analog value into a digital, one gets numbers between 0 (no voltage) and 1023 (full voltage). In the center, a digital value of approx. 512 is returned on both axes.", 6, 4, "Analogous Raspberry Pi Sensors", true);
//            result = productManager.findProductByName("Joystick");
//            System.out.println(result);
//            // Delete product
//            System.out.println("deleteProduct");
//            productManager.deleteProduct(1);
//            result = productManager.findProductByName("USB GPS Receiver");
//            System.out.println(result);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            //test out orderManager functions
            //set up connections
            DBConnector connector  = new DBConnector();
            Connection conn = connector.openConnection();
            DBOrderManager orderManager = new DBOrderManager(conn);
            Order result;
            ArrayList<Order> resultList = new ArrayList<>();
//            // Find order by userID
//            System.out.println("findOrderByUserID");
//            result = orderManager.findOrderByUserID(41);
//            System.out.println(result);
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
            orderManager.addOrder(41, 2, 6, new Date(2022, 2, 11), 24.50, "Delivered");
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
            System.out.println(result);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}