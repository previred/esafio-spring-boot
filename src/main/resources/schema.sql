DROP TABLE IF EXISTS login CASCADE;
DROP TABLE IF EXISTS task_status CASCADE;
DROP TABLE IF EXISTS tasks CASCADE;
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE login (login_id uuid NOT NULL, email varchar(50) NOT NULL UNIQUE, password varchar(80) NOT NULL, PRIMARY KEY (login_id));
CREATE TABLE task_status (task_status_id uuid NOT NULL, description varchar(255) NOT NULL, PRIMARY KEY (task_status_id));
CREATE TABLE tasks (task_id uuid NOT NULL, description varchar(255) NOT NULL, start_date timestamp, end_date timestamp, user_id uuid NOT NULL, task_status_id uuid NOT NULL, PRIMARY KEY (task_id));
CREATE TABLE users (user_id uuid NOT NULL, identification_number varchar(20) NOT NULL UNIQUE, name varchar(100) NOT NULL, phone varchar(15) NOT NULL, login_id uuid NOT NULL UNIQUE, PRIMARY KEY (user_id));
ALTER TABLE users ADD CONSTRAINT FKusers719567 FOREIGN KEY (login_id) REFERENCES login (login_id);
ALTER TABLE tasks ADD CONSTRAINT FKtasks132703 FOREIGN KEY (task_status_id) REFERENCES task_status (task_status_id);
ALTER TABLE tasks ADD CONSTRAINT FKtasks555156 FOREIGN KEY (user_id) REFERENCES users (user_id);

