CREATE TABLE IF NOT EXISTS Rol (
    codigo VARCHAR(5) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Usuario (
    rut VARCHAR(12) PRIMARY KEY,
    usuario VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    CONSTRAINT unique_usuario UNIQUE (usuario)
);

CREATE TABLE IF NOT EXISTS Usuario_Rol (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_rol VARCHAR(5) NOT NULL,
    rut VARCHAR(12)NOT NULL,
    CONSTRAINT unique_usuario_rol UNIQUE (codigo_rol, rut)
);

CREATE TABLE IF NOT EXISTS Tablero (
    codigo VARCHAR(5) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    fecha_creacion TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Tarea (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_rut VARCHAR(12),
    codigo_tablero VARCHAR(5) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    estado VARCHAR(50),
    fecha_creacion TIMESTAMP NOT NULL,
    fecha_asignacion TIMESTAMP,
    fecha_termino TIMESTAMP,
    FOREIGN KEY (usuario_rut) REFERENCES Usuario(rut) ON DELETE CASCADE,
    FOREIGN KEY (codigo_tablero) REFERENCES Tablero(codigo) ON DELETE CASCADE
);






