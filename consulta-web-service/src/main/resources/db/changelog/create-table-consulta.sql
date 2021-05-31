create table if not exists consulta (id bigint not null auto_increment, usuario_id bigint not null, exame_id bigint not null, laboratorio_id bigint not null, primary key (id)) engine=InnoDB;
