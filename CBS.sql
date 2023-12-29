create database CBS;

use CBS;

create table patient(
patient_id INT,
first_name VARCHAR(50),
last_name VARCHAR(50),
phone_num VARCHAR(50)
);

CREATE TABLE appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    time VARCHAR(10),
    patient_name VARCHAR(255),
    notes TEXT
);


create table clinic(
clinic_id INT,
clinic_name VARCHAR(100),
clinic_location VARCHAR(300),
contact_details VARCHAR(30)
);

insert into clinic VALUES (1111, "OzU Clinic", "Özyeğin University, Çekmeköy Campus Nişantepe District, Orman Street, 34794 Çekmeköy - İSTANBUL", null);




