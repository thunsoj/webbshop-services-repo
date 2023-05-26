CREATE USER 'springuser_customer'@'%' IDENTIFIED BY 'ThePassword';
GRANT SELECT, INSERT, DELETE, UPDATE ON db_customer.* TO 'springuser_customer'@'%';

