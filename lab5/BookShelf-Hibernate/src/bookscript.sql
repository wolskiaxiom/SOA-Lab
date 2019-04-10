-- auto-generated definition
create table book
(
    idbook          integer not null
        constraint book_pk
            primary key,
    title           varchar(255),
    authorfirstname varchar(255),
    authorlastname  varchar(255),
    isbn            varchar(255),
    issueyear       integer,
    type            varchar(255),
    price           numeric(7, 2),
    originprice     numeric(7, 2),
    currency        varchar(255),
    ififcheched       BOOLEAN
);

alter table book
    owner to postgres;

