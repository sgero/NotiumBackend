drop table if exists promocion;
drop table if exists producto_tipo_bebida;
drop table if exists producto_tipo_plato;
drop table if exists mensaje;
drop table if exists chat;
drop table if exists comentario;
drop table if exists producto;
drop table if exists carta_ocio;
drop table if exists carta_rest;
drop table if exists reserva_restaurante;
drop table if exists turno_restaurante;
drop table if exists mesa_restaurante;
drop table if exists reservado_ocio_cliente ;
drop table if exists reservado_ocio ;
drop table if exists lista_ocio_cliente;
drop table if exists lista_ocio;
drop table if exists entrada_ocio_cliente;
drop table if exists entrada_ocio;
drop table if exists evento;
drop table if exists rpp;
drop table if exists ocio_nocturno;
drop table if exists restaurante;
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
    fecha_nacimiento timestamp(6)    not null,
    activo           boolean default true,
    id_usuario       integer      not null,
    id_direccion    integer not null,
    primary key (id),
    constraint id_cliente_usuario_fk foreign key (id_usuario) references usuario (id),
    constraint id_cliente_direccion_fk foreign key (id_direccion) references direccion (id)
);



create table restaurante
(
    id            serial        not null,
    nombre        varchar(100)  not null,
    cif           varchar(9)    not null,
    telefono      varchar(20)   not null,
    hora_apertura time     not null,
    hora_cierre   time    not null,
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


create table ocio_nocturno
(
    id            serial        not null,
    nombre        varchar(100)  not null,
    cif           varchar(9)    not null,
    telefono      varchar(20)   not null,
    hora_apertura time    not null,
    hora_cierre   time     not null,
    aforo		  int 	   not null,
    imagen_marca  varchar(1000) not null,
    activo        boolean default true,
    verificado boolean default false,
    id_usuario    integer       not null,
    id_direccion    integer not null,
    primary key (id),
    constraint id_ocio_nocturno_usuario_fk foreign key (id_usuario) references usuario (id),
    constraint id_ocio_nocturno_direccion_fk foreign key (id_direccion) references direccion (id)
);

create table rpp
(
    id               serial       not null,
    nombre           varchar(100) not null,
    apellidos        varchar(100) not null,
    dni              varchar(9)   not null,
    telefono         varchar(20)  not null,
    fecha_nacimiento timestamp(6)    not null,
    activo           boolean default true,
    verificado      boolean default false,
    id_usuario       integer      not null,
    id_direccion    integer not null,
    id_ocio_nocturno integer not null ,
    primary key (id),
    constraint id_rpp_usuario_fk foreign key (id_usuario) references usuario (id),
    constraint id_rpp_direccion_fk foreign key (id_direccion) references direccion (id),
    constraint id_rpp_ocio_nocturno foreign key (id_ocio_nocturno) references ocio_nocturno(id)
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

create table entrada_ocio (
                              id serial not null ,
                              precio float not null ,
                              total_entradas integer not null ,
                              activo boolean default  true not null ,
                              id_evento integer not null ,
                              primary key (id),
                              constraint id_entrada_ocio_evento_fk foreign key (id_evento) references evento(id)
);

create table entrada_ocio_cliente(
                                     id serial not null ,
                                     codigo varchar (30) not null ,
                                     fecha_compra timestamp(6) not null ,
                                     id_cliente integer not null ,
                                     id_entrada_ocio integer not null ,
                                     primary key  (id),
                                     constraint id_entrada_ocio_cliente_cliente_fk foreign key (id_cliente) references cliente(id),
                                     constraint id_entrada_ocio_cliente_entrada_ocio_fk foreign key (id_entrada_ocio) references entrada_ocio(id)
);

create table lista_ocio (
                            id serial not null,
                            precio float not null,
                            total_invitaciones int not null,
                            activo boolean default true not null,
                            id_rpp int not null,
                            id_evento int not null,
                            primary key (id),
                            constraint id_lista_ocio_rpp_fk foreign key (id_rpp) references rpp(id),
                            constraint id_lista_ocio_evento_fk foreign key (id_evento) references evento(id)
);

create table lista_ocio_cliente(
                                   id serial not null ,
                                   fecha timestamp(6) not null ,
                                   id_cliente int not null ,
                                   id_lista_ocio int not null ,
                                   primary key (id),
                                   constraint id_lista_ocio_cliente_cliente_fk foreign key (id_cliente) references cliente(id),
                                   constraint id_lista_ocio_cliente_lista_ocio_fk foreign key (id_lista_ocio) references lista_ocio(id)
);

create table reservado_ocio(
                               id serial not null ,
                               reservados_disponibles int not null ,
                               personas_max_por_reservado int not null default 2,
                               precio float not null ,
                               activo boolean default true not null ,
                               id_evento int not null,
                               primary key (id),
                               constraint id_reservado_ocio_evento_fk foreign key (id_evento) references evento(id)
);

create table reservado_ocio_cliente(
                                       id serial not null ,
                                       codigo varchar(10) not null ,
                                       fecha_compra timestamp(6) not null ,
                                       cantidad_personas int not null ,
                                       id_cliente int not null ,
                                       id_reservado_ocio int not null ,
                                       primary key (id),
                                       constraint id_reservado_ocio_cliente_cliente_fk foreign key (id_cliente) references cliente(id),
                                       constraint id_reservado_ocio_cliente_reservado_ocio_fk foreign key (id_reservado_ocio) references reservado_ocio(id)
);

create table mesa_restaurante (
                                  id serial not null ,
                                  num_plazas int not null ,
                                  reservada boolean default false not null ,
                                  activo boolean default true not null ,
                                  id_restaurante integer not null ,
                                  primary key (id),
                                  constraint id_mesa_restaurante_restaurante_fk foreign key (id_restaurante) references restaurante (id)
);

create table turno_restaurante (
                                   id serial not null ,
                                   hora_inicio time not null ,
                                   hora_fin time not null ,
                                   activo boolean default true not null ,
                                   id_restaurante integer not null ,
                                   primary key (id),
                                   constraint id_turno_restaurante_restaurante_fk foreign key (id_restaurante) references restaurante (id)
);

create table reserva_restaurante (
                                     id serial not null ,
                                     codigo_reserva varchar(10) not null ,
                                     activo boolean default true not null ,
                                     fecha date not null ,
                                     id_turno_restaurante integer not null ,
                                     id_cliente integer not null ,
                                     id_restaurante integer not null ,
                                     id_mesa_restaurante integer not null ,
                                     primary key (id),
                                     constraint id_turno_restaurante_reserva_restaurante_fk foreign key (id_turno_restaurante) references turno_restaurante (id),
                                     constraint id_cliente_reserva_restaurante_fk foreign key (id_cliente) references cliente (id),
                                     constraint id_restaurante_reserva_restaurante_fk foreign key (id_restaurante) references restaurante (id),
                                     constraint id_mesa_restaurante_reserva_restaurante_fk foreign key (id_mesa_restaurante) references mesa_restaurante (id)

);

create table carta_rest (
                            id serial not null ,
                            activo boolean default true not null ,
                            id_restaurante integer not null ,
                            primary key (id),
                            constraint id_carta_rest_restaurante_fk foreign key (id_restaurante) references restaurante (id)
);

create table carta_ocio (
                            id serial not null ,
                            activo boolean default true not null ,
                            id_ocio_nocturno integer not null ,
                            primary key (id),
                            constraint id_carta_ocio_ocio_nocturno_fk foreign key (id_ocio_nocturno) references ocio_nocturno (id)
);



create table producto (
                          id serial not null ,
                          nombre varchar(100) not null ,
                          tipo_categoria int not null,
                          activo boolean default true not null ,
                          id_carta_rest integer ,
                          id_carta_ocio integer ,
                          primary key (id),
                          constraint id_producto_carta_restaurante_fk foreign key (id_carta_rest) references carta_rest (id),
                          constraint id_ocomentario_ocio_nocturno_fk foreign key (id_carta_ocio) references carta_ocio (id)
);



create table comentario (
                            id serial not null ,
                            texto varchar(150) not null ,
                            fecha_comentario timestamp(6) not null ,
                            activo boolean default true not null ,
                            id_restaurante integer not null ,
                            id_ocio_nocturno integer not null ,
                            id_cliente integer not null ,
                            primary key (id),
                            constraint id_comentario_restaurante_fk foreign key (id_restaurante) references restaurante (id),
                            constraint id_ocomentario_ocio_nocturno_fk foreign key (id_ocio_nocturno) references ocio_nocturno (id),
                            constraint id_comentario_cliente_fk foreign key (id_cliente) references cliente (id)
);


create table chat (
                      id serial not null ,
                      activo boolean default true not null ,
                      id_evento integer not null ,
                      primary key (id),
                      constraint id_chat_evento_fk foreign key (id_evento) references evento (id)

);

create table mensaje (
                         id serial not null ,
                         activo boolean default true not null ,
                         texto varchar(150) not null ,
                         hora_envio time not null ,
                         id_cliente integer not null ,
                         id_chat integer not null ,
                         primary key (id),
                         constraint id_mensaje_cliente_fk foreign key (id_cliente) references cliente (id),
                         constraint id_mensaje_chat_fk foreign key (id_chat) references chat (id)

);


create table producto_tipo_plato (
                                     id serial not null ,
                                     precio float not null,
                                     id_producto int not null,
                                     tipo_plato int not null,
                                     primary key (id),
                                     constraint id_producto_tipo_plato_producto_fk foreign key (id_producto) references producto (id)

);

create table producto_tipo_bebida (
                                      id serial not null ,
                                      precio float not null,
                                      id_producto int not null,
                                      tipo_bebida int not null,
                                      primary key (id),
                                      constraint id_producto_tipo_bebida_producto_fk foreign key (id_producto) references producto (id)
);

create table promocion (
                           id serial not null ,
                           tipo int not null ,
                           titulo varchar(50) not null ,
                           foto varchar(10000) not null,
                           activo boolean default true not null ,
                           id_evento integer not null ,
                           id_restaurante integer not null ,
                           primary key (id),
                           constraint id_promocion_evento_fk foreign key (id_evento) references evento (id),
                           constraint id_promocion_restaurante_fk foreign key (id_restaurante) references restaurante (id)
);