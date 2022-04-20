/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblStaff (
    Staff_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Password VARCHAR(30) NOT NULL,
    Staff_Number VARCHAR(10) NOT NULL,
    Is_Activated BOOLEAN NOT NULL,
    PRIMARY KEY (Staff_ID),
    FOREIGN KEY (Staff_ID) REFERENCES tblUser(User_ID)
);
