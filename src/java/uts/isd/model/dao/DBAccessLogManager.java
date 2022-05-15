package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import uts.isd.model.AccessLog;

/**
 *
 * @author Gabrielle K
 */
public class DBAccessLogManager {
    
    private Connection conn;
    
    public DBAccessLogManager(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    //Find access log by user ID
    public ArrayList <AccessLog> findAccessLogByUserID (int user_ID) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM  tblAccess_Log WHERE User_ID = ?");
        selectStatement.setInt(1, user_ID);
        ResultSet rs = selectStatement.executeQuery();
        
        ArrayList <AccessLog> AccessLogList = new ArrayList<>();
        
        while (rs.next()){
            int userID = rs.getInt(2);
            Timestamp access_date_time = rs.getTimestamp(3);
            String event = rs.getString(4);
            
            AccessLogList.add(new AccessLog(userID, access_date_time, event));
        }
        selectStatement.close();
        
        if (AccessLogList.isEmpty()){
            throw new SQLException("Access log does not exist.");
        }
        return AccessLogList;
    }
    
    //Find all access logs
    public ArrayList <AccessLog> findAllAccessLog() throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM  tblAccess_Log");
        ResultSet rs = selectStatement.executeQuery();
        
        ArrayList <AccessLog> AccessLogList = new ArrayList<>();
        
        while (rs.next()){
            int userID = rs.getInt(2);
            Timestamp access_date_time = rs.getTimestamp(3);
            String event = rs.getString(4);
            
            AccessLogList.add(new AccessLog(userID, access_date_time, event));
        }
        selectStatement.close();
        
        if (AccessLogList.isEmpty()){
            throw new SQLException("Access log does not exist.");
        }
        return AccessLogList;
    }
    
    //Find all access logs by user ID and date
    public ArrayList <AccessLog> findAccessLogByUserIDDate (int user_ID, Date access_date) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM  tblAccess_Log WHERE User_ID = ? AND DATE(Access_Date_Time) = ?");
        selectStatement.setInt(1, user_ID);
        selectStatement.setDate(2, access_date);
        ResultSet rs = selectStatement.executeQuery();
        
        ArrayList <AccessLog> AccessLogList = new ArrayList<>();
        
        while (rs.next()){
            int userID = rs.getInt(2);
            Timestamp access_date_time = rs.getTimestamp(3);
            String event = rs.getString(4);
            
            AccessLogList.add(new AccessLog(userID, access_date_time, event));
        }
        selectStatement.close();
        
        if (AccessLogList.isEmpty()){
            throw new SQLException("Access log does not exist.");
        }
        return AccessLogList;
    }
    
    //Find all access logs by date
    public ArrayList <AccessLog> findAllAccessLogByDate (Date access_date) throws SQLException {
        PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM  tblAccess_Log WHERE DATE(Access_Date_Time) = ?");
        selectStatement.setDate(1, access_date);
        ResultSet rs = selectStatement.executeQuery();
        
        ArrayList <AccessLog> AccessLogList = new ArrayList<>();
        
        while (rs.next()){
            int userID = rs.getInt(2);
            Timestamp access_date_time = rs.getTimestamp(3);
            String event = rs.getString(4);
            
            AccessLogList.add(new AccessLog(userID, access_date_time, event));
        }
        selectStatement.close();
        
        if (AccessLogList.isEmpty()){
            throw new SQLException("Access log does not exist.");
        }
        return AccessLogList;
    }
    
    //Add an access log entry
    public void addAccessLog (int user_ID, Timestamp date, String event) throws SQLException {
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO  tblAccess_Log(User_ID, Access_Date_Time, Event) VALUES (?,?,?)");
        insertStatement.setInt(1, user_ID);
        insertStatement.setTimestamp(2, date);
        insertStatement.setString(3, event);
        
        insertStatement.executeUpdate();
        insertStatement.close();
        
    }
}
