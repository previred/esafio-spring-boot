
INSERT INTO users (user_id, full_name, username, password, is_active, created, last_login)
VALUES (1, 'Wilmer Palomino', 'wpalomino', 'Elmaestro1$', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tasks ( title, description, user_id)
VALUES ('Elaboración de Pruebas', 'Se elaboran pruebas unitarias para el despliegue', 1);

INSERT INTO tasks ( title, description, user_id)
VALUES ('Despliegue a Producción', 'Elaboración de manual de pase a producción', 1);

INSERT INTO task_status (status, last_modified, task_id)
VALUES ('POR INICIAR', CURRENT_TIMESTAMP, 1);
INSERT INTO task_status (status, last_modified, task_id)
VALUES ('EN PROGRESO', CURRENT_TIMESTAMP, 1);
INSERT INTO task_status (status, last_modified, task_id)
VALUES ('TERMINADO', CURRENT_TIMESTAMP, 1);

INSERT INTO task_status (status, last_modified, task_id)
VALUES ('POR INICIAR', CURRENT_TIMESTAMP, 2);

