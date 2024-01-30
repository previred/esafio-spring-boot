insert into task_status(name_status_task) values ('TO_DO');
insert into task_status(name_status_task) values ('DOING');
insert into task_status(name_status_task) values ('DONE');

insert into  users  (name, lastname, email) VALUES ('Admin', 'administrator','super.admin@correo.com');
insert into  users  (name, lastname, email) VALUES ('Gabriel', 'Martinez','gabriel_martinez@fake.com');

insert into tasks (id, name_task, description_task, task_priority, id_user, id_status_task) VALUES ('785FF4E2-CD89-4BE5-A0D2-22F46451856A', 'Install Tinder', 'description','LOW', '2', '3');
insert into tasks (id, name_task, description_task, task_priority, id_user, id_status_task) VALUES ('017F9C05-15FF-4EAC-9166-EC609CCBB6C3', 'Create a sexy profile','description','HIGH', '2', '2');
insert into tasks (id, name_task, description_task, task_priority, id_user, id_status_task) VALUES ('D93D8D53-7E56-472C-929A-A386D33656E3', 'Make a lot of MATCH','description','HIGH', '2', '1');
