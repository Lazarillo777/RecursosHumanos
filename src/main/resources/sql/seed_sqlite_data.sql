-- Seed data for SQLite (local testing)
PRAGMA foreign_keys = ON;

-- Universities
INSERT OR IGNORE INTO university(name,country,city) VALUES
('Universidad Nacional de Colombia','Colombia','Bogotá'),
('Universidad de Antioquia','Colombia','Medellín'),
('Universidad de los Andes','Colombia','Bogotá'),
('Pontificia Universidad Javeriana','Colombia','Bogotá'),
('Universidad del Valle','Colombia','Cali');

-- Employees (use lookup subqueries to get ids)
INSERT OR IGNORE INTO employee(document_type_id,document_number,first_name,last_name,gender_id,marital_status_id,birth_date,address,city,email,hire_date,status)
VALUES
((SELECT id FROM document_type WHERE code='CC'),'100200300','Juan','Pérez',(SELECT id FROM gender WHERE code='M'),(SELECT id FROM marital_status WHERE code='S'),'1985-04-12','Calle 123 #45-67','Bogotá','juan.perez@example.com','2015-06-01','ACTIVE'),
((SELECT id FROM document_type WHERE code='CC'),'100200301','María','Gómez',(SELECT id FROM gender WHERE code='F'),(SELECT id FROM marital_status WHERE code='M'),'1990-11-22','Carrera 45 #12-34','Medellín','maria.gomez@example.com','2018-03-15','ACTIVE'),
((SELECT id FROM document_type WHERE code='PAS'),'P1234567','Liam','Smith',(SELECT id FROM gender WHERE code='M'),(SELECT id FROM marital_status WHERE code='S'),'1982-07-03','Baker Street 221B','London','liam.smith@example.co.uk','2010-09-01','ACTIVE'),
((SELECT id FROM document_type WHERE code='DNI'),'A9876543','Ana','Martínez',(SELECT id FROM gender WHERE code='F'),(SELECT id FROM marital_status WHERE code='P'),'1992-02-10','Av. Siempre Viva 742','Quito','ana.martinez@example.com','2020-01-20','ACTIVE'),
((SELECT id FROM document_type WHERE code='CC'),'100200302','Carlos','Rodríguez',(SELECT id FROM gender WHERE code='M'),(SELECT id FROM marital_status WHERE code='D'),'1978-05-30','Calle 8 #7-10','Cali','carlos.rodriguez@example.com','2005-05-10','INACTIVE');

-- Phones
INSERT OR IGNORE INTO phone(employee_id,phone_number,type) VALUES
((SELECT id FROM employee WHERE document_number='100200300'),'571234567890','mobile'),
((SELECT id FROM employee WHERE document_number='100200301'),'574001112233','mobile'),
((SELECT id FROM employee WHERE document_number='100200302'),'572223334445','landline');

-- Family members
INSERT OR IGNORE INTO family_member(employee_id,family_role_id,first_name,last_name,gender_id,birth_date,document_type_id,document_number,phone,email)
VALUES
((SELECT id FROM employee WHERE document_number='100200300'),(SELECT id FROM family_role WHERE code='SPO'),'Laura','Pérez',(SELECT id FROM gender WHERE code='F'),'1987-08-15',(SELECT id FROM document_type WHERE code='CC'),'100200303','5711122233','laura.perez@example.com'),
((SELECT id FROM employee WHERE document_number='100200301'),(SELECT id FROM family_role WHERE code='CHD'),'Sofía','Gómez',(SELECT id FROM gender WHERE code='F'),'2012-06-01',NULL,NULL,NULL,NULL);

-- Education records
INSERT OR IGNORE INTO education(employee_id,university_id,level_id,title,field_of_study,year,notes)
VALUES
((SELECT id FROM employee WHERE document_number='100200300'),(SELECT id FROM university WHERE name LIKE 'Universidad Nacional%'),(SELECT id FROM education_level WHERE code='PRF'),'Ingeniería de Sistemas','Ingeniería de Software',2008,'Título obtenido con honores'),
((SELECT id FROM employee WHERE document_number='100200301'),(SELECT id FROM university WHERE name LIKE 'Universidad de Antioquia%'),(SELECT id FROM education_level WHERE code='MA'),'Maestría en Administración','Gestión',2016,'');

-- Ensure admin user exists (compatibility)
INSERT OR IGNORE INTO users(id,name) VALUES (1,'Admin');

-- End of seed
