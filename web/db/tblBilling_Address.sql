/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblBilling_Address(
    Billing_Address_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Address_ID int NOT NULL,
    User_ID int,
    Order_ID int,
    Purpose VARCHAR(5) NOT NULL,
    PRIMARY KEY (Billing_Address_ID),
    FOREIGN KEY (Address_ID) REFERENCES tblAddress(Address_ID),
    FOREIGN KEY (User_ID) REFERENCES tblUser(User_ID),
    FOREIGN KEY (Order_ID) REFERENCES tblOrder(Order_ID)
)