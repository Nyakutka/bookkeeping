create schema if not exists bookkeeping;

use bookkeeping;

create table if not exists departments
(
    id   bigint unsigned auto_increment not null
        primary key,
    name varchar(20)
);

create table if not exists employees
(
    id          bigint unsigned auto_increment not null
        primary key,
    firstName  varchar(20) not null,
    lastName   varchar(20) not null,
    patherName varchar(20),
    position    varchar(50),
    salary      decimal(18, 2)
);

create table if not exists departments_employees
(
    id            bigint unsigned auto_increment not null
        primary key,
    department_id bigint unsigned not null,
    employee_id   bigint unsigned not null,
    constraint fk_depts_empoloyees_depts
        foreign key (department_id) references departments (id),
    constraint fk_depts_employees_employees
        foreign key (employee_id) references employees (id)
);

create table if not exists projects
(
    id            bigint unsigned auto_increment not null
        primary key,
    name          varchar(200)    not null,
    cost          float(18, 2),
    department_id bigint unsigned not null,
    date_beg      date,
    date_end      date,
    date_end_real date,
    constraint fk_projects_depts
        foreign key (department_id) references departments (id)
);

