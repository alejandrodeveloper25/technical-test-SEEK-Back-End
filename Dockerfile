FROM openjdk:17-jdk-slim

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el JAR de la aplicación al contenedor
COPY target/tecnical-test-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
