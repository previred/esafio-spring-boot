-- H2 2.3.232;
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.USERS;   
INSERT INTO "PUBLIC"."USERS" VALUES
(1, 'jano@gmail.com', '$2a$12$tmIa0j75GNzd4gnWA71znO20REZGnL3ns5laM0DDgTZYD8NomDtsi', 'jano');           
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.STATUSTASK;              
INSERT INTO "PUBLIC"."STATUSTASK" VALUES
(1, 'Creado'),
(2, 'En Espera'),
(3, 'Asignado'),
(4, 'En Proceso'),
(5, 'Bloqueado'),
(6, 'Finalizado');      
