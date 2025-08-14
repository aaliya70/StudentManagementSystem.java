# Student Management System (Java + MySQL)

This is a simple console-based CRUD application built with Java and MySQL.  
It allows you to:
- Add a new student
- View all students
- Update student details
- Delete a student

## Database Setup
1. Create database and table:
```sql
CREATE DATABASE studentdb;
USE studentdb;
CREATE TABLE students (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  age INT NOT NULL,
  grade VARCHAR(10) NOT NULL
);
