/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblOrder (
    Order_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    User_ID int NOT NULL,
    Payment_Info_ID int NOT NULL,
    Shipment_Detail_ID int,
    Order_Date DATE NOT NULL,
    Total_Cost DOUBLE NOT NULL,
    Order_Status VARCHAR(15) NOT NULL, 
    PRIMARY KEY (Order_ID),
    FOREIGN KEY (User_ID) REFERENCES tblUser(User_ID),
    FOREIGN KEY (Payment_Info_ID) REFERENCES tblPayment_Information(Payment_Info_ID)  
    FOREIGN KEY (Shipment_Detail_ID) REFERENCES tblShipment_Detail(Shipment_Detail_ID)
);
INSERT INTO tblOrder (User_ID, Payment_Info_ID, Shipment_Detail_ID, Order_Date, Total_Cost, Order_Status) VALUES
(20, 1, 1, '2022-2-9', 23.00, 'Dispatched'),
(20, 1, 2, '2022-2-21', 20.00, 'Delivered'),
(20, 1, 3, '2022-3-6', 2.90, 'Dispatched'),
(20, 1, 4, '2022-3-13', 10.20, 'Not Submitted'),
(20, 1, 5, '2022-4-13', 900.10, 'Cancelled'),
(40, 2, 6, '2022-2-11', 24.50, 'Delivered'),
(41, 3, 7, '2022-2-11', 302.30, 'Dispatched'),
(42, 4, 8, '2022-3-29', 304.90, 'Not Submitted'),
(21, 5, 9, '2022-5-13', 202.02, 'Dispatched'),
(22, 6, 10, '2022-4-20', 349.20, 'Cancelled'),
(23, 7, 11, '2022-2-9', 37.80, 'Dispatched'),
(24, 8, 12, '2022-4-13', 2.90, 'Not Submitted'),
(25, 9, 13, '2022-4-20', 3.09, 'Dispatched'),
(26, 10, 14, '2022-4-13', 10.90, 'Delivered'),
(27, 11, 15, '2022-5-19', 12.50, 'Cancelled'),
(28, 12, 16, '2022-2-21', 20.20, 'Delivered'),
(29, 13, 17, '2022-2-11', 300.03, 'Delivered'),
(30, 14, 18, '2022-2-22', 20.20, 'Dispatched'),
(31, 15, 19, '2022-1-23', 77.07, 'Not Submitted'),
(32, 16, 20, '2022-4-13', 20.20, 'Delivered');