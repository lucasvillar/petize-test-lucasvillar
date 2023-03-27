-- create the databases
CREATE DATABASE IF NOT EXISTS petizetest;

-- create the users for each database
CREATE USER 'petizetest'@'%' IDENTIFIED BY 'petizetest';
GRANT CREATE, ALTER, INDEX, LOCK TABLES, REFERENCES, UPDATE, DELETE, DROP, SELECT, INSERT ON `petizetest`.* TO 'petizetest'@'%';

FLUSH PRIVILEGES;
