/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author Gabrielle
 */
public class Customer implements Serializable {
    private int customerID;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private boolean isActivated;

    public Customer(int customerID, String emailAddress, String firstName, String lastName, String phoneNumber, String password, boolean isActivated) {
        this.customerID = customerID;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isActivated = isActivated;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    @Override
    public String toString() {
        return "customerID: " + customerID + ", emailAddress: " + emailAddress + ", firstName: "+ firstName + ", lastName: " + lastName + ", phoneNumber: " + phoneNumber + ", password: " + password + ", isActivated: " + isActivated;
    }

    
}

