create table associado (
	id varchar(64) not null default random_uuid(),
    nome varchar(128) not null,
    cadastro TIMESTAMP WITH TIME ZONE not null default now(),
    primary key (id)
);
