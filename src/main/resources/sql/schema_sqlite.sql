-- SQLite schema for local testing: Recurso Humano (IUDIgital)

PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS document_type (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS gender (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS marital_status (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS family_role (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS education_level (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS university (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    country TEXT,
    city TEXT
);

CREATE TABLE IF NOT EXISTS employee (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    document_type_id INTEGER NOT NULL,
    document_number TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    gender_id INTEGER,
    marital_status_id INTEGER,
    birth_date TEXT,
    address TEXT,
    city TEXT,
    email TEXT,
    hire_date TEXT,
    status TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    FOREIGN KEY(document_type_id) REFERENCES document_type(id),
    FOREIGN KEY(gender_id) REFERENCES gender(id),
    FOREIGN KEY(marital_status_id) REFERENCES marital_status(id)
);

CREATE TABLE IF NOT EXISTS phone (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    employee_id INTEGER NOT NULL,
    phone_number TEXT NOT NULL,
    type TEXT,
    FOREIGN KEY(employee_id) REFERENCES employee(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS family_member (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    employee_id INTEGER NOT NULL,
    family_role_id INTEGER NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    gender_id INTEGER,
    birth_date TEXT,
    document_type_id INTEGER,
    document_number TEXT,
    phone TEXT,
    email TEXT,
    FOREIGN KEY(employee_id) REFERENCES employee(id) ON DELETE CASCADE,
    FOREIGN KEY(family_role_id) REFERENCES family_role(id),
    FOREIGN KEY(gender_id) REFERENCES gender(id),
    FOREIGN KEY(document_type_id) REFERENCES document_type(id)
);

CREATE TABLE IF NOT EXISTS education (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    employee_id INTEGER NOT NULL,
    university_id INTEGER,
    level_id INTEGER,
    title TEXT,
    field_of_study TEXT,
    year INTEGER,
    notes TEXT,
    FOREIGN KEY(employee_id) REFERENCES employee(id) ON DELETE CASCADE,
    FOREIGN KEY(university_id) REFERENCES university(id),
    FOREIGN KEY(level_id) REFERENCES education_level(id)
);

-- Lookup data
-- Lookup data
INSERT OR IGNORE INTO document_type(code,name) VALUES
('CC','Cédula de ciudadanía'),
('TI','Tarjeta de identidad'),
('CE','Cédula de extranjería'),
('PAS','Pasaporte'),
('DNI','Documento Nacional de Identidad'),
('NIE','Número de Identidad Extranjero'),
('SSN','Número de Seguro Social (SSN)'),
('NID','National ID'),
('ID','Identificación (otro)');

INSERT OR IGNORE INTO gender(code,name) VALUES
('M','Masculino'),
('F','Femenino'),
('NB','No binario'),
('X','No especificado/Prefiero no decir'),
('O','Otro');

INSERT OR IGNORE INTO marital_status(code,name) VALUES
('S','Soltero(a)'),
('M','Casado(a)'),
('P','Pareja de hecho/Unión libre'),
('D','Divorciado(a)'),
('SEP','Separado(a)'),
('W','Viudo(a)'),
('U','No especificado');

INSERT OR IGNORE INTO family_role(code,name) VALUES
('SPO','Cónyuge/pareja'),
('CHD','Hijo(a)'),
('PAR','Padre/Madre'),
('SIB','Hermano(a)'),
('GUA','Tutor/Guardián'),
('DEP','Dependiente'),
('OTH','Otro');

INSERT OR IGNORE INTO education_level(code,name) VALUES
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

-- Compatibilidad: tabla simple `users` usada por MainController ejemplo
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

INSERT OR IGNORE INTO users(id,name) VALUES (1,'Admin');
