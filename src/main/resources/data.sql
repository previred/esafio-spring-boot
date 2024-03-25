CREATE TABLE IF NOT EXISTS usuarios (
     id INT AUTO_INCREMENT PRIMARY KEY,
     username VARCHAR(100) NOT NULL,
     email VARCHAR(255) NOT NULL,
     password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS estados_tarea (
   id INT AUTO_INCREMENT PRIMARY KEY,
   status VARCHAR(255) NOT NULL
);

INSERT INTO usuarios (username, email, password) VALUES ('USUARIO1','user1@previred.com','123456');
INSERT INTO usuarios (username, email, password) VALUES ('SOLICITANTE','solicitante@previred.com','123456');

INSERT INTO estados_tarea (status) VALUES ('Creado');
INSERT INTO estados_tarea (status) VALUES ('Pendiente');
INSERT INTO estados_tarea (status) VALUES ('Asignado');
INSERT INTO estados_tarea (status) VALUES ('En Progreso');
INSERT INTO estados_tarea (status) VALUES ('Completado');
INSERT INTO estados_tarea (status) VALUES ('Cancelado');



