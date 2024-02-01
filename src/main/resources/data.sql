DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS task_status;

CREATE TABLE task_status (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             value_status VARCHAR(10) NOT NULL
);

CREATE TABLE task (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(100) NOT NULL,
                      detail VARCHAR(100) NOT NULL,
                      id_status INT,
                      foreign key (id_status) references task_status(id)
);

INSERT INTO task_status (value_status) VALUES ('ACTIVO');
INSERT INTO task_status (value_status) VALUES ('PENDIENTE');
INSERT INTO task_status (value_status) VALUES ('COMPLETADO');


INSERT INTO task (title, detail, id_status) VALUES ('CEPILLADO','CEPILLADO - 5PM - AGENDADO',2);
INSERT INTO task (title, detail, id_status) VALUES ('TINTE','TINTE PARA CABELLO - 6PM',3);
INSERT INTO task (title, detail, id_status) VALUES ('CORTE','CORTE REGULAR - HOMBRE - 3PM',1);

