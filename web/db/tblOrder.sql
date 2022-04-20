/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblOrder (
    Order_ID int NOT NULL,
    User_ID int NOT NULL,
    Payment_Info_ID int NOT NULL,
    Order_Date TIMESTAMP NOT NULL,
    Total_Cost DOUBLE NOT NULL,
    Delivery_Fee DOUBLE NOT NULL,
    Delivery_Instructions VARCHAR(100),
    Delivery_Status VARCHAR(15) NOT NULL,
    Delivery_Date DATE NOT NULL,
    PRIMARY KEY (Order_ID),
    FOREIGN KEY (User_ID) REFERENCES tblUser(User_ID),
    FOREIGN KEY (Payment_Info_ID) REFERENCES tblPayment_Information(Payment_Info_ID)  
);