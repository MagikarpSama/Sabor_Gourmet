# Sabor Gourmet - Sistema de Reservas

Este proyecto es una aplicación web para la gestión de reservas de mesas en el restaurante "Sabor Gourmet". Desarrollada con Spring Boot, Spring MVC, Spring Data JPA, Thymeleaf y H2.

## Requisitos previos
- Java JDK 17 (recomendado Temurin)
- Maven 3.6+

## Instalación y ejecución

1. Clona o descarga este repositorio.
2. Abre una terminal en la carpeta raíz del proyecto (donde está el archivo `pom.xml`).
3. Ejecuta el siguiente comando para iniciar la aplicación:

```powershell
mvn spring-boot:run
```

4. Cuando veas el mensaje de que el servidor está corriendo, abre tu navegador y accede a:
- [http://localhost:8080/reservas](http://localhost:8080/reservas) — Gestión de reservas
- [http://localhost:8080/clientes](http://localhost:8080/clientes) — Gestión de clientes
- [http://localhost:8080/mesas](http://localhost:8080/mesas) — Gestión de mesas

## Estructura principal del proyecto
- `src/main/java/com/saborgourmet/` — Código fuente Java
- `src/main/resources/templates/` — Vistas Thymeleaf
- `src/main/resources/static/` — Archivos estáticos (CSS, imágenes)
- `pom.xml` — Configuración de dependencias y plugins Maven

## Notas
- La base de datos H2 es en memoria y se reinicia cada vez que se apaga la aplicación.
- Puedes modificar el puerto o la configuración en `src/main/resources/application.properties` si lo necesitas.

## Contacto
Para dudas o soporte, contacta a tu profesor o abre un issue en el repositorio.
