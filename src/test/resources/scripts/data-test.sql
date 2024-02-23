INSERT INTO public.estado_tarea (descripcion, activo) VALUES('PENDING', true);
INSERT INTO public.estado_tarea (descripcion, activo) VALUES('STARTED', true);
INSERT INTO public.estado_tarea (descripcion, activo) VALUES('PROCESS', true);
INSERT INTO public.estado_tarea (descripcion, activo) VALUES('DONE', true);

INSERT INTO public.tb_user (usr_username, usr_password, last_password_change, created_date, last_access, active, expired, locked, authorities_text) VALUES ('dpichinil', '$2a$10$aQapC4dFuvXzLm1dN5tTZeeEkX/jARqvi5xUoKQBFUIICwd5Zr4oa', '2023-12-31 23:59:59', '2023-12-31 23:59:59', '2023-12-31 23:59:59', true, false, false, 'USER,ADMIN');
INSERT INTO public.tb_user (usr_username, usr_password, last_password_change, created_date, last_access, active, expired, locked, authorities_text) VALUES ('otro', '$2a$10$hIGiRJ4Wv0cvINAho6Yi6ejsRolriwZV4dDpoo0dtGCxQuQWRl5pi', '2023-12-31 23:59:59', '2023-12-31 23:59:59', '2023-12-31 23:59:59', true, false, false, 'USER');