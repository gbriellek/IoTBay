/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K & Sarah F
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

INSERT INTO tblAccess_Log (User_ID, Access_Date_Time, Event) VALUES
(1,'2022-2-5 09:10:35','login'),
(1,'2022-2-5 10:15:23','logout'),
(1,'2022-5-27 09:30:11','login'),
(1,'2022-5-27 13:40:12','logout'),
(2,'2022-3-15 09:40:28','login'),
(2,'2022-3-15 14:21:45','logout'),
(21,'2022-2-9 10:20:12','login'),
(21,'2022-2-9 12:56:58','logout'),
(21,'2022-2-21 12:05:23','login'),
(21,'2022-2-21 15:34:23','logout'),
(21,'2022-3-6 12:20:59','login'),
(21,'2022-3-6 16:12:23','logout'),
(21,'2022-3-13 13:20:43','login'),
(21,'2022-3-13 14:49:49','logout'),
(21,'2022-4-13 13:45:23','login'),
(21,'2022-4-13 14:22:12','logout'),
(22,'2022-5-13 13:55:32','login'),
(22,'2022-5-13 15:12:12','logout'),
(23,'2022-4-20 14:55:23','login'),
(23,'2022-4-20 16:23:43','logout'),
(24,'2022-2-9 15:15:15','login'),
(24,'2022-2-9 16:16:16','logout'),
(25,'2022-4-13 15:30:21','login'),
(25,'2022-4-13 16:34:12','logout'),
(26,'2022-4-20 17:10:23','login'),
(26,'2022-4-20 23:14:23','logout'),
(27,'2022-4-13 17:25:12','login'),
(27,'2022-4-13 21:12:12','logout'),
(28,'2022-5-19 17:40:12','login'),
(28,'2022-5-19 20:10:11','logout'),
(29,'2022-2-21 17:46:19','login'),
(29,'2022-2-21 19:12:48','logout'),
(30,'2022-2-11 19:20:05','login'),
(30,'2022-2-11 21:04:39','logout'),
(31,'2022-2-22 20:00:00','login'),
(31,'2022-2-22 22:00:00','logout'),
(32,'2022-1-23 20:30:12','login'),
(32,'2022-1-23 21:18:23','logout'),
(33,'2022-4-13 20:57:22','login'),
(33,'2022-4-13 21:47:11','logout');