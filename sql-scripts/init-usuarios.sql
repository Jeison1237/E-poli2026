-- ========================================
-- Script de inicialización para db_usuarios
-- ========================================

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS db_usuarios CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE db_usuarios;

-- Crear tabla usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_rol (rol)
) ENGINE=InnoDB;

-- Insertar usuarios demo
-- Contraseña 'admin123' encriptada con BCrypt
INSERT INTO usuarios (username, email, password, rol) VALUES 
('admin', 'admin@ecommerce.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRdvabO6Ewn976pgyKpls4x4tJu', 'ADMIN'),
('user', 'user@ecommerce.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRdvabO6Ewn976pgyKpls4x4tJu', 'USER'),
('maria', 'maria@email.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRdvabO6Ewn976pgyKpls4x4tJu', 'USER'),
('juan', 'juan@email.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRdvabO6Ewn976pgyKpls4x4tJu', 'USER'),
('ana', 'ana@email.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRdvabO6Ewn976pgyKpls4x4tJu', 'USER')
ON DUPLICATE KEY UPDATE username=VALUES(username);

-- Mostrar usuarios creados
SELECT username, email, rol, created_at FROM usuarios;

COMMIT;