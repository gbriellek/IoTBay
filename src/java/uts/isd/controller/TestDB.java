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
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBUserManager;

 

public class TestDB {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        try {

            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBUserManager db = new DBUserManager(conn);
//            db.addUser("bob@gmail.com", "bob", "smith", "0412345781");
//            db.updateUser("bib@gmail.com", "bob", "smith", "0412345781");
//            db.deleteUser("bob@gmail.com");
            User x = db.findUser("bob@gmail.com");
            System.out.println(x.getPhoneNumber());


//            System.out.print("User email: ");
//
//            String email = in.nextLine();
//
//            System.out.print("User name: ");
//
//            String name = in.nextLine();
//
//            System.out.print("User password: ");
//
//            String password = in.nextLine();
//
//            System.out.print("User gender: ");
//
//            String gender = in.nextLine();
//
//            System.out.print("User favorite color: ");
//
//            String favcol = in.nextLine();
//
//            db.addUser( email, name, password, gender, favcol);
//
//            System.out.println("User is added to the database.");
//
//            connector.closeConnection();

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}