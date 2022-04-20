/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblAddress (
    Address_ID int NOT NULL,
    Unit_Number VARCHAR(4),
    Street_Number VARCHAR(4) NOT NULL,
    Street VARCHAR(30) NOT NULL,
    Postcode int NOT NULL,
    Address_State VARCHAR(3) NOT NULL,
    PRIMARY KEY (Address_ID)
);