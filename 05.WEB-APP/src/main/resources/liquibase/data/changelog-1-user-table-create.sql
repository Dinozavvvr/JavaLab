--liquibase formatted sql

--changeset DinarShagaliev:1

create table "user" (
    id bigserial primary key,
    username varchar(14) unique,
    first_name varchar(30),
    last_name varchar(30),
    password varchar
);