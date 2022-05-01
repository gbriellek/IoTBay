/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;

import java.util.Date;

/**
 *
 * @author Gabrielle
 */
public class AccessLog {
    private int userID;
    private Date accessDateTime;
    private String event;

    public AccessLog(int userID, Date accessDateTime, String event) {
        this.userID = userID;
        this.accessDateTime = accessDateTime;
        this.event = event;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getAccessDateTime() {
        return accessDateTime;
    }

    public void setAccessDateTime(Date accessDateTime) {
        this.accessDateTime = accessDateTime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
    
    
}
