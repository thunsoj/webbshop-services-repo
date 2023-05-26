CREATE USER 'springuser_product'@'%' IDENTIFIED BY 'ThePassword';
GRANT SELECT, INSERT, DELETE, UPDATE ON db_product.* TO 'springuser_product'@'%';