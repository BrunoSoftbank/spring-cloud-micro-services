create table if not exists status (id bigint not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB;

create table if not exists tipo (id bigint not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB;

create table if not exists exame (id bigint not null auto_increment, nome varchar(255), status bigint, tipo bigint, primary key (id)) engine=InnoDB;

alter table exame add constraint FKbfmnfqvo4s65rfakofp9qwust foreign key (status) references status (id);

alter table exame add constraint FKnfg81220vtgsjxqveyhiqsnak foreign key (tipo) references tipo (id);
