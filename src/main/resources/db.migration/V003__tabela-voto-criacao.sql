create table voto (
	id varchar(64) not null default random_uuid(),
	associado_id varchar(64) not null,
    tipo varchar(128) not null,
    cadastro TIMESTAMP WITH TIME ZONE not null default now(),
    primary key (id)
);

alter table voto add constraint fk_voto_associado foreign key (associado_id) references associado;

