INSERT INTO estados_tarea (nombre_estado) VALUES ('Pendiente'), ('En progreso'), ('Completado');

INSERT INTO usuarios (nombre_usuario, correo_electronico, contrasena_hash) VALUES
('Héctor', 'hector@example.com', 'hash_del_password');

INSERT INTO tareas (id_usuario, titulo, descripcion, fecha_limite, id_estado) VALUES
(1, 'Tarea de prueba', 'Esta es una descripción de la tarea de prueba.', '2023-12-31', 1);
