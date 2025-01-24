-- Crear tabla de usuarios
CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Crear tabla de estados de tarea
CREATE TABLE estado_tarea (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL
);

-- Crear tabla de tareas
CREATE TABLE tarea (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    estado_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    FOREIGN KEY (estado_id) REFERENCES estado_tarea(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

