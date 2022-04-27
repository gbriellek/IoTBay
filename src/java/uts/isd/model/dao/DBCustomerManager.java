/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import uts.isd.model.User;

/**
 *
 * @author Gabrielle K
 */
public class DBCustomerManager {

    private Connection conn;
   
    public DBCustomerManager(Connection conn) throws SQLException {     
       this.conn = conn;
    }

    //Find user by email and password in the database   
    public User findUser(String email, String password) throws SQLException {       
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters    
//       ResultSet rs = st.executeQuery();
       return null;   
    }

    //Add a user-data into the database   
    public void addUser(String email, String fname, String lname, String phoneno) throws SQLException {                    
        //code for add-operation       
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO tblUser(Email_Address, First_Name, Last_Name, Phone_Number) VALUES (?,?,?,?)");
        insertStatement.setString(1, email);
        insertStatement.setString(2, fname);
        insertStatement.setString(3, lname);
        insertStatement.setString(4, phoneno);
         
        insertStatement.executeUpdate();
        insertStatement.close();
    }

    //update a user details in the database   
    public void updateUser( String email, String fname, String lname, String phoneno) throws SQLException {       
       //code for update-operation   
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblUser SET Email_Address = ?, First_Name = ?, Last_Name = ?, Phone_Number = ?");
        updateStatement.setString(1, email);
        updateStatement.setString(2, fname);
        updateStatement.setString(3, lname);
        updateStatement.setString(4, phoneno);
         
        updateStatement.executeUpdate();
        updateStatement.close();
    }       

    //delete a user from the database   
    public void deleteUser(String email) throws SQLException{       
       //code for delete-operation   
       PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM tblUser WHERE Email_Address = ?");
        deleteStatement.setString(1, email);
         
        deleteStatement.executeUpdate();
        deleteStatement.close();
    }


 

}

