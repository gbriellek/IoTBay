package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author Gabrielle
 */
public class PaymentInformation implements Serializable{
    private int paymentInformationID;
    private String cardNumber;
    private String cardType;
    private String expiryDate;
    private int cvv;

    public PaymentInformation(int paymentInformationID, String cardNumber, String cardType, String expiryDate, int cvv) {
        this.paymentInformationID = paymentInformationID;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
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

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCVV() {
        return cvv;
    }

    public void setCVV(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "paymentInformationID: "+paymentInformationID + ", cardNumber: " + cardNumber+ ", cardType: " + cardType+ ", expiryDate: " + expiryDate+ ", cvv: " +cvv;
    }
}
