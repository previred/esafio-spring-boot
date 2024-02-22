--
-- Dummy data for testing
--
insert into registered_user (id, email, password) values
(1, 'user1@email.com', '$2a$10$2L8YXy7SAfGGFTRWFaW55OKQfV.uJYKLrqL4epJk0c2WdeO3Tz/ti');

insert into registered_user (id, email, password) values
(2, 'user2@email.com', '$2a$10$PcI2/Vf0.RJfDNi9M4SYkOt.UpGNLvyt9tlK5UBmfyTUL49BoQ9Me');

insert into task (id, name, description, status, user_id) values
(1, 'Conquistar el mundo', 'Lo que hacemos todas las noches pinky', 'DONE', 1);

insert into task (id, name, description, status, user_id) values
(2, 'Preparar cafe', 'Latte con caramelo', 'TODO', 1);
