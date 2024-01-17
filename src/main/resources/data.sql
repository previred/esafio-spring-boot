insert into users (ID,EMAIL,NAME,ROLE) values (1,'jbecker@gmail.com','Jason Becker','DEV');
insert into users (ID,EMAIL,NAME,ROLE) values (2,'mwallace@hotmail.com','Mia Wallace','DEV');
insert into users (ID,EMAIL,NAME,ROLE) values (3,'vincent_v@hotmail.com','Vincent Vega','DEV');
insert into users (ID,EMAIL,NAME,ROLE) values (4,'marsellus@gmail.com','Marsellus Wallace','DEV');
insert into users (ID,EMAIL,NAME,ROLE) values (6,'honey_bunny@hotmail.com','Honey Bunny','DEV');
insert into users (ID,EMAIL,NAME,ROLE) values (5,'j_winnfield@gmail.com','Jules Winnfield','DEV');
insert into users (ID,EMAIL,NAME,ROLE) values (7,'coolidge@gmail.com','Butch Coolidge','DEV');

insert into user_password(id,user_id,password) values (1,1,'$2a$10$uLX8IL0qFlrHCp9oSm4WN.sVG/FYl.z9Y0H/256JL1RjmX6gGox8e');
insert into user_password(id,user_id,password) values (2,2,'$2a$10$uLX8IL0qFlrHCp9oSm4WN.sVG/FYl.z9Y0H/256JL1RjmX6gGox8e');
insert into user_password(id,user_id,password) values (3,3,'$2a$10$uLX8IL0qFlrHCp9oSm4WN.sVG/FYl.z9Y0H/256JL1RjmX6gGox8e');
insert into user_password(id,user_id,password) values (4,4,'$2a$10$uLX8IL0qFlrHCp9oSm4WN.sVG/FYl.z9Y0H/256JL1RjmX6gGox8e');
insert into user_password(id,user_id,password) values (5,5,'$2a$10$uLX8IL0qFlrHCp9oSm4WN.sVG/FYl.z9Y0H/256JL1RjmX6gGox8e');
insert into user_password(id,user_id,password) values (6,6,'$2a$10$uLX8IL0qFlrHCp9oSm4WN.sVG/FYl.z9Y0H/256JL1RjmX6gGox8e');
insert into user_password(id,user_id,password) values (7,7,'$2a$10$uLX8IL0qFlrHCp9oSm4WN.sVG/FYl.z9Y0H/256JL1RjmX6gGox8e');

insert into task_status (id,code,description) values (1,'CREATED', 'Tarea creada');
insert into task_status (id,code,description) values (2,'PENDING', 'Tarea pendiente');
insert into task_status (id,code,description) values (3,'DONE', 'Tarea terminada');
insert into task_status (id,code,description) values (4,'PROGRESS', 'Tarea en progreso');
