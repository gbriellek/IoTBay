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
public class PaymentInformation implements Serializable{
    private int paymentInformationID;
    private String cardNumber;
    private String cardType;
    private Date expiryDate;
    private int CVV;

    public PaymentInformation(int paymentInformationID, String cardNumber, String cardType, Date expiryDate, int CVV) {
        this.paymentInformationID = paymentInformationID;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
    }

    public int getPaymentInformationID() {
        return paymentInformationID;
    }

    public void setPaymentInformationID(int paymentInformationID) {
        this.paymentInformationID = paymentInformationID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }
    
}
