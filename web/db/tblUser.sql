/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K & Raunak K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblUser (
    User_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Email_Address VARCHAR(60) NOT NULL,
    First_Name VARCHAR(25) NOT NULL,
    Last_Name VARCHAR(25) NOT NULL,
    Phone_Number VARCHAR(10) NOT NULL,
    PRIMARY KEY (User_ID)
);

INSERT INTO tblUser (Email_Address, First_Name, Last_Name, Phone_Number) VALUES 
    ('hudson.kramer@iotbay.com.au', 'Hudson', 'Kramer', '0434379125'), 
    ('naomi.church@iotbay.com.au', 'Naomi', 'Church', '0407367846'), 
    ('paul.lim@iotbay.com.au', 'Paul', 'Lim', '0424815363'),
    ('mila.woodley@iotbay.com.au', 'Mila', 'Woodley', '0434964689'),
    ('wesley.mccarty@iotbay.com.au', 'Wesley', 'Mccarty', '0407063954'),
    ('aisha.sheridan@iotbay.com.au', 'Aisha', 'Sheridan', '0488826170'),
    ('frankie.heaton@iotbay.com.au', 'Frankie', 'Heaton', '0474326584'),
    ('tonisha.ellison@iotbay.com.au', 'Tonisha', 'Ellison', '0453647869'),
    ('ezekiel.maldonado@iotbay.com.au', 'Ezekiel', 'Maldonado', '0462175890'),
    ('cathy.tucker@iotbay.com.au', 'Cathy', 'Tucker', '0478086571'),
    ('wilson.costa@iotbay.com.au', 'Wilson', 'Costa', '0461087941'),
    ('cali.ferry@iotbay.com.au', 'Cali', 'Ferry', '0436718459'),
    ('robert.hickman@iotbay.com.au', 'Robert', 'Hickman', '0461374908'),
    ('marissa.goulding@iotbay.com.au', 'Marissa', 'Goulding', '0483206582'),
    ('ivan.serrano@iotbay.com.au', 'Ivan', 'Serrano', '0429968934'),
    ('iylah.forrest@iotbay.com.au', 'Iylah', 'Forrest', '0462865963'),
    ('adam.oakley@iotbay.com.au', 'Adam', 'Oakley', '0451341452'),
    ('taylah.buxton@iotbay.com.au', 'Taylah', 'Buxton', '0491928428'),
    ('amaan.grey@iotbay.com.au', 'Amaan', 'Grey', '0426929192'),
    ('molly.garza@iotbay.com.au', 'Molly', 'Garza', '0487289902'),
    
    ('ahmed.bender@gmail.com', 'Ahmed', 'Bender', '0461163236'),
    ('slconrad@yahoo.com', 'Sarah', 'Conrad', '0261650536'),
    ('reeseg15@yahoo.com', 'Reese', 'Gentry', '0431886821'),
    ('princesscrown1@gmail.com', 'Sienna', 'Moses', '0457593334'),
    ('gamingtourn@gmail.com', 'Reese', 'Gentry', '0468836836'),
    ('ellafores198@gmail.com', 'Ella', 'Flores', '0437765834'), 
    ('garfieldwaltz@yahoo.com', 'Garfield', 'Walton', '0275839273'),
    ('junewilkins38@gmail.com', 'June', 'Wilkins', '0498573945'),
    ('tymajor57@yahoo.com', 'Tyrone', 'Major', '0478392007'),
    ('karinahardyyy@gmail.com', 'Karina', 'Hardy', '0478297448'),
    ('johnmaxwell22@gmail.com', 'John', 'Maxwell', '0482693293'),
    ('a.mcgregor85@yahoo.com', 'Alayna', 'Mcgregor', '0495379457'),
    ('vincentharveystrong@gmail.com', 'Vincent', 'Harvey', '0487538972'),
    ('sbarrow475@gmail.com', 'Sydney', 'Barrow', '0427486286'),
    ('brendonframes@yahoo.com', 'Brendon', 'Frame', '0429479237'),
    ('perry.briggs12@gmail.com', 'Perry', 'Briggs', '0428648268'),
    ('bethlopez55@yahoo.com', 'Bethany', 'Lopez', '0247593479'),
    ('damien143@gmail.com', 'Damien', 'Greenaway', '0498279492'),
    ('darciexiong@yahoo.com', 'Darcie', 'Xiong', '0429749272'),
    ('jimwild16@gmail.com', 'Jimmie', 'Wilde', '0429748767'),

    ('tamaramairy3@gmail.com', 'Tamara', 'Mair', '0492792731'),
    ('charlesavalos.2@gmail.com', 'Charles', 'Avalos', '0424759374'),
    ('hope.bar91@yahoo.com', 'Hope', 'Barr', '0498742972');