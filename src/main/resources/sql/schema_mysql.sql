-- MySQL schema (copied/adapted from workspace HRIUDigital.sql)
-- Creates database and tables for RHIUdigital

CREATE DATABASE IF NOT EXISTS `RHIUdigital` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
USE `RHIUdigital`;

-- Tablas auxiliares (lookup)
CREATE TABLE IF NOT EXISTS `document_type` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(10) NOT NULL UNIQUE,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `gender` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(10) NOT NULL UNIQUE,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `marital_status` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(10) NOT NULL UNIQUE,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `family_role` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(10) NOT NULL UNIQUE,
    `name` VARCHAR(80) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `education_level` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(10) NOT NULL UNIQUE,
    `name` VARCHAR(80) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `university` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(250) NOT NULL,
    `country` VARCHAR(80),
    `city` VARCHAR(80),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla principal: funcionarios (employees)
CREATE TABLE IF NOT EXISTS `employee` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `document_type_id` INT NOT NULL,
    `document_number` VARCHAR(50) NOT NULL,
    `first_name` VARCHAR(120) NOT NULL,
    `last_name` VARCHAR(120) NOT NULL,
    `gender_id` INT,
    `marital_status_id` INT,
    `birth_date` DATE,
    `address` VARCHAR(250),
    `city` VARCHAR(120),
    `email` VARCHAR(150),
    `hire_date` DATE,
    `status` VARCHAR(50),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_employee_document_type` FOREIGN KEY (`document_type_id`) REFERENCES `document_type`(`id`),
    CONSTRAINT `fk_employee_gender` FOREIGN KEY (`gender_id`) REFERENCES `gender`(`id`),
    CONSTRAINT `fk_employee_marital_status` FOREIGN KEY (`marital_status_id`) REFERENCES `marital_status`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Telefonos (múltiples por funcionario)
CREATE TABLE IF NOT EXISTS `phone` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `employee_id` INT NOT NULL,
    `phone_number` VARCHAR(50) NOT NULL,
    `type` VARCHAR(50),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_phone_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Grupo familiar
CREATE TABLE IF NOT EXISTS `family_member` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `employee_id` INT NOT NULL,
    `family_role_id` INT NOT NULL,
    `first_name` VARCHAR(120) NOT NULL,
    `last_name` VARCHAR(120) NOT NULL,
    `gender_id` INT,
    `birth_date` DATE,
    `document_type_id` INT,
    `document_number` VARCHAR(50),
    `phone` VARCHAR(50),
    `email` VARCHAR(150),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_family_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee`(`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_family_role` FOREIGN KEY (`family_role_id`) REFERENCES `family_role`(`id`),
    CONSTRAINT `fk_family_gender` FOREIGN KEY (`gender_id`) REFERENCES `gender`(`id`),
    CONSTRAINT `fk_family_doc_type` FOREIGN KEY (`document_type_id`) REFERENCES `document_type`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Formación académica
CREATE TABLE IF NOT EXISTS `education` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `employee_id` INT NOT NULL,
    `university_id` INT,
    `level_id` INT,
    `title` VARCHAR(250),
    `field_of_study` VARCHAR(200),
    `year` INT,
    `notes` TEXT,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_education_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee`(`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_education_university` FOREIGN KEY (`university_id`) REFERENCES `university`(`id`),
    CONSTRAINT `fk_education_level` FOREIGN KEY (`level_id`) REFERENCES `education_level`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Índices útiles
CREATE INDEX IF NOT EXISTS `idx_employee_document` ON `employee`(`document_number`);
CREATE INDEX IF NOT EXISTS `idx_family_employee` ON `family_member`(`employee_id`);

-- Datos iniciales (lookup)
INSERT IGNORE INTO `document_type`(`code`,`name`) VALUES
('CC','Cédula de ciudadanía'),
('TI','Tarjeta de identidad'),
('CE','Cédula de extranjería'),
('PAS','Pasaporte'),
('DNI','Documento Nacional de Identidad'),
('NIE','Número de Identidad Extranjero'),
('SSN','Número de Seguro Social (SSN)'),
('NID','National ID'),
('ID','Identificación (otro)');

INSERT IGNORE INTO `gender`(`code`,`name`) VALUES
('M','Masculino'),
('F','Femenino'),
('NB','No binario'),
('X','No especificado/Prefiero no decir'),
('O','Otro');

INSERT IGNORE INTO `marital_status`(`code`,`name`) VALUES
('S','Soltero(a)'),
('M','Casado(a)'),
('P','Pareja de hecho/Unión libre'),
('D','Divorciado(a)'),
('SEP','Separado(a)'),
('W','Viudo(a)'),
('U','No especificado');

INSERT IGNORE INTO `family_role`(`code`,`name`) VALUES
('SPO','Cónyuge/pareja'),
('CHD','Hijo(a)'),
('PAR','Padre/Madre'),
('SIB','Hermano(a)'),
('GUA','Tutor/Guardián'),
('DEP','Dependiente'),
('OTH','Otro');

INSERT IGNORE INTO `education_level`(`code`,`name`) VALUES
('PR','Primaria'),
('LS','Secundaria (básica)'),
('HS','Secundaria / Bachillerato'),
('TVET','Formación técnica (TVET)'),
('TS','Técnico'),
('TT','Tecnólogo'),
('AS','Técnico superior / Asociado'),
('PRF','Profesional (Pregrado)'),
('ESP','Especialización / Postgrado corto'),
('MA','Maestría'),
('PHD','Doctorado / PhD'),
('DIP','Diplomado'),
('CERT','Certificado profesional');
