/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import uts.isd.model.Staff;

/**
 *
 * @author Gabrielle K
 */
public class DBStaffManager {
    
    private Connection conn;
    
    public DBStaffManager(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    public Staff findStaff(String email) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblUser INNER JOIN tblStaff ON tblUser.User_ID = tblStaff.Staff_ID WHERE Email_Address = ? AND Is_Activated = True");
        selectStatement.setString(1, email);
        ResultSet rs = selectStatement.executeQuery();
        
        while (rs.next()){
            int userid = rs.getInt(1);
            String user_email = rs.getString(2);
            String fname = rs.getString(3);
            String lname = rs.getString(4);
            String phone_number = rs.getString(5);
            String password = rs.getString(7);
            String staff_number = rs.getString(8);
            boolean is_Activated = rs.getBoolean(9);
           
           return new Staff(userid, user_email, fname, lname, phone_number, password, staff_number, is_Activated);
       }
       selectStatement.close();
       throw new SQLException("No such staff exists.");
    }
    
    public void addStaff (String email, String fname, String lname, String phoneno, String password, String staff_number, boolean is_Activated) throws SQLException {
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO tblUser(Email_Address, First_Name, Last_Name, Phone_Number) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, email);
        insertStatement.setString(2, fname);
        insertStatement.setString(3, lname);
        insertStatement.setString(4, phoneno);
         
        insertStatement.executeUpdate();
        
        int id;
        
        try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);   

            }
            else {
                insertStatement.close();
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        
        insertStatement.close();
        
        PreparedStatement insertStatement1 = conn.prepareStatement("INSERT INTO tblCustomer(Customer_ID, Password, Is_Activated) VALUES (?,?,?)");
        insertStatement1.setInt(1,id);
        insertStatement1.setString(2, password);
        insertStatement1.setBoolean(3, is_Activated);
        
        insertStatement1.executeUpdate();
        insertStatement1.close();
    }
    
    public void updateStaff(int id, String email, String fname, String lname, String phoneno, String password, String staff_number, boolean is_Activated) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblUser SET First_Name = ?, Last_Name = ?, Phone_Number = ? WHERE User_ID = ?");
        updateStatement.setString(1, fname);
        updateStatement.setString(2, lname);
        updateStatement.setString(3, phoneno);
        updateStatement.setInt(4, id);
         
        updateStatement.executeUpdate();
        updateStatement.close();
        
        PreparedStatement updateStatement1 = conn.prepareStatement("UPDATE tblStaff SET Password = ?, Staff_Number = ?, Is_Activated = ? WHERE User_ID = ?");
        updateStatement.setString(1, password);
        updateStatement.setString(2, staff_number);
        updateStatement.setBoolean(3, is_Activated);
        updateStatement.setInt(4, id);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
    
    public void deleteStaff(int id) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblStaff SET Is_Activated = False WHERE User_ID = ?");
        updateStatement.setInt(1, id);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
}
