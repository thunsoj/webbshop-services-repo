CREATE DATABASE db_customer;
USE db_customer;

CREATE TABLE customer (
    id INTEGER NOT NULL,
    name VARCHAR(255),
    ssn INT,
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE customer_seq (next_val BIGINT) ENGINE=InnoDB;
INSERT INTO customer_seq VALUES (1);

