INSERT INTO ROLES(name) VALUES ('ADMIN');
INSERT INTO ROLES(name) VALUES ('GENERAL_USER');
INSERT INTO USUARIOS(name, lastname, username, password, mail, role_id) VALUES ('Sergio', 'Silva','usertest', '$2a$10$PhCza71w/vxIu0HF6cPHEuy4/MB4suksc2/5S.FnpRvbbjWGr6OW.', 'sergio.silva.arancibia@gmail.com', 1);
INSERT INTO ESTADOS_TAREA(status) VALUES ('VALID');
INSERT INTO ESTADOS_TAREA(status) VALUES ('INVALID');