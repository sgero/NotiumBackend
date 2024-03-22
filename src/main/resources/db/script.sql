
drop table if exists ocio_nocturno;
drop table if exists restaurante;
drop table if exists rpp;
drop table if exists cliente;
drop table if exists usuario;



create table usuario
(
    id       serial       not null,
    username varchar(100) not null,
    email    varchar(100) not null,
    password varchar(100) not null,
    rol      varchar(100) not null,
    activo   boolean,
    primary key (id)
);


create table cliente
(
    id               serial       not null,
    nombre           varchar(100) not null,
    apellidos        varchar(100) not null,
    dni              varchar(9)   not null,
    telefono         varchar(20)  not null,
    fecha_nacimiento timestamp    not null,
    direccion        varchar(100) not null,
    activo           boolean,
    id_usuario       integer      not null,
    primary key (id),
    constraint id_cliente_usuario_fk foreign key (id_usuario) references usuario (id)
);

create table rpp
(
    id               serial       not null,
    nombre           varchar(100) not null,
    apellidos        varchar(100) not null,
    dni              varchar(9)   not null,
    telefono         varchar(20)  not null,
    fecha_nacimiento timestamp    not null,
    direccion        varchar(150) not null,
    activo           boolean,
    id_usuario       integer      not null,
    primary key (id),
    constraint id_rpp_usuario_fk foreign key (id_usuario) references usuario (id)
);

create table restaurante
(
    id            serial        not null,
    nombre        varchar(100)  not null,
    cif           varchar(9)    not null,
    direccion     varchar(200)  not null,
    telefono      varchar(20)   not null,
    hora_apertura timestamp     not null,
    hora_cierre   timestamp     not null,
    valoracion    boolean,
    disponible    boolean,
    imagen_marca  varchar(1000) not null,
    activo        boolean,
    id_usuario    integer       not null,
    primary key (id),
    constraint id_restaurante_usuario_fk foreign key (id_usuario) references usuario (id)
);


--Y el aforo???
create table ocio_nocturno
(
    id            serial        not null,
    nombre        varchar(100)  not null,
    cif           varchar(9)    not null,
    direccion     varchar(200)  not null,
    telefono      varchar(20)   not null,
    hora_apertura timestamp     not null,
    hora_cierre   timestamp     not null,
    valoracion    boolean,
    disponible    boolean,
    imagen_marca  varchar(1000) not null,
    activo        boolean,
    id_usuario    integer       not null,
    primary key (id),
    constraint id_ocio_nocturno_usuario_fk foreign key (id_usuario) references usuario (id)
);





