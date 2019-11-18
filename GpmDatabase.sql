create database if not exists transportadora;

use transportadora;

create table if not exists caminhao(
	id_caminhao integer not null auto_increment,
    modelo varchar(30) not null,
	capacidade integer not null,
    id_produto integer unique,
    
    primary key (id_caminhao),
    
    constraint fk_id_produto 
    foreign key (id_produto) 
    references produto(id_produto)
    
    on delete cascade
    on update cascade
    
);

create table if not exists produto(
	id_produto integer not null auto_increment,
	nome varchar(30) not null,
    tipo varchar(30) not null,
    fornecedor varchar(30),
    
    primary key (id_produto)
);

create table if not exists deposito(
	id_deposito integer not null auto_increment,
	rua varchar(30) not null,
    num integer not null,
    bairro varchar(30) not null,
	telefone varchar(15),
    id_produto integer,
    id_caminhao integer,
    
    primary key (id_deposito),
    
    constraint fk_id_produtoo
    foreign key (id_produto)
    references produto(id_produto)
    
    on delete cascade
    on update cascade,
    
    constraint fk_id_caminhaoo
    foreign key (id_caminhao)
    references caminhao(id_caminhao)
    
    on delete cascade
    on update cascade
    
);

create table if not exists motorista(
	id_motorista integer not null auto_increment,
    nome varchar(30) not null,
    idade integer not null,
    salario varchar(10),
    sexo varchar(1),
    data_entrada date,
    
    primary key (id_motorista)
);

create table if not exists motorista_drives_caminhao(
	id_motorista integer,
    id_caminhao integer,
    
    primary key(id_motorista, id_caminhao),
	
	constraint fk_id_motorista
    foreign key (id_motorista)
    references motorista(id_motorista)
    
    on delete cascade
    on update cascade,
    
    constraint fk_id_caminhao
    foreign key (id_caminhao)
    references caminhao(id_caminhao)
    
    on delete cascade
    on update cascade    
);

drop table produto;
select * from deposito;
select * from produto;
select * from produto where nome = "Ferro";

