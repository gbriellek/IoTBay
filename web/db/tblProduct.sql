/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblProduct (
    Product_ID int NOT NULL,
    Product_Name VARCHAR(50) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    Price DOUBLE NOT NULL,
    Stock int NOT NULL,
    Category VARCHAR(30) NOT NULL,
    Is_Active BOOLEAN NOT NULL,
    PRIMARY KEY (Product_ID)
);