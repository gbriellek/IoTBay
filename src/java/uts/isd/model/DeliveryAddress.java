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
public class DeliveryAddress implements Serializable{
    private DeliveryAddress deliveryAddress;
    private Address address;
    private User user;
    private Customer customer;
    private Order order;
    private String purpose;

    public DeliveryAddress(DeliveryAddress deliveryAddress, Address address, User user, Customer customer, Order order, String purpose) {
        this.deliveryAddress = deliveryAddress;
        this.address = address;
        this.user = user;
        this.customer = customer;
        this.order = order;
        this.purpose = purpose;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    
}
