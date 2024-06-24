-- Creación de la tabla usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Creación de la tabla estado_tarea
CREATE TABLE IF NOT EXISTS estado_tarea (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Creación de la tabla tareas
CREATE TABLE IF NOT EXISTS tareas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    information VARCHAR(255),
    status_id INT,
    user_id INT,
    FOREIGN KEY (status_id) REFERENCES estado_tarea(id),
    FOREIGN KEY (user_id) REFERENCES usuarios(id)
);

-- Insertar usuarios
INSERT INTO usuarios (username, password) VALUES ('user1', '$2y$10$J6fbYmuY2MeW0sOLegfQVe6RS3swzd9i2msfUbuzacTZx95z4osEG'); -- password: hola123 
INSERT INTO usuarios (username, password) VALUES ('user2', '$2y$10$BV9guocnrpf2WIKJ61zmV.eDarbGHlcbFrrV5Ufo0NbWWKnHRunKC'); -- password: hello123
INSERT INTO usuarios (username, password) VALUES ('user3', '$2y$10$y10hQWnt8z3rR97h76IQk.hk6Nbnnw6WRebVrNXQpOrpVtBYOnlkq'); -- password: password123
INSERT INTO usuarios (username, password) VALUES ('user4', '$2y$10$HTue01J0Slc.HhVVtOYXLeuagtXRu8PKEJpUkjpKFVDUNSCM..Zdq'); -- password: secret123
INSERT INTO usuarios (username, password) VALUES ('user5', '$2y$10$ntqqnWRl4Hfqe9aw4L/l9Oi3YznsNK6qzgG4eDjuXZAovY9WESVF2'); -- password: admin123


-- Insertar estados de tarea
INSERT INTO estado_tarea (name) VALUES ('PENDIENTE');
INSERT INTO estado_tarea (name) VALUES ('EN PROCESO');
INSERT INTO estado_tarea (name) VALUES ('COMPLETADA');

-- Insertar tareas
INSERT INTO tareas (title, description, information, status_id, user_id) VALUES ('Tarea 1', 'Descripción de la Tarea 1', 'Nueva Tarea', 1, 1);
INSERT INTO tareas (title, description, information, status_id, user_id) VALUES ('Tarea 2', 'Descripción de la Tarea 2', 'La tarea se esta avanzando', 2, 2);
INSERT INTO tareas (title, description, information, status_id, user_id) VALUES ('Tarea 3', 'Descripción de la Tarea 3', 'La tarea se termino', 3, 2);
