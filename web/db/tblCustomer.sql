/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K & Raunak K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblCustomer (
    Customer_ID int NOT NULL,
    Password VARCHAR(30) NOT NULL,
    Is_Activated BOOLEAN NOT NULL,
    PRIMARY KEY (Customer_ID),
    FOREIGN KEY (Customer_ID) REFERENCES tblUser(User_ID)
);

INSERT INTO tblCustomer (Customer_ID, Password, Is_Activated) VALUES
    (21, 'Reading38', true),
    (22, 'LoveTech2', true),
    (23, 'skateboard39', true),
    (24, 'BasketballNBA', false),
    (25, 'Kitten&Cats', true),
    (26, 'Birds338', true),
    (27, 'BunnyRabbit', true),
    (28, 'London.bridge29', true),
    (29, 'cornBread', false),
    (30, 'IlikeTrains', true),
    (31, 'ears&eyes', true),
    (32, 'ZiGzAg', true),
    (33, 'BumbleBee', true),
    (34, 'M0nkeys', false),
    (35, 'pencil.pen67', true),
    (36, 'Triangl48', true),
    (37, 'Square94', true),
    (38, 'blanketComfy', true),
    (39, 'Moon&Stars', true),
    (40, 'Popsicles48', true);