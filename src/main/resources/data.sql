/*insert en la tabla de estados de tareas task_status*/
insert into task_status(name,state) values ('TO_DO', true);
insert into task_status(name,state) values ('IN_PROGRESS', true);
insert into task_status(name,state) values ('TO_VERIFY', true);
insert into task_status(name,state) values ('DONE', true);
/*insert en la tabla de usuarios(user)*/
insert into user(username,password,firstname,lastname,country,role) values ('admin', '$2a$10$z4UzI97e63idnXNb.MCoTOlf4VndvNzuELoAjzErk/jRS9MIsxKj.','adminName','adminLastName','PE','ADMIN');
/*insert en la tabla tarea(tasks) la primera tarea asignada*/
insert into tasks(task,TASK_STATUS_ID,ID_USER,created_date,last_modified_date,create_by, last_modified_by) values('modelarBD',1,1, NOW(), NOW(), 'admin', 'admin');