/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 27 Apr. 2022
 */

CREATE TABLE tblShipment_Detail (
    Shipment_Detail_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Address_ID int NOT NULL,
    Order_ID int NOT NULL,
    Delivery_Fee DOUBLE NOT NULL,
    Delivery_Instructions VARCHAR(100),
    Delivery_Date DATE NOT NULL,
    Delivery_Method VARCHAR(8) NOT NULL,
    PRIMARY KEY (Shipment_Detail_ID),
    FOREIGN KEY (Address_ID) REFERENCES tblAddress(Address_ID),
    FOREIGN KEY (Order_ID) REFERENCES tblOrder(Order_ID)  
);