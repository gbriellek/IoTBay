/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import uts.isd.model.OrderLine;

/**
 *
 * @author Gabrielle K
 */
public class DBOrderLineManager {
    
    private Connection conn;
    
    public DBOrderLineManager(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    
    
    public ArrayList<OrderLine> findOrderLineByOrderID (int orderID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblOrder_Line WHERE Order_ID = ?");
        selectStatement.setInt(1, orderID);
        ResultSet rs = selectStatement.executeQuery();
        
        ArrayList<OrderLine> OrderLineList = new ArrayList<>();
        
        while (rs.next()){
            int productID = rs.getInt(1);
            int quantity = rs.getInt(2);
            double price = rs.getDouble(3);
            
            OrderLineList.add(new OrderLine(orderID, productID, quantity, price));
        }
        selectStatement.close();
        
        if (OrderLineList.isEmpty()){
            throw new SQLException("Order line does not exist.");
        }
        return OrderLineList;
        
    }
    
    public void addOrderLine(int OrderID, int ProductID, int quantity, double price) throws SQLException {
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO tblOrder_Line (Order_ID, Product_ID, Quantity, Price) VALUES (?,?,?,?)");
        insertStatement.setInt(1, OrderID);
        insertStatement.setInt(2, ProductID);
        insertStatement.setInt(3, quantity);
        insertStatement.setDouble(4, price);
        
        insertStatement.executeUpdate();
        insertStatement.close();
    }
    
    public void deleteOrderLine(int ProductID) throws SQLException {
        PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM tblOrder_Line WHERE Product_ID = ?");
        deleteStatement.setInt(1, ProductID);
        
        deleteStatement.executeUpdate();
        deleteStatement.close();
    }
    
    public void updateOrderLine(int OrderID, int ProductID, int quantity, double price) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblOrder_Line SET Quantity = ?, Price = ? WHERE Order_ID = ?, Product_ID = ?");
        updateStatement.setInt(1, quantity);
        updateStatement.setDouble(2, price);
        updateStatement.setInt(3, OrderID);
        updateStatement.setInt(4, ProductID);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
    
    
}
