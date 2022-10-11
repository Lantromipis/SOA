create table country
(
    id   bigserial primary key,
    name text not null
);

create table house
(
    id               bigserial primary key,
    name             text    not null,
    year             bigint  not null,
    number_of_floors bigint  not null,
    number_of_lifts  bigint  not null,
    country_id       bigint  not null,
    donated          boolean not null,
    sponsor_id       bigint
);

create table sponsor
(
    id   bigserial primary key,
    name text not null
);

