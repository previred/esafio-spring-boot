--
-- tabla de estados de las tareas
--
CREATE TABLE task_status(
   	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	status VARCHAR(100)
);
--
-- data precargada
--
insert into task_status(status) values ('Pendiente'); 
insert into task_status(status) values ('Activa'); 
insert into task_status(status) values ('Cancelada'); 
insert into task_status(status) values ('Expirada');
insert into task_status(status) values ('Completada');
--
-- tabla de usuarios
--
CREATE TABLE users (
    id          int AUTO_INCREMENT PRIMARY KEY,
	username    varchar(100),
	firstname   varchar(100),
	lastname    varchar(100),
	country     varchar(100),
	password    varchar(100),
	role        varchar(100)
);
--
--
-- tabla de tareas
--
CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    status_id INT DEFAULT  1, 
    FOREIGN KEY (status_id) REFERENCES task_status(id)
);
--
-- data de prueba
--
insert into tasks(description, status_id) values ('Contratar postulante Pablo.Hirsh',1);
insert into tasks(description, status_id) values ('tarea 1',1);
insert into tasks(description, status_id) values ('tarea 2',2);
insert into tasks(description, status_id) values ('tarea 3',3);
insert into tasks(description, status_id) values ('tarea 4',4);
insert into tasks(description, status_id) values ('tarea 5',5);
insert into tasks(description, status_id) values ('tarea 6',5);
--