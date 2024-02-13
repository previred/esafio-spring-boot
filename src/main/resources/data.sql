-- Insert predefined task states
INSERT INTO task_states (id, name) VALUES (1, 'PENDIENTE');
INSERT INTO task_states (id, name) VALUES (2, 'EN_PROGRESO');
INSERT INTO task_states (id, name) VALUES (3, 'COMPLETADO');

-- Inserta algunos usuarios con UUIDs como strings y asigna roles
INSERT INTO users (id, username, password, email, role) VALUES (RANDOM_UUID(), 'user1', '$2a$12$jIqoRZeFoEVQZTWQrC6yYOkLJ6rHQhLsuAo/VKhKu.mLq590FbbC2', 'user1@example.com', 'ROLE_USER');
INSERT INTO users (id, username, password, email, role) VALUES (RANDOM_UUID(), 'user2', '$2a$12$jIqoRZeFoEVQZTWQrC6yYOkLJ6rHQhLsuAo/VKhKu.mLq590FbbC2', 'user2@example.com', 'ROLE_ADMIN');
