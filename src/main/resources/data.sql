INSERT INTO usuarios (id,email,password,password2,token) VALUES (1,'juan@prueba.com','JuanRod1&','JuanRod1&','');
INSERT INTO usuarios (id,email,password,password2,token) VALUES (2,'fabiola@prueba.com','FabiRod1&','FabiRod1&','');
INSERT INTO usuarios (id,email,password,password2,token) VALUES (3,'marco@prueba.com','MarcoRod1&','MarcoRod1&','');
UPDATE usuarios SET password2=HASH('SHA256',password2, 1000);

INSERT INTO estados_tarea (id,estado) VALUES (1,'En Curso');
INSERT INTO estados_tarea (id,estado) VALUES (2,'En Curso');
INSERT INTO estados_tarea (id,estado) VALUES (3,'En Curso');