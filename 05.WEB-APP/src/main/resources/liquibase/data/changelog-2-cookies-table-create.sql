--liquibase formatted sql

--changeset DinarShagaliev:2

create table cookie(
    user_id bigserial,
    auth char(36) unique,
    foreign key(user_id) references "user"(id)
);