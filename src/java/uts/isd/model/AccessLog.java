/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Gabrielle
 */
public class AccessLog implements Serializable{
    private int userID;
    private Timestamp accessDateTime;
    private String event;

    public AccessLog(int userID, Timestamp accessDateTime, String event) {
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

    public Timestamp getAccessDateTime() {
        return accessDateTime;
    }

    public void setAccessDateTime(Timestamp accessDateTime) {
        this.accessDateTime = accessDateTime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
    
    
}
