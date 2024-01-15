CREATE TABLE users (
    email VARCHAR(100) PRIMARY KEY,
    role ENUM('ADMIN', 'MANAGER', 'EMPLOYEE') NOT NULL,
    name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE', 'PENDING_APPROVAL', 'REJECTED', 'APPROVED') NOT NULL,
    last_login_date TIMESTAMP WITH TIME ZONE
);

CREATE TABLE passwords (
    user_email VARCHAR(100) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_email) REFERENCES users(email)
);

CREATE TABLE task_status (
    status VARCHAR(20) PRIMARY KEY,
    description VARCHAR(150)
);

CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT ,
    description VARCHAR(255) NOT NULL,
    user_email VARCHAR(100) NOT NULL ,
    status VARCHAR(20) NOT NULL,
    comment VARCHAR(250),
    assigned_date TIMESTAMP WITH TIME ZONE,
    init_date TIMESTAMP WITH TIME ZONE,
    completed_date TIMESTAMP WITH TIME ZONE,
    blocked_date TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (user_email) REFERENCES users(email),
    FOREIGN KEY (status) REFERENCES task_status(status)
);
