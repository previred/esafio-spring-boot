DROP TABLE IF EXISTS sessions CASCADE;
DROP TABLE IF EXISTS tasks CASCADE;
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE sessions (id_session uuid NOT NULL, token varchar(500) NOT NULL, is_active boolean NOT NULL, last_login timestamp NOT NULL, id_user uuid NOT NULL, PRIMARY KEY (id_session));
CREATE TABLE tasks (id_task uuid NOT NULL, description varchar(100) NOT NULL, status varchar(10) NOT NULL, created timestamp NOT NULL, modified timestamp NOT NULL, id_user uuid NOT NULL, PRIMARY KEY (id_task));
CREATE TABLE users (id_user uuid NOT NULL, name varchar(80) NOT NULL, email varchar(100) NOT NULL, password varchar(100) NOT NULL, created timestamp NOT NULL, modified timestamp NOT NULL, PRIMARY KEY (id_user));
ALTER TABLE tasks ADD CONSTRAINT FKtasks547531 FOREIGN KEY (id_user) REFERENCES users (id_user);
ALTER TABLE sessions ADD CONSTRAINT FKsessions401362 FOREIGN KEY (id_user) REFERENCES users (id_user);