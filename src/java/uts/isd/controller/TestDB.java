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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
//            //test out accessLogManager functions
//            //set up connections
//            DBConnector connector  = new DBConnector();
//            Connection conn = connector.openConnection();
//            DBAccessLogManager accessLogManager = new DBAccessLogManager(conn);
//            AccessLog result;
//            ArrayList<AccessLog> resultList = new ArrayList<>();
//            
//            // Find access log by userID
//            System.out.println("findAccessLogByUserID");
//            resultList = accessLogManager.findAccessLogByUserID(1);
//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.println(resultList.get(i));
//            }
//            // Find access log by userID and date
//            System.out.println("findAccessLogByUserIDDate");
//            resultList = accessLogManager.findAccessLogByUserIDDate(1,Timestamp.valueOf("2022-2-5 09:10:35"));
//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.println(resultList.get(i));
//            }
//            // Add access log
//            System.out.println("addAccessLog");
//            accessLogManager.addAccessLog(18,Timestamp.valueOf("2022-2-4 10:34:26"),"login");
//            resultList = accessLogManager.findAccessLogByUserID(18);
//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.println(resultList.get(i));
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        try {
//            //test out staffManager functions
//            //set up connections
//            DBConnector connector  = new DBConnector();
//            Connection conn = connector.openConnection();
//            DBStaffManager staffManager = new DBStaffManager(conn);
//            Staff result;
//            // Find staff by email
//            System.out.println("findStaff");
//            result = staffManager.findStaff("paul.lim@iotbay.com.au");
//            System.out.println(result);
//            // Add staff
//            System.out.println("addStaff");
//            staffManager.addStaff("hello@gmail.com", "Hello", "World", "0412345678", "helloworld", "S21", true,52);
//            result = staffManager.findStaff("hello@gmail.com");
//            System.out.println(result);
//            // Update staff
//            System.out.println("updateStaff");
//            staffManager.updateStaff(52, "hello@gmail.com", "Hello", "World", "0412345678", "helloworld", "S21", true);
//            result = staffManager.findStaff("hello@gmail.com");
//            System.out.println(result);
//            // Delete staff
//            System.out.println("deleteStaff");
//            staffManager.deleteStaff(52);
//            result = staffManager.findStaff("hello@gmail.com");
//            System.out.println(result);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        try {
//            //test out userManager functions
//            //set up connections
//            DBConnector connector  = new DBConnector();
//            Connection conn = connector.openConnection();
//            DBUserManager userManager = new DBUserManager(conn);
//            User result;
//            // Find user by email
//            System.out.println("findUser");
//            result = userManager.findUser("hope.bar91@yahoo.com");
//            System.out.println(result);
//            // Add user
//            System.out.println("addUser");
//            userManager.addUser("hello@gmail.com", "Hello", "World", "0412345678");
//            result = userManager.findUser("hello@gmail.com");
//            System.out.println(result);
//            // Update user
//            System.out.println("updateUser");
//            userManager.updateUser("hello@gmail.com", "Goodbye", "World", "0412345678");
//            result = userManager.findUser("hello@gmail.com");
//            System.out.println(result);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        try {
//            //test out customerManager functions
//            //set up connections
//            DBConnector connector  = new DBConnector();
//            Connection conn = connector.openConnection();
//            DBPaymentInformationManager paymentInformationManager = new DBPaymentInformationManager(conn);
//            PaymentInformation result;
//            ArrayList<PaymentInformation> resultList = new ArrayList<>();
//            // Find payment information by id
//            System.out.println("findPaymentInformationByID");
//            result = paymentInformationManager.findPaymentInformationByID(1);
//            System.out.println(result);
//            // Find payment information by card number
//            System.out.println("findPaymentInformationByCardNumber");
//            result = paymentInformationManager.findPaymentInformationByCardNumber("4603651184490994");
//            System.out.println(result);
//            // Find payment information by id and date
//            System.out.println("findPaymentInformationByIDDate");
//            resultList = paymentInformationManager.findPaymentInformationByIDDate(1,Date.valueOf("2022-02-09"));
//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.println(resultList.get(i));
//            }
//            // Add customer
//            System.out.println("addPaymentInformation");
//            paymentInformationManager.addPaymentInformation("1234567890123456","AMEX","2025-03");
//            result = paymentInformationManager.findPaymentInformationByID(21);
//            System.out.println(result);
//            // Update customer
//            System.out.println("updatePaymentInformation");
//            paymentInformationManager.updatePaymentInformation(21, "AMEX","2025-03");
//            result = paymentInformationManager.findPaymentInformationByID(21);
//            System.out.println(result);
//            // Delete customer
//            System.out.println("deletePaymentInformation");
//            paymentInformationManager.deletePaymentInformation(21);
//            result = paymentInformationManager.findPaymentInformationByID(21);
//            System.out.println(result);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            //test out customerManager functions
            //set up connections
            DBConnector connector  = new DBConnector();
            Connection conn = connector.openConnection();
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
//            // Update address
//            System.out.println("updateCustomerByID");
//            addressManager.updateAddress(null, "26", "First Street", "Sydney", 2000, "NSW");
//            result = addressManager.findCustomerByEmail("hello@gmail.com");
//            System.out.println(result);;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        try {
//            //test out customerManager functions
//            //set up connections
//            DBConnector connector  = new DBConnector();
//            Connection conn = connector.openConnection();
//            DBShipmentDetailManager shipmentDetailManager = new DBShipmentDetailManager(conn);
//            ShipmentDetail result;
//            // Find shipment detail
//            System.out.println("findShipmentDetail");
//            result = shipmentDetailManager.findShipmentDetail(1);
//            System.out.println(result);
//            // Add shipment detail
//            System.out.println("addShipmentDetail");
//            shipmentDetailManager.addShipmentDetail(2,10,null,Date.valueOf("2022-7-23"),"standard");
//            result = shipmentDetailManager.findShipmentDetail(26);
//            System.out.println(result);
//            // Update shipment detail
//            System.out.println("updateShipmentDetail");
//            shipmentDetailManager.updateShipmentDetail(26,20,null,Date.valueOf("2022-7-23"),"express");
//            result = shipmentDetailManager.findShipmentDetail(26);
//            System.out.println(result);
//            // Delete shipment detail
//            System.out.println("deleteShipmentDetail");
//            shipmentDetailManager.deleteShipmentDetail(3);
//            result = shipmentDetailManager.findShipmentDetail(3);
//            System.out.println(result);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

}