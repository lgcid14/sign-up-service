-- Table users
create table if not exists users (
    id uuid primary key,
    name varchar(150),
    email varchar(150) not null,
    password varchar(150) not null,
    created timestamp not null default current_timestamp(),
    lastLogin timestamp not null default current_timestamp(),
    token varchar(180),
    isActive boolean
);

-- Table phones associated to table users
create table if not exists phones (
    id int primary key,
    number bigint,
    cityCode int,
    countryCode varchar(150),
    userId varchar(150)
);

create sequence if not exists phone_sequence_id start with (select max(id) + 1 from phones);