
# Proyecto: API REST con Spring Boot y JWT (SEEK)

A brief description of what this project does and who it's for


## 🚀 Tecnologías Utilizadas
- **Spring Boot** (Framework principal)
- **Spring Security** (Autenticación y autorización con JWT)
- **SQLite** (Base de datos)
- **Swagger/OpenAPI** (Documentación de la API)
- **Lombok** (Reducción de código boilerplate)
- **BCrypt** (Encriptación de contraseñas)


## ⚙️ Instalación y Configuración

**1. Clonar el repositorio**

```bash
 git clone https://github.com/alejandrodeveloper25/technical-test-SEEK-Back-End
cd tu-repo
```

**2. Configurar la base de datos**

Edita el archivo application.properties con la configuración de la base de datos:

```bash
 spring.datasource.url=jdbc:sqlite:task.db
 spring.datasource.driver-class-name=org.sqlite.JDBC
 spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
 spring.jpa.hibernate.ddl-auto=update
```


## 📌 Endpoints Principales

#### 🔐 Autenticación

### Registro usuario

```http
POST /auth/register
```

Registra un nuevo usuario en el sistema.

**Request Body:**
```json
{
  "username": "usuario",
  "password": "contraseña"
}
```
**Response:**
```json
{
  "message": "Usuario registrado con éxito"
}
```
### Inicio se sesión

```http
POST /auth/login
```

**Request Body:**
```json
{
  "username": "usuario",
  "password": "contraseña"
}
```
**Response:**
```json
{
  "status": 200,
  "message": "Autenticación exitosa",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbGVqYW5kcm9BZG1pbiIsImlhdCI6MTc0MTI4ODE2MCwiZXhwIjoxNzQxMzc0NTYwfQ.pdW6-myME3t7Vb1j7KH7UR-gGxZTSK3IEoZrvl-cFZw"
  }
}
```

#### ✅ Gestión de Tareas (/task)

Este controlador permite la gestión de tareas en el sistema. Requiere autenticación con un token JWT en el encabezado
```bash
Authorization: Bearer <TOKEN>
```

### Traer todas las tareas

```http
GET /task
```

### Crear una Tarea

```http
POST /task
```
**Request Body:**
```json
{
  "title": "Nueva tarea",
  "description": "Descripción de la tarea"
}
```

### buscar tarea por Id

```http
GET /task/{id}
```

### Actualizar Tarea existente

```http
PUT /task/{id}
```
**Request Body:**
```json
{
  "id": 1,
  "title": "nuevo titulo",
  "description": "nueva descripcion"
}
```

### Eliminar Tarea existente
```http
DELETE /task/{id}
```


## 🔒 Seguridad
* Los endpoints protegidos requieren autenticación mediante JWT.

* Se utiliza BCrypt para almacenar contraseñas de forma segura.

* Se recomienda configurar variables de entorno para manejar claves secretas y credenciales.
## Authors

- [@alejandrodeveloper25](https://github.com/alejandrodeveloper25)

