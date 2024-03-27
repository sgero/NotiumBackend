drop table if exists evento;
drop table if exists ocio_nocturno;
drop table if exists restaurante;
drop table if exists rpp;
drop table if exists cliente;
drop table if exists direccion;
drop table if exists usuario;



create table usuario
(
    id       serial       not null,
    username varchar(100) not null,
    email    varchar(100) not null,
    password varchar(100) not null,
    rol      integer not null,
    activo   boolean default true,
    verificado boolean default false,
    primary key (id)
);

create table direccion(
    id       serial       not null,
    calle varchar(100) not null,
    numero int4 ,
    puerta varchar(20) ,
    codigo_postal int8 not null ,
    ciudad varchar(100) not null ,
    provincia varchar(150) not null ,
    pais varchar(100) not null ,
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
    activo           boolean default true,
    verificado      boolean default false,
    id_usuario       integer      not null,
    id_direccion    integer not null,
    primary key (id),
    constraint id_cliente_usuario_fk foreign key (id_usuario) references usuario (id),
    constraint id_cliente_direccion_fk foreign key (id_direccion) references direccion (id)
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
    activo           boolean default true,
    verificado      boolean default false,
    id_usuario       integer      not null,
    id_direccion    integer not null,
    primary key (id),
    constraint id_rpp_usuario_fk foreign key (id_usuario) references usuario (id),
    constraint id_rpp_direccion_fk foreign key (id_direccion) references direccion (id)
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
    activo        boolean default true,
    verificado boolean default false,
    id_usuario    integer       not null,
    id_direccion    integer not null,
    primary key (id),
    constraint id_restaurante_usuario_fk foreign key (id_usuario) references usuario (id),
    constraint id_restaurante_direccion_fk foreign key (id_direccion) references direccion (id)
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
    activo        boolean default true,
    verificado boolean default false,
    id_usuario    integer       not null,
    id_direccion    integer not null,
    primary key (id),
    constraint id_ocio_nocturno_usuario_fk foreign key (id_usuario) references usuario (id),
    constraint id_ocio_nocturno_direccion_fk foreign key (id_direccion) references direccion (id)
);

create table evento (
    id serial not null ,
    nombre varchar(30) not null ,
    descripcion varchar(200) not null ,
    fecha timestamp(6) not null ,
    tematica varchar(50) not null ,
    codigo_vestimenta integer not null ,
    edad integer not null ,
    aforo integer not null ,
    activo boolean default true not null ,
    id_ocio_nocturno integer not null ,
    primary key (id),
    constraint id_evento_ocio_nocturno_fk foreign key (id_ocio_nocturno) references ocio_nocturno (id)
);


