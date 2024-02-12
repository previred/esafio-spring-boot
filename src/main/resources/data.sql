--DROP TABLE IF EXISTS task_status;
--CREATE TABLE task_status (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    status VARCHAR(30)
--);

--DROP TABLE IF EXISTS users;
--CREATE TABLE users (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    name VARCHAR(50),
--    last_name VARCHAR(50),
--    dni VARCHAR(12),
--    email VARCHAR(255),
--    role VARCHAR(30)
--);

--DROP TABLE IF EXISTS task;
--CREATE TABLE task (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    title VARCHAR(255),
--    description VARCHAR(255),
--    creation_date TIMESTAMP,
--    last_update_date TIMESTAMP,
--    delete_date TIMESTAMP,
--    user_id BIGINT NOT NULL,
--    status_id BIGINT NOT NULL,
--    FOREIGN KEY (status_id) REFERENCES task_status(id),
--    FOREIGN KEY (user_id) REFERENCES users(id)
--);


INSERT INTO users (id, name, last_name, dni, email, role) VALUES (1, 'Miguel', 'San Juan', '179426870', 'miguel.sanjuanm@gmail.com', 'ADMIN');
INSERT INTO users (id, name, last_name, dni, email, role) VALUES (2, 'Karina', 'Araya', '98064362', 'karina@gmail.com', 'USER');

-- INSERT INTO task_status (id, STATUS) VALUES (1, 'PENDING');
-- INSERT INTO task_status (id, STATUS) VALUES (2, 'DOING');
-- INSERT INTO task_status (id, STATUS) VALUES (3, 'DONE');
-- INSERT INTO task_status (id, STATUS) VALUES (4, 'BLOCKED');

