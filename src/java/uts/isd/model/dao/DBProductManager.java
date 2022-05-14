package uts.isd.model.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.isd.model.Product;

/**
 *
 * @author Gabrielle K & Jemma S
 */
public class DBProductManager {
    
    private Connection conn;
    
    public DBProductManager(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    public ArrayList<Product> findProductByName(String name) throws SQLException {       
       // Setup the select sql query string       
       // Execute this query using the statement field       
       // Add the results to a ResultSet       
       // Search the ResultSet for a user using the parameters      
       PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblProduct WHERE lower(Product_name) LIKE ? AND Is_Active = True");
       selectStatement.setString(1, "%"+name.toLowerCase() +"%");
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
       // If no such product exists after the search is conducted, tell the user
       if (productList.isEmpty()){
           throw new SQLException("No such product exists."); 
       }
       return productList;
    }
    
    public ArrayList<Product> findProductByNameAndCategory(String name, String category) throws SQLException {       
       // Setup the select sql query string       
       // Execute this query using the statement field       
       // Add the results to a ResultSet       
       // Search the ResultSet for a user using the parameters    
       PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblProduct WHERE lower(Product_name) LIKE ? AND lower(Category) LIKE ? AND Is_Active = True");
       selectStatement.setString(1, "%"+name.toLowerCase() +"%");
       selectStatement.setString(2, "%"+category.toLowerCase() +"%");
       ResultSet rs = selectStatement.executeQuery();
       
       ArrayList <Product> productList = new ArrayList<>();
       
       while (rs.next()){
           int productid = rs.getInt(1);
           String productname = rs.getString(2);
           String description = rs.getString(3);
           double price = rs.getDouble(4);
           int stock = rs.getInt(5);
           boolean is_active = rs.getBoolean(7);
           
           productList.add(new Product(productid, productname, description, price, stock, category, is_active));
       }
       selectStatement.close();
       // If no such product exists after the search is conducted, tell the user
       if (productList.isEmpty()){
           throw new SQLException("No such product exists."); 
       }
       return productList; 
    }
    
    public ArrayList<Product> findProductByCategory(String category) throws SQLException {       
       // Setup the select sql query string       
       // Execute this query using the statement field       
       // Add the results to a ResultSet       
       // Search the ResultSet for a user using the parameters    
       PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblProduct WHERE lower(Category) LIKE ? AND Is_Active = True");
       selectStatement.setString(1, "%"+category.toLowerCase() +"%");
       ResultSet rs = selectStatement.executeQuery();
       
       ArrayList <Product> productList = new ArrayList<>();
       
       while (rs.next()){
           int productid = rs.getInt(1);
           String productname = rs.getString(2);
           String description = rs.getString(3);
           double price = rs.getDouble(4);
           int stock = rs.getInt(5);
           String productcategory = rs.getString(6);
           boolean is_active = rs.getBoolean(7);
           
           productList.add(new Product(productid, productname, description, price, stock, category, is_active));
       }
       selectStatement.close();
       // If no such product exists after the search is conducted, tell the user
       if (productList.isEmpty()){
           throw new SQLException("No such product exists."); 
       }
       return productList;
    }
    
    public ArrayList <Product> findAllProduct() throws SQLException {       
       // Setup the select sql query string       
       // Execute this query using the statement field       
       // Add the results to a ResultSet       
       // Search the ResultSet for a user using the parameters     
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
       // If no such product exists after the search is conducted, tell the user
       if (productList.isEmpty()){
           throw new SQLException("No such product exists."); 
       }
       return productList;
       
    }
    
    public int findProductID(String name) throws SQLException {       
       // Setup the select sql query string       
       // Execute this query using the statement field       
       // Add the results to a ResultSet       
       // Search the ResultSet for a user using the parameters      
       PreparedStatement selectStatement = conn.prepareStatement("SELECT Product_ID FROM tblProduct WHERE Product_name = ? AND Is_Active = True");
       selectStatement.setString(1, name);
       ResultSet rs = selectStatement.executeQuery();
       
       while (rs.next()){
           int productid = rs.getInt(1);
           
           return productid;
       }
       selectStatement.close();
       // If no such product exists after the search is conducted, tell the user
       throw new SQLException("No such product exists."); 
    }
    
    public String findProductNameByID(int productID) throws SQLException {       
       // Setup the select sql query string       
       // Execute this query using the statement field       
       // Add the results to a ResultSet       
       // Search the ResultSet for a user using the parameters      
       PreparedStatement selectStatement = conn.prepareStatement("SELECT Product_Name FROM tblProduct WHERE Product_ID = ?");
       selectStatement.setInt(1, productID);
       ResultSet rs = selectStatement.executeQuery();
       
       while (rs.next()){
           String productName = rs.getString(1);
           
           return productName;
       }
       selectStatement.close();
       // If no such product exists after the search is conducted, tell the user
       throw new SQLException("No such product exists."); 
    }
    
    public Product findProductByID(int productID) throws SQLException {       
       // Setup the select sql query string       
       // Execute this query using the statement field       
       // Add the results to a ResultSet       
       // Search the ResultSet for a user using the parameters     
       PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblProduct WHERE Product_ID = ?");
       selectStatement.setInt(1, productID);
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
       // If no such product exists after the search is conducted, tell the user
       throw new SQLException("No such product exists."); 
    }

    // Add a product-data into the database   
    public void addProduct(String name, String description, double price, int stock, String category, boolean is_active) throws SQLException {                    
        // Code for add-operation       
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

    // Update a product details in the database   
    public void updateProduct(int productID, String name, String description, double price, int stock, String category, boolean is_active) throws SQLException {       
       // Code for update-operation   
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
    
    // Only update the stock
    public void updateProductStock(int productID, int stock) throws SQLException {       
       // Code for update-operation   
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblProduct SET Stock = ? WHERE Product_ID = ?");
        updateStatement.setInt(1, stock);
        updateStatement.setInt(2, productID);
         
        updateStatement.executeUpdate();
        updateStatement.close();
    }

    // Delete a product from the database   
    public void deleteProduct(int productID) throws SQLException{       
       // Code for delete-operation   
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblProduct SET Is_Active = False WHERE Product_ID = ?");
        updateStatement. setInt(1, productID);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
}
