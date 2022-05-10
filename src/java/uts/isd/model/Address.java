package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author Gabrielle
 */
public class Address implements Serializable{
    private int addressID;
    private String unitNo;
    private String streetNo;
    private String streetName;
    private String city;
    private int postcode;
    private String state;

    public Address(int addressID, String unitNo, String streetNo, String streetName, String city, int postcode, String state) {
        this.addressID = addressID;
        this.unitNo = unitNo;
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.city = city;
        this.postcode = postcode;
        this.state = state;
    }

     public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }
    
    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "addressID: " + addressID + ", unitNo: " + unitNo + ", streetNo: " + streetNo +", streetName: " +streetName +", city: " + city +", postcode: "+ postcode +", state: "+ state;
    }
}
