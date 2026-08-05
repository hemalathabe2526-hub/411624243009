-- Create Database
CREATE DATABASE College_Admin;
USE College_Admin;

-- Create Student Table
CREATE TABLE Student (Student_ID INT PRIMARY KEY, Student_Name VARCHAR(30), Department VARCHAR(20), Marks INT);

-- Create Faculty Table
CREATE TABLE Faculty (Faculty_ID INT PRIMARY KEY, Faculty_Name VARCHAR(30), Department VARCHAR(20), Salary DECIMAL(10,2));

-- Insert Student Records
INSERT INTO Student VALUES (101, 'Arun', 'CSE', 85),
(102, 'Priya', 'ECE', 78),
(103, 'Kavin', 'CSE', 92),
(104, 'Meena', 'IT', 70),
(105, 'Ravi', 'ECE', 88),
(106, 'Divya', 'IT', 95);

-- Insert Faculty Records
INSERT INTO Faculty VALUES (201, 'Anand', 'CSE', 50000),
(202, 'Lakshmi', 'ECE', 55000),
(203, 'Kumar', 'IT', 60000),
(204, 'Suresh', 'CSE', 65000);

----------------------------------------------------
-- SUBQUERY EXAMPLES
----------------------------------------------------

-- 1. Students with marks greater than average marks
SELECT * FROM Student WHERE Marks > (SELECT AVG(Marks) FROM Student);

-- 2. Faculty with salary greater than average salary
SELECT * FROM Faculty WHERE Salary > (SELECT AVG(Salary) FROM Faculty);

-- 3. Student with highest marks
SELECT * FROM Student WHERE Marks = (SELECT MAX(Marks) FROM Student);

-- 4. Faculty with highest salary
SELECT * FROM Faculty WHERE Salary = (SELECT MAX(Salary) FROM Faculty);

-- 5. Students in the same department as faculty Anand
SELECT * FROM Student WHERE Department = (SELECT Department FROM Faculty WHERE Faculty_Name = 'Anand');

-- 6. Faculty in the same department as student Arun
SELECT * FROM Faculty WHERE Department = (SELECT Department FROM Student WHERE Student_Name = 'Arun');

-- 7. Students with marks greater than the CSE department average
SELECT * FROM Student WHERE Marks > (SELECT AVG(Marks) FROM Student WHERE Department = 'CSE');

-- 8. Faculty earning more than Kumar
SELECT * FROM Faculty WHERE Salary > (SELECT Salary FROM Faculty WHERE Faculty_Name = 'Kumar');

-- 9. Student with second-highest marks
SELECT * FROM Student WHERE Marks = (SELECT MAX(Marks) FROM Student WHERE Marks < (SELECT MAX(Marks) FROM Student));

-- 10. Students whose department exists in Faculty table
SELECT * FROM Student WHERE Department IN (SELECT Department FROM Faculty);

-- 11. Faculty whose department has students scoring above 90
SELECT * FROM Faculty WHERE Department IN (SELECT Department FROM Student WHERE Marks > 90);

-- 12. Count of students scoring above average
SELECT COUNT(*) AS Students_Above_Average FROM Student WHERE Marks > (SELECT AVG(Marks) FROM Student);

-- 13. Count of faculty earning above average salary
SELECT COUNT(*) AS Faculty_Above_Average FROM Faculty WHERE Salary > (SELECT AVG(Salary) FROM Faculty);

-- Display Tables
SELECT * FROM Student;
SELECT * FROM Faculty;