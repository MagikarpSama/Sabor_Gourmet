Introducción
El proyecto "Sabor Gourmet" es una aplicación web diseñada para gestionar reservas y mesas en un restaurante. Este sistema permite a los administradores y clientes interactuar de manera eficiente con las funcionalidades del restaurante, como la creación de reservas, la gestión de mesas y la administración de usuarios.


Estructura del Proyecto
El proyecto está desarrollado en Java utilizando el framework Spring Boot. A continuación, se detalla la estructura principal del proyecto:

1. Backend:
Controladores (controller): Manejan las solicitudes HTTP y dirigen las operaciones a los servicios correspondientes. Ejemplos:
•	AdminController.java: Gestiona las operaciones administrativas.
•	AuthController.java: Maneja el inicio de sesión y registro de usuarios.
•	ClienteController.java: Gestiona las operaciones relacionadas con los clientes.
•	CrearMesaController.java: Permite la creación de nuevas mesas.
Servicios (service): Contienen la lógica de negocio. Ejemplos:
•	MesaService.java: Lógica relacionada con las mesas.
•	ReservaService.java: Lógica para gestionar reservas.
Modelos (model): Representan las entidades del sistema. Ejemplos:
•	Cliente.java: Representa a un cliente.
•	Reserva.java: Representa una reserva.
Repositorios (repository): Interactúan con la base de datos. Ejemplos:
•	ClienteRepository.java: Acceso a datos de clientes.
•	ReservaRepository.java: Acceso a datos de reservas.


2. Frontend:
Plantillas (templates): Archivos HTML que definen la interfaz de usuario. Ejemplos:
•	index.html: Página principal.
•	login.html: Página de inicio de sesión.
•	crear_reserva.html: Formulario para crear reservas.
  Recursos estáticos (static): Incluyen archivos como styles.css para los estilos.

Funcionalidades Principales

1. Gestión de Usuarios
- Registro e inicio de sesión de usuarios.
- Roles diferenciados para administradores y clientes.
2. Gestión de Mesas
•	Creación, edición y eliminación de mesas.
•	Visualización del estado de las mesas.
3. Gestión de Reservas
•	Creación de reservas por parte de los clientes.
•	Confirmación y cancelación de reservas.
•	Visualización de reservas existentes.

4. Panel de Administración
•	Acceso a estadísticas y reportes.
•	Gestión de clientes y reservas.

Tecnologías Utilizadas
•	Backend: Java, Spring Boot.
•	Frontend: HTML, CSS.
•	Base de Datos: H2 (en memoria) o cualquier base de datos compatible con JPA.
•	Herramientas de Construcción: Maven.
Ejecución del Proyecto
1. Clonar el repositorio desde GitHub. 
2. Asegurarse de tener Java y Maven instalados.
3. Ejecutar el comando mvn spring-boot:run desde la raíz del proyecto.
4. Acceder a la aplicación en http://localhost:8080.
Repositorio: https://github.com/MagikarpSama/Sabor_Gourmet
