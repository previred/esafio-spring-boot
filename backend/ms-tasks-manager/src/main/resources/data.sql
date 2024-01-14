INSERT INTO usuarios(name, email, password, status, created, last_login) values ('usuario1', 'usuario1@mail.com','$2a$10$42yn1JteZNAEs0u2ysP7tO5fONhCXwZ0tK7gwVU.VR/wh81UnJJxW', 1, CURRENT_TIMESTAMP, null);

INSERT INTO estados_tarea (status) VALUES ('DOING');
INSERT INTO estados_tarea (status) VALUES ('DONE');
INSERT INTO estados_tarea (status) VALUES ('TO DO');
