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
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {

private Statement st;
   
public DBManager(Connection conn) throws SQLException {       
   st = conn.createStatement();   
}

//Find user by email and password in the database   
public User findUser(String email, String password) throws SQLException {       
   //setup the select sql query string       
   //execute this query using the statement field       
   //add the results to a ResultSet       
   //search the ResultSet for a user using the parameters               
   return null;   
}

//Add a user-data into the database   
public void addUser(String email, String name, String password, String gender, String favcol) throws SQLException {                    //code for add-operation       
    st.executeUpdate("INSERT INTO IoTBayDB" + "VALUES");   

}

//update a user details in the database   
public void updateUser( String email, String name, String password, String gender, String favcol) throws SQLException {       
   //code for update-operation   
   
}       

//delete a user from the database   
public void deleteUser(String email) throws SQLException{       
   //code for delete-operation   

}


 

}
