-- ========================================
-- Script de inicialización para db_ordenes (PostgreSQL)
-- ========================================

-- Crear la base de datos si no existe
-- Este comando debe ejecutarse como superusuario
-- CREATE DATABASE db_ordenes WITH ENCODING='UTF8' LC_COLLATE='en_US.UTF-8' LC_CTYPE='en_US.UTF-8';

-- Conectar a la base de datos
\c db_ordenes;

-- Crear tabla orders
CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    producto_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    cantidad INTEGER NOT NULL CHECK (cantidad > 0),
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    total DECIMAL(10,2),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear índices para mejorar el rendimiento
CREATE INDEX IF NOT EXISTS idx_orders_usuario_id ON orders(usuario_id);
CREATE INDEX IF NOT EXISTS idx_orders_producto_id ON orders(producto_id);
CREATE INDEX IF NOT EXISTS idx_orders_estado ON orders(estado);
CREATE INDEX IF NOT EXISTS idx_orders_fecha ON orders(fecha);

-- Insertar órdenes de ejemplo
INSERT INTO orders (producto_id, usuario_id, cantidad, estado, total, fecha) VALUES 
-- Órdenes del usuario admin (id=1)
(1, 1, 1, 'ENTREGADO', 899.99, '2024-01-15 10:30:00'),
(5, 1, 1, 'ENTREGADO', 699.99, '2024-01-20 14:15:00'),

-- Órdenes del usuario regular (id=2)
(6, 2, 2, 'ENTREGADO', 49.98, '2024-01-25 09:45:00'),
(11, 2, 1, 'ENVIADO', 649.99, '2024-02-01 16:20:00'),
(15, 2, 3, 'PENDIENTE', 47.97, '2024-02-10 11:30:00'),

-- Órdenes de maria (id=3)
(3, 3, 1, 'ENTREGADO', 279.99, '2024-01-30 13:45:00'),
(8, 3, 1, 'PROCESANDO', 45.99, '2024-02-05 10:15:00'),

-- Órdenes de juan (id=4)
(2, 4, 1, 'ENTREGADO', 649.99, '2024-01-28 15:30:00'),
(10, 4, 1, 'ENVIADO', 119.99, '2024-02-08 12:45:00'),

-- Órdenes de ana (id=5)
(13, 5, 1, 'ENTREGADO', 399.99, '2024-01-22 14:20:00'),
(7, 5, 1, 'PROCESANDO', 89.99, '2024-02-12 16:10:00'),
(20, 5, 2, 'PENDIENTE', 59.98, '2024-02-15 09:30:00');

-- Mostrar resumen de órdenes por estado
SELECT 
    estado,
    COUNT(*) as cantidad_ordenes,
    SUM(total) as total_ventas,
    AVG(total) as promedio_orden
FROM orders 
GROUP BY estado 
ORDER BY 
    CASE estado 
        WHEN 'PENDIENTE' THEN 1
        WHEN 'PROCESANDO' THEN 2  
        WHEN 'ENVIADO' THEN 3
        WHEN 'ENTREGADO' THEN 4
        WHEN 'CANCELADO' THEN 5
    END;

-- Mostrar órdenes recientes
SELECT 
    id,
    usuario_id,
    producto_id,
    cantidad,
    total,
    estado,
    fecha
FROM orders 
ORDER BY fecha DESC 
LIMIT 10;

COMMIT;