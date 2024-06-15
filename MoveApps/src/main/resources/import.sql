INSERT INTO  users  (name, lastname, email) VALUES ('Admin', 'super','admin.super@correo.com');
INSERT INTO  users  (name, lastname, email) VALUES ('Fernando', 'Gordillo','fernando.gordillo@correo.com');

INSERT INTO status_tasks (name_status_task) VALUES ('Complete');
INSERT INTO status_tasks (name_status_task) VALUES ('Pending');


INSERT INTO tasks (name_task,description_task,task_priority,id_responsible,id_status_task) VALUES ('Coding Java','realizarlo con java 17','ALTA','1','1');
INSERT INTO tasks (name_task,description_task,task_priority,id_responsible,id_status_task) VALUES ('Create Table SQL','realizarlo en SQL','BAJA','2','2');



