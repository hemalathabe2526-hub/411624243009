CREATE DATABASE school_demoDB;
USE school_demoDB;
CREATE TABLE users(
    id int auto_increment PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(100)
);
SELECT * FROM users;