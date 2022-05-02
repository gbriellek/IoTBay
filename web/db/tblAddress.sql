/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K & Sarah F
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblAddress (
    Address_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Unit_Number VARCHAR(4),
    Street_Number VARCHAR(4) NOT NULL,
    Street VARCHAR(30) NOT NULL,
    City VARCHAR(30) NOT NULL,
    Postcode int NOT NULL,
    Address_State VARCHAR(3) NOT NULL,
    PRIMARY KEY (Address_ID)
);

INSERT INTO tblAddress (Unit_Number, Street_Number, Street, City, Postcode, Address_State) VALUES
(null, '48', 'Patton Street', 'Beenak', 3139, 'VIC'),
(null, '53A','Chatsworth Drive', 'Wilson', 6107, 'WA'),
('10', '25 ','McKillop Street', 'Raglan', 3373, 'VIC'),
(null, '15','Clifton Street', 'Woorinen North', 3589, 'VIC'),
('5', '83','Thomas Lane', 'Northcote', 3070, 'VIC'),
(null, '91','Zipfs Road', 'Booval Bc', 4304, 'QLD'),
(null, '65B','Cherokee Road', 'Sailors Falls', 3461, 'VIC'),
(null, '44','Queen Street', 'North Balgowlah', 2093, 'NSW'),
(null, '79','Blairgowrie Avenue', 'Jerangle', 2630, 'NSW'),
(null, '98','Elizabeth Street', 'Tamaree', 4570, 'QLD'),
(null, '63B','Webb Road', 'The Junction', 2291, 'NSW'),
('1', '57','Bass Street', 'Lagoon Grass', 2480, 'NSW'),
(null, '95','Villeneuve Street', 'Woodstock', 3751, 'VIC'),
(null, '95','Point Walter Road', 'North Fremantle', 6159, 'WA'),
(null, '15A','Oriana Street', 'Tuggerah', 2259, 'NSW'),
(null, '45','Amiens Road', 'Long Creek', 2850, 'NSW'),
('400', '59','Trelawney Street', 'Centennial Park', 2021, 'NSW'),
(null, '3','Marx Hill Road', 'Darkwood', 2454, 'NSW'),
(null, '51','Plug Street', 'Brockley', 2365, 'NSW'),
(null, '68','Bungana Drive', 'Euromina', 5454, 'SA');
