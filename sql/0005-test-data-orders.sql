USE db_orders;
INSERT INTO orders (id, created, updated, customer_id) VALUES (1, NOW(), NOW(), 1);
INSERT INTO orders_product_ids (orders_id, product_ids) VALUES (1, 1);
INSERT INTO orders_product_ids (orders_id, product_ids) VALUES (1, 2);
UPDATE `orders_seq` SET next_val = 51;
