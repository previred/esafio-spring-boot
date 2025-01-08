-- Pre carga de datos en la tabla usuarios
INSERT INTO usuario (username, password) VALUES ('user1', '$2a$10$0Ir816uoKvhM.IYrQPZqqeZGf51OxweQYF5R9uXDf91IxQ1vuLoxK');
INSERT INTO usuario (username, password) VALUES ('user2', '$2a$10$0Ir816uoKvhM.IYrQPZqqeZGf51OxweQYF5R9uXDf91IxQ1vuLoxK');

-- Pre carga de datos en la tabla estados_tarea
INSERT INTO estado_tarea (status) VALUES ('DELETED');
INSERT INTO estado_tarea (status) VALUES ('PENDING');
INSERT INTO estado_tarea (status) VALUES ('IN_PROGRESS');
INSERT INTO estado_tarea (status) VALUES ('COMPLETED');
