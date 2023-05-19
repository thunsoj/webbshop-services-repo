USE db_orders;
INSERT INTO `orders` VALUES (1, NOW(), NOW());
UPDATE `orders_seq` SET next_val = 51;
