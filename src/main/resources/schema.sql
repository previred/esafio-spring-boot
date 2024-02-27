CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(255),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50),
    username VARCHAR(100) UNIQUE
);
CREATE TABLE estadotarea (
    estado_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255)
);