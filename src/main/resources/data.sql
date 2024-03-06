CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol_id INT,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

CREATE TABLE estados_tarea (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE tareas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_creacion TIMESTAMP,
    fecha_actualizacion TIMESTAMP,
    estado_id INT,
    usuario_id INT,
    FOREIGN KEY (estado_id) REFERENCES estados_tarea(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Datos de inicialización

INSERT INTO roles (id, nombre) VALUES (1, 'ADMIN');
INSERT INTO roles (id, nombre) VALUES (2, 'USER');

INSERT INTO estados_tarea (id, nombre) VALUES (1, 'Pendiente');
INSERT INTO estados_tarea (id, nombre) VALUES (2, 'En Curso');
INSERT INTO estados_tarea (id, nombre) VALUES (3, 'Completada');

INSERT INTO usuarios (id, username, nombre, email, contrasena, rol_id) VALUES (1, 'admin', 'Admin Apellido', 'admin@nuevospa.com', '70be2932a9786b17a1351b8d3b9fdf22', 1);
INSERT INTO usuarios (id, username, nombre, email, contrasena, rol_id) VALUES (2, 'usuario1', 'Usuario Apellido', 'user1@nuevospa.com', '70be2932a9786b17a1351b8d3b9fdf22', 2);