/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author Sarah
 */
public class ShipmentDetail implements Serializable{
    private int shipmentDetailID;
    private Address address;
    private double deliveryFee;
    private String deliveryInstructions;
    private Date deliveryDate;
    private String deliveryMethod;

    public ShipmentDetail(int shipmentDetailID, Address address, double deliveryFee, String deliveryInstructions, Date deliveryDate, String deliveryMethod) {
        this.shipmentDetailID = shipmentDetailID;
        this.address = address;
        this.deliveryFee = deliveryFee;
        this.deliveryInstructions = deliveryInstructions;
        this.deliveryDate = deliveryDate;
        this.deliveryMethod = deliveryMethod;
    }

    public int getShipmentDetailID() {
        return shipmentDetailID;
    }

    public void setShipmentDetailID(int shipmentDetailID) {
        this.shipmentDetailID = shipmentDetailID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
    
    
}
