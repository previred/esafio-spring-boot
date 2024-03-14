CREATE TABLE usuarios (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL
);

INSERT INTO usuarios (username, password) VALUES ('usuario1', '$2y$10$smyRUG4DRhYY7U/IHjReAexUnPaF3CcUIE6LqADOi01PaOBIuzdEW');
INSERT INTO usuarios (username, password) VALUES ('usuario2', '$2y$10$1RGmAIXTErodMLPNkowyt.RZPhwC1t5o2xciu7AW8Mi.7q9CPBw/C');

CREATE TABLE estados_tarea (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               nombre VARCHAR(255) NOT NULL
);

INSERT INTO estados_tarea (nombre) VALUES ('PENDIENTE');
INSERT INTO estados_tarea (nombre) VALUES ('EN_PROGRESO');
INSERT INTO estados_tarea (nombre) VALUES ('COMPLETADA');

CREATE TABLE tareas (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        descripcion TEXT,
                        fecha_limite DATE,
                        usuario_id INT NOT NULL,
                        estado_id INT NOT NULL,
                        FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                        FOREIGN KEY (estado_id) REFERENCES estados_tarea(id)
);
