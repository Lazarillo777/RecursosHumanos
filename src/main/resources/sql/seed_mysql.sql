-- MySQL seed data complementary to schema_mysql.sql
USE `RHIUdigital`;

INSERT IGNORE INTO `university`(`name`,`country`,`city`) VALUES
('Universidad Nacional de Colombia','Colombia','Bogotá'),
('Universidad de Antioquia','Colombia','Medellín'),
('Universidad de los Andes','Colombia','Bogotá'),
('Pontificia Universidad Javeriana','Colombia','Bogotá'),
('Universidad del Valle','Colombia','Cali');

-- Employees
INSERT IGNORE INTO `employee`(`document_type_id`,`document_number`,`first_name`,`last_name`,`gender_id`,`marital_status_id`,`birth_date`,`address`,`city`,`email`,`hire_date`,`status`)
VALUES
((SELECT id FROM document_type WHERE code='CC'),'100200300','Juan','Pérez',(SELECT id FROM gender WHERE code='M'),(SELECT id FROM marital_status WHERE code='S'),'1985-04-12','Calle 123 #45-67','Bogotá','juan.perez@example.com','2015-06-01','ACTIVE'),
((SELECT id FROM document_type WHERE code='CC'),'100200301','María','Gómez',(SELECT id FROM gender WHERE code='F'),(SELECT id FROM marital_status WHERE code='M'),'1990-11-22','Carrera 45 #12-34','Medellín','maria.gomez@example.com','2018-03-15','ACTIVE'),
((SELECT id FROM document_type WHERE code='PAS'),'P1234567','Liam','Smith',(SELECT id FROM gender WHERE code='M'),(SELECT id FROM marital_status WHERE code='S'),'1982-07-03','Baker Street 221B','London','liam.smith@example.co.uk','2010-09-01','ACTIVE');

-- Phones
INSERT IGNORE INTO `phone`(`employee_id`,`phone_number`,`type`) VALUES
((SELECT id FROM employee WHERE document_number='100200300'),'571234567890','mobile'),
((SELECT id FROM employee WHERE document_number='100200301'),'574001112233','mobile');

-- Family members
INSERT IGNORE INTO `family_member`(`employee_id`,`family_role_id`,`first_name`,`last_name`,`gender_id`,`birth_date`,`document_type_id`,`document_number`,`phone`,`email`)
VALUES
((SELECT id FROM employee WHERE document_number='100200300'),(SELECT id FROM family_role WHERE code='SPO'),'Laura','Pérez',(SELECT id FROM gender WHERE code='F'),'1987-08-15',(SELECT id FROM document_type WHERE code='CC'),'100200303','5711122233','laura.perez@example.com');

-- Education records
INSERT IGNORE INTO `education`(`employee_id`,`university_id`,`level_id`,`title`,`field_of_study`,`year`,`notes`)
VALUES
((SELECT id FROM employee WHERE document_number='100200300'),(SELECT id FROM university WHERE name LIKE 'Universidad Nacional%'),(SELECT id FROM education_level WHERE code='PRF'),'Ingeniería de Sistemas','Ingeniería de Software',2008,'Título obtenido con honores');
