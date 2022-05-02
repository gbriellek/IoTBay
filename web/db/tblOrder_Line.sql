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
INSERT INTO tblOrder_Line (Order_ID, Product_ID, Quantity, Price) VALUES
(1, 1, 5, 25),
(2, 1, 2, 10),
(3, 3, 10, 20),
(4, 4, 1, 12),
(5, 10, 50, 350),
(5, 1, 1, 1),
(6, 6, 10, 150),
(7, 15, 8, 24),
(8, 4, 1, 12),
(10, 9, 20, 200),
(11, 8, 2, 4),
(11, 3, 20, 40),
(11, 10, 1, 7),
(12, 2, 1, 2),
(13, 5, 2, 20),
(14, 11, 2, 60),
(15, 12, 2, 6),
(16, 13, 10, 20),
(17, 19, 2, 12),
(18, 20, 4, 24);
