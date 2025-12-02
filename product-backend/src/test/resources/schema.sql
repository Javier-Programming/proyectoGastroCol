-- Schema mínimo para tests de órdenes
-- Las tablas se crean en el orden que respeten sus FK para evitar errores en H2

CREATE TABLE tb_roles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  rol VARCHAR(50) NOT NULL,
  descripcion VARCHAR(255)
);

CREATE TABLE tb_usuario (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  correo VARCHAR(255) NOT NULL,
  contrasena VARCHAR(255) NOT NULL,
  biografia TEXT,
  ubicacion VARCHAR(255),
  imagen VARCHAR(255),
  rol_id BIGINT NOT NULL,
  fecha_creacion TIMESTAMP
);

CREATE TABLE tb_publicaciones (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  descripcion TEXT NOT NULL,
  precio_plato DECIMAL(10,2),
  imagen VARCHAR(255),
  usuario_id BIGINT NOT NULL,
  fecha_creacion TIMESTAMP
);

CREATE TABLE tb_comentario (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  usuario_id BIGINT NOT NULL,
  publicacion_id BIGINT,
  contenido TEXT,
  fecha_creacion TIMESTAMP
);

CREATE TABLE tb_reaccion (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  usuario_id BIGINT NOT NULL,
  publicacion_id BIGINT NOT NULL,
  fecha_creacion TIMESTAMP,
  CONSTRAINT uq_reaccion_usuario_publicacion UNIQUE (usuario_id, publicacion_id)
);

CREATE TABLE tb_ordenar_plato (
  id INT AUTO_INCREMENT PRIMARY KEY,
  consumidor_id BIGINT NOT NULL,
  restaurante_id BIGINT NOT NULL,
  publicacion_id BIGINT NOT NULL,
  cantidad INT NOT NULL,
  estado VARCHAR(50) NOT NULL,
  fecha_creacion TIMESTAMP
);

CREATE TABLE tb_orden_estado (
  id INT AUTO_INCREMENT PRIMARY KEY,
  orden_id INT NOT NULL,
  estado VARCHAR(50) NOT NULL,
  fecha_cambio TIMESTAMP
);

-- Constraints (opcional, H2 permite crear sin ellas para simplicidad)
ALTER TABLE tb_usuario ADD CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES tb_roles(id);
ALTER TABLE tb_publicaciones ADD CONSTRAINT fk_publicacion_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id);
ALTER TABLE tb_comentario ADD CONSTRAINT fk_comentario_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id);
ALTER TABLE tb_comentario ADD CONSTRAINT fk_comentario_publicacion FOREIGN KEY (publicacion_id) REFERENCES tb_publicaciones(id);
ALTER TABLE tb_reaccion ADD CONSTRAINT fk_reaccion_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id);
ALTER TABLE tb_reaccion ADD CONSTRAINT fk_reaccion_publicacion FOREIGN KEY (publicacion_id) REFERENCES tb_publicaciones(id);
ALTER TABLE tb_ordenar_plato ADD CONSTRAINT fk_orden_consumidor FOREIGN KEY (consumidor_id) REFERENCES tb_usuario(id);
ALTER TABLE tb_ordenar_plato ADD CONSTRAINT fk_orden_restaurante FOREIGN KEY (restaurante_id) REFERENCES tb_usuario(id);
ALTER TABLE tb_ordenar_plato ADD CONSTRAINT fk_orden_publicacion FOREIGN KEY (publicacion_id) REFERENCES tb_publicaciones(id);
ALTER TABLE tb_orden_estado ADD CONSTRAINT fk_orden_estado_orden FOREIGN KEY (orden_id) REFERENCES tb_ordenar_plato(id);