package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import uts.isd.model.PaymentInformation;

/**
 *
 * @author Gabrielle K
 */
public class DBPaymentInformationManager {
    
    private Connection conn;
    
    public DBPaymentInformationManager(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    public PaymentInformation findPaymentInformationByID (int paymentInfoID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblPayment_Information WHERE Payment_Info_ID = ?");
        selectStatement.setInt(1,paymentInfoID);
        ResultSet rs = selectStatement.executeQuery();
        
        while (rs.next()){
            int ID = rs.getInt(1);
            String card_number = rs.getString(2);
            String card_type = rs.getString(3);
            String expiry_date = rs.getString(4);
            
            return new PaymentInformation(ID, card_number, card_type, expiry_date, 0);
        }
        selectStatement.close();
        throw new SQLException("No such payment information exists.");
    }
    
    public ArrayList<PaymentInformation> findPastPaymentInformationByUserID (int userID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblPayment_Information INNER JOIN tblOrder ON tblPayment_Information.Payment_Info_ID = tblOrder.Payment_Info_ID WHERE user_id = ? AND Order_Status != 'Not Submitted'");
        selectStatement.setInt(1,userID);
        ResultSet rs = selectStatement.executeQuery();
        
        ArrayList<PaymentInformation> paymentInfo = new ArrayList<PaymentInformation>();
        
        while (rs.next()){
            int ID = rs.getInt(1);
            String card_number = rs.getString(2);
            String card_type = rs.getString(3);
            String expiry_date = rs.getString(4);
            
            paymentInfo.add(new PaymentInformation(ID, card_number, card_type, expiry_date, 0));
        }
        selectStatement.close();
        
        if (paymentInfo.isEmpty()){
            throw new SQLException("No such payment information exists.");
        }
        return paymentInfo;
    }
    
    public PaymentInformation findSavedPaymentInformationByUserID (int userID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblPayment_Information INNER JOIN tblOrder WHERE user_id = ? AND Order_Status = 'Not Submitted'");
        selectStatement.setInt(1,userID);
        ResultSet rs = selectStatement.executeQuery();
        
        while (rs.next()){
            int ID = rs.getInt(1);
            String card_number = rs.getString(2);
            String card_type = rs.getString(3);
            String expiry_date = rs.getString(4);
            
            return new PaymentInformation(ID, card_number, card_type, expiry_date, 0);
        }
        selectStatement.close();
        throw new SQLException("No such payment information exists.");
    }    
    
    public PaymentInformation findPaymentInformationByCardNumber (String card_number) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblPayment_Information WHERE Card_Number = ?");
        selectStatement.setString(1,card_number);
        ResultSet rs = selectStatement.executeQuery();
        
        while (rs.next()){
            int paymentInfoID = rs.getInt(1);
            String number = rs.getString(2);
            String card_type = rs.getString(3);
            String expiry_date = rs.getString(4);
            
            return new PaymentInformation(paymentInfoID, number, card_type, expiry_date, 0);
        }
        selectStatement.close();
        throw new SQLException("No such payment information exists.");
    }
    
    public ArrayList <PaymentInformation> findPaymentInformationByIDDate(int paymentInfoID, Date date) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM tblPayment_Information INNER JOIN tblOrder ON tblPayment_Information.Payment_Info_ID = tblOrder.Payment_Info_ID WHERE Order_Date = ? AND tblOrder.Payment_Info_ID = ?");
        selectStatement.setDate(1, (java.sql.Date) date);
        selectStatement.setInt(2, paymentInfoID);
        ResultSet rs = selectStatement.executeQuery();
        
        ArrayList <PaymentInformation> PaymentInfoList = new ArrayList<>();
        
        while (rs.next()){
            String number = rs.getString(2);
            String card_type = rs.getString(3);
            String expiry_date = rs.getString(4);
            
            PaymentInfoList.add(new PaymentInformation(paymentInfoID, number, card_type, expiry_date, 0));
        }
        selectStatement.close();
        
        if (PaymentInfoList.isEmpty()){
            throw new SQLException("No such payment information exists.");
        }
        return PaymentInfoList;
    }
    
    public int addPaymentInformation (String card_number, String card_type, String expiry_date) throws SQLException {
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO tblPayment_Information (Card_Number, Card_Type, Expiry_Date) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, card_number);
        insertStatement.setString(2, card_type);
        insertStatement.setString(3, expiry_date);
        
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
        return id;
    }
    
    public void updatePaymentInformation (int paymentInfoID, String card_type, String expiry_date) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblPayment_Information SET Card_Type = ?, Expiry_Date = ? WHERE Payment_Info_ID = ?");
        updateStatement.setString(1, card_type);
        updateStatement.setString(2, expiry_date);
        updateStatement.setInt(3, paymentInfoID);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
    
    public void deletePaymentInformation (int orderID) throws SQLException {
        PreparedStatement updateStatement = conn.prepareStatement("UPDATE tblOrder SET Payment_Info_ID = null WHERE Order_ID = ?");
        updateStatement.setInt(1, orderID);
        
        updateStatement.executeUpdate();
        updateStatement.close();
    }
}
