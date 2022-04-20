/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblUser (
    User_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Email_Address VARCHAR(60) NOT NULL,
    First_Name VARCHAR(25) NOT NULL,
    Last_Name VARCHAR(25) NOT NULL,
    Phone_Number VARCHAR(10) NOT NULL,
    PRIMARY KEY (User_ID)
);
