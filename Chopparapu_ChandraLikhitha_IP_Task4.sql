DROP TABLE IF EXISTS Employee
/* Creation of Employee Table*/
CREATE  TABLE Employee (e_name varchar(64),
e_address varchar(64), 
salary REAL Primary KEY(e_name))
DROP TABLE IF EXISTS Products
/* Creation of Products Table*/
CREATE TABLE Products(prod_id VARCHAR(64),
prod_date DATE,
Time_spent TIME,
Tech_staff_name varchar(64),
Tester_name varchar(64),
Worker_name varchar(64),
PRIMARY KEY(prod_id))
DROP TABLE IF EXISTS Account
/* Creation of Account Table*/
CREATE TABLE Account(Account_no VARCHAR(64), 
Account_date DATE,
cost REAL, 
PRIMARY KEY(Account_no))
DROP TABLE IF EXISTS Accidents
/* Creation of Accidents Table*/
CREATE TABLE Accidents(Accident_no VARCHAR(64),
accident_date DATE,
no_of_workdays int, 
PRIMARY KEY(Accident_no))
DROP TABLE IF EXISTS Complaint
/* Creation of Complaints Table*/
CREATE TABLE Complaint(Complaint_id VARCHAR(64),
Complaint_date DATE,
description_of_product varchar(64),
type_of_treatment varchar(64),
PRIMARY KEY(Complaint_id))
DROP TABLE IF EXISTS Customer
/* Creation of Customer Table*/
CREATE TABLE Customer (customer_name VARCHAR(64),
customer_address VARCHAR(64), 
PRIMARY KEY(customer_name))
DROP TABLE IF EXISTS Technical_staff
/* Creation of Technical_staff Table*/
CREATE TABLE Technical_staff(Technical_staff_name VARCHAR(64),
Technical_position VARCHAR(64), 
PRIMARY KEY(Technical_staff_name),
FOREIGN KEY(Technical_staff_name) references Employee)
INSERT INTO Technical_staff VALUES('Divya','Manager')
DROP TABLE IF EXISTS Technical_degree
/* Creation of TechnicalDegrees Table*/
CREATE TABLE Technical_degree(Technical_staff_name VARCHAR(64),
Degrees varchar(64), 
PRIMARY KEY(Technical_staff_name), 
FOREIGN KEY(Technical_staff_name) references Technical_staff )
DROP TABLE IF EXISTS Workers
/* Creation of Workers Table*/
CREATE TABLE Workers(Worker_name VARCHAR(64),
max_no_of_products VARCHAR(64), 
PRIMARY KEY(Worker_name), 
FOREIGN KEY(Worker_name) REFERENCES Employee)
DROP TABLE IF EXISTS Quality_controller
/* Creation of Quality Controllers Table*/
CREATE TABLE Quality_controller(Tester_name VARCHAR(64), 
type_of_product VARCHAR(64), 
PRIMARY KEY(Tester_name), 
FOREIGN KEY(Tester_name) REFERENCES Employee)
/* Creation of Product1_Account Table*/
DROP TABLE IF EXISTS Product1_Account
CREATE TABLE Product1_Account(Account1_no VARCHAR(64) , 
PRIMARY KEY(Account1_no), 
FOREIGN KEY(Account1_no) REFERENCES Account)
DROP TABLE IF EXISTS Product2_Account
/* Creation of Product2_Account Table*/
CREATE TABLE Product2_Account(Account2_no VARCHAR(64) , 
PRIMARY KEY(Account2_no),
 FOREIGN KEY(Account2_no) REFERENCES Account)
 DROP TABLE IF EXISTS Product3_Account
 /* Creation of Product3_Account Table*/
CREATE TABLE Product3_Account(Account3_no VARCHAR(64) , PRIMARY KEY(Account3_no), FOREIGN KEY(Account3_no) REFERENCES Account)
DROP TABLE IF EXISTS Product1
 /* Creation of Product1 Table*/
CREATE TABLE Product1(prod_id VARCHAR(64),
size_of_prod1 VARCHAR(64),
name_of_software VARCHAR(64), 
Account1_no VARCHAR(64),
PRIMARY KEY(prod_id),
 FOREIGN KEY(Account1_no) REFERENCES Product1_Account, 
 FOREIGN KEY(prod_id) REFERENCES Products
 )
 DROP TABLE IF EXISTS Product2
  /* Creation of Product2 Table*/
CREATE TABLE Product2(prod_id VARCHAR(64),
size_of_prod2 VARCHAR(64), 
color_of_prod VARCHAR(64), 
Account2_no VARCHAR(64),
PRIMARY KEY(prod_id), 
FOREIGN KEY(Account2_no) REFERENCES Product2_Account, 
FOREIGN KEY(prod_id) REFERENCES Products)
DROP TABLE IF EXISTS Product3
 /* Creation of Product3 Table*/
CREATE TABLE Product3(prod_id VARCHAR(64),
size_of_prod3 VARCHAR(64), 
weight_of_prod VARCHAR(64), 
Account3_no VARCHAR(64),
PRIMARY KEY(prod_id),
 FOREIGN KEY(Account3_no) REFERENCES Product3_Account, 
 FOREIGN KEY(prod_id) REFERENCES Products)
 DROP TABLE IF EXISTS Repairs
  /* Creation of Repairs Table*/
CREATE TABLE Repairs(Technical_staff_name VARCHAR(64),
PRIMARY key(Technical_staff_name, prod_id),
prod_id VARCHAR(64),
repair_date Date, 
FOREIGN KEY(Technical_staff_name) REFERENCES Technical_staff, 
FOREIGN KEY(prod_id) REFERENCES Products )
DROP TABLE IF EXISTS Requests
 /* Creation of Requests Table*/
CREATE TABLE Requests(Technical_staff_name VARCHAR(64),
prod_id VARCHAR(64),
Tester_name VARCHAR(64), 
PRIMARY KEY(Technical_staff_name,prod_id,Tester_name), 
FOREIGN KEY(Technical_staff_name) REFERENCES Technical_staff, 
FOREIGN KEY(prod_id) REFERENCES Products, 
FOREIGN KEY(Tester_name) REFERENCES Quality_Controller)
DROP TABLE IF EXISTS Purchases
 /* Creation of Purchases Table*/
CREATE TABLE Purchases(Customer_name VARCHAR(64), 
prod_id VARCHAR(64),
PRIMARY KEY(customer_name,prod_id), 
FOREIGN KEY(customer_name) REFERENCES  Customer, 
FOREIGN KEY(prod_id) REFERENCES Products )
DROP TABLE IF EXISTS Faces
 /* Creation of Faces Table*/
CREATE TABLE Faces( Technical_staff_name VARCHAR(64),
prod_id VARCHAR(64),
Accident_no VARCHAR(64),
PRIMARY KEY(Technical_staff_name,prod_id,Accident_no), 
FOREIGN KEY(Technical_staff_name) REFERENCES Technical_staff, 
FOREIGN KEY(prod_id) REFERENCES Products, 
FOREIGN KEY(Accident_no) REFERENCES Accidents )
DROP TABLE IF EXISTS Have
 /* Creation of Have Table*/
CREATE TABLE Have( Worker_name VARCHAR(64),
prod_id VARCHAR(64),
Accident_no VARCHAR(64),
 PRIMARY KEY(Worker_name,prod_id,Accident_no),
 FOREIGN KEY(Worker_name) REFERENCES Workers, 
 FOREIGN KEY(prod_id) REFERENCES Products, 
 FOREIGN KEY(Accident_no) REFERENCES Accidents )
 DROP TABLE IF EXISTS Got
  /* Creation of Got Table*/
CREATE TABLE Got(Technical_staff_name VARCHAR(64),
prod_id VARCHAR(64),complaint_id VARCHAR(64),
PRIMARY KEY(Technical_staff_name,prod_id,complaint_id), 
FOREIGN KEY(Technical_staff_name) REFERENCES Technical_staff, 
FOREIGN KEY(prod_id) REFERENCES Products, 
FOREIGN KEY(complaint_id) REFERENCES Complaint )
DROP TABLE IF EXISTS makes
 /* Creation of makes Table*/
CREATE TABLE makes(Customer_name VARCHAR(64), 
prod_id VARCHAR(64),complaint_id VARCHAR(64), 
PRIMARY KEY(Customer_name,prod_id,complaint_id), 
FOREIGN KEY(Customer_name) REFERENCES Customer,
 FOREIGN KEY(prod_id) REFERENCES Products,
 FOREIGN KEY(complaint_id) REFERENCES Complaint)
 DROP TABLE IF EXISTS Checks
  /* Creation of Checks Table*/
CREATE TABLE Checks( Tester_name VARCHAR(64),
 prod_id VARCHAR(64),
 FOREIGN KEY(Tester_name) REFERENCES Quality_Controller,
 FOREIGN KEY(prod_id) REFERENCES Products,
 PRIMARY KEY(Tester_name,prod_id))
   /* Creation of Checks Table*/
DROP TABLE IF EXISTS Tracks
 CREATE TABLE Tracks(prod_id VARCHAR(64),Account_no VARCHAR(64),
 FOREIGN KEY(prod_id) REFERENCES Products,
 FOREIGN KEY(Account_no) REFERENCES Account,
 PRIMARY KEY(prod_id,Account_no))

