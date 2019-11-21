create database if not exists transportadora;

use transportadora;

create table if not exists motorista(
	id_motorista integer not null auto_increment,
    nome varchar(30) not null,
    idade integer not null,
    salario varchar(10),
    sexo varchar(15),
    data_entrada varchar(40),
    
    primary key (id_motorista)
);

create table if not exists produto(
	id_produto integer not null auto_increment,
	nome varchar(30) not null,
    tipo varchar(30) not null,
    fornecedor varchar(30),
    id_deposito integer,
    
    primary key (id_produto)    
);

create table if not exists caminhao(
	id_caminhao integer not null auto_increment,
    modelo varchar(30) not null,
	capacidade integer not null,
    id_produto integer unique,
    
    primary key (id_caminhao)    
);


create table if not exists deposito(
	id_deposito integer not null auto_increment,
	rua varchar(30) not null,
    num integer not null,
    bairro varchar(30) not null,
	telefone varchar(30),
    id_caminhao integer,
    
    primary key (id_deposito),    
    
    constraint fk_id_caminhaoo
    foreign key (id_caminhao)
    references caminhao(id_caminhao)
    
    on delete cascade
    on update cascade    
);

alter table produto
add constraint  fk_id_depositoo
    foreign key (id_deposito)
    references deposito(id_deposito)
    
    on delete cascade
    on update cascade;


create table if not exists motorista_drives_caminhao(
	id_motorista integer,
    id_caminhao integer,
    estado boolean not null,
    
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

alter table produto
add constraint  fk_id_deposito
    foreign key (id_deposito)
    references deposito(id_deposito)
    
    on delete cascade
    on update cascade;
    
alter table caminhao
add constraint fk_id_produto 
    foreign key (id_produto) 
    references produto(id_produto)
    
    on delete cascade
    on update cascade;
    
    
drop database transportadora;    
select * from deposito;
select * from produto;
select * from caminhao;
select * from motorista;
