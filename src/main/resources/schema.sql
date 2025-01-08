-- schema.sql
CREATE TABLE IF NOT EXISTS usuario (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       username VARCHAR(50) NOT NULL,
                                       password VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS estado_tarea (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            status VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tarea (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
                                     description VARCHAR(255),
                                     status VARCHAR(50),
                                     user_id VARCHAR(50)
);