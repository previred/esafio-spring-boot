insert into task_status values (1, 'TO_DO');
insert into task_status values (2, 'IN_PROGRESS');
insert into task_status values (3, 'DONE');

insert into USER_ENTITY (created_at, email, fullname, password)
values (CURRENT_TIMESTAMP(), 'usuario1@mail.com', 'Usuario 1', '$2a$10$jkx4E9fBuoKb8PxxaAOTlukI403z64buF5OzdQHsaC6hrU/7O.0CK');
insert into USER_ENTITY (created_at, email, fullname, password)
values (CURRENT_TIMESTAMP(), 'usuario2@mail.com', 'Usuario 2', '$2a$10$jkx4E9fBuoKb8PxxaAOTlukI403z64buF5OzdQHsaC6hrU/7O.0CK');
insert into USER_ENTITY (created_at, email, fullname, password)
values (CURRENT_TIMESTAMP(), 'usuario3@mail.com', 'Usuario 3', '$2a$10$jkx4E9fBuoKb8PxxaAOTlukI403z64buF5OzdQHsaC6hrU/7O.0CK');