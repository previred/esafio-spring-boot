insert into estado_tarea  (descripcion)values('CREADA');
insert into estado_tarea  (descripcion)values('ASIGNADA');
insert into estado_tarea  (descripcion)values('EN_PROCESO');
insert into estado_tarea  (descripcion)values('FINALIZADA');

-- password : 12345
insert into usuario (username,email,password,role)values('cmartinez','cmartinez@gmail.com','$2a$10$grht1iEFzAgPG87sWwG.7OfmRYKyThWizvAbSX0hy033d4KPMRXjm','USER');

-- password : 54321
insert into usuario (username,email,password,role)values('mlopez','mlopez@gmail.com','$2a$10$AIMTpuZ7sU4WFFUKQMdyCuvUWZ3LEjijhdABYOL8xGeY2gACStTo6','USER');

-- password : admin
insert into usuario (username,email,password,role)values('previred','previred@prueba.com','$2a$10$J.yTUTsaTr59rWvXwGC4ouYw/TqoZKUbiVvfHA3bHZtTqHhBnVFem','USER');
