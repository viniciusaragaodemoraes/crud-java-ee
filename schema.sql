create table company
(
data_cadastro date not null,
nome_fantasia varchar(100),
cnpj varchar(20) not null,
tipo_logradouro varchar(15),
logradouro varchar(50),
bairro varchar(50),
cidade varchar(50),
uf varchar(50),
cep varchar(20),
id_company int identity not null,
primary key(id_company))


create table officer
(
id_company int not null,
id_department int not null,
data_cadastro date not null,
nome varchar(100) not null,
cpf varchar(20) not null,
pis varchar(20) not null,
data_nascimento date,
tipo_logradouro varchar(15),
logradouro varchar(50),
bairro varchar(50),
cidade varchar(50),
uf varchar(50),
cep varchar(20),
situacao varchar(20) not null,
id_officer int identity not null,
primary key(id_officer))

alter table officer
add constraint fk_officer_id_company foreign key (id_company) references company (id_company);

alter table officer
add constraint fk_officer_id_department foreign key (id_department) references department (id_department);


create table department
(
data_cadastro date not null,
descricao varchar(100) not null,
situacao varchar(20) not null,
id_department int identity not null,
primary key(id_department))