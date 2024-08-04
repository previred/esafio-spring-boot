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
ALTER TABLE appuser_roles ADD CONSTRAINT appuser_roles_pkey PRIMARY KEY(appuser_id, role_id);
ALTER TABLE appuser_roles ADD CONSTRAINT appuser_roles_user_fkey FOREIGN KEY(appuser_id) REFERENCES appuser(id);
ALTER TABLE appuser_roles ADD CONSTRAINT appuser_roles_role_fkey FOREIGN KEY(role_id) REFERENCES role(id);


INSERT INTO appuser (id, name, username, password) VALUES (default, 'user-1', 'user-1','$2a$10$gUxZp/vJ8TJDGBCGiKnYJ.LbSAbtJbaUzbxf7CPEMYBCoJZ5LDmya');
INSERT INTO appuser (id, name, username, password) VALUES (default, 'user-2', 'user-2','$2a$10$S0q9gjpQFgu/0HUupHh4f./jTGArgN4rboYfCkQoLNhUgYuG4DceG');

INSERT INTO role (id, name) VALUES (default, 'ROLE-1');
INSERT INTO role (id, name) VALUES (default, 'ROLE-2');

INSERT INTO appuser_roles (appuser_id, role_id) VALUES (1,1);
INSERT INTO appuser_roles (appuser_id, role_id) VALUES (2,2);