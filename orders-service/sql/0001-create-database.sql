create table orders (id integer not null, CREATED datetime, updated DATETIME, primary key (id)) engine=InnoDB;
create table orders_seq (next_val bigint) engine=InnoDB;
insert into orders_seq values ( 1 );
