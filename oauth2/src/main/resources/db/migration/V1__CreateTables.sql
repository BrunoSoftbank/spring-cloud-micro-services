create table if not exists token (id bigint not null auto_increment, valor varchar(255), usuario_id bigint, primary key (id)) engine=InnoDB;

create table if not exists role (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB;

create table if not exists usuario (id bigint not null auto_increment, data_cadastro date, email varchar(255), is_ativo bit, nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB;

create table if not exists usuario_roles (usuario_id bigint not null, roles_id bigint not null) engine=InnoDB;

alter table token add constraint FK4ro3grr0c5bo0q3nixse352er foreign key (usuario_id) references usuario (id);

alter table usuario_roles add constraint FKr5p0u8r15jjo6u7xr1928hsld foreign key (roles_id) references role (id);

alter table usuario_roles add constraint FKqblnumndby0ftm4c7sg6uso6p foreign key (usuario_id) references usuario (id);
