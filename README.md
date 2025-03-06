Proyecto: API REST con Spring Boot y JWT

Descripción

Este proyecto es una API REST construida con Spring Boot que gestiona tareas y usuarios. Implementa autenticación con JWT (JSON Web Token) para proteger los endpoints.

Tecnologías utilizadas

Java 17

Spring Boot 3

Spring Security

JWT (JSON Web Token)

Spring Data JPA (para acceso a base de datos)

H2 / MySQL (según configuración)

Swagger OpenAPI (para documentación de la API)

Instalación y configuración

Clonar el repositorio:

git clone https://github.com/tu-usuario/tu-repo.git
cd tu-repo

Configurar la base de datos:
Modifica el archivo application.properties o application.yml para ajustar la conexión a tu base de datos.

Construir el proyecto:

mvn clean install

Ejecutar la aplicación:

mvn spring-boot:run

Endpoints principales

1. Autenticación y Registro de Usuario

Método

Endpoint

Descripción

POST

/auth/register

Registra un nuevo usuario

POST

/auth/login

Inicia sesión y devuelve un token JWT

Ejemplo de Registro (/auth/register)

Request:

{
  "username": "usuario123",
  "password": "password123"
}

Response:

{
  "message": "Usuario registrado con éxito",
  "user": {
    "id": 1,
    "username": "usuario123"
  }
}

Ejemplo de Login (/auth/login)

Request:

{
  "username": "usuario123",
  "password": "password123"
}

Response:

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."
}

2. Gestor de Tareas (TaskController)

Estos endpoints requieren autenticación con JWT.

Método

Endpoint

Descripción

GET

/task

Obtiene todas las tareas

POST

/task

Crea una nueva tarea

GET

/task/{id}

Obtiene una tarea por ID

PUT

/task/{id}

Actualiza una tarea

DELETE

/task/{id}

Elimina una tarea

Ejemplo de Creación de Tarea (/task)

Request:

{
  "title": "Nueva tarea",
  "description": "Esta es una tarea de prueba"
}

Response:

{
  "message": "Tarea creada con éxito",
  "data": {
    "id": 1,
    "title": "Nueva tarea",
    "description": "Esta es una tarea de prueba"
  }
}

Seguridad y Validación del Token

El sistema usa JWT para validar cada petición protegida.

Se debe enviar el token en la cabecera Authorization de cada petición:

Authorization: Bearer <TOKEN>

Si el token es inválido o expira, la API devuelve 403 Forbidden.

Documentación de la API (Swagger)

Una vez corriendo la aplicación, puedes ver la documentación interactiva en:

http://localhost:8080/swagger-ui.html

http://localhost:8080/v3/api-docs

Contribuciones

Si deseas contribuir, puedes hacer un fork del repositorio y enviar un pull request con tus mejoras.

Licencia

Este proyecto está bajo la licencia MIT.

Si necesitas agregar más detalles o modificar algo, dímelo. 🚀

