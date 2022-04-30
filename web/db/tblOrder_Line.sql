/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblOrder_Line (
    Order_ID int NOT NULL,
    Product_ID int NOT NULL,
    Quantity int NOT NULL,
    Price DOUBLE NOT NULL,
    PRIMARY KEY (Order_ID, Product_ID),
    FOREIGN KEY (Order_ID) REFERENCES tblOrder(Order_ID),
    FOREIGN KEY (Product_ID) REFERENCES tblProduct(Product_ID)
);
