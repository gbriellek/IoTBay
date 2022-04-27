/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;

/**
 *
 * @author Gabrielle K
 */
import uts.isd.model.User;
import java.sql.*;

/* 
* DBUserManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBUserManager {

    private Connection conn;
   
    public DBUserManager(Connection conn) throws SQLException {     
       this.conn = conn;
    }

    //Find user by email and password in the database   
    public User findUser(String email) throws SQLException {       
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters    
//       ResultSet rs = st.executeQuery();
       PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblUser WHERE Email_Address = ?");
       selectStatement.setString(1, email);
       ResultSet rs = selectStatement.executeQuery();
       
       while (rs.next()){
           String emailaddress = rs.getString(2);
           String fname = rs.getString(3);
           String lname = rs.getString(4);
           String phoneno = rs.getString(5);
           return new User(emailaddress, fname, lname, phoneno);
       }
       selectStatement.close();
       throw new SQLException("No such customer exists."); 
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
