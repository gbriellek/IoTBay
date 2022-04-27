/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblAccess_Log (
    Access_Log_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    User_ID int NOT NULL,
    Access_Date_Time TIMESTAMP NOT NULL,
    Event VARCHAR(30) NOT NULL,
    PRIMARY KEY (Access_Log_ID),
    FOREIGN KEY (User_ID) REFERENCES tblUser(User_ID)
);