INSERT INTO ESTADOS_TAREA(status) VALUES ('VALID');
INSERT INTO ESTADOS_TAREA(status) VALUES ('INVALID');
INSERT INTO ROLES(name) VALUES ('ADMIN');
INSERT INTO ROLES(name) VALUES ('NORMAL_USER');
INSERT INTO USUARIOS(name, lastname, username, password, mail, role_id) VALUES ('Gabriel', 'rivera','GaboTest', '$2a$10$PhCza71w/vxIu0HF6cPHEuy4/MB4suksc2/5S.FnpRvbbjWGr6OW.', 'gabriel.rivera.gonzalez@gmail.com', 1);