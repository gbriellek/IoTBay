/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblPayment_Information (
    Payment_Info_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    User_ID int NOT NULL,
    Card_Number VARCHAR(16) NOT NULL,
    Card_Type VARCHAR(10) NOT NULL,
    Expiry_Date DATE NOT NULL,
    PRIMARY KEY (Payment_Info_ID),
    FOREIGN KEY (User_ID) REFERENCES tblUser(User_ID)
);
