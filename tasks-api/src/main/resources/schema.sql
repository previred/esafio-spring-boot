drop table if exists TOKEN;
drop table if exists USERS;
drop table if exists TASK;
drop table if exists TASK_STATUS;
create table USERS(
  USER_ID int not null AUTO_INCREMENT,
  USER_NAME varchar(100) not null,
  EMAIL varchar(200),
  PASSWORD varchar(100),
  ROLE VARCHAR (100),
  CREATED DATETIME,
  MODIFIED DATETIME,
  LAST_LOGIN DATETIME,
  TOKEN VARCHAR(200),
  IS_ACTIVE BOOLEAN,
PRIMARY KEY ( USER_ID )
);

create table TOKEN(
  TOKEN_ID int not null AUTO_INCREMENT,
  USER_ID int not null,
  TOKEN varchar (200) not null,
  TOKEN_TYPE varchar (50) not null,
  REVOKED boolean,
  EXPIRED boolean,
  PRIMARY KEY ( TOKEN_ID ),
  FOREIGN KEY ( USER_ID ) REFERENCES USERS(USER_ID)
);

create table TASK_STATUS(
  TASK_STATUS_ID int not null AUTO_INCREMENT,
  TASK_STATUS_NAME varchar (100) not null,
  PRIMARY KEY ( TASK_STATUS_ID )
);

create table TASK(
  TASK_ID int not null AUTO_INCREMENT,
  TASK_STATUS_ID int not null,
  TASK_NAME varchar (100) not null,
  TASK_DESCRIPTION varchar (300) null,
  PRIMARY KEY ( TASK_ID ),
  FOREIGN KEY ( TASK_STATUS_ID ) REFERENCES TASK_STATUS(TASK_STATUS_ID)
);
