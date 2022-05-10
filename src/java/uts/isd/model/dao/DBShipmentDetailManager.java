package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Statement;
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
    
    
    
    public ShipmentDetail findShipmentDetailByUserIDNotSubmitted(int userID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblShipment_Detail INNER JOIN tblOrder ON tblShipment_Detail.Shipment_Detail_ID = tblOrder.Shipment_Detail_ID WHERE tblOrder.User_ID = ? AND tblOrder.Order_Status = 'Not Submitted'");
        selectStatement.setInt(1, userID);
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
    
    public ArrayList findShipmentDetailByUserIDSubmitted(int userID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblShipment_Detail INNER JOIN tblOrder ON tblShipment_Detail.Shipment_Detail_ID = tblOrder.Shipment_Detail_ID WHERE tblOrder.User_ID = ? AND tblOrder.Order_Status NOT IN ('Not Submitted','Cancelled')");
        selectStatement.setInt(1, userID);
        ResultSet rs = selectStatement.executeQuery();
       
        ArrayList<ShipmentDetail> shipmentDetailList = new ArrayList<>();
        
        while (rs.next()){
            int ID = rs.getInt(1);
            int addressID = rs.getInt(2);
            double fee = rs.getDouble(3);
            String instructions = rs.getString(4);
            Date date = rs.getDate(5);
            String method = rs.getString(6);
           
           shipmentDetailList.add(new ShipmentDetail(ID, addressID, fee, instructions, date, method));
       }
        selectStatement.close();
        if (shipmentDetailList.isEmpty()){
            throw new SQLException("No such shipment detail exists."); 
        } 
        return shipmentDetailList;
       
       
    }
    
    public ArrayList findPastShipmentDetailByUserIDDate(int userID, Date date) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblShipment_Detail INNER JOIN tblOrder ON tblShipment_Detail.Shipment_Detail_ID = tblOrder.Shipment_Detail_ID WHERE tblOrder.User_ID = ? AND tblShipment_Detail.Delivery_Date = ? AND tblOrder.Order_Status NOT IN ('Not Submitted','Cancelled')");
        selectStatement.setInt(1, userID);
        selectStatement.setDate(2, date);
        ResultSet rs = selectStatement.executeQuery();
       
        ArrayList<ShipmentDetail> shipmentDetailList = new ArrayList<>();
        
        while (rs.next()){
            int ID = rs.getInt(1);
            int addressID = rs.getInt(2);
            double fee = rs.getDouble(3);
            String instructions = rs.getString(4);
            Date shipment_date = rs.getDate(5);
            String method = rs.getString(6);
           
           shipmentDetailList.add(new ShipmentDetail(ID, addressID, fee, instructions, shipment_date, method));
       }
        selectStatement.close();
        if (shipmentDetailList.isEmpty()){
            throw new SQLException("No such shipment detail exists."); 
        } 
        return shipmentDetailList;
       
       
    }
    
    public ArrayList findPastShipmentDetailByUserIDShipmentID(int userID, int shipmentID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblShipment_Detail INNER JOIN tblOrder ON tblShipment_Detail.Shipment_Detail_ID = tblOrder.Shipment_Detail_ID WHERE tblOrder.User_ID = ? AND tblShipment_Detail.Shipment_Detail_ID = ? AND tblOrder.Order_Status NOT IN ('Not Submitted','Cancelled')");
        selectStatement.setInt(1, userID);
        selectStatement.setInt(2, shipmentID);
        ResultSet rs = selectStatement.executeQuery();
       
        ArrayList<ShipmentDetail> shipmentDetailList = new ArrayList<>();
        
        while (rs.next()){
            int ID = rs.getInt(1);
            int addressID = rs.getInt(2);
            double fee = rs.getDouble(3);
            String instructions = rs.getString(4);
            Date shipment_date = rs.getDate(5);
            String method = rs.getString(6);
           
           shipmentDetailList.add(new ShipmentDetail(ID, addressID, fee, instructions, shipment_date, method));
       }
        selectStatement.close();
        if (shipmentDetailList.isEmpty()){
            throw new SQLException("No such shipment detail exists."); 
        } 
        return shipmentDetailList;
       
       
    }
    
     public ArrayList findPastShipmentDetailByUserIDDateOrderID(int userID, Date date, int shipmentID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblShipment_Detail INNER JOIN tblOrder ON tblShipment_Detail.Shipment_Detail_ID = tblOrder.Shipment_Detail_ID WHERE tblOrder.User_ID = ? AND tblShipment_Detail.Shipment_Detail_ID = ? AND tblShipment_Detail.Delivery_Date = ? AND tblOrder.Order_Status NOT IN ('Not Submitted','Cancelled')");
        selectStatement.setInt(1, userID);
        selectStatement.setInt(2, shipmentID);
        selectStatement.setDate(3, date);
        ResultSet rs = selectStatement.executeQuery();
       
        ArrayList<ShipmentDetail> shipmentDetailList = new ArrayList<>();
        
        while (rs.next()){
            int ID = rs.getInt(1);
            int addressID = rs.getInt(2);
            double fee = rs.getDouble(3);
            String instructions = rs.getString(4);
            Date shipment_date = rs.getDate(5);
            String method = rs.getString(6);
           
           shipmentDetailList.add(new ShipmentDetail(ID, addressID, fee, instructions, shipment_date, method));
       }
        selectStatement.close();
        if (shipmentDetailList.isEmpty()){
            throw new SQLException("No such shipment detail exists."); 
        } 
        return shipmentDetailList;
       
       
    }
    
    public int addShipmentDetail (int addressID, double fee, String instructions, Date date, String method) throws SQLException {
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO tblShipment_Detail (Address_ID, Delivery_Fee, Delivery_Instructions, Delivery_Date, Delivery_Method) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        insertStatement.setInt(1, addressID);
        insertStatement.setDouble(2, fee);
        insertStatement.setString(3, instructions);
        insertStatement.setDate(4, date);
        insertStatement.setString(5, method);
         
        insertStatement.executeUpdate();
        
        int id;
        
        try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);   
            }
            else {
                insertStatement.close();
                throw new SQLException("Creating shipment detail failed, no ID obtained.");
            }
        }
        
        insertStatement.close();
        return id;
    }
    
    public void updateShipmentDetail (int shipmentID, int addressID, double fee, String instructions, Date date, String method) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblShipment_Detail SET Address_ID = ?, Delivery_Fee = ?, Delivery_Instructions = ?, Delivery_Date = ?, Delivery_Method = ? WHERE Shipment_Detail_ID = ?");
        updateStatement.setInt(1, addressID);
        updateStatement.setDouble(2, fee);
        updateStatement.setString(3, instructions);
        updateStatement.setDate(4, (java.sql.Date) date);
        updateStatement.setString(5, method);
        updateStatement.setInt(6, shipmentID);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
    
    public void updateShipmentDate (int shipmentID, Date date) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblShipment_Detail SET Delivery_Date = ? WHERE Shipment_Detail_ID = ?");
        updateStatement.setDate(1, date);
        updateStatement.setInt(2, shipmentID);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
    
    public void deleteShipmentDetail(int shipmentID) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("DELETE FROM tblShipment_Detail WHERE Shipment_Detail_ID = ?");
        updateStatement.setInt(1, shipmentID);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
    
}
