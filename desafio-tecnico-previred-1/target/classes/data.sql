insert into task_status  (description)values('CREADA');
insert into task_status  (description)values('ASIGNADA');
insert into task_status  (description)values('EN_PROCESO');
insert into task_status  (description)values('FINALIZADA');

-- password : 12345
insert into usuarios 
(username,email,password,role)values('luciano','luciano.pantillon.alc@gmail.com','$2a$10$grht1iEFzAgPG87sWwG.7OfmRYKyThWizvAbSX0hy033d4KPMRXjm','USER');

-- password : 54321
insert into usuarios (username,email,password,role)values('leonardo','leonardo@gmail.com','$2a$10$AIMTpuZ7sU4WFFUKQMdyCuvUWZ3LEjijhdABYOL8xGeY2gACStTo6','USER');

-- password : admin
insert into usuarios (username,email,password,role)values('previred','previred@prueba.com','$2a$10$J.yTUTsaTr59rWvXwGC4ouYw/TqoZKUbiVvfHA3bHZtTqHhBnVFem','USER');