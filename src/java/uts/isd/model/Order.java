/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gabrielle
 */
public class Order implements Serializable{
    private int orderID;
    private int userID;
    private int paymentInformationID;
    private int shipmentDetailID;
    private Date orderDate;
    private double totalCost;
    private String orderStatus;

    public Order(int orderID, int userID, int paymentInformationID, int shipmentDetailID, Date orderDate, double totalCost, String orderStatus) {
        this.orderID = orderID;
        this.userID = userID;
        this.paymentInformationID = paymentInformationID;
        this.shipmentDetailID = shipmentDetailID;
        this.orderDate = orderDate;
        this.totalCost = totalCost;
        this.orderStatus = orderStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPaymentInformationID() {
        return paymentInformationID;
    }

    public void setPaymentInformationID(int paymentInformationID) {
        this.paymentInformationID = paymentInformationID;
    }

    public int getShipmentDetailID() {
        return shipmentDetailID;
    }

    public void setShipmentDetailID(int shipmentDetailID) {
        this.shipmentDetailID = shipmentDetailID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "orderID:" + userID + ", paymentInformationID: " + paymentInformationID + ", shipmentDetailID: "  + shipmentDetailID + ", orderDate: "  + orderDate + ", totalCost: "  + totalCost + ", orderStatus: "  + orderStatus;
    }
    
}
