drop table if exists restaurante;
drop table if exists club;
drop table if exists cliente;
drop table if exists categoria;



create table restaurante (
    id serial primary key,
    nombre varchar(100) not null,
    direccion varchar(100) not null,
    telefono varchar(20) not null
);

create table club (
    id serial primary key,
    nombre varchar(100) not null,
    direccion varchar(100) not null,
    telefono varchar(20) not null
);

create table cliente (
    id serial primary key,
    nombre varchar(100) not null,
    email varchar(100) not null,
    password varchar(100) not null
);

create table categoria (
    id serial primary key,
    nombre varchar(100) not null
);



