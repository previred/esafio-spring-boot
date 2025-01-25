INSERT INTO users (username, password) VALUES ('user1', '$2a$10$gY9BqwRbegCeOd3JUe/uX.q..E4QrSHT.EDddSwyQZs3.Ln17AbbC');
INSERT INTO users (username, password) VALUES ('user2', '$2a$10$gY9BqwRbegCeOd3JUe/uX.q..E4QrSHT.EDddSwyQZs3.Ln17AbbC');
INSERT INTO users (username, password) VALUES ('user3', '$2a$10$gY9BqwRbegCeOd3JUe/uX.q..E4QrSHT.EDddSwyQZs3.Ln17AbbC');


INSERT INTO tasks (title, description, user_id) VALUES ('Task 1', 'Description 1', 1);
INSERT INTO tasks (title, description, user_id) VALUES ('Task 2', 'Description 2', 1);

INSERT INTO status_task (status_name, task_id, changed_at) VALUES ('CREATED', 1, '2025-01-24T14:20:00Z');
INSERT INTO status_task (status_name, task_id, changed_at) VALUES ('IN_PROGRESS', 1, '2025-01-24T14:25:00Z');
INSERT INTO status_task (status_name, task_id, changed_at) VALUES ('CREATED', 2, '2025-01-24T14:20:00Z');
