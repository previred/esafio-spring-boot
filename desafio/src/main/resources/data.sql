-- Usuarios
INSERT INTO usuario (id, username, password) VALUES (1, 'admin', 'admin123');
INSERT INTO usuario (id, username, password) VALUES (2, 'user', 'user123');

-- Estados de Tareas
INSERT INTO estado_tarea (id, nombre) VALUES (1, 'Pendiente');
INSERT INTO estado_tarea (id, nombre) VALUES (2, 'En Proceso');
INSERT INTO estado_tarea (id, nombre) VALUES (3, 'Completada');


-- Insertar tareas iniciales
INSERT INTO tarea (titulo, descripcion, estado_id, usuario_id) VALUES
('Tarea 1', 'Descripcion de la tarea 1', 1, 2),
('Tarea 2', 'Descripcion de la tarea 2', 2, 2),
('Tarea 3', 'Descripcion de la tarea 3', 3, 2);