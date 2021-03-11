create schema padaria;

use padaria;

create table comanda(
	idComanda int primary key not null
);

create table produto(
	idProduto int primary key not null,
    nome varchar(20),
	valor float
);

create table consumido(
	idComanda int not null,
	idProduto int not null,
	criadoem TIMESTAMP(6),
	quantidade int not null,
	foreign key (idComanda) references comanda(idComanda),
	foreign key(idProduto) references produto(idProduto)
);