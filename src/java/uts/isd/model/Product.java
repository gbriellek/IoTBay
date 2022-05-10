package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author Gabrielle
 */
public class Product implements Serializable{
    private int productID;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private boolean isActive;

    public Product(int productID, String name, String description, double price, int stock, String category, boolean isActive) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.isActive = isActive;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    @Override
    public String toString() {
        return "productID: " + productID +", name: " + name +", description: " +description +", price: " +price + ", stock: " +stock + ", category: " +category+ ", isActive: " +isActive;
    } 
}
