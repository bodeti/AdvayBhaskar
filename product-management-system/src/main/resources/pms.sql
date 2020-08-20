DROP DATABASE pmsDb;

CREATE DATABASE pmsDb;

USE pmsDb;

CREATE TABLE Products(
	pid int primary key,
	pname varchar(20) not null,
	pcost DECIMAL(10,2),
	pdescription varchar(100) not null
);

INSERT INTO Products VALUES
(1,"Face Mask","100.00","N95 Branded Face Mask"),
(2,"Sanitizer","300.00","Hand Sanitizer Alcohol Based Kills 99.9% of Germs"),
(3,"Medicines","1500.00","Immunity Boosters"); 