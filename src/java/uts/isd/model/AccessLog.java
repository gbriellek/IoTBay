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
    private Staff staff;
    private Date accessDateTime;
    private String event;

    public AccessLog(Staff staff, Date accessDateTime, String event) {
        this.staff = staff;
        this.accessDateTime = accessDateTime;
        this.event = event;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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
