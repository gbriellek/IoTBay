/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K & Jemma S
 * Created: 20 Apr. 2022
 */

DROP TABLE tblOrder_Line;
DROP TABLE tblProduct;
DROP TABLE tblOrder;
DROP TABLE tblShipment_Detail;
DROP TABLE tblAddress;
DROP TABLE tblPayment_Information;
DROP TABLE tblAccess_Log;
DROP TABLE tblStaff;
DROP TABLE tblCustomer;
DROP TABLE tblUser;

CREATE TABLE tblPayment_Information (
    Payment_Info_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Card_Number VARCHAR(16) NOT NULL,
    Card_Type VARCHAR(10) NOT NULL,
    Expiry_Date VARCHAR(7) NOT NULL,
    PRIMARY KEY (Payment_Info_ID)
);

INSERT INTO tblPayment_Information (Card_Number, Card_Type, Expiry_Date) VALUES
('4603651184490994', 'Visa', '2023-03'),
('3403691184490995', 'AMEX', '2022-09'),
('5098327428392392', 'Mastercard', '2025-06'),
('3789283910384820', 'AMEX', '2030-03'),
('3482384920183849', 'AMEX', '2023-01'),
('4482284928201920', 'Visa', '2022-09'),
('5728283021082839', 'Mastercard', '2023-10'),
('3489284010293849', 'AMEX', '2024-12'),
('4728283912931023', 'Visa', '2022-10'),
('3484234938401920', 'AMEX', '2023-02'),
('4527289429201920', 'Visa', '2030-01'),
('3784284038401839', 'AMEX', '2022-12'),
('3493392029402810', 'AMEX', '2031-03'),
('4923283940382812', 'Visa', '2023-03'),
('5493283438492840', 'Mastercard', '2033-03'),
('3484283448932839', 'Visa', '2022-10'),
('4738384928203849', 'Visa', '2023-08'),
('5734384928308302', 'Mastercard', '2024-02'),
('3482384938392809', 'AMEX', '2030-10'),
('4238492028102830', 'Visa', '2022-02');