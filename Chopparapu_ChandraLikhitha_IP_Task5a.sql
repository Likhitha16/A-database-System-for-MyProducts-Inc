/* SQL Queries for Task 5 */
/* Stored Procedures */
/*--------------------------- Query 1-------------------------------------------------------  */
/*Query to insert values to insert employee data */
DROP PROCEDURE IF EXISTS insert_employee
 GO 
CREATE PROCEDURE insert_employee
    @e_name varchar(64),
    @e_address varchar(64),
    @salary REAL
AS
BEGIN
Insert into Employee VALUES(@e_name,@e_address,@salary);
END
GO
/* Query to insert values to tech_staff */
DROP PROCEDURE IF EXISTS insert_tech_staff
GO
CREATE PROCEDURE insert_tech_staff
@tech_staff_name VARCHAR(64),
@tech_position VARCHAR(64)
AS
BEGIN
Insert into Technical_Staff VALUES(@tech_staff_name,@tech_position)
END
GO
/* insert values to technical staff degree */
DROP PROCEDURE IF EXISTS insert_tech_staff_degrees
GO
CREATE PROCEDURE insert_tech_staff_degrees
@tech_staff_name VARCHAR(64),
@degree VARCHAR(64)
AS
BEGIN
Insert into Technical_degree VALUES(@tech_staff_name,@degree)
END
GO
/*insert values to workers table*/
DROP PROCEDURE IF EXISTS insert_workers
GO
CREATE PROCEDURE insert_workers
@worker_name varchar(64),
@no_of_products varchar(64)
AS
BEGIN
INSERT into Workers VALUES(@worker_name,@no_of_products)
END
GO
/*insert values to Quality Controllers table */
DROP PROCEDURE IF EXISTS insert_testers
GO
CREATE PROCEDURE insert_testers
@tester_name VARCHAR(64),
@type VARCHAR(64)
AS
BEGIN
INSERT into Quality_Controller VALUES(@tester_name,@type)
END
GO

/* SELECT statements for Query 1 */
SELECT * FROM Employee
SELECT * FROM Technical_staff
SELECT * from Technical_degree
SELECT * FROM Workers
SELECT * from Quality_controller

/*---------------------------------------- Query 2------------------------------------------------------------ */
DROP PROCEDURE IF EXISTS insert_workertester_prods
GO
CREATE PROCEDURE insert_workertester_prods
@prod_id INT,
@prod_date VARCHAR(64),
@time_spent VARCHAR(64),
@Worker_name VARCHAR(64),
@Tester_name VARCHAR(64)
AS
BEGIN
INSERT INTO Products(prod_id,prod_date,time_spent,Worker_name,Tester_name) VALUES(@prod_id,@prod_date,@time_spent,@Worker_name,@Tester_name)
END
GO
/* INSERT products associated with Technical staff*/
DROP PROCEDURE IF EXISTS insert_tech_staff_prods
GO
CREATE PROCEDURE insert_tech_staff_prods
@prod_id INT,
@prod_date DATE,
@time_spent VARCHAR(64),
@tech_staff_name VARCHAR(64)
AS
BEGIN
INSERT INTO Products(prod_id,prod_date,time_spent,Tech_staff_name) VALUES(@prod_id,@prod_date,@time_spent,@tech_staff_name)
END
GO
SELECT * FROM Products
/*---------------------------------------------------------------QUERY3--------------------------------------------------------*/
/*INSERT VALUES INTO CUSTOMER*/
DROP PROCEDURE IF EXISTS insert_customer
GO 
CREATE PROCEDURE insert_customer
@customer_name VARCHAR(64),
@customer_address VARCHAR(64)
AS
BEGIN
INSERT INTO Customer VALUES(@customer_id,@customer_address)
END
GO
/*INSERT INTO Purchase*/
DROP PROCEDURE insert_purchase
GO 
CREATE PROCEDURE insert_purchase
@customer_name VARCHAR(64),
@prod_id VARCHAR(64)
AS
BEGIN
INSERT INTO Purchases VALUES(@customer_id,@prod_id)
END
GO
SELECT * From Customer;
SELECT * from Purchases
/*----------------------------------------------------------QUERY4 ----------------------------------------------------------------*/
/* INSERT INTO Account*/
DROP PROCEDURE IF EXISTS insert_Account
GO
CREATE PROCEDURE insert_account
@account_no REAL,
@account_date VARCHAR(64),
@cost REAL
AS
BEGIN
INSERT into Account Values(@account_no,@account_date,@cost)
END
GO
/* INSERT INTO PRODUCT1Account*/
DROP PROCEDURE IF EXISTS insert_prod1
GO
CREATE PROCEDURE insert_prod1
@account1_no REAL
AS
BEGIN
INSERT INTO Product1_Account VALUES(@account1_no)
END
GO
/*INSERT INTO PRODUCT2 Account */
DROP PROCEDURE IF EXISTS insert_prod2
GO
CREATE PROCEDURE insert_prod2
@account2_no VARCHAR(64)
AS
BEGIN
INSERT INTO Product2_Account VALUES(@account2_no)
END
go
/* INSERT INTO PROD3_ACCOUNT */
DROP PROCEDURE IF EXISTS insert_prod3
GO
CREATE PROCEDURE insert_prod3
@account3_no VARCHAR(64)
AS
BEGIN
INSERT INTO Product3_Account VALUES(@account3_no)
END
GO
SELECT * FROM Account
/*-------------------------------------------------Query 5 --------------------------------------------------------------------------*/
/* INSERT INTO Complaint */
DROP PROCEDURE IF EXISTS insert_complaint
GO
CREATE PROCEDURE insert_complaint
@complaint_id VARCHAR(64),
@complaint_date VARCHAR(64),
@description_of_product varchar(64),
@type_of_treatment varchar(64)
AS
BEGIN
INSERT into Complaint VALUES(@complaint_id,@complaint_date,@description_of_product,@type_of_treatment)
END
GO
/* INSERT INTO PRODUCTS */
DROP PROCEDURE IF EXISTS insert_prods
GO
CREATE PROCEDURE insert_prods
@prod_id INT,
@prod_date DATE,
@time_spent VARCHAR(64),
@Worker_name VARCHAR(64),
@Tester_name VARCHAR(64),
@Technical_staff VARCHAR(64)
AS
BEGIN
INSERT INTO Products(prod_id,prod_date,time_spent,Worker_name,Tester_name,Tech_staff_name) VALUES(@prod_id,@prod_date,@time_spent,@Worker_name,@Tester_name,@Technical_staff)
END
GO
/*INSERT PRODUCTS TO MAKE */
DROP PROCEDURE IF EXISTS insert_make
GO
CREATE PROCEDURE insert_make
@cust_name VARCHAR(64),
@prod_id REAL,
@complaint_id REAL
AS
BEGIN
INSERT into makes VALUES(@cust_name,@prod_id,@complaint_id)
END
GO
SELECT * from Complaints
/*---------------------------Query 6 ----------------------------------------------------------------------------*/
/*INSERT INTO ACCIDENTS */
DROP PROCEDURE IF EXISTS insert_accidents
GO
CREATE PROCEDURE insert_accidents
@accident_id REAL,
@date_of_accident VARCHAR(64),
@no_of_workdays REAL
AS
BEGIN
INSERT into Accidents VALUES(@accident_id,@date_of_accident,@no_of_workdays)
END
GO
/*INSERT INTO FACES */
DROP PROCEDURE IF EXISTS insert_faces
GO
CREATE PROCEDURE insert_faces
@Technical_staff_name VARCHAR(64),
@prod_id REAL,
@accident_id REAL
AS
BEGIN
INSERT into Faces VALUES(@Technical_staff_name,@prod_id,@accident_id)
END
GO
/*INSERT INTO HAVE*/
DROP PROCEDURE IF EXISTS insert_have
GO
CREATE PROCEDURE insert_have
@worker_name VARCHAR(64),
@prod_id REAL,
@accident_no VARCHAR(64)
AS
BEGIN
INSERT into Have VALUES(@worker_name,@prod_id,@accident_no)
END
GO
SELECT * from Accidents
/*---------------------------------------------------------------Query 7 ---------------------------------------------------------------*/
SELECT prod_date, Time_spent from Products s,Product1 p where s.prod_id = p.prod_id
SELECT prod_date, Time_spent from Products s,Product2 p where s.prod_id = p.prod_id
SELECT prod_date, Time_spent from Products s,Product3 p where s.prod_id = p.prod_id
/*--------------------------------------------------------------------Query 8 ----------------------------------------------------------*/
SELECT prod_id from Products p, Workers w where p.Worker_name = w.Worker_name
/*--------------------------------------------------------------------Query 9----------------------------------------------------------*/
SELECT COUNT(s.prod_id) AS count  from Products p ,Checks s,Quality_controller q , Got g where s.prod_id = p.prod_id and p.Tester_name = s.Tester_name  and g.prod_id = p.prod_id
/*-----------------------------------------------------------------Query10-----------------------------------------------------------------*/

SELECT sum(cost) as totalcost FROM Account a , Requests r,Repairs p,Product3 pd,Product3_Account pa3 where r.prod_id = p.prod_id and pd.Account3_no = pa3.Account3_no and pa3.Account3_no = a.Account_no
/*----------------------------------------------------------------------Query 11 -----------------------------------------------------------*/
SELECT c.customer_name  as Customer from Customer c , Purchases p , Product2 p2 where c.customer_name = p.Customer_name and p.prod_id = p2.prod_id and p2.color_of_prod = 'red' ORDER BY c.customer_name
/*-------------------------------------------------------------------------Query 12 --------------------------------------------------------------------------*/
 DROP PROCEDURE IF EXISTS compare_Salary
 GO
 CREATE PROCEDURE compare_Salary
 @empsalary INT
 AS
 BEGIN
SELECT e_name,salary from Employee where salary > @empsalary
END
GO
/*--------------------------------------------------------------------------Query 13 ---------------------------------------------------*/
SELECT COUNT(no_of_workdays) as totalworkdays from Accidents a, Repairs r,Got g,Faces f WHERE r.prod_id = g.prod_id and f.Accident_no = a.Accident_no
/*-------------------------------------------------------------------------QUERY14-------------------------------------------------------------*/
SELECT Avg(cost) as avgcost FROM Account a ,  Products p,Tracks t where YEAR(prod_date) = '2001';
/*-----------------------------------------------------------------------------Query15----------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS delete_accident
GO
CREATE PROCEDURE delete_accident
@RANGE1  DATE,
@RANGE2 DATE
AS
BEGIN
DELETE FROM Accidents where accident_date  BETWEEN @Range1 and @RANGE2;

END
GO
SELECT * FROM Acciden-- Get a list of tables and views in the current database
SELECT table_catalog [database], table_schema [schema], table_name [name], table_type [type]
FROM INFORMATION_SCHEMA.TABLES
GO