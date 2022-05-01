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
    Order_Date TIMESTAMP NOT NULL,
    Total_Cost DOUBLE NOT NULL,
    Order_Status VARCHAR(15) NOT NULL, 
-- Not Submitted, Pending, DISPATCHED, Delivered
    PRIMARY KEY (Order_ID),
    FOREIGN KEY (User_ID) REFERENCES tblUser(User_ID),
    FOREIGN KEY (Payment_Info_ID) REFERENCES tblPayment_Information(Payment_Info_ID)  
    FOREIGN KEY (Shipment_Detail_ID) REFERENCES tblShipment_Detail(Shipment_Detail_ID)
);
INSERT INTO tblOrder (User_ID, Payment_Info_ID, Order_Date, Total_Cost, Order_Status) VALUES
(20, 1, '2022-09-01 23:03:20', 23.00, 'Dispatched'),
(20, 1, '2018-08-02 20:02:10', 20.00, 'Delivered'),
(20, 1, '2017-09-02 12:22:09', 2.90, 'Dispatched'),
(20, 1, '2022-10-01 09:00:10', 10.20, 'Not Submitted'),
(20, 1, '2020-12-20 20:05:20', 900.10, 'Cancelled'),
(40, 2, '2020-10-21 08:03:20', 24.50, 'Delivered'),
(41, 3, '2019-09-19 04:02:30', 302.30, 'Dispatched'),
(42, 4, '2022-09-19 02:39:49', 304.90, 'Not Submitted'),
(21, 5, '2020-02-02 02:02:02', 202.02, 'Dispatched'),
(22, 6, '2009-09-20 09:20:39', 349.20, 'Cancelled'),
(23, 7, '2021-02-29 10:20:23', 37.80, 'Dispatched'),
(24, 8, '2020-03-40 12:30:24', 2.90, 'Not Submitted'),
(25, 9, '2014-07-20 08:20:15', 3.09, 'Dispatched'),
(26, 10, '2020-09-30 09:20:34', 10.90, 'Delivered'),
(27, 11, '2019-09-19 10:24:09', 12.50, 'Cancelled'),
(28, 12, '2020-02-20 20:20:20', 20.20, 'Delivered'),
(29, 13, '2022-04-09 09:20:35', 300.03, 'Delivered'),
(30, 14, '2020-02-20 20:02:20', 20.20, 'Dispatched'),
(31, 15, '2019-12-20 07:07:77', 77.07, 'Not Submitted'),
(32, 16, '2020-02-20 20:20:20', 20.20, 'Delivered');