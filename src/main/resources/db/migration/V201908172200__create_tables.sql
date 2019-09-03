create table role(
    id integer auto_increment,
    name varchar(100) not null,
    constraint pk_role_id primary key(id)
);

create table employee(
    id integer auto_increment,
    name varchar(100) not null,
    role_id integer not null,
    company_time integer not null,
    constraint pk_employee_id primary key(id),
    constraint fk_role_id_employee foreign key(role_id) references role(id)
);