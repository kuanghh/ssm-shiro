/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/4/13 20:38:42                           */
/*==============================================================*/


drop table if exists t_permission;

drop table if exists t_role;

drop table if exists t_role_permission;

drop table if exists t_user;

drop table if exists t_user_role;

/*==============================================================*/
/* Table: t_permission                                          */
/*==============================================================*/
create table t_permission
(
   id                   varchar(32) not null,
   name                 varchar(20) not null,
   sign                 varchar(20) not null,
   description          varchar(30) not null,
   id_vaild             tinyint not null,
   primary key (id)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   varchar(32) not null,
   name                 varchar(20) not null,
   description          varchar(30) not null,
   sign                 varchar(20) not null,
   is_vaild             tinyint not null,
   primary key (id)
);

/*==============================================================*/
/* Table: t_role_permission                                     */
/*==============================================================*/
create table t_role_permission
(
   id                   varchar(32) not null,
   role_id              varchar(32) not null,
   permission_id        varchar(32) not null,
   is_vaild             tinyint not null,
   primary key (id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   varchar(32) not null,
   name                 varchar(20) not null,
   password             varchar(32) not null,
   state                int not null,
   create_time          timestamp not null,
   is_valid             tinyint not null,
   primary key (id)
);

/*==============================================================*/
/* Table: t_user_role                                           */
/*==============================================================*/
create table t_user_role
(
   id                   varchar(32) not null,
   use_id               varchar(32) not null,
   role_id              varchar(32) not null,
   is_valid             tinyint not null,
   primary key (id)
);

