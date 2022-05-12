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

CREATE TABLE tblProduct (
    Product_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Product_Name VARCHAR(50) NOT NULL,
    Description VARCHAR(500) NOT NULL,
    Price DOUBLE NOT NULL,
    Stock int NOT NULL,
    Category VARCHAR(30) NOT NULL,
    Is_Active BOOLEAN NOT NULL,
    PRIMARY KEY (Product_ID)
);

INSERT INTO tblProduct (Product_Name, Description, Price, Stock, Category, Is_Active) VALUES
('Moisture Sensor', 'This analogue humidity sensor finds an excellent place in automatic irrigation systems. It is placed in the ground and measures the humidity by current flowing between the strands. The more humid the earth in between, the higher the (analog) signal.', 5, 89, 'Temperature/Humidity/Gas', True),
('MQ-2 Gas Sensor', 'The MQ gas sensors can detect different gases at room temperature. Depending on the model, other gases are supported. The MQ-2 can recognize methane, butane, LPG and smoke, the MQ3 detects, for example, alcohol, ethanol and smoke, etc.', 2, 0, 'Temperature/Humidity/Gas', True),
('PIR Motion Sensor', 'The PIR motion sensor has some advantages over other similar products: besides the low price, a signal is sent only if something moves. This allows you to wait for signal flanks using the GPIOs. In addition, a resistance can be varied so that a signal is only sent when the movement is close, or changes that are already far away are perceived.', 2, 150, 'Motion Sensor', True),
('GP2Y0A02YK', 'With the GP2Y0A02YK infrared distance meter, much more accurate measurements can be performed, as with e.g. the HC-SR04. The module is limited to a range of 20-150cm.', 12, 0, 'Magnetic Switch/Reed Relay', True),
('GPS NEO-6M Module', 'The most common and best known GPS receiver is the NEO-6M module. All GPS position data can be determined with the help of the orbiting satellites.', 10, 55, 'Navigation Modules', True),
('USB GPS Receiver', 'As an alternative to GPS modules which are connected via the GPIOs, USB GPS receivers can also be used. Those have the advantage that (almost) all are compatible with Windows, Linux and Mac and no additional connection is necessary. On the other hand, these modules are usually more expensive, but they are not inferior in terms of accuracy. It is therefore an individual question, which type of receiver is preferred.', 15, 200, 'Navigation Modules', True),
('MPU-6050 Gyroscope', 'A gyroscope (circular instrument) is used to detect the rotation along the three axes. The MPU 6050 sensor also contains an acceleration sensor. This module can be used e.g. in robot arms to determine the angle of rotation.', 4, 0, 'Navigation Modules', False),
('DS1307 RTC', 'If the Raspberry Pi is connected to the Internet, it can request the exact time. This could be a problem in applications where no (permanent) Internet connection is given, but the date and the exact time is important (car PC, weather station, etc.).', 2, 230, 'Navigation Modules', True),
('Si4703 Radio Receiver', 'The Si470x module offers the option to upgrade the Pi to a radio receiver, which can be very interesting in Car PCs or Raspberry Pi Jukeboxes. As with conventional radios, the frequency and certain options can be adjusted (via software). If that is not enough, you can also use your Pi as a radio station.', 10, 34, 'Radio Controlled Outlets', True),
('Bluetooth Adapter', 'The Raspberry Pi has not always had an integrated Bluetooth module. Before the model 3 was published, neither Bluetooth nor WiFi modules were onboard. The inexpensive Zero model also comes without a Bluetooth adapter. Since almost every mobile phone supports this communication method as standard, it is so easy to exchange pictures and other files between the smartphone and Raspberry Pi.', 7, 78, 'Radio Controlled Outlets', True),
('GSM Surfstick', 'The Raspberry Pi is used in many outdoors projects, e.g. as a weather station or for monitoring certain things. However, even if no (or only a weak) WIFI signal is available, many functions are restricted. If you still want to have access to the Pi and also receive the data of such an outside project, an Internet connection is necessary. The mobile surfsticks, which are also often available as gifts for data rate contracts, can be useful.', 30, 20, 'Radio Controlled Outlets', True),
('Infrared Diodes', 'Most remote controls use infrared LEDs to transmit signals. These codes can be read and stored easily with an infrared receiver. With the program LIRC, it is also possible to send those codes with an IR transmitter diode. For example, a TV can be controlled with the Raspberry Pi.', 3, 2, 'Radio Controlled Outlets', True),
('Laser Module', 'Although standard laser modules do not have great functionality (can be switched on and off), they are used in various interesting projects. Thus, for example, there are projects of distance measuring devices, which are using a camera and a laser module. The laser is switched on and off very quickly and pictures are recorded. The distance can then be calculated by means of the beam set.', 2, 20, 'Radio Controlled Outlets', True),
('Servo Motors', 'Unlike ordinary motors, servo motors can be individually controlled. Only the indication of the angle of rotation for moving the motor is necessary. PWM (pulse width modulation) signals are sent to the motor. The Raspberry Pi can use this method of transmission. Using the Python GPIO library or WiringPi is particularly easy.', 3, 0, 'Motors', False),
('28BYJ-48 Stepper Motor', 'Step motors are motors that can go a certain number of steps in one revolution. Two electromagnets are built in, which move the axis through different poles. How the polarity looks like is written in the data sheet of the motor.', 3, 8, 'Motors', True),
('PCA9685 Servo Board', 'Using PWM, servos can be controlled directly from the Raspberry Pi. However, as soon as you want to control several servo motors, either the GPIOs can become scarce, or you need more power. The PCA9685 servo driver board is ideally suited for this purpose because you can control up to 16 motors per board via I2C. And not enough. It is even possible to connect several boards one after the other. In addition, an external power supply can be easily connected.', 5, 23, 'Motors', True),
('ULN2003', 'Those 28BYJ-48 stepping motors are often supplied with a driver board. The supplied board usually has a ULN2003 IC, which holds the voltage for the 5V motor, but can be controlled with 3.3V. This is important because the GPIOs are protected and no transistor or relay is needed.', 3, 12, 'Motors', True),
('L293D', 'An alternative driver IC is the L293D. The advantage of this module, compared to the ULN2003, is that it can also be used with higher voltages than 5V. Because many alternative stepping motors (e.g., fewer steps for faster rotation or higher pulling force) require more than 5V, they must be powered by an external current source. The L293 IC is ideal for controlling these motors. By the way, it is even possible to control two motors simultaneously (individually).', 2, 90, 'Motors', True),
('A4988', 'This IC is another way to control step motors. It is especially designed for motors in 3D printers and can withstand voltages of 8V to 35V with a current of one amp. Since it can get hot very quickly, a cooling sink is included on the chip of the breakout board.', 6, 98, 'Motors', True),
('Joystick', 'One of these analog modules is a 2-axis joystick. Two potentiometers (see below) for X and Y axes are installed, which allow more or less voltage to pass through the movement. If one converts the analog value into a digital, one gets numbers between 0 (no voltage) and 1023 (full voltage). In the center, a digital value of approx. 512 is returned on both axes.', 6, 4, 'Analogous Raspberry Pi Sensors', True);