create table assembleia_votacao (
	assembleia_id varchar(255) not null,
	voto_id varchar(255) not null
);
alter table assembleia_votacao add constraint fk_assembleia_votacao_assembleia foreign key (assembleia_id) references assembleia;
alter table assembleia_votacao add constraint fk_assembleia_votacao_voto foreign key (voto_id) references voto;