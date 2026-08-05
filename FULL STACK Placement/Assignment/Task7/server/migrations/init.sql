-- Run this SQL to create the notes table
CREATE DATABASE notedb;
USE notedb;

CREATE TABLE notes (
  id VARCHAR(128) PRIMARY KEY,
  title TEXT,
  content TEXT,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM notedb.notes;