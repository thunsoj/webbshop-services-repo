CREATE DATABASE db_orders;
USE db_orders;

CREATE TABLE orders (
    id BIGINT NOT NULL,
    created DATETIME,
    updated DATETIME,
    customer_id BIGINT,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE orders_product_ids (
    orders_id BIGINT,
    product_ids BIGINT,
    PRIMARY KEY (orders_id, product_ids),
    FOREIGN KEY (orders_id) REFERENCES orders(id)
) ENGINE=InnoDB;

CREATE TABLE orders_seq (next_val BIGINT) ENGINE=InnoDB;
INSERT INTO orders_seq VALUES (1);


