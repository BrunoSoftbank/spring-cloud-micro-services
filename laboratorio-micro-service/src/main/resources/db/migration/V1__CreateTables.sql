create table if not exists status (id bigint not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB;

create table if not exists endereco (id bigint not null auto_increment, bairro varchar(255), cidade varchar(255), numero varchar(255), rua varchar(255), primary key (id)) engine=InnoDB;

create table if not exists laboratorio (id bigint not null auto_increment, nome varchar(255), endereco bigint, status bigint, primary key (id)) engine=InnoDB;

alter table laboratorio add constraint FKref2tl3o7lf667jsu4fahn57a foreign key (endereco) references endereco (id);

alter table laboratorio add constraint FK1uhpof5i5997kfdvre1ya18l4 foreign key (status) references status (id);
