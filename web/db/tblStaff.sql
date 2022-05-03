/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K & Raunak K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblStaff (
    Staff_ID int NOT NULL,
    Password VARCHAR(30) NOT NULL,
    Staff_Number VARCHAR(10) NOT NULL,
    Is_Activated BOOLEAN NOT NULL,
    PRIMARY KEY (Staff_ID),
    FOREIGN KEY (Staff_ID) REFERENCES tblUser(User_ID)
);

INSERT INTO tblStaff (Staff_ID, Password, Staff_Number, Is_Activated) VALUES 
    (1, 'wheel423', 'S1', true),
    (2, 'naomi@24', 'S2', true),
    (3, 'king.energy', 'S3', true),
    (4, 'Mila-r0se', 'S4', false), 
    (5, 'Imagin@tion', 'S5', true),
    (6, 'actor.method21', 'S6', true),
    (7, 'guitarWorld', 'S7', true),
    (8, 'marketingLover', 'S8', true),
    (9, 'nation.person65', 'S9', true),
    (10, 'salesDepartment', 'S10', true),
    (11, 'Enthusi@sm236', 'S11', false),
    (12, 'LoveCookies12', 'S12', true),
    (13, 'groceries23', 'S13', true),
    (14, 'HorrorSeries', 'S14', true),
    (15, 'Midnightdream34', 'S15', true),
    (16, 'SafetyWHS', 'S16', true),
    (17, 'queen233', 'S17', true),
    (18, 'Ch0c0late', 'S18', true),
    (19, 'IOTStaff', 'S19', true),
    (20, 'LoveMusic49', 'S20', true);
