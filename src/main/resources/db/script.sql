drop table if exists datos_comprador;
drop table if exists promocion;
drop table if exists producto_formato;
drop table if exists formato;
drop table if exists restaurante_clase;
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
drop table if exists clase;
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
    token_verificacion varchar(100),
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
    hora_apertura varchar(7)     not null,
    hora_cierre   varchar(7)    not null,
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
                        nombre varchar(200) not null ,
                        descripcion varchar(200) not null ,
                        fecha timestamp(6) not null ,
                        tematica varchar(50) not null ,
                        codigo_vestimenta integer not null ,
                        edad integer not null ,
                        aforo integer not null ,
                        activo boolean default true not null ,
                        cartel varchar(50000),
                        id_ocio_nocturno integer not null ,
                        primary key (id),
                        constraint id_evento_ocio_nocturno_fk foreign key (id_ocio_nocturno) references ocio_nocturno (id)
);

create table entrada_ocio (
                              id serial not null ,
                              precio float not null ,
                              total_entradas integer not null ,
                              activo boolean default  true not null ,
                              detalle_entrada varchar(200),
                              consumiciones integer,
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
                                     id_promocion integer,
                                     datos_comprador integer,
                                     primary key  (id),
                                     constraint id_entrada_ocio_cliente_cliente_fk foreign key (id_cliente) references cliente(id),
                                     constraint id_entrada_ocio_cliente_entrada_ocio_fk foreign key (id_entrada_ocio) references entrada_ocio(id),
                                     constraint  fk_entrada_ocio_cliente_promocion foreign key (id_promocion) references promocion(id),
                                     constraint fk_entrada_ocio_cliente_datos_comprador foreign key (datos_comprador) references datos_comprador(id)
);

create table lista_ocio (
                            id serial not null,
                            precio float not null,
                            total_invitaciones int not null,
                            activo boolean default true not null,
                            detalle_lista varchar(200),
                            consumiciones integer,
                            id_rpp int not null,
                            id_evento int not null,
                            primary key (id),
                            constraint id_lista_ocio_rpp_fk foreign key (id_rpp) references rpp(id),
                            constraint id_lista_ocio_evento_fk foreign key (id_evento) references evento(id)
);

create table lista_ocio_cliente(
                                   id serial not null ,
                                   fecha timestamp(6) not null ,
                                   codigo varchar(200),
                                   id_cliente int not null ,
                                   id_lista_ocio int not null ,
                                   id_promocion integer,
                                   datos_comprador integer,
                                   primary key (id),
                                   constraint id_lista_ocio_cliente_cliente_fk foreign key (id_cliente) references cliente(id),
                                   constraint id_lista_ocio_cliente_lista_ocio_fk foreign key (id_lista_ocio) references lista_ocio(id),
                                   constraint  fk_lista_ocio_cliente_promocion foreign key (id_promocion) references promocion(id),
                                   constraint fk_lista_ocio_cliente_datos_comprador foreign key (datos_comprador) references datos_comprador(id)
);

create table reservado_ocio(
                               id serial not null ,
                               reservados_disponibles int not null ,
                               personas_max_por_reservado int not null default 2,
                               precio float not null ,
                               activo boolean default true not null ,
                               detalle_reservado varchar(200),
                               botellas integer,
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
                                       id_promocion integer,
                                       primary key (id),
                                       constraint id_reservado_ocio_cliente_cliente_fk foreign key (id_cliente) references cliente(id),
                                       constraint id_reservado_ocio_cliente_reservado_ocio_fk foreign key (id_reservado_ocio) references reservado_ocio(id),
                                       constraint fk_reservado_ocio_cliente_promocion foreign key (id_promocion) references promocion(id)
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
                                   hora_inicio varchar(7) not null ,
                                   hora_fin varchar(7) not null ,
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
                            valoracion int,
                            codigo_reserva varchar(50) not null,
                            activo boolean default true not null ,
                            id_restaurante integer ,
                            id_ocio_nocturno integer ,
                            primary key (id),
                            constraint id_comentario_restaurante_fk foreign key (id_restaurante) references restaurante (id),
                            constraint id_ocomentario_ocio_nocturno_fk foreign key (id_ocio_nocturno) references ocio_nocturno (id)
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

create table clase (
                         id serial not null ,
                         clase varchar(100) not null ,
                         primary key (id)
);

create table restaurante_clase (
                          id serial not null ,
                          id_restaurante int not null,
                          id_clase int not null,
                          primary key (id),
                          constraint id_restaurante_clase_fk foreign key (id_restaurante) references restaurante (id),
                          constraint id_clase_restaurante_fk foreign key (id_clase) references clase (id)
);


create table formato (
                                     id serial not null ,
                                     formato varchar(100) not null ,
                                     primary key (id)
);

create table producto_formato (
                                      id serial not null ,
                                      precio float not null,
                                      id_producto int not null,
                                      id_formato int not null,
                                      primary key (id),
                                      constraint id_producto_tipo_fk foreign key (id_producto) references producto (id),
                                      constraint id_formato_tipo_fk foreign key (id_formato) references formato (id)
);

create table promocion (
                           id serial not null ,
                           tipo int not null ,
                           titulo varchar(50) not null ,
                           foto varchar(10000) not null,
                           activo boolean default true not null ,
                           codigo varchar(100) not null,
                           id_restaurante integer,
                           primary key (id),
                           constraint id_promocion_restaurante_fk foreign key (id_restaurante) references restaurante (id)
);

create table datos_comprador (
     id               serial       not null,
     nombre           varchar(100) not null,
     apellidos        varchar(100) not null,
     email            varchar(100)   not null,
     telefono         varchar(20)  not null,
     fecha_nacimiento timestamp(6)    not null,
     reservado_ocio_cliente integer,
     genero integer,
     primary key (id),
    constraint fk_datos_comprador_reservado_ocio_cliente foreign key (reservado_ocio_cliente) references reservado_ocio_cliente(id)
);


INSERT INTO clase (clase) VALUES
                                  ('CHINO'),
                                  ('KOREANO'),
                                  ('JAPONES'),
                                  ('ESPAÑOL'),
                                  ('ITALIANO'),
                                  ('FRANCES'),
                                  ('ALEMAN'),
                                  ('BRITANICO'),
                                  ('MEXICANO');

INSERT INTO formato (formato) VALUES
                                  ('TAPA'),
                                  ('MEDIA'),
                                  ('RACION'),
                                  ('CHUPITO'),
                                  ('COPA'),
                                  ('JARRA'),
                                  ('VASO'),
                                  ('PINTA'),
                                  ('MACETA');


