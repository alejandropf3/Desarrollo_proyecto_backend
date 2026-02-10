create database economic;
use economic;

create table Usuario(
ID_usuario bigint auto_increment primary key,
Nombre varchar(255) not null,
Contraseña varchar(255) not null
);

create table Email(
ID_correo int auto_increment primary key,
ID_usuario bigint,
Correo_electronico varchar(255) not null unique,
foreign key (ID_usuario) references Usuario(ID_usuario)
);

create table Imagenes(
ID_imagen int auto_increment primary key,
ID_usuario bigint,
Url_imagen varchar(500) not null,
foreign key (ID_usuario) references Usuario(ID_usuario)
);

create table Rol(
ID_rol int auto_increment primary key,
Nombre_rol varchar(100) not null unique
);

create table Permisos(
ID_permisos int auto_increment primary key,
Permiso varchar (100) not null unique
);

create table Rol_Permisos(
ID_rol int not null,
ID_permisos int not null,
PRIMARY KEY (ID_rol, ID_permisos),
foreign key (ID_rol) references Rol(ID_rol),
foreign key (ID_permisos) references Permisos(ID_permisos)
);

create table Usuario_Rol(
ID_rol int not null,
ID_usuario bigint not null,
primary key (ID_rol, ID_usuario),
foreign key (ID_rol) references Rol(ID_rol),
foreign key (ID_usuario) references Usuario(ID_usuario)
);

create table Token_recuperacion(
ID_token int auto_increment primary key,
ID_usuario bigint,
Token varchar(255) not null unique,
Fecha_creacion datetime not null,
Fecha_expriracion datetime,
Estado boolean not null,
foreign key (ID_usuario) references Usuario(ID_usuario)
);

create table Categoria(
ID_categoria int auto_increment primary key,
Tipo_transaccion enum ("Ingreso","Egreso") not null,
Nombre_categoria varchar (100) not null unique
);

create table Metas_Ahorro(
ID_meta bigint auto_increment primary key,
ID_usuario bigint not null,
Nombre_meta varchar(255) not null,
Monto_objetivo decimal(10, 2) not null,
Monto_actual decimal(10, 2) default 0.00,
Fecha_creacion date not null,
Fecha_limite date not null,
Estado enum("Activa", "Completada", "Cancelada") default "Activa",
foreign key (ID_usuario) references Usuario(ID_usuario)
);

create table Transacciones(
ID_transaccion bigint auto_increment primary key,
ID_usuario bigint,
ID_categoria int,
ID_meta bigint null,
Valor_transaccion decimal(10, 2) not null,
Descripcion varchar (255) null,
Fecha_realizacion date not null,
foreign key (ID_usuario) references Usuario(ID_usuario),
foreign key (ID_categoria) references Categoria(ID_categoria),
foreign key (ID_meta) references Metas_Ahorro(ID_meta)
);

CREATE INDEX idx_transaccion_usuario_fecha ON Transacciones (ID_usuario, Fecha_realizacion);
CREATE INDEX idx_meta_usuario_estado ON Metas_Ahorro (ID_usuario, Estado);
CREATE INDEX idx_token_estado ON Token_recuperacion (Token, Estado);
CREATE INDEX idx_categoria_tipo_nombre ON Categoria (Tipo_transaccion, Nombre_categoria);
CREATE INDEX idx_rolpermisos_permiso_rol ON Rol_Permisos (ID_permisos, ID_rol);



create view Vista_perfil_usuario as
select
U.ID_usuario,
U.Nombre,
E.Correo_electronico,
R.nombre_rol
from Usuario U
join Email E on U.ID_usuario = E.ID_usuario
left join Usuario_Rol UR on U.ID_usuario = UR.ID_usuario
left join Rol R on UR.ID_rol = R.ID_rol;

create view Vista_transacciones_detalladas as 
select
T.ID_transaccion,
T.ID_usuario,
T.Valor_transaccion,
T.Descripcion,
T.Fecha_realizacion,
C.Nombre_categoria,
C.Tipo_transaccion
from Transacciones T
join Categoria C on T.ID_categoria = C.ID_categoria;

create view Vista_progreso_metas as
select
M.ID_meta,
M.ID_usuario,
M.Nombre_meta,
M.Monto_objetivo,
M.Monto_actual,
M.Fecha_creacion,
M.Fecha_limite,
M.Estado,
(M.Monto_actual / M.Monto_objetivo) * 100 AS Porcentaje_Progreso
from Metas_Ahorro M;

create view Vista_permisos_rol as
select
R.Nombre_rol,
P.Permiso
from Rol R
join Rol_Permisos RP on R.ID_rol = RP.ID_rol
join Permisos P on RP.ID_permisos = P.ID_permisos;

CREATE VIEW Vista_balance_total_usuario AS
SELECT
    T.ID_usuario,
    SUM(CASE 
        WHEN C.Tipo_transaccion = 'Ingreso' THEN T.Valor_transaccion 
        ELSE -T.Valor_transaccion 
    END) AS Balance_Neto_Actual
FROM Transacciones T
JOIN Categoria C ON T.ID_categoria = C.ID_categoria
GROUP BY T.ID_usuario;
 
