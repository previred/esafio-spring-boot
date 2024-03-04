DROP TABLE IF EXISTS ROLES;
CREATE TABLE ROLES(
                      id 			int auto_increment primary key,
                      name		varchar(50) unique
);

DROP TABLE IF EXISTS USUARIOS;
CREATE TABLE USUARIOS(
                         id 			int auto_increment primary key,
                         name		varchar(50),
                         lastname	varchar(50),
                         username	varchar(50) unique,
                         password	varchar(100),
                         mail		varchar(50),
                         role_id		int,
                         FOREIGN KEY (role_id) REFERENCES ROLES(id)
);


DROP TABLE IF EXISTS ESTADOS_TAREA;
CREATE TABLE ESTADOS_TAREA(
                              id 			int auto_increment primary key,
                              status		varchar(50) unique
);

DROP TABLE IF EXISTS TAREAS;
CREATE TABLE TAREAS(
                       id 			int auto_increment primary key,
                       name_task	varchar(50) unique,
                       status_id	int,
                       FOREIGN KEY (status_id) REFERENCES ESTADOS_TAREA(id)
);