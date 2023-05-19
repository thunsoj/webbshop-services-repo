CREATE DATABASE db_customer;
USE db_customer;
create table customer (id integer not null, name varchar(255), ssn int, CREATED datetime, updated DATETIME, primary key (id)) engine=InnoDB;
create table customer_seq (next_val bigint) engine=InnoDB;
insert into customer_seq values ( 1 );
