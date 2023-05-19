CREATE DATABASE db_product;
USE db_product;
create table product (id integer not null, name varchar(255), price double, primary key (id)) engine=InnoDB;
create table product_seq (next_val bigint) engine=InnoDB;
insert into product_seq values ( 1 );
