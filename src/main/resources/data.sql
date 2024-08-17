-- Insertar datos en la tabla usuarios (sin especificar el ID)
INSERT INTO usuarios (nombre, correo, contraseña, fecha_creacion) VALUES
('Pamela Polanco', 'pamepolanco@example.com', 'pame123', CURRENT_TIMESTAMP),
('Usuario dos', 'usuariodos@example.com', 'dos123', CURRENT_TIMESTAMP);

-- Insertar datos en la tabla estados_tarea (sin especificar el ID)
INSERT INTO estados_tarea (nombre) VALUES
('Pendiente'),
('En Progreso'),
('Completada');
