create table if not exists quadros (

    id bigint auto_increment primary key,
    titulo varchar(100) not null,
    data_criacao datetime default current_timestamp

);