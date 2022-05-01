/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Gabrielle
 */
public class Order implements Serializable{
    private int orderID;
    private int userID;
    private PaymentInformation paymentInformation;
    private ShipmentDetail shipmentDetail;
    private Date orderDate;
    private double totalCost;
    private String orderStatus;
    private ArrayList<OrderLine> orderLine;

    public Order(int orderID, int userID, PaymentInformation paymentInformation, ShipmentDetail shipmentDetail, Date orderDate, double totalCost, String orderStatus, ArrayList<OrderLine> orderLine) {
        this.orderID = orderID;
        this.userID = userID;
        this.paymentInformation = paymentInformation;
        this.shipmentDetail = shipmentDetail;
        this.orderDate = orderDate;
        this.totalCost = totalCost;
        this.orderStatus = orderStatus;
        this.orderLine = orderLine;
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

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public ShipmentDetail getShipmentDetail() {
        return shipmentDetail;
    }

    public void setShipmentDetail(ShipmentDetail shipmentDetail) {
        this.shipmentDetail = shipmentDetail;
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

    public ArrayList<OrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(ArrayList<OrderLine> orderLine) {
        this.orderLine = orderLine;
    }
    
}
