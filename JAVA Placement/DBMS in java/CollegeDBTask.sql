/* 1. Create Database */
CREATE DATABASE CollegeDB;

/* 2. Use Database */
USE CollegeDB;

/* 3. Create Students Table */
CREATE TABLE Students_Data ( ID INT PRIMARY KEY, NAME VARCHAR(50) NOT NULL, AGE INT, DEPT VARCHAR(30), CGPA DECIMAL(3,2), YEAR VARCHAR(30), PHONE_NO VARCHAR(10)
);

/* 4. Show the CREATE TABLE statement */
SHOW CREATE TABLE Students_Data;

/* 5. Insert 10 Student Records */
INSERT INTO Students_Data (ID, NAME, AGE, DEPT, CGPA, YEAR, PHONE_NO) VALUES 
(101, 'Hema', 20, 'AI&DS', 9.20, 'II', '7629823417'),
(102, 'Rahul', 21, 'CSE', 8.50, 'III', '9824567011'),
(103, 'Priya', 19, 'ECE', 9.00, 'I', '9876543256'),
(104, 'Kavin', 20, 'IT', 8.75, 'IV', '3459823476'),
(105, 'Sneha', 21, 'MECH', 8.20, 'II', '3698645341'),
(106, 'Arun', 22, 'EEE', 7.95, 'IV', '7653097634'),
(107, 'Divya', 20, 'CIVIL', 8.85, 'III', '9991827555'),
(108, 'Vikram', 21, 'AI&DS', 8.60, 'I', '9257308324'),
(109, 'Anjali', 19, 'CSE', 9.40, 'II', '6246830872'),
(110, 'Surya', 22, 'ECE', 8.10, 'I', '9873047452');

/* 6. Display All Records */
SELECT * FROM Students_Data;

/* For Drop the table if also table exists */
-- DROP TABLE IF EXISTS Students_Data;

/* 7. Display Only Name and Department */
SELECT NAME, DEPT FROM Students_Data;

/* 8. Add a New Student */
INSERT INTO Students_Data VALUES (111, 'Harini', 20, 'BCA', 9.15, 'III', '7773457991');

/* 9. Update CGPA of Student with ID = 108 */
UPDATE Students_Data SET CGPA = 8.30 WHERE ID = 108;

/* 10. Change Department Name (IT to Information Technology) */
UPDATE Students_Data SET DEPT = 'Information Technology' WHERE ID = 104;

/* 11. Add a New Column (Mobile Number) */
ALTER TABLE Students_Data ADD Mobile BIGINT;

/* 12. Change Datatype of Mobile Column */
ALTER TABLE Students_Data MODIFY Mobile BIGINT;

/* 13. Drop a Column (Mobile) */
ALTER TABLE Students_Data DROP COLUMN Mobile;

/* 14. Change Table Name */
ALTER TABLE Students_Data RENAME TO Student_Details;