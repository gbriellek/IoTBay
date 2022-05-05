/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Sarah
 * Created: 30 Apr. 2022
 */

INSERT INTO tblOrder_Line (Quantity, Price) VALUES (2,10);

DROP TABLE tblOrder_Line;
DROP TABLE tblProduct;
DROP TABLE tblOrder;
DROP TABLE tblShipment_Detail;
DROP TABLE tblAddress;
DROP TABLE tblPayment_Information;
DROP TABLE tblAccess_Log;
DROP TABLE tblStaff;
DROP TABLE tblCustomer;
DROP TABLE tblUser;

CREATE TABLE tblUser (
    User_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Email_Address VARCHAR(60) NOT NULL,
    First_Name VARCHAR(25) NOT NULL,
    Last_Name VARCHAR(25) NOT NULL,
    Phone_Number VARCHAR(10) NOT NULL,
    PRIMARY KEY (User_ID)
);

CREATE TABLE tblStaff (
    Staff_ID int NOT NULL,
    Password VARCHAR(30) NOT NULL,
    Staff_Number VARCHAR(10) NOT NULL,
    Is_Activated BOOLEAN NOT NULL,
    PRIMARY KEY (Staff_ID),
    FOREIGN KEY (Staff_ID) REFERENCES tblUser(User_ID)
);

CREATE TABLE tblCustomer (
    Customer_ID int NOT NULL,
    Password VARCHAR(30) NOT NULL,
    Is_Activated BOOLEAN NOT NULL,
    PRIMARY KEY (Customer_ID),
    FOREIGN KEY (Customer_ID) REFERENCES tblUser(User_ID)
);

CREATE TABLE tblAccess_Log (
    Access_Log_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    User_ID int NOT NULL,
    Access_Date_Time TIMESTAMP NOT NULL,
    Event VARCHAR(30) NOT NULL,
    PRIMARY KEY (Access_Log_ID),
    FOREIGN KEY (User_ID) REFERENCES tblUser(User_ID)
);

CREATE TABLE tblPayment_Information (
    Payment_Info_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Card_Number VARCHAR(16) NOT NULL,
    Card_Type VARCHAR(10) NOT NULL,
    Expiry_Date VARCHAR(7) NOT NULL,
    PRIMARY KEY (Payment_Info_ID)
);

CREATE TABLE tblAddress (
    Address_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Unit_Number VARCHAR(4),
    Street_Number VARCHAR(4) NOT NULL,
    Street VARCHAR(30) NOT NULL,
    City VARCHAR(30) NOT NULL,
    Postcode int NOT NULL,
    Address_State VARCHAR(3) NOT NULL,
    PRIMARY KEY (Address_ID)
);

CREATE TABLE tblShipment_Detail (
    Shipment_Detail_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Address_ID int NOT NULL,
    Delivery_Fee DOUBLE NOT NULL,
    Delivery_Instructions VARCHAR(100),
    Delivery_Date DATE NOT NULL,
    Delivery_Method VARCHAR(8) NOT NULL,
    PRIMARY KEY (Shipment_Detail_ID),
    FOREIGN KEY (Address_ID) REFERENCES tblAddress(Address_ID)  
);

CREATE TABLE tblOrder (
    Order_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    User_ID int NOT NULL,
    Payment_Info_ID int,
    Shipment_Detail_ID int,
    Order_Date DATE NOT NULL,
    Total_Cost DOUBLE NOT NULL,
    Order_Status VARCHAR(15) NOT NULL, 
    PRIMARY KEY (Order_ID),
    FOREIGN KEY (User_ID) REFERENCES tblUser(User_ID),
    FOREIGN KEY (Payment_Info_ID) REFERENCES tblPayment_Information(Payment_Info_ID),  
    FOREIGN KEY (Shipment_Detail_ID) REFERENCES tblShipment_Detail(Shipment_Detail_ID)
);

CREATE TABLE tblProduct (
    Product_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Product_Name VARCHAR(50) NOT NULL,
    Description VARCHAR(500) NOT NULL,
    Price DOUBLE NOT NULL,
    Stock int NOT NULL,
    Category VARCHAR(30) NOT NULL,
    Is_Active BOOLEAN NOT NULL,
    PRIMARY KEY (Product_ID)
);

CREATE TABLE tblOrder_Line (
    Order_ID int NOT NULL,
    Product_ID int NOT NULL,
    Quantity int NOT NULL,
    Price DOUBLE NOT NULL,
    PRIMARY KEY (Order_ID, Product_ID),
    FOREIGN KEY (Order_ID) REFERENCES tblOrder(Order_ID),
    FOREIGN KEY (Product_ID) REFERENCES tblProduct(Product_ID)
);

