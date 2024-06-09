insert into ROLE ( name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into SYSUSER ( username, name, last_login, password, active)
values ( 'admin', 'Administrador', null, '$2b$10$uxfnGIiUm/cv0KLrndoL3eG.g5edvswMdioKpegCvgbIUqm508w/q',  true),
( 'user001', 'Usuario numero 1 ', null, '$2b$10$uxfnGIiUm/cv0KLrndoL3eG.g5edvswMdioKpegCvgbIUqm508w/q',  true),
( 'user002', 'Usuario numero 2 ', null, '$2b$10$uxfnGIiUm/cv0KLrndoL3eG.g5edvswMdioKpegCvgbIUqm508w/q',  false);

insert into TASK_STATUS ( task_status_name, active)
values ( 'Asignado', true);
insert into TASK_STATUS ( task_status_name, active)
values ( 'En Proceso', true);
insert into TASK_STATUS ( task_status_name, active)
values ( 'Finalizado', true);
insert into TASK_STATUS ( task_status_name, active)
values ( 'Anulado', true);



insert into TASK ( task_name)
values ( 'Corte de cabello');
insert into TASK ( task_name)
values ( 'Lavado de cabello');
insert into TASK ( task_name)
values ( 'Pedicure');
insert into TASK ( task_name)
values ( 'Manicure');


insert into USER_HAS_ROLE (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 2);
