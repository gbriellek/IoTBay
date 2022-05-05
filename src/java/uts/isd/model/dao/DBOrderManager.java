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
import java.sql.Date;
import uts.isd.model.Order;
import uts.isd.model.OrderLine;

/**
 *
 * @author Gabrielle K
 */
public class DBOrderManager {
    
    private Connection conn;
    
    public DBOrderManager(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    public Order findOrderByUserID (int userID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblOrder WHERE User_ID = ? AND Order_Status = 'Not submitted'");
        selectStatement.setInt(1, userID);
        ResultSet rs = selectStatement.executeQuery();
        
        while (rs.next()){
            int order_ID = rs.getInt(1);
            int user_ID = rs.getInt(2);
            int paymentinfo_ID = rs.getInt(3);
            int shipment_ID = rs.getInt(4);
            Date order_date = rs.getDate(5);
            double cost = rs.getDouble(6);
            String status = rs.getString(7);
            
            
            return new Order(order_ID, user_ID, paymentinfo_ID, shipment_ID, order_date, cost, status);
        }
        selectStatement.close();
        throw new SQLException("Order does not exist.");
    }
    
    public ArrayList<Order> findPastOrdersByUserID (int userID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblOrder WHERE User_ID = ? AND Order_Status NOT IN ('Not submitted','Cancelled')");
        selectStatement.setInt(1, userID);
        ResultSet rs = selectStatement.executeQuery();
        
        ArrayList <Order> OrderList = new ArrayList<>();
        
        while (rs.next()){
            int order_ID = rs.getInt(1);
            int user_ID = rs.getInt(2);
            int paymentinfo_ID = rs.getInt(3);
            int shipment_ID = rs.getInt(4);
            Date order_date = rs.getDate(5);
            double cost = rs.getDouble(6);
            String status = rs.getString(7);
            
            
            OrderList.add(new Order(order_ID, user_ID, paymentinfo_ID, shipment_ID, order_date, cost, status));
        }
        
        selectStatement.close();
        
        if (OrderList.isEmpty()){
            throw new SQLException("Order does not exist.");
        }
        return OrderList;
    }
    
    public ArrayList<Order> findPastOrdersByUserIDDate (int userID, Date date) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblOrder WHERE User_ID = ? AND Order_Status NOT IN ('Not submitted','Cancelled') AND Order_Date = ?");
        selectStatement.setInt(1, userID);
        selectStatement.setDate(2, (java.sql.Date) (java.util.Date) date);
        ResultSet rs = selectStatement.executeQuery();
        
        ArrayList <Order> OrderList = new ArrayList<>();
        
        while (rs.next()){
            int order_ID = rs.getInt(1);
            int user_ID = rs.getInt(2);
            int paymentinfo_ID = rs.getInt(3);
            int shipment_ID = rs.getInt(4);
            Date order_date = rs.getDate(5);
            double cost = rs.getDouble(6);
            String status = rs.getString(7);
            
            
            OrderList.add(new Order(order_ID, user_ID, paymentinfo_ID, shipment_ID, order_date, cost, status));
        }
        
        selectStatement.close();
        
        if (OrderList.isEmpty()){
            throw new SQLException("Order does not exist.");
        }
        return OrderList;
    }
    
    public void addOrder (int userID, int paymentinfoID, int shipmentID, Date date, double cost, String status) throws SQLException {
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO tblOrder(User_ID, Payment_Information_ID, Shipment_Detail_ID, Order_Date, Total_Cost, Order_Status VALUES (?,?,?,?,?,?)");
        insertStatement.setInt(1, userID);
        insertStatement.setInt(2, paymentinfoID);
        insertStatement.setInt(3, shipmentID);
        insertStatement.setDate(4, (java.sql.Date) date);
        insertStatement.setDouble(5, cost);
        insertStatement.setString(6, status);
        
        insertStatement.executeUpdate();
        insertStatement.close();
    }
    
    public void updateOrder (int orderID, int userID, int paymentinfoID, int shipmentID, Date date, double cost, String status) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblOrder SET User_ID = ?, Payment_Information_ID = ?, Shipment_Detail_ID = ?, Order_Date = ?, Total_Cost = ?, Order_Status = ? WHERE Order_ID = ?");
        updateStatement.setInt(1, userID);
        updateStatement.setInt(2, paymentinfoID);
        updateStatement.setInt(3, shipmentID);
        updateStatement.setDate(4, (java.sql.Date) date);
        updateStatement.setDouble(5, cost);
        updateStatement.setString(6, status);
        updateStatement.setInt(7, orderID);
         
        updateStatement.executeUpdate();
        updateStatement.close();
    }
    
    public void deleteOrder (int orderID) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblOrder SET Order_Status = 'Cancelled' WHERE Order_ID = ?");
        updateStatement.setInt(1, orderID);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
}
