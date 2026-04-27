# E-Commerce Microservices Platform

Sistema de comercio electrónico desarrollado con arquitectura de microservicios usando Spring Boot, Spring Cloud y base de datos MySQL/PostgreSQL.

## 🏗️ Arquitectura del Sistema

```
┌─────────────────────┐    ┌─────────────────┐
│   Frontend Web      │    │   API Gateway   │
│   (Templates)       │────│   Port: 8080    │
└─────────────────────┘    └─────────────────┘
                                    │
                            ┌─────────────────┐
                            │ Service Registry│
                            │ Eureka (8761)   │
                            └─────────────────┘
                                    │
        ┌───────────────┬───────────┼───────────┬───────────────┐
        │               │           │           │               │
┌───────▼──┐   ┌────────▼──┐ ┌──────▼───┐ ┌────▼──────┐ ┌──────▼────┐
│User      │   │Product    │ │Order     │ │Frontend   │ │Admin      │
│Service   │   │Service    │ │Service   │ │Interface  │ │Panel      │
│(8081)    │   │(8082)     │ │(8083)    │ │           │ │           │
└─────┬────┘   └─────┬─────┘ └────┬─────┘ └───────────┘ └───────────┘
      │              │            │
┌─────▼────┐  ┌──────▼─────┐ ┌────▼──────┐
│MySQL     │  │MySQL       │ │PostgreSQL │
│db_usuarios│  │db_productos│ │db_ordenes │
│(3307)    │  │(3308)      │ │(5432)     │
└──────────┘  └────────────┘ └───────────┘
```

## 🚀 Servicios

### 1. **Eureka Server** - `localhost:8761`
- Registro y descubrimiento de servicios
- Panel de administración de microservicios
- Spring Boot 3.2.5

### 2. **API Gateway** - `localhost:8080`
- Gateway principal y frontend web
- Autenticación y autorización
- Templates HTML con Bootstrap 5.3.2
- Base de datos MySQL (demo_db)

### 3. **User Service** - `localhost:8081`
- Gestión de usuarios y autenticación
- Registro, login, roles (USER/ADMIN)
- Base de datos MySQL (db_usuarios)

### 4. **Product Service** - `localhost:8082`
- Catálogo de productos
- CRUD productos, gestión de stock
- Base de datos MySQL (db_productos)

### 5. **Order Service** - `localhost:8083`
- Gestión de pedidos
- Estados: PENDIENTE, PROCESANDO, ENVIADO, ENTREGADO, CANCELADO
- Base de datos PostgreSQL (db_ordenes)

## 🛠️ Tecnologías

- **Backend**: Spring Boot 3.2.5, Spring Cloud, Spring Security
- **Frontend**: HTML5, Bootstrap 5.3.2, JavaScript (Vanilla)
- **Bases de datos**: MySQL 8, PostgreSQL 15
- **Comunicación**: OpenFeign, REST APIs
- **Contenedores**: Docker, Docker Compose

## 📋 Prerrequisitos

### Para desarrollo local:
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- PostgreSQL 15+

### Para Docker:
- Docker
- Docker Compose

## 🚀 Ejecución con Docker (Recomendado)

1. **Clonar el repositorio**:
   ```bash
   git clone <repository-url>
   cd E-poli2026
   ```

2. **Construir y ejecutar con Docker Compose**:
   ```bash
   # Construir los servicios
   docker-compose build

   # Ejecutar todos los servicios
   docker-compose up -d

   # Ver logs
   docker-compose logs -f
   ```

3. **Acceder a la aplicación**:
   - **Frontend**: http://localhost:8080
   - **Eureka Dashboard**: http://localhost:8761
   - **API Gateway**: http://localhost:8080/api/

4. **Detener los servicios**:
   ```bash
   docker-compose down
   ```

## 🔧 Ejecución Local (Desarrollo)

### 1. Configurar Bases de Datos

**MySQL** (usuarios, productos, gateway):
```sql
CREATE DATABASE db_usuarios;
CREATE DATABASE db_productos; 
CREATE DATABASE demo_db;
```

**PostgreSQL** (órdenes):
```sql
CREATE DATABASE db_ordenes;
```

### 2. Orden de Ejecución

1. **Eureka Server**:
   ```bash
   cd Eureka-server/EurekaServer
   mvn spring-boot:run
   ```

2. **User Service**:
   ```bash
   cd User-Service
   mvn spring-boot:run
   ```

3. **Product Service**:
   ```bash
   cd Product-Service
   mvn spring-boot:run
   ```

4. **Order Service**:
   ```bash
   cd Order-Service
   mvn spring-boot:run
   ```

5. **API Gateway**:
   ```bash
   cd Apirestfull/Apirestfull
   mvn spring-boot:run
   ```

## 📚 API Endpoints

### **Productos** (`/api/productos`)
- `GET /` - Listar productos
- `POST /` - Crear producto
- `GET /{id}` - Obtener producto
- `PUT /{id}` - Actualizar producto
- `DELETE /{id}` - Eliminar producto
- `POST /reduce-stock` - Reducir stock
- `POST /compra` - Procesar compra

### **Usuarios** (`/api/usuarios`)
- `POST /registro` - Registrar usuario
- `POST /login` - Iniciar sesión
- `GET /` - Listar usuarios (Admin)
- `GET /{id}` - Obtener usuario
- `PUT /{id}` - Actualizar usuario
- `DELETE /{id}` - Eliminar usuario

### **Órdenes** (`/api/ordenes`)
- `GET /` - Listar órdenes
- `POST /` - Crear orden
- `GET /{id}` - Obtener orden
- `GET /usuario/{id}` - Órdenes por usuario
- `PUT /{id}/estado` - Actualizar estado

## 👥 Usuarios por Defecto

| Usuario | Contraseña | Rol   |
|---------|-----------|-------|
| admin   | admin123  | ADMIN |
| user    | user123   | USER  |

## 🖥️ Funcionalidades Web

### **Usuario Regular**:
- Registro e inicio de sesión
- Navegación de productos por categorías
- Carrito de compras (localStorage)
- Proceso de checkout
- Historial de pedidos
- Perfil de usuario

### **Administrador**:
- Dashboard con estadísticas
- Gestión completa de productos
- Gestión de órdenes y estados
- Gestión de usuarios
- Reportes y análisis con gráficos
- Exportación de datos

## 🗄️ Estructura de Base de Datos

### **Usuarios** (MySQL - db_usuarios)
```sql
usuarios: id, username, email, password, rol, created_at
```

### **Productos** (MySQL - db_productos)  
```sql
productos: id, nombre, descripcion, precio, stock, imagen, categoria
```

### **Órdenes** (PostgreSQL - db_ordenes)
```sql
orders: id, producto_id, usuario_id, cantidad, estado, fecha, total
```

### **Gateway** (MySQL - demo_db)
```sql
usuarios: id, username, email, password, rol
productos: id, nombre, descripcion, precio, stock, imagen, categoria
carrito_items: id, usuario_id, producto_id, cantidad
```

## 🔒 Seguridad

- **Autenticación**: Spring Security con formularios
- **Autorización**: Roles USER/ADMIN
- **Contraseñas**: Encriptadas con BCrypt
- **Sesiones**: Gestión automática por Spring Security

## 🧪 Testing

```bash
# Ejecutar tests en cada servicio
mvn test
```

## 📝 Logs

Los logs se pueden consultar:
- **Docker**: `docker-compose logs [service-name]`
- **Local**: En consola de cada servicio

## 🐛 Troubleshooting

### Problemas comunes:

1. **Error de conexión a BD**: Verificar que las bases de datos estén corriendo
2. **Servicios no se registran**: Esperar a que Eureka esté completamente iniciado
3. **Puerto ocupado**: Verificar que los puertos estén libres
4. **Error de dependencias**: Ejecutar `mvn clean install` en cada servicio

### Verificar estado de servicios:
```bash
# Ver servicios registrados
curl http://localhost:8761/eureka/apps

# Healthcheck de servicios
curl http://localhost:8080/actuator/health
curl http://localhost:8081/actuator/health
curl http://localhost:8082/actuator/health
curl http://localhost:8083/actuator/health
```

## 📈 Próximas Mejoras

- [ ] Implementación de caching con Redis
- [ ] Monitoreo con Spring Boot Actuator + Micrometer
- [ ] CI/CD con GitHub Actions
- [ ] Testing automatizado con TestContainers
- [ ] Documentación API con Swagger/OpenAPI
- [ ] Implementación de Circuit Breaker
- [ ] Message Queue con RabbitMQ

## 👨‍💻 Desarrollo

Para contribuir al proyecto:

1. Fork del repositorio
2. Crear branch feature: `git checkout -b feature/nueva-funcionalidad`
3. Commit cambios: `git commit -m 'Agregar nueva funcionalidad'`
4. Push branch: `git push origin feature/nueva-funcionalidad`
5. Crear Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

---

**Desarrollado con ❤️ usando Spring Boot y microservicios**