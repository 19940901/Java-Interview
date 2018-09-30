
# role 表
create table `role` (
  `id`     int         not null auto_increment,
  `name`   varchar(50) not null,
  `grade`  int         not null,
  `remark` varchar(50) not null,
  primary key (id)
);

  # user table
create table `user` (
  `id`        int         not null auto_increment,
  `name`      varchar(50) not null,
  `account`   varchar(50) not null,
  `password`  varchar(50) not null,
  `salt`      varchar(50) not null,
  `forbidden` varchar(10) not null,
  primary key (id)
);

create  table `hibernate_sequence`(

`next_val` int not null primary key
);