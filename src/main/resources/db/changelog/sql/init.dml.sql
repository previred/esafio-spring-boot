-- liquibase formatted sql

-- changeset andresduran0205:inserts_table_user.0.1 context: dev
INSERT INTO auth.user_ (id, firstname, lastname, phone, enable)
VALUES ('018eac36-6063-7f52-9f1c-75ad0881fb30', 'Alejandro', 'Martinez', 3058520079, true);

INSERT INTO auth.user_ (id, firstname, lastname, phone, enable)
VALUES ('018eac36-6063-725f-9d1d-2562a2516940', 'Fernanda', 'Molina', 3109015507, true);

INSERT INTO auth.user_ (id, firstname, lastname, phone, enable)
VALUES ('0b79f49d-c974-4785-b1c2-700e2085d491', 'Camilo Andres', 'Sacristan', 3119820152, true);


-- changeset andresduran0205:inserts_table_auth_user.0.1 context: dev

INSERT INTO auth.auth_user (id, user_id, username, password)
VALUES ('dd120f56-6a1c-4b59-a211-ff2b2f8af0fe', '018eac36-6063-7f52-9f1c-75ad0881fb30', 'testing@yopmail.com',
        '202cb962ac59075b964b07152d234b70');

INSERT INTO auth.auth_user (id, user_id, username, password)
VALUES ('caae8971-897f-4e1f-aca4-6f03f44aa059', '018eac36-6063-725f-9d1d-2562a2516940', 'fermolina@yopmail.com',
        'e10adc3949ba59abbe56e057f20f883e');

INSERT INTO auth.auth_user (id, user_id, username, password)
VALUES ('fe4043a6-ab05-4519-a409-ea47327bf7fd', '0b79f49d-c974-4785-b1c2-700e2085d491', 'camisacristan@yopmail.com',
        'e10adc3949ba59abbe56e057f20f883e');


-- changeset andresduran0205:inserts_table_status_task.0.1 context: dev

INSERT INTO core.status_task (id, name)
VALUES (1, 'NEW'),
       (2, 'IN PROGRESS'),
       (3, 'DONE'),
       (4, 'CANCELED'),
       (5, 'CHECK');
