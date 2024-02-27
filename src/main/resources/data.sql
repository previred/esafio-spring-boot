--DELETE FROM usuario;
-- Insertar datos pre cargados para la tabla de usuarios
INSERT INTO usuario (id, country, firstname, lastname, password, role, username) 
VALUES (100, 'Chile', 'Daniela', 'Araneda', 'password', 'ADMIN', 'daniela@gmail.com');
INSERT INTO usuario (id, country, firstname, lastname, password, role, username) 
VALUES (101, 'Chile', 'Alejandro', 'Sandoval', 'password', 'USER', 'alejandro@gmail.com');--estado de Tareas
--estado de Tareas
INSERT INTO estadotarea (nombre) VALUES ('PROGRESO');
INSERT INTO estadotarea (nombre) VALUES ('CERRADO');
INSERT INTO estadotarea (nombre) VALUES ('ABIERTO');


