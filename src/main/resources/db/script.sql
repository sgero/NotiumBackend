drop table if exists rpp;
drop table if exists ocio_nocturno;
drop table if exists cliente;
drop table if exists restaurante;
drop table if exists usuario;




create table usuario
(
    id       serial primary key,
    username varchar(100) not null,
    email    varchar(100) not null,
    password varchar(100) not null,
    rol      varchar(100) not null,
    activo   boolean

);


create table cliente (
    id serial primary key,
    nombre varchar(100) not null,
    direccion varchar(100) not null,
    telefono varchar(20) not null,
    hora_apertura timestamp not null,
    hora_cierre timestamp not null,
    valoracion boolean,
    disponible boolean,
    imagen_marca varchar(1000) not null,
    activo boolean,
    id_usuario serial references usuario(id)

);

-- create table restaurante (
--     id serial primary key,
--     nombre varchar(100) not null,
--     cif varchar(9) not null,
--     direccion varchar(100) not null,
--     telefono varchar(20) not null,
--
-- );
--
-- create table ocio_nocturno (
--     id serial primary key,
--     nombre varchar(100) not null,
--     email varchar(100) not null,
--     password varchar(100) not null
-- );
--
-- create table rpp (
--     id serial primary key,
--     nombre varchar(100) not null
-- );
--
--

