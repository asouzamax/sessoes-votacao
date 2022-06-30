create table assembleia (
	id varchar(64) not null default random_uuid(),
    pauta varchar(128) not null unique,
    cadastro TIMESTAMP WITH TIME ZONE not null default now(),
    abertura TIMESTAMP WITH TIME ZONE not null default now(),
    primary key (id)
);
