/* Creating Database */
CREATE DATABASE College;

/* Display all databases */
SHOW DATABASES;

/* Select the database */
USE College;

/* Check the current database */
SELECT DATABASE();

/* Create the Students table */
CREATE TABLE Students (
    ID INT PRIMARY KEY, NAME VARCHAR(50) NOT NULL, AGE INT, DEPT VARCHAR(50), CGPA DOUBLE(3,2)
);

/* Display all tables */
SHOW TABLES;

/* Check the table structure */
DESCRIBE Students;

/* Show the CREATE TABLE statement */
SHOW CREATE TABLE Students;

/* Insert records into the table */
INSERT INTO Students (ID, NAME, AGE, DEPT, CGPA) VALUES (001, 'Hema', 20, 'AI&DS', 9.20),
(002, 'Manish', 21, 'CSE', 8.75),
(003, 'Antony', 19, 'ECE', 9.10),
(004, 'Harsha', 20, 'IT', 8.90),
(005, 'Surandharan', 25, 'BME', 9.87);

/* For Printing only the selected columns */
SELECT NAME, DEPT FROM Students;

/* For Displaying the unique keywords */
SELECT DISTINCT DEPT FROM Students;

/* For Counting the Number of Rows */
SELECT COUNT(*) FROM Students;

/* Rename the columns while Displaying */
SELECT NAME AS Student_name, DEPT AS Branch FROM Students;

/* To add new columns */
ALTER TABLE Students ADD Email VARCHAR(30);
DESC STudents;

/* Changing the DataType of column name */
ALTER TABLE Students MODIFY CGPA  DECIMAL(3,2);

/* Renaming the column name */
ALTER TABLE Students RENAME column Branch to DEPT;

/* Display all records */
SELECT * FROM Students;