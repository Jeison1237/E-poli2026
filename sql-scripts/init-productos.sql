-- ========================================
-- Script de inicialización para db_productos
-- ========================================

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS db_productos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE db_productos;

-- Crear tabla productos
CREATE TABLE IF NOT EXISTS producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    imagen VARCHAR(500),
    categoria VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_categoria (categoria),
    INDEX idx_precio (precio),
    INDEX idx_stock (stock)
) ENGINE=InnoDB;

-- Insertar productos demo
INSERT INTO producto (nombre, descripcion, precio, stock, imagen, categoria) VALUES 
-- Electrónicos
('Smartphone Samsung Galaxy S23', 'Teléfono inteligente con pantalla AMOLED de 6.1 pulgadas, 128GB de almacenamiento y cámara de 50MP', 899.99, 15, 'https://images.samsung.com/is/image/samsung/p6pim/ar/2302/gallery/ar-galaxy-s23-s911-446267-sm-s911bzaaaro-534851975', 'Electrónicos'),
('Laptop HP Pavilion 15', 'Laptop con procesador Intel i5, 8GB RAM, 512GB SSD, pantalla Full HD de 15.6 pulgadas', 649.99, 8, 'https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c08140051.png', 'Electrónicos'),
('Auriculares Sony WH-1000XM4', 'Auriculares inalámbricos con cancelación de ruido activa, batería de 30 horas', 279.99, 25, 'https://www.sony.com/image/5d02da5df552836db894540b6a5b8d39?fmt=pjpeg&wid=330&bgcolor=FFFFFF&bgc=FFFFFF', 'Electrónicos'),
('Apple iPad Air', 'Tablet con chip M1, pantalla Liquid Retina de 10.9 pulgadas, 64GB WiFi', 549.99, 12, 'https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/ipad-air-select-wifi-blue-202203', 'Electrónicos'),
('TV Smart LG 55"', 'Smart TV LED 4K UHD de 55 pulgadas con WebOS y HDR', 699.99, 6, 'https://www.lg.com/content/dam/channel/wcms/us/images/tvs/55up7000pua_awm_ecare_350x262.jpg', 'Electrónicos'),

-- Ropa
('Camiseta Nike Dri-FIT', 'Camiseta deportiva de algodón con tecnología Dri-FIT para mayor comodidad', 24.99, 50, 'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/99486859-0ff3-46b4-949b-2d16af2ad421/camiseta-dri-fit-hombre-5F1Nn7.png', 'Ropa'),
('Jeans Levis 501', 'Jeans clásicos de corte regular, 100% algodón, color azul índigo', 89.99, 30, 'https://lsco.scene7.com/is/image/lsco/005010000-front-pdp-lse', 'Ropa'),
('Chaqueta Adidas', 'Chaqueta deportiva con capucha, material ligero y transpirable', 79.99, 20, 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/8ea578f6c07847fca2e0ac85011d7f1f_9366/Campera_Adicolor_Classics_3_Franjas_Negro_H06651_01_laydown.jpg', 'Ropa'),
('Vestido Zara Floral', 'Vestido de verano con estampado floral, manga corta, tela ligera', 45.99, 15, 'https://static.zara.net/photos///2023/V/0/1/p/1165/144/330/2/w/750/1165144330_6_1_1.jpg', 'Ropa'),
('Zapatillas Nike Air Max', 'Zapatillas deportivas con amortiguación Air Max, para running y uso casual', 119.99, 25, 'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/awjogtdnqxniqqk0q4qo/air-max-90-zapatillas-hombre-6n3vKB.png', 'Ropa'),

-- Hogar
('Aspiradora Dyson V15', 'Aspiradora inalámbrica con tecnología de detección láser, batería de 60 minutos', 649.99, 10, 'https://dyson-h.assetsadobe2.com/is/image/content/dam/dyson/products/vacuums/V15/overview/dyson-v15-detect-overview-hero-image-169.jpg', 'Hogar'),
('Cafetera Nespresso', 'Cafetera de cápsulas automática con espumador de leche integrado', 199.99, 18, 'https://www.nespresso.com/shared_res/agility/n-components/src/ncp/product-details/gallery/essenza-mini-white-c30-me-wh-ne_360x360.png', 'Hogar'),
('Sofá 3 Plazas IKEA', 'Sofá cómodo de 3 plazas con estructura de madera y tapizado en tela gris', 399.99, 5, 'https://www.ikea.com/us/en/images/products/ektorp-3-seat-sofa-with-chaise-lofallet-beige__0819242_pe774506_s5.jpg', 'Hogar'),
('Mesa de Comedor', 'Mesa de comedor extensible de madera maciza para 6-8 personas', 299.99, 8, 'https://images.thdstatic.com/productImages/2e8dd79e-74bb-42ae-b745-8b5c1f88f5c4/svn/brown-dining-tables-hdp1801-64_1000.jpg', 'Hogar'),
('Lámpara LED Philips', 'Lámpara de mesa LED regulable con control táctil, 3 niveles de intensidad', 49.99, 35, 'https://images.philips.com/is/image/PhilipsConsumer/71570_31_PO_3000K-RTP-global-001', 'Hogar'),

-- Libros
('Cien Años de Soledad', 'Novela de Gabriel García Márquez, obra maestra del realismo mágico', 15.99, 40, 'https://images.cdn3.buscalibre.com/fit-in/360x360/61/8d/618d227e8967274cd9589a549adff52d.jpg', 'Libros'),
('El Quijote de la Mancha', 'Obra clásica de Miguel de Cervantes, edición completa ilustrada', 22.99, 25, 'https://www.penguinlibros.com/img/portadas_libros/9788491051367.jpg', 'Libros'),
('Harry Potter - Colección', 'Colección completa de los 7 libros de Harry Potter por J.K. Rowling', 89.99, 12, 'https://images-na.ssl-images-amazon.com/images/P/0545162076.01.L.jpg', 'Libros'),
('Steve Jobs - Biografía', 'Biografía oficial de Steve Jobs por Walter Isaacson', 18.99, 20, 'https://images-na.ssl-images-amazon.com/images/P/1501127624.01.L.jpg', 'Libros'),
('Atomic Habits', 'Libro de desarrollo personal sobre la formación de hábitos por James Clear', 16.99, 30, 'https://images-na.ssl-images-amazon.com/images/P/0735211299.01.L.jpg', 'Libros'),

-- Deportes
('Bicicleta de Montaña', 'Bicicleta de montaña con suspensión delantera, frenos de disco, 21 velocidades', 459.99, 7, 'https://contents.mediadecathlon.com/p1485003/c5661f17ba5df4bf953b1e72e7ba0d7d/p1485003.jpg', 'Deportes'),
('Pelota de Fútbol Nike', 'Balón de fútbol profesional FIFA Quality Pro, para canchas de césped', 29.99, 60, 'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/d6cd1f2e-bc98-4de8-b72b-94d44d70c448/balon-de-futbol-premier-league-flight-oficial-LTnNSB.png', 'Deportes'),
('Raqueta de Tenis Wilson', 'Raqueta de tenis profesional con marco de grafito y cuerdas sintéticas', 149.99, 15, 'https://www.wilson.com/dw/image/v2/BBDV_PRD/on/demandware.static/-/Sites-wilson-master-catalog/default/dw4a8b3d7e/images/racquets/tennis/WR018211U2_Clash_100_v2_BLK-GLD_3D_RIGHT.png', 'Deportes'),
('Pesas Ajustables', 'Set de pesas ajustables de 5 a 25kg cada una, ideal para gimnasio en casa', 299.99, 12, 'https://cdn.shopify.com/s/files/1/0253/7355/6981/products/powerblock-sport-9-adjustable-dumbbells-8_600x600.jpg', 'Deportes'),
('Cinta de Correr', 'Cinta de correr eléctrica plegable con pantalla LCD y programas preestablecidos', 899.99, 4, 'https://images.thdstatic.com/productImages/6a0eb9c5-31c3-48dd-b3f2-9e4e4c4f8e8f/svn/black-sunny-health-fitness-treadmills-sf-t7515-64_1000.jpg', 'Deportes');

-- Mostrar productos creados por categoría
SELECT categoria, COUNT(*) as cantidad, AVG(precio) as precio_promedio 
FROM producto 
GROUP BY categoria 
ORDER BY categoria;

-- Mostrar todos los productos
SELECT id, nombre, precio, stock, categoria FROM producto ORDER BY categoria, precio;

COMMIT;