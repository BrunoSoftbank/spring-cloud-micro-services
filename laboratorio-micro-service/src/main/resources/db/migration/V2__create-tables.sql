create table if not exists softbank_laboratorio.status (id INTEGER DEFAULT NEXTVAL('softbank_laboratorio.status_id_seq') primary key, descricao varchar(255));

create table if not exists softbank_laboratorio.endereco (id INTEGER DEFAULT NEXTVAL('softbank_laboratorio.endereco_id_seq') primary key, bairro varchar(255), cidade varchar(255), numero varchar(255), rua varchar(255));

create table if not exists softbank_laboratorio.laboratorio (id INTEGER DEFAULT NEXTVAL('softbank_laboratorio.laboratorio_id_seq') primary key, nome varchar(255), endereco bigint, status bigint);

