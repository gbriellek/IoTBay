/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.isd.model.Product;

/**
 *
 * @author Gabrielle K
 */
public class DBProductManager {
    
    private Connection conn;
    
    public DBProductManager(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    public Product findProductByName(String name) throws SQLException {       
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters    
       PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblProduct WHERE Product_name = ? AND Is_Active = True");
       selectStatement.setString(1, name);
       ResultSet rs = selectStatement.executeQuery();
       
       while (rs.next()){
           int productid = rs.getInt(1);
           String productname = rs.getString(2);
           String description = rs.getString(3);
           double price = rs.getDouble(4);
           int stock = rs.getInt(5);
           String category = rs.getString(6);
           boolean is_active = rs.getBoolean(7);
           
           return new Product(productid, productname, description, price, stock, category, is_active);
       }
       selectStatement.close();
       throw new SQLException("No such product exists."); 
    }
    
    public Product findProductByCategory(String category) throws SQLException {       
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters    
       PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblProduct WHERE Category = ? AND Is_Active = True");
       selectStatement.setString(1, category);
       ResultSet rs = selectStatement.executeQuery();
       
       while (rs.next()){
           int productid = rs.getInt(1);
           String productname = rs.getString(2);
           String description = rs.getString(3);
           double price = rs.getDouble(4);
           int stock = rs.getInt(5);
           String productcategory = rs.getString(6);
           boolean is_active = rs.getBoolean(7);
           
           return new Product(productid, productname, description, price, stock, productcategory, is_active);
       }
       selectStatement.close();
       throw new SQLException("No such product exists."); 
    }
    
    public ArrayList <Product> findAllProduct() throws SQLException {       
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters    
       PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblProduct WHERE Is_Active = True");
       ResultSet rs = selectStatement.executeQuery();
       
       ArrayList <Product> productList = new ArrayList<>();
       
       while (rs.next()){
           int productid = rs.getInt(1);
           String productname = rs.getString(2);
           String description = rs.getString(3);
           double price = rs.getDouble(4);
           int stock = rs.getInt(5);
           String category = rs.getString(6);
           boolean is_active = rs.getBoolean(7);
           
           productList.add(new Product(productid, productname, description, price, stock, category, is_active));
       }
       selectStatement.close();
       if (productList.isEmpty()){
           throw new SQLException("No such product exists."); 
       }
       return productList;
       
    }
    
    public int findProductID(String name) throws SQLException {       
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters    
       PreparedStatement selectStatement = conn.prepareStatement("SELECT Product_ID FROM tblProduct WHERE Product_name = ? AND Is_Active = True");
       selectStatement.setString(1, name);
       ResultSet rs = selectStatement.executeQuery();
       
       while (rs.next()){
           int productid = rs.getInt(1);
           
           return productid;
       }
       selectStatement.close();
       throw new SQLException("No such product exists."); 
    }

    //Add a user-data into the database   
    public void addProduct(String name, String description, double price, int stock, String category, boolean is_active) throws SQLException {                    
        //code for add-operation       
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO tblProduct(Product_name, Description, Price, Stock, Category, Is_Active) VALUES (?,?,?,?,?,?)");
        insertStatement.setString(1, name);
        insertStatement.setString(2, description);
        insertStatement.setDouble(3, price);
        insertStatement.setInt(4, stock);
        insertStatement.setString(5, category);
        insertStatement.setBoolean(6, is_active);
         
        insertStatement.executeUpdate();
        insertStatement.close();
    }

    //update a user details in the database   
    public void updateProduct(int productID, String name, String description, double price, int stock, String category, boolean is_active) throws SQLException {       
       //code for update-operation   
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblProduct SET Product_name = ?, Description = ?, Price = ?, Stock = ?, Category = ?, Is_Active = ? WHERE Product_ID = ?");
        updateStatement.setString(1, name);
        updateStatement.setString(2, description);
        updateStatement.setDouble(3, price);
        updateStatement.setInt(4, stock);
        updateStatement.setString(5, category);
        updateStatement.setBoolean(6, is_active);
        updateStatement.setInt(7, productID);
         
        updateStatement.executeUpdate();
        updateStatement.close();
    }       

    //delete a user from the database   
    public void deleteProduct(int productID) throws SQLException{       
       //code for delete-operation   
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblProduct SET Is_Active = False WHERE Product_ID = ?");
        updateStatement. setInt(1, productID);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
}
