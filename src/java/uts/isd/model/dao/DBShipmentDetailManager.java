/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import uts.isd.model.ShipmentDetail;

/**
 *
 * @author Gabrielle K
 */
public class DBShipmentDetailManager {
    
    private Connection conn;
    
    public DBShipmentDetailManager(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    public ShipmentDetail findShipmentDetail(int shipmentID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblShipment_Detail WHERE Shipment_Detail_ID = ?");
        selectStatement.setInt(1, shipmentID);
        ResultSet rs = selectStatement.executeQuery();
       
        while (rs.next()){
            int ID = rs.getInt(1);
            int addressID = rs.getInt(2);
            double fee = rs.getDouble(3);
            String instructions = rs.getString(4);
            Date date = rs.getDate(5);
            String method = rs.getString(6);
           
           return new ShipmentDetail(ID, addressID, fee, instructions, date, method);
       }
       selectStatement.close();
       throw new SQLException("No such shipment detail exists."); 
    }
    
    public void addShipmentDetail (int addressID, double fee, String instructions, Date date, String method) throws SQLException {
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO tblShipment_Detail (Address_ID, Delivery_Fee, Delivery_Instructions, Delivery_Date, Delivery_Method) VALUES (?,?,?,?,?)");
        insertStatement.setInt(1, addressID);
        insertStatement.setDouble(2, fee);
        insertStatement.setString(3, instructions);
        insertStatement.setDate(4, (java.sql.Date) date);
        insertStatement.setString(5, method);
         
        insertStatement.executeUpdate();
        insertStatement.close();
    }
    
    public void updateShipmentDetail (int shipmentID, double fee, String instructions, Date date, String method) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblShipment_Detail SET Delivery_Fee = ?, Delivery_Instructions = ?, Delivery_Date = ?, Delivery_Method = ? WHERE Shipment_Detail_ID = ?");
        updateStatement.setDouble(1, fee);
        updateStatement.setString(2, instructions);
        updateStatement.setDate(3, (java.sql.Date) date);
        updateStatement.setString(4, method);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
    
    public void deleteShipmentDetail(int shipmentID) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblOrder SET tblOrder.Shipment_Detail_ID = ? WHERE tblShipment_Detail.Shipment_Detail_ID = ?");
        updateStatement.setInt(1, shipmentID);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
    
}
