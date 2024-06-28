INSERT INTO task_manager_schema.roles (name, description) VALUES
('Admin', 'Administrator with full access'),
('User', 'Regular user with limited access');

INSERT INTO task_manager_schema.users (email, name, last_name, user_name, password, role_id) VALUES
('alex.diaz@mockprevired.com', 'Alex', 'Diaz', 'alex.diaz', '$2a$10$bpwGFFMCE2cBIsP/rXozquT3DcU7618grslg0XN8dBFLlJRtABYK.', 1),
('juan.perez@mockprevired.com', 'Juan', 'Perez', 'juan.perez', '$2a$10$bpwGFFMCE2cBIsP/rXozquT3DcU7618grslg0XN8dBFLlJRtABYK.', 2),
('maria.gil@mockprevired.com', 'Maria', 'Gil', 'maria.gil', '$2a$10$bpwGFFMCE2cBIsP/rXozquT3DcU7618grslg0XN8dBFLlJRtABYK.', 2),
('ana.gomez@mockprevired.com', 'Ana', 'Gomez', 'ana.gomez', '$2a$10$bpwGFFMCE2cBIsP/rXozquT3DcU7618grslg0XN8dBFLlJRtABYK.', 2),
('luis.torres@mockprevired.com', 'Luis', 'Torres', 'luis.torres', '$2a$10$bpwGFFMCE2cBIsP/rXozquT3DcU7618grslg0XN8dBFLlJRtABYK.', 2);

INSERT INTO task_manager_schema.statuses (name) VALUES
('To Do'),
('In Progress'),
('Stopped'),
('Completed');

INSERT INTO task_manager_schema.priorities (name) VALUES
('Low'),
('Normal'),
('High'),
('Urgent');

INSERT INTO task_manager_schema.tasks (title, description, start_date, due_date, created_at, status_id, priority_id, user_id) VALUES
('Task 1', 'This task is the number 1', '2024-07-01 09:00:00', '2024-07-10 17:00:00', CURRENT_TIMESTAMP, 1, 1, 1),
('Task 2', 'This task is the number 2', '2024-07-01 09:00:00', '2024-07-09 17:00:00', CURRENT_TIMESTAMP, 2, 1, 1),
('Task 3', 'This task is the number 3', '2024-07-01 09:00:00', '2024-07-08 17:00:00', CURRENT_TIMESTAMP, 4, 4, 1),
('Task 4', 'This task is the number 4', '2024-07-02 09:00:00', '2024-07-08 17:00:00', CURRENT_TIMESTAMP, 1, 3, 2),
('Task 5', 'This task is the number 5', '2024-07-03 09:00:00', '2024-07-07 17:00:00', CURRENT_TIMESTAMP, 1, 3, 2),
('Task 6', 'This task is the number 6', '2024-07-04 09:00:00', '2024-07-11 17:00:00', CURRENT_TIMESTAMP, 2, 1, 3),
('Task 7', 'This task is the number 7', '2024-07-03 09:00:00', '2024-07-15 17:00:00', CURRENT_TIMESTAMP, 2, 1, 4),
('Task 8', 'This task is the number 8', '2024-07-03 09:00:00', '2024-07-15 17:00:00', CURRENT_TIMESTAMP, 1, 3, 4),
('Task 9', 'This task is the number 9', '2024-07-05 09:00:00', '2024-07-15 17:00:00', CURRENT_TIMESTAMP, 1, 2, 4),
('Task 10', 'This task is the number 10', '2024-07-05 09:00:00', '2024-07-15 17:00:00', CURRENT_TIMESTAMP, 2, 3, 5),
('Task 11', NULL, '2024-07-10 09:00:00', '2024-07-11 17:00:00', CURRENT_TIMESTAMP, 3, 3, 5),
('Task 12', NULL, '2024-07-17 09:00:00', '2024-07-28 17:00:00', CURRENT_TIMESTAMP, 3, 1, 5);
