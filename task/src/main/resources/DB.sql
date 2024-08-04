CREATE TABLE role (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE appuser (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) UNIQUE NOT NULL,
                         username VARCHAR(255) UNIQUE NOT NULL,
                         password VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE appuser_roles (
                               appuser_id BIGINT NOT NULL,
                               role_id BIGINT NOT NULL
);

CREATE TABLE state_task(
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE task (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(250) NOT NULL,
                      description VARCHAR(250) NOT NULL,
                      state_id INT);

ALTER TABLE appuser_roles ADD CONSTRAINT appuser_roles_pkey PRIMARY KEY(appuser_id, role_id);
ALTER TABLE appuser_roles ADD CONSTRAINT appuser_roles_user_fkey FOREIGN KEY(appuser_id) REFERENCES appuser(id);
ALTER TABLE appuser_roles ADD CONSTRAINT appuser_roles_role_fkey FOREIGN KEY(role_id) REFERENCES role(id);
ALTER TABLE task ADD CONSTRAINT task_state_task_fkey FOREIGN KEY(state_id) REFERENCES state_task(id);


INSERT INTO appuser (id, name, username, password) VALUES (default, 'user-1', 'user-1','$2a$10$gUxZp/vJ8TJDGBCGiKnYJ.LbSAbtJbaUzbxf7CPEMYBCoJZ5LDmya');
INSERT INTO appuser (id, name, username, password) VALUES (default, 'user-2', 'user-2','$2a$10$S0q9gjpQFgu/0HUupHh4f./jTGArgN4rboYfCkQoLNhUgYuG4DceG');

INSERT INTO role (id, name) VALUES (default, 'ROLE-1');
INSERT INTO role (id, name) VALUES (default, 'ROLE-2');

INSERT INTO appuser_roles (appuser_id, role_id) VALUES (1,1);
INSERT INTO appuser_roles (appuser_id, role_id) VALUES (2,2);

INSERT INTO state_task (id, name) VALUES (default, 'CREADA');
INSERT INTO state_task (id, name) VALUES (default, 'INICIADA');
INSERT INTO state_task (id, name) VALUES (default, 'FINALIZADA');
INSERT INTO state_task (id, name) VALUES (default, 'ELIMINADA');

INSERT INTO task (id ,name ,description ,state_id ) VALUES (default, 'tarea-1', 'Analisis del desafio',3);
INSERT INTO task (id ,name ,description ,state_id ) VALUES (default, 'tarea-1', 'Creacion de la estructura inicial',3);