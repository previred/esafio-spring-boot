create table ROLE (
    id bigint auto_increment not null,
    name varchar(100),
    primary  key (id)
);

create table SYSUSER (
    id bigint auto_increment not null,
    username varchar(100) not null,
    name varchar(100),
    last_login timestamp,
    password varchar(255) not null,
    active boolean,
    primary key (id)
);

create table TASK(
    id bigint auto_increment not null,
    task_name varchar(50) not null,
    primary  key (id)
);

create table TASK_STATUS(
    id bigint auto_increment not null,
    task_status_name varchar(50) not null,
    active boolean not null,
    primary  key (id)

);

create table USER_HAS_ROLE (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);

create table ASSIGN_TASK(
    id bigint auto_increment not null,
    id_user_assign bigint not null,
    id_task_status bigint not null,
    id_task        bigint not null
);

alter table ROLE add constraint ROLE_NAME_UINDEX unique (name);
alter table USER_HAS_ROLE add constraint USER_HAS_ROLE_ROLE__FK foreign key (role_id) references ROLE;
alter table USER_HAS_ROLE add constraint USER_HAS_ROLE_USER__FK foreign key (user_id) references SYSUSER;