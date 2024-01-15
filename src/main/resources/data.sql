
INSERT INTO task_status (description, status) VALUES
    ('Tarea creada', 'OPEN'),
    ('Tarea en progreso', 'IN_PROGRESS'),
    ('Tarea completada con exito', 'COMPLETED'),
    ('Tarea cancelada', 'CANCELLED'),
    ('Tarea bloqueada', 'BLOCKED');

INSERT INTO users (email, role, name, last_name, status, last_login_date) VALUES
    ('admin@admin.com', 'ADMIN', 'admin', 'admin', 'INACTIVE', NOW());
INSERT INTO passwords(user_email, password) VALUES
    ('admin@admin.com','$2a$12$C0gNawpaEWWtJ6Lqw1bRVu5FF7NMvkjIKJmB.sRey0SdMX82q9iFK');