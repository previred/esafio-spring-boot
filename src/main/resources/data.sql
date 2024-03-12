CREATE TABLE usuarios (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL
);

INSERT INTO usuarios (username, password) VALUES ('usuario1', 'password1');
INSERT INTO usuarios (username, password) VALUES ('usuario2', 'password2');

CREATE TABLE estados_tarea (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               nombre VARCHAR(255) NOT NULL
);

INSERT INTO estados_tarea (nombre) VALUES ('Pendiente');
INSERT INTO estados_tarea (nombre) VALUES ('En Progreso');
INSERT INTO estados_tarea (nombre) VALUES ('Completada');

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
