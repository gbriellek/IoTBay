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

import java.util.*;

import java.util.logging.*;
import uts.isd.model.dao.*;
import uts.isd.model.*;

 

public class TestDB {

    public static void main(String[] args) {          
        try {
            //test out customerManager functions
            //set up connections
            DBConnector connector  = new DBConnector();
            Connection conn = connector.openConnection();
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

    }

}