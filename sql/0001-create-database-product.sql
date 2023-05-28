CREATE DATABASE db_product;
USE db_product;

CREATE TABLE product (
    id INTEGER NOT NULL,
    name VARCHAR(255),
    price DOUBLE NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE product_seq (next_val BIGINT) ENGINE=InnoDB;
INSERT INTO product_seq VALUES (1);
