package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import uts.isd.model.User;

/**
 *
 * @author Gabrielle K
 */
public class DBUserManager {

    private Connection conn;
   
    public DBUserManager(Connection conn) throws SQLException {     
       this.conn = conn;
    }

    //Find user by email in the database   
    public User findUser(String email) throws SQLException {       
        //set up and execute insert statement
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblUser WHERE Email_Address = ?");
        selectStatement.setString(1, email);
        ResultSet rs = selectStatement.executeQuery();
       
        while (rs.next()){
            // read in returned row from database and return user object
            int userid = rs.getInt(1);
            String user_email = rs.getString(2);
            String fname = rs.getString(3);
            String lname = rs.getString(4);
            String phone_number = rs.getString(5);
           
           return new User(userid, user_email, fname, lname, phone_number);
       }
       selectStatement.close();
       throw new SQLException("No such user exists."); 
    }

    //Add a user-data into the database   
    public void addUser(String email, String fname, String lname, String phoneno) throws SQLException {                    
        //set up and execute sql insert statement
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO tblUser(Email_Address, First_Name, Last_Name, Phone_Number) VALUES (?,?,?,?)");
        insertStatement.setString(1, email);
        insertStatement.setString(2, fname);
        insertStatement.setString(3, lname);
        insertStatement.setString(4, phoneno);
         
        insertStatement.executeUpdate();
        insertStatement.close();
    }

    //update a user details in the database based on email  
    public void updateUser(String email, String fname, String lname, String phoneno) throws SQLException {       
       // set up and execute sql update statement
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblUser SET First_Name = ?, Last_Name = ?, Phone_Number = ? WHERE Email_Address = ?");
        updateStatement.setString(1, fname);
        updateStatement.setString(2, lname);
        updateStatement.setString(3, phoneno);
        updateStatement.setString(4, email);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }    
}

