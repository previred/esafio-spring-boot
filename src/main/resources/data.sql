INSERT INTO usuarios (ID_USER, NOMBRE_COMPLETO, email, PASSWORD, FECHA_CREACION) VALUES
                                                                                     ('12345678-1', 'Nombre Usuario 1', 'usuario1@example.com', 'pass1234', '2024-01-01'),
                                                                                     ('98765432-2', 'Nombre Usuario 2', 'usuario2@example.com', 'pass4567', '2024-01-02'),
                                                                                     ('45367812-3', 'Nombre Usuario 3', 'usuario3@example.com', 'pass7890', '2024-01-03');

INSERT INTO estados_tarea (ESTADO_TAREA, DESCRIPCION_ESTADO) VALUES
                                                                 ('INI', 'Tarea nueva'),
                                                                 ('PRO', 'Tarea actualmente en progreso'),
                                                                 ('SUS', 'Tarea momentaneamente suspendida'),
                                                                 ('FIN', 'Tarea completada satisfactoriamente');