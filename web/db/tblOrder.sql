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
    Order_Date TIMESTAMP NOT NULL,
    Total_Cost DOUBLE NOT NULL,
    Order_Status VARCHAR(15) NOT NULL, 
-- Not Submitted, Pending, DISPATCHED, Delivered
    PRIMARY KEY (Order_ID),
    FOREIGN KEY (User_ID) REFERENCES tblUser(User_ID),
    FOREIGN KEY (Payment_Info_ID) REFERENCES tblPayment_Information(Payment_Info_ID)  
);
INSERT INTO tblOrder (User_ID, Payment_Info_ID, Order_Date, Total_Cost, Order_Status) VALUES
(1, 1, '2022-09-01 23:03:20', 23.00, ''),
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
();