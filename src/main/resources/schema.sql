create table usuarios
(
    id BIGINT auto_increment,
    nombre_usuario VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

create table estados_tarea
(
    id BIGINT auto_increment,
    nombre VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

create table tareas
(
    id BIGINT auto_increment,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    estado_tarea_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT tarea_estado_tarea_fk FOREIGN KEY(estado_tarea_id) REFERENCES estados_tarea(id),
    CONSTRAINT tarea_usuario_fk FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);

CREATE INDEX estado_tarea_idx_fk ON tareas(estado_tarea_id);
CREATE INDEX tarea_usuario_idx_fk ON tareas(usuario_id);