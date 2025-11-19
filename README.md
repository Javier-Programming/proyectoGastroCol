📘 README — Guía de desarrollo del backend (Spring Boot)
📁 Estructura general del proyecto:
com.appGastroCol.product_backend
│
├── config/
│     ├── CorsConfig.java
│     └── SecurityConfig.java
│
├── controller/
│     ├── AuthController.java
│     ├── UsuarioController.java
│     ├── RestauranteController.java
│     ├── PublicacionController.java
│     ├── ComentarioController.java
│     ├── ReaccionController.java
│     ├── OrdenController.java
│     ├── SeguidoresController.java
│
├── service/
│     ├── AuthService.java
│     ├── UsuarioService.java
│     ├── RestauranteService.java
│     ├── PublicacionService.java
│     ├── ComentarioService.java
│     ├── ReaccionService.java
│     ├── OrdenService.java
│     ├── SeguidoresService.java
│
├── service/impl/
│     ├── AuthServiceImpl.java
│     ├── UsuarioServiceImpl.java
│     ├── RestauranteServiceImpl.java
│     ├── PublicacionServiceImpl.java
│     ├── ComentarioServiceImpl.java
│     ├── ReaccionServiceImpl.java
│     ├── OrdenServiceImpl.java
│     ├── SeguidoresServiceImpl.java
│
├── repository/
│     ├── UsuarioRepository.java
│     ├── RolRepository.java
│     ├── PublicacionRepository.java
│     ├── ComentarioRepository.java
│     ├── ReaccionRepository.java
│     ├── OrdenRepository.java
│     ├── SeguidoresRepository.java
│
├── entity/
│     ├── Usuario.java
│     ├── Rol.java
│     ├── Publicacion.java
│     ├── Comentario.java
│     ├── Reaccion.java
│     ├── Orden.java
│     ├── Seguidores.java
│
├── dto/
│     ├── LoginRequest.java
│     ├── RegisterRequest.java
│     ├── PublicacionDTO.java
│     ├── ComentarioDTO.java
│     ├── ComentarioResponseDTO.java
│     ├── DTOMapper.java
│     ├── ReaccionDTO.java
│     ├── OrdenDTO.java
│     ├── UsuarioDTO.java
│     ├── UsuarioSimpleDTO.java
│
└── exception/
      ├── ResourceNotFoundException.java
      ├── BadRequestException.java
      └── GlobalExceptionHandler.java

🧩 Ramas del proyecto
🧵 Rama principal

main → versión estable del proyecto (solo código probado)

🛠️ Rama de desarrollo

developer → integración de todas las funcionalidades nuevas

🌿 Ramas por cada desarrollador:
feature/core               (Javier Díaz — Líder)
feature/auth              (Miembro 1)
feature/restaurants-posts (Miembro 2)
feature/social            (Miembro 3)
feature/orders            (Miembro 4)
🌿 Cada rama corresponde a una funcionalidad completa del backend.

🔧 Cómo debe crear su rama cada integrante
1️⃣ Actualizar la rama main (solo la primera vez)
git checkout main
git pull origin main

2️⃣ Crear la rama developer local
git checkout -b developer
git push -u origin developer

3️⃣ Crear su propia rama partiendo de developer

Ejemplo para el miembro 1:
git checkout developer
git pull origin developer
git checkout -b feature/auth
git push -u origin feature/auth

4️⃣ Trabajar SIEMPRE dentro de su rama
git add .
git commit -m "Descripción del avance"
git push

📌 Guía por módulos: qué debe hacer cada miembro

A continuación se detalla la ruta de trabajo archivo por archivo por cada carpeta.

⭐ 1. feature/auth — Miembro 1

Responsable: Autenticación y seguridad completa
Incluye: login, registro, JWT, seguridad, roles

📌 Archivos donde debe trabajar (en orden):
1. entity/

Usuario.java (validaciones)

Rol.java (enum o entidad)

2. dto/

LoginRequest.java

RegisterRequest.java

UsuarioDTO.java

UsuarioSimpleDTO.java

3. repository/

UsuarioRepository.java

RolRepository.java

4. config/

SecurityConfig.java

CorsConfig.java

5. service/

AuthService.java

UsuarioService.java

6. service/impl/

AuthServiceImpl.java

UsuarioServiceImpl.java

7. controller/

AuthController.java

UsuarioController.java

✔️ Objetivos del módulo:

Registro de usuario

Inicio de sesión

Generación y validación de JWT

Configurar seguridad con Spring Security

Manejo de roles

Protección de rutas privadas

⭐ 2. feature/restaurants-posts — Miembro 2

Responsable: Restaurantes + publicaciones

📍 2.1 Restaurantes
Archivos donde debe trabajar:
entity/

Usuario.java (si el restaurante es un tipo de usuario)

Publicacion.java (si restaurante publica)

controller/

RestauranteController.java

dto/

PublicacionDTO.java

Crear RestauranteDTO.java (si es necesario)

repository/

UsuarioRepository.java (si restaurantes son usuarios)

PublicacionRepository.java

service/

RestauranteService.java

PublicacionService.java

service/impl/

RestauranteServiceImpl.java

PublicacionServiceImpl.java

✔️ Objetivos del módulo:

CRUD de restaurantes

CRUD de publicaciones (textos, imágenes)

Listar publicaciones por restaurante

Conectar publicaciones con comentarios y reacciones

⭐ 3. feature/social — Miembro 3

Responsable: Comentarios (ya listos), reacciones, seguidores

Archivos donde debe trabajar:

Ya terminado:

ComentarioController.java

ComentarioService.java

ComentarioServiceImpl.java

ComentarioRepository.java

ComentarioDTO.java

FALTANTE:

entity/

Reaccion.java

Seguidores.java

controller/

ReaccionController.java

SeguidoresController.java

dto/

ReaccionDTO.java

repository/

ReaccionRepository.java

SeguidoresRepository.java

service/

ReaccionService.java

SeguidoresService.java

service/impl/

ReaccionServiceImpl.java

SeguidoresServiceImpl.java

✔️ Objetivos del módulo:

Reaccionar a una publicación (like, favorito, etc.)

Seguir y dejar de seguir usuarios

Obtener seguidores y seguidos

⭐ 4. feature/orders — Miembro 4

Responsable: sistema de pedidos

Archivos donde debe trabajar:
entity/

Orden.java

dto/

OrdenDTO.java

controller/

OrdenController.java

repository/

OrdenRepository.java

service/

OrdenService.java

service/impl/

OrdenServiceImpl.java

✔️ Objetivos del módulo:

Crear orden

Actualizar estado de orden

Listar órdenes de un usuario o restaurante

Gestión de historial

⭐ 5. feature/core — Javier Díaz

Responsable: arquitectura, excepciones, integración, soporte general

Archivos principales:
exception/

ResourceNotFoundException.java

BadRequestException.java

GlobalExceptionHandler.java

dto/

DTOMapper.java

config/

Arquitectura global

Integración entre módulos

Ajustes de seguridad y despliegue

🎯 Resumen general del flujo de trabajo

Cada desarrollador crea su rama desde developer.

Trabaja exclusivamente en los archivos asignados de su módulo.

Cuando termine una funcionalidad → merge a developer.

Antes de lanzar versión → merge a main.
