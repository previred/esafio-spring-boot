
-- clave Nuevospa123
INSERT INTO usuarios(name, email, password, status, created, last_login) 
values ('jorge rodríguez', 'jrodriguez@gmail.com','$2a$10$wG6o8.U/grhAh6aFDlQta.Ai.p/jqVoUwm4gQ.St.KY0IHsPFHPPa', 1, CURRENT_TIMESTAMP, null);

INSERT INTO estados_tarea (status) VALUES ('IN PROGRESS');
INSERT INTO estados_tarea (status) VALUES ('DONE');
INSERT INTO estados_tarea (status) VALUES ('TO DO');
INSERT INTO estados_tarea (status) VALUES ('APROVED');
INSERT INTO estados_tarea (status) VALUES ('CANCELLED');
INSERT INTO estados_tarea (status) VALUES ('REJECTED');
