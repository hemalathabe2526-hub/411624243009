-- Create Database
CREATE DATABASE COMPANY;
USE COMPANY;

-- Create Employees Table
CREATE TABLE Employees_Data (Emp_ID INT PRIMARY KEY, Emp_Name VARCHAR(30), Dept_ID VARCHAR(10), Salary DOUBLE, Manager_ID INT);

-- Create Department Table
CREATE TABLE Department (Dept_ID VARCHAR(10) PRIMARY KEY, Dept_Name VARCHAR(30));

-- Insert Records into Employees_Data
INSERT INTO Employees_Data (Emp_ID, Emp_Name, Dept_ID, Salary, Manager_ID) VALUES(101, 'Helen', 'SD71', 50000, NULL),
(102, 'Selvam', 'DA11', 100000, 101),
(103, 'Tamilarasi', 'SD71', 80000, 101),
(104, 'Nivetha', 'BD25', 75000, 102),
(105, 'Dev', 'TS18', 40000, 102);

-- Insert Records into Department
INSERT INTO Department (Dept_ID, Dept_Name) VALUES ('SD71', 'Software Developer'),
('DA11', 'Data Analyst'),
('TS18', 'Testing Service'),
('BD25', 'Backend Developer');

----------------------------------------------------
-- JOIN OPERATIONS
----------------------------------------------------

-- Inner Join
SELECT * FROM Employees_Data INNER JOIN Department ON Employees_Data.Dept_ID = Department.Dept_ID;

-- Left Join
SELECT * FROM Employees_Data LEFT JOIN Department ON Employees_Data.Dept_ID = Department.Dept_ID;

-- Right Join
SELECT * FROM Employees_Data RIGHT JOIN Department ON Employees_Data.Dept_ID = Department.Dept_ID;

-- Full Outer Join (MySQL)
SELECT * FROM Employees_Data LEFT JOIN Department ON Employees_Data.Dept_ID = Department.Dept_ID
       UNION
SELECT * FROM Employees_Data RIGHT JOIN Department ON Employees_Data.Dept_ID = Department.Dept_ID;

-- Self Join (Employee and Manager)
SELECT E.Emp_ID, E.Emp_Name AS Employee, M.Emp_Name AS Manager FROM Employees_Data E LEFT JOIN Employees_Data M ON E.Manager_ID = M.Emp_ID;

----------------------------------------------------
-- SUBQUERY OPERATIONS
----------------------------------------------------

-- Employees in Software Developer and Data Analyst departments
SELECT * FROM Employees_Data WHERE Dept_ID IN (SELECT Dept_ID FROM Department WHERE Dept_Name IN ('Software Developer', 'Data Analyst'));

-- Employees earning more than average salary
SELECT * FROM Employees_Data WHERE Salary > (SELECT AVG(Salary) FROM Employees_Data);

-- Employee with highest salary
SELECT * FROM Employees_Data WHERE Salary = (SELECT MAX(Salary) FROM Employees_Data);

-- Employee with lowest salary
SELECT * FROM Employees_Data WHERE Salary = (SELECT MIN(Salary) FROM Employees_Data);

-- Employees working in Helen's department
SELECT * FROM Employees_Data WHERE Dept_ID = (SELECT Dept_ID FROM Employees_Data WHERE Emp_Name = 'Helen');

-- Employees earning more than Dev
SELECT * FROM Employees_Data WHERE Salary > (SELECT Salary FROM Employees_Data WHERE Emp_Name = 'Dev');

-- Employee with second-highest salary
SELECT * FROM Employees_Data WHERE Salary = (SELECT MAX(Salary) FROM Employees_Data WHERE Salary < (SELECT MAX(Salary) FROM Employees_Data));

-- Count of employees earning above average salary
SELECT COUNT(*) AS Employees_Above_Average FROM Employees_Data WHERE Salary > (SELECT AVG(Salary) FROM Employees_Data);

----------------------------------------------------
-- DISPLAY TABLES
----------------------------------------------------
DESCRIBE Employees_Data;
DESCRIBE Department;

SELECT * FROM Employees_Data;
SELECT * FROM Department;

----------------------------------------------------
-- DROP TABLES
----------------------------------------------------
DROP TABLE Employees_Data;
DROP TABLE Department;