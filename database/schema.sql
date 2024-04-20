CREATE DATABASE IF NOT EXISTS sms;

USE sms;

-- Table for storing student records
CREATE TABLE IF NOT EXISTS students (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) DEFAULT 'Unknown' NOT NULL,
    lastName VARCHAR(255) DEFAULT 'Unknown' NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Table for storing login credentials
CREATE TABLE IF NOT EXISTS login (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL
);

