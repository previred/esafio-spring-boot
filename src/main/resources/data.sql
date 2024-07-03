set mode MySQL;

insert into estados_tarea (id, descripcion) values (1, 'PENDIENTE'), (2, 'COMPLETADO'), (3, 'CANCELADO')
ON DUPLICATE KEY UPDATE descripcion = descripcion;

insert into usuarios (id, correo, nombres, apellidos, contrasenha, telefono, estado, usuario)
values (1, 'user@test.com', 'User', 'Test', '$2a$10$kUXz2GFmcdes/5ssLzva1enSNQE5sptwU1lw3yRYCWJK954MoZhrq', null, 1, 'user001')
ON DUPLICATE KEY UPDATE correo = correo;
