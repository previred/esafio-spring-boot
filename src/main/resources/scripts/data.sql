INSERT INTO public.estado_tarea (descripcion, activo) VALUES('PENDING', true);
INSERT INTO public.estado_tarea (descripcion, activo) VALUES('STARTED', true);
INSERT INTO public.estado_tarea (descripcion, activo) VALUES('PROCESS', true);
INSERT INTO public.estado_tarea (descripcion, activo) VALUES('DONE', true);

INSERT INTO public.tb_user (usr_username, usr_password, last_password_change, created_date, last_access, active, expired, authorities_text) VALUES ('dpichinil', '123456', '2023-12-31 23:59:59', '2023-12-31 23:59:59', '2023-12-31 23:59:59', true, false, 'USER,ADMIN');
INSERT INTO public.tb_user (usr_username, usr_password, last_password_change, created_date, last_access, active, expired, authorities_text) VALUES ('otro', '123456', '2023-12-31 23:59:59', '2023-12-31 23:59:59', '2023-12-31 23:59:59', true, false, 'USER');