CREATE TABLE Banda(
	id serial,
	descricao varchar(100),
        primary key (id)
);

CREATE TABLE Integrante(
	id serial,
	nome varchar(100),
	cpf varchar(14),
	banda int,
        primary key (id),
	constraint fk foreign key (banda) references Banda(id) on delete cascade on update cascade
);

CREATE TABLE Album(
	id serial,
	descricao varchar(100),
	dataDeLancamento varchar(10),
	banda int,
	primary key (id),
	constraint fk foreign key (banda) references Banda(id) on delete cascade on update cascade
);
