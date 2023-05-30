USE db_customer;
INSERT INTO `customer` VALUES (1,'Jonas Deg', 123456789, NOW(), NOW());
INSERT INTO `customer` VALUES (2,'Per Apa', 723456789, NOW(), NOW());
UPDATE `customer_seq` SET next_val = 51;
