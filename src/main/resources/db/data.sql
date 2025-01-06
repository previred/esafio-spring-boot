
INSERT INTO Rol (codigo, nombre, descripcion) VALUES ('SCM', 'Scrum master', 'Scrum master');
INSERT INTO Rol (codigo, nombre, descripcion) VALUES ('DEV', 'Desarrollador', 'Desarrollador');


INSERT INTO Usuario (rut, usuario, password, nombre, email) VALUES ('12345678-9', 'jperez', 'BFECmDP5LTwfYarhHM7nGU', 'Juan Pérez', 'juan.perez@example.com');
INSERT INTO Usuario (rut, usuario, password, nombre, email) VALUES ('98765432-1', 'mgonzalez', 'yVp3DX8xreCUAm7JcYgqb2', 'María González', 'maria.gonzalez@example.com');
INSERT INTO Usuario (rut, usuario, password, nombre, email) VALUES ('11223344-5', 'clopez', 'H9tZvKA8JCL42rGj6YEfhP', 'Carlos López', 'carlos.lopez@example.com');
INSERT INTO Usuario (rut, usuario, password, nombre, email) VALUES ('55667788-0', 'amartinez', 'gs7GEzR6XdZKj2yxuAHFVm', 'Ana Martínez', 'ana.martinez@example.com');
INSERT INTO Usuario (rut, usuario, password, nombre, email) VALUES ('99887766-3', 'lfernandez', 'Xea6PS2Zt93Q4hnNHmy8bW', 'Luis Fernández', 'luis.fernandez@example.com');

INSERT INTO Usuario_Rol (codigo_rol, rut) VALUES ('DEV', '12345678-9');
INSERT INTO Usuario_Rol (codigo_rol, rut) VALUES ('DEV', '98765432-1');
INSERT INTO Usuario_Rol (codigo_rol, rut) VALUES ('DEV', '11223344-5');
INSERT INTO Usuario_Rol (codigo_rol, rut) VALUES ('DEV', '55667788-0');
INSERT INTO Usuario_Rol (codigo_rol, rut) VALUES ('SCM', '99887766-3');

INSERT INTO Tablero (codigo, nombre, descripcion, fecha_creacion) VALUES ('PROY1', 'Proyecto 1', 'Implementacion Proyecto 1', '2025-01-04T17:49:57.849+00:00');
INSERT INTO Tablero (codigo, nombre, descripcion, fecha_creacion) VALUES ('PROY2', 'Proyecto 2', 'Implementacion Proyecto 2', '2025-01-05T11:21:40.849+00:00');