-- Pre carga de datos en la tabla usuarios
INSERT INTO usuario (username, password) VALUES ('user1', 'password1');
INSERT INTO usuario (username, password) VALUES ('user2', 'password2');

-- Pre carga de datos en la tabla estados_tarea
INSERT INTO estado_tarea (estado) VALUES ('PENDING');
INSERT INTO estado_tarea (estado) VALUES ('IN_PROGRESS');
INSERT INTO estado_tarea (estado) VALUES ('COMPLETED');