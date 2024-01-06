
INSERT INTO usuario ( name, email, password, created, modified, last_login, is_active) VALUES
  ('harry', 'harry@gmail.com', '$2a$10$NE9Zj7eRPAg5yrjhVpGfqeiQVGl30azMg.VJlbv1Vnz.MmAfOFOlu', '2021-01-24', '2021-01-14', '2021-01-02', true);



INSERT INTO estado_tarea (name) VALUES
  ('to do'),
  ('doing'),
  ('done');

INSERT INTO tarea(title, description,created, modified, taskstate_id) VALUES
    ('TAREA1', 'DESCRIPCION', '2024-01-24', '2024-01-14', 1);