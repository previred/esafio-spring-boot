CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE estado_tareas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE tareas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_vencimiento TIMESTAMP,
    usuario_id INT,
    estado_tarea_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (estado_tarea_id) REFERENCES estado_tareas(id)
);

/*
INSERT INTO usuarios (username, password, roles) VALUES ('admin', 'sfsdfsdf', 'ROLE_ADMIN');
INSERT INTO usuarios (username, password, roles) VALUES ('user1', 'sfsdfsdf', 'ROLE_USER');
INSERT INTO usuarios (username, password, roles) VALUES ('user2', 'sdfsdfsdf', 'ROLE_USER');*/

INSERT INTO estado_tareas (descripcion) VALUES ('Pendiente');
INSERT INTO estado_tareas (descripcion) VALUES ('En Progreso');
INSERT INTO estado_tareas (descripcion) VALUES ('Completada');
INSERT INTO estado_tareas (descripcion) VALUES ('Cancelada');

INSERT INTO tareas (titulo, descripcion, fecha_creacion) VALUES ('Configurar proyecto', 'Configurar el proyecto Spring Boot', CURRENT_TIMESTAMP);
INSERT INTO tareas (titulo, descripcion, fecha_creacion) VALUES ('Crear API REST', 'Desarrollar endpoints para la gestion de tareas', CURRENT_TIMESTAMP);
INSERT INTO tareas (titulo, descripcion, fecha_creacion) VALUES ('Documentar API', 'Agregar documentacion OpenAPI y Swagger', CURRENT_TIMESTAMP);