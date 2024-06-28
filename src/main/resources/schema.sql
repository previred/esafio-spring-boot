CREATE SCHEMA IF NOT EXISTS task_manager_schema;

CREATE TABLE IF NOT EXISTS task_manager_schema.roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS task_manager_schema.users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES task_manager_schema.roles(id) ON DELETE CASCADE,
    UNIQUE (email),
    UNIQUE (user_name)
);

CREATE TABLE IF NOT EXISTS task_manager_schema.statuses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS task_manager_schema.priorities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS task_manager_schema.tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(64) NOT NULL,
    description VARCHAR(255),
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    due_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status_id BIGINT NOT NULL,
    priority_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES task_manager_schema.users(id) ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES task_manager_schema.statuses(id) ON DELETE CASCADE,
    FOREIGN KEY (priority_id) REFERENCES task_manager_schema.priorities(id) ON DELETE CASCADE
);

CREATE INDEX idx_users_role_id ON task_manager_schema.users(role_id);
CREATE INDEX idx_tasks_user_id ON task_manager_schema.tasks(user_id);
CREATE INDEX idx_tasks_status_id ON task_manager_schema.tasks(status_id);
CREATE INDEX idx_tasks_priority_id ON task_manager_schema.tasks(priority_id);
