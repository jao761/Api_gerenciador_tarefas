create table if not exists tarefas(

    id bigint auto_increment primary key,
    titulo varchar(100) not null,
    descricao varchar(5000),
    data_criacao datetime default current_timestamp,
    data_finalizacao date,
    status varchar(50),
    quadro_id bigint,

    foreign key (quadro_id) references quadros(id)

);