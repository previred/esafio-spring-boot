
DROP TABLE IF EXISTS task_status CASCADE;
CREATE TABLE task_status (id uuid NOT NULL,
                          name varchar(60),
                          description varchar(255) NOT NULL,
                          PRIMARY KEY (id));


DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id uuid NOT NULL,
                    dni varchar(20),
                    name varchar(60),
                    phone varchar(15) NOT NULL,
                    address varchar(100) NOT NULL,
                    email varchar(100) NOT NULL,
                    password varchar(100) NOT NULL,
                    PRIMARY KEY (id));



DROP TABLE IF EXISTS task CASCADE;
CREATE TABLE task (id uuid NOT NULL,
                   start_date date ,
                   end_date date ,
                   description varchar(255) NOT NULL,
                   user_id uuid NOT NULL,
                   task_status_id uuid NOT NULL,
                   PRIMARY KEY (id));


ALTER TABLE task ADD CONSTRAINT FK_task_status FOREIGN KEY (task_status_id) REFERENCES task_status (id);
ALTER TABLE task ADD CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES users (id);