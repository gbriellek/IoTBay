/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Gabrielle K
 * Created: 20 Apr. 2022
 */

CREATE TABLE tblPayment_Information (
    Payment_Info_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Card_Number VARCHAR(16) NOT NULL,
    Card_Type VARCHAR(10) NOT NULL,
    Expiry_Date VARCHAR(7) NOT NULL,
    PRIMARY KEY (Payment_Info_ID)
);
INSERT INTO tblPayment_Information (Card_Number, Card_Type, Expiry_Date) VALUES
('4603 6511 8449 0994', 'Visa', '2023-03'),
('3403 6911 8449 0995', 'AMEX', '2022-09'),
('5098 3274 2839 2392', 'Mastercard', '2025-06'),
('3789 2839 1038 4820', 'AMEX', '2030-03'),
('3482 3849 2018 3849', 'AMEX', '2023-01'),
('4482 2849 2820 1920', 'Visa', '2022-09'),
('5728 2830 2108 2839', 'Mastercard', '2023-10'),
('3489 2840 1029 3849', 'AMEX', '2024-12'),
('4728 2839 1293 1023', 'Visa', '2022-10'),
('3484 2349 3840 1920', 'AMEX', '2023-02'),
('4527 2894 2920 1920', 'Visa', '2030-01'),
('3784 2840 3840 1839', 'AMEX', '2022-12'),
('3493 3920 2940 2810', 'AMEX', '2031-03'),
('4923 2839 4038 2812', 'Visa', '2023-03'),
('5493 2834 3849 2840', 'Mastercard', '2033-03'),
('3484 2834 4893 2839', 'Visa', '2022-10'),
('4738 3849 2820 3849', 'Visa', '2023-08'),
('5734 3849 2830 8302', 'Mastercard', '2024-02'),
('3482 3849 3839 2809', 'AMEX', '2030-10'),
('4238 4920 2810 2830', 'Visa', '2022-02');