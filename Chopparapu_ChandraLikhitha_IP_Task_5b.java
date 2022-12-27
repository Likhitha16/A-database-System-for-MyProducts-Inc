import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class IndividualProject {

    // Database credentials
    final static String HOSTNAME = "chop0005-sql-server.database.windows.net";
    final static String DBNAME = "cs-dsa-4513-sql-db";
    final static String USERNAME = "chop0005";
    final static String PASSWORD = "VeleikChintu@2001";

    // Database connection string
    final static String URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
            HOSTNAME, DBNAME, USERNAME, PASSWORD);

    // Query templates
//    final static String QUERY_TEMPLATE_1 = "INSERT INTO Student " + 
//                                           "VALUES (?, ?, ?, ?, ?, ?)
//    // User input prompt//
    final static String PROMPT = 
            "\nPlease select one of the options below: \n" +
            "1) Insert into Employees; \n" + 
            "2) Insert into Products; \n" + 
            "3) Insert into Customers;\n"
            + "4) Insert into Accounts\n" +
            "5) Insert into complaints\n" +
            "6) Insert into Accidents\n" + 
            "7) Select date and time from particular products\n" +
            "8)Retreive all products made by a particular Worker\n" +
            "9) Retrieve the total number of errors a particular quality controller made.\n" +
            "10)Retrieve the total costs of the products in the product3 category which were repaired at the request of a particular quality controller\n" +
            "11) Retrieve all customers (in name order) who purchased all products of a particular color\n" +
            "12) Retrieve all employees whose salary is above a particular salary (1/month).\r\n"
            + "13) SELECT COUNT(no_of_workdays) as totalworkdays from Accidents a, Repairs r,Got g,Faces f WHERE r.prod_id = g.prod_id and f.Accident_no = a.Accident_no\r\n"
            + "14) SELECT Avg(cost) as avgcost FROM Account a ,  Products p,Tracks t where YEAR(prod_date) = '2001'\r\n"
            + "15)Delete\n" +
            "16) Import the file\n"+
            "17) Export the file\n"+
            "18) Quit";

    public static void main(String[] args) throws SQLException, NumberFormatException, IOException {

        System.out.println("WELCOME TO THE DATABASE SYSTEM OF MyProducts, Inc.\r\n"+ "");

        final Scanner sc = new Scanner(System.in); // Scanner is used to collect the user input
        String option = ""; // Initialize user option selection as nothing
        while (!option.equals("3")) { // As user for options until option 3 is selected
            System.out.println(PROMPT); // Print the available options
            option = sc.next(); // Read in the user option selection

            switch (option) { // Switch between different options
              //Case 1 to enter employee details  
            case "1":
            	
            		System.out.println("Enter Employee name");
            		sc.nextLine();

            		String e_name = sc.nextLine();
            		System.out.println("Enter Address of the employee");
            		String e_address = sc.nextLine();
            		System.out.println("Enter salary of the employee");
            		double salary = sc.nextDouble();
            		System.out.println("Connecting to the database...");
                    // Get the database connection, create statement and execute it right away, as no user input need be collected
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        System.out.println("Dispatching the query...");
                        try (
                            final PreparedStatement statement = connection.prepareStatement("EXEC insert_employee @e_name =?, @e_address =?, @salary =?;")){
                        	//Adding employee name, address and salary to the database				
                        	statement.setString(1, e_name);
                        					statement.setString(2, e_address);
                        					statement.setDouble(3, salary);
                        					int result = statement.executeUpdate();
                        					System.out.println(result+ "Rows inserted" );
                        					//Choosing which type of employee you want to add data to 1)Technical_staff 2)Workers 3)Quality Controller
                        					final String Staff_options = "\"\\nWhich staff you want to add values to? \\n\" +\r\n"
                        							+ "            \"1) Technical Staff; \\n\" + \r\n"
                        							+ "            \"2) Workers; \\n\" + \r\n"
                        							+ "            \"3) Quality_Controllers";
                        				System.out.println(Staff_options);
				                        System.out.println("Enter your choice:\n");
				                        int choice = sc.nextInt();
				                        //Choice 1 to add data to a employee who is a technical staff
				                        if(choice == 1)
				                        {
				                 
				                    		System.out.println("Enter Technical position");
				                    		sc.nextLine();
				                    		String tech_position = sc.nextLine();
				                    		final PreparedStatement statement1 = connection.prepareStatement("EXEC insert_tech_staff @tech_staff_name = ?, @tech_position =?;");
				                            statement1.setString(1,e_name);
				                            statement1.setString(2, tech_position);
				                            result = statement1.executeUpdate();
				                            System.out.println(result+ "Rows inserted" );
				                            System.out.println("Enter degree: ");
				                            String degree = sc.nextLine();
				                    		final PreparedStatement statement2 = connection.prepareStatement("EXEC insert_tech_staff_degrees @tech_staff_name = ?, @degree =?;");
				                    		statement2.setString(1,e_name);
				                    		statement2.setString(2, degree);
				                    		result = statement2.executeUpdate();
				                            System.out.println(result+ "Rows inserted" );
				                            
				                          

				                    		
				                        }
				                        //choice2 to add data to employee who is a Worker
				                        if(choice==2)
				                        {
				       
				                    		System.out.println("Enter maximum number of products");
				                    		int number = sc.nextInt();
				                    		final PreparedStatement statement3 = connection.prepareStatement("EXEC insert_workers @worker_name = ?, @no_of_products =?;");
				                    		statement3.setString(1,e_name);
				                    		statement3.setInt(2, number);
				                    		result = statement3.executeUpdate();
				                            System.out.println(result+ "Rows inserted" );
				                        }
				                        //choice3 to add data to a employee who is a Quality Controller
				                        if(choice == 3)
				                        {
				                    		System.out.println("Enter type of the product");
				                    		sc.nextLine();
				                    		String Type_of_product = sc.nextLine();
				                    		final PreparedStatement statement4 = connection.prepareStatement("EXEC insert_testers @tester_name = ?, @type =?;");
				                    		statement4.setString(1, e_name);
				                    		statement4.setString(2, Type_of_product);
				                    		result = statement4.executeUpdate();
				                            System.out.println(result+ "Rows inserted" );
				                    		
				                    		
				                        }
                        					
                        				
                               
                        }}
                    

                    break;
                    
                  //Case 2 to enter the Data to products Table  
            case "2" :
            	System.out.println("Enter product Id");
            	int prod_id = sc.nextInt();
            	System.out.println("Enter Product date");
            	sc.nextLine();
            	String pdate = sc.nextLine();
            	System.out.println("Enter time spent on the product");
            	String ptime = sc.nextLine();
            	//Choose product is associated with which type of worker
            	final String ProdAssociations = "\"\\nChoose Product is associated with which which staff? \\n\" +\r\n"
						+ "            \"1) Worker and Quality_Controllers; \\n\" + \r\n"
						+ "            \"2) Technical Staff; \\n\" + \r\n"
						+ "            \"3) ";
            	System.out.println(ProdAssociations);
            	System.out.println("Enter your Choice");
            	int selectedoption = sc.nextInt();
        		System.out.println("Connecting to the database...");
        		final Connection connection = DriverManager.getConnection(URL);
                     System.out.println("Dispatching the query...");
            	if(selectedoption ==1)
            	{
            		//option 1 is to insert the values to products that are associated with worker and tester
            		System.out.println("Enter name of worker");
            		sc.nextLine();
            		String workers_name = sc.nextLine();
            		System.out.println("Enter name of Tester");
            		String Tester_name = sc.nextLine();
            		final PreparedStatement statement5 = connection.prepareStatement(" EXEC insert_workertester_prods @prod_id = ?,@prod_date = ? , @time_spent = ? , @Worker_name = ? , @Tester_name = ?;");
            		statement5.setInt(1, prod_id);
            		statement5.setString(2, pdate);
            		statement5.setString(3, ptime);
            		statement5.setString(4, workers_name );
            		statement5.setString(5, Tester_name);
            		int result = statement5.executeUpdate();
                    System.out.println(result+ "Rows inserted" );
            		
            		
            		
            		
            	}
            	if(selectedoption==2)
            	{
            		//option2 is to insert values to products associated with Technical_staff
            		System.out.println("Enter name of Technical Staff Name");
            		sc.nextLine();
            		String Tech_staff_name = sc.nextLine();
            		final PreparedStatement statement6 = connection.prepareStatement("EXEC insert_tech_staff_prods @prod_id = ?,@prod_date = ? , @time_spent = ? , @Tech_staff_name=?;");
            		statement6.setInt(1, prod_id);
            		statement6.setString(2, pdate);
            		statement6.setString(3, ptime);
            		statement6.setString(4,Tech_staff_name);
            		int result = statement6.executeUpdate();
                    System.out.println(result+ "Rows inserted" );
            		
            	}
            	break;
            
            case "3":
            	//Add customer data to database
            	System.out.println("Enter name of the customer");
            	sc.nextLine();
            	String Customer_name = sc.nextLine();
            	System.out.println("Enter Customer address");

            	String customer_address = sc.nextLine();
            	System.out.println("Connecting to the database...");
        		final Connection  connections = DriverManager.getConnection(URL);
                     System.out.println("Dispatching the query...");
                     //inserting the customer
             		final PreparedStatement statement7 = connections.prepareStatement(" EXEC insert_customer @customer_name = ?,@customer_address = ?;");
             		statement7.setString(1, Customer_name);
             		statement7.setString(2, customer_address);
             		int result = statement7.executeUpdate();
                    System.out.println(result+ "Rows inserted" );
                    System.out.println("Enter the prod_id for which a Customer is Associated");
                    prod_id = sc.nextInt();//takes the product id for which the customer is associated
                    	final PreparedStatement statement8 = connections.prepareStatement("EXEC insert_purchase @customer_name = ?,@prod_id = ?;");
                    	statement8.setString(1, Customer_name);
                    	statement8.setInt(2, prod_id);
                    	result = statement8.executeUpdate();
                        System.out.println(result+ "Rows inserted" );
                        break;

                    

                
            case "4":
            	//query toadd data to account
            	System.out.println("Enter Account No.");
            	int account_no = sc.nextInt();
            	System.out.println("Enter Account_date");
            	sc.nextLine();
            	String account_date = sc.nextLine();
            	System.out.println("Enter cost of the product");
            	int cost = sc.nextInt();
            	final Connection connection1 = DriverManager.getConnection(URL);
                System.out.println("Dispatching the query...");
                //sq; query to insert the data into accounts
        		final PreparedStatement statement9 = connection1.prepareStatement(" EXEC insert_account @account_no = ?,@account_date = ?,@cost = ?;");
        		statement9.setInt(1, account_no);
        		statement9.setString(2, account_date);
        		statement9.setInt(3, cost);
        		result = statement9.executeUpdate();
                System.out.println(result+ "Rows inserted" );
                //choosing which type of product you want to add data?
                final String Account_options = "\"\\nWhich Account you want? \\n\" +\r\n"
						+ "            \"1) Product1 Account; \\n\" + \r\n"
						+ "            \"2) Product2 Account; \\n\" + \r\n"
						+ "            \"3) Product3 Account";
			System.out.println(Account_options);
            System.out.println("Enter your choice:\n");
            int choice = sc.nextInt();
            if(choice == 1)
            {
            	//this adds account no to product1
        		final PreparedStatement statement10 = connection1.prepareStatement("EXEC insert_prod1 @account1_no = ?;");

                statement10.setInt(1,account_no);
                result = statement10.executeUpdate();
                System.out.println(result+ "Rows inserted" );
                }
            if(choice==2)
            {
//this adds accountno to Product2
            	
        		final PreparedStatement statement10 = connection1.prepareStatement("EXEC insert_prod2 @account2_no = ?;");
                statement10.setInt(1,account_no);
                result = statement10.executeUpdate();
                System.out.println(result+ "Rows inserted" );
            }
            if(choice == 3)
            {
            	//this adds accountno to product3
        		final PreparedStatement statement10 = connection1.prepareStatement("EXEC insert_prod3 @account3_no = ?;");
                statement10.setInt(1,account_no);
                result = statement10.executeUpdate();
                System.out.println(result+ "Rows inserted" );
        		
        		
            }
				
             break;   
             
            case "5" :
            	System.out.println("Enter Complaint id");
            	int complaint_id = sc.nextInt();
            	System.out.println("Enter date of complaint");
            	sc.nextLine();
            	String complaint_date = sc.nextLine();
            	System.out.println("Enter description of complaint");
            	String complaint_desc = sc.nextLine();
            	System.out.println("Enter type of treatment");
            	String treatment_type = sc.nextLine();
            	final Connection connection2= DriverManager.getConnection(URL);
                System.out.println("Dispatching the query...");
        		final PreparedStatement statement11 = connection2.prepareStatement(" EXEC insert_complaint @complaint_id = ?,@complaint_date = ?,@description_of_product = ?,@type_of_treatment = ?;");
            	statement11.setInt(1, complaint_id);
            	statement11.setString(2, complaint_date);
            	statement11.setString(3, complaint_desc);
            	statement11.setString(4,treatment_type);
            	result = statement11.executeUpdate();
                System.out.println(result+ "Rows inserted" );
                System.out.println("Enter name of the customer");
            	String Customer_name1 = sc.nextLine();
            	System.out.println("Enter Customer address");
            	String customer_address1 = sc.nextLine();
        		final PreparedStatement statement12 = connection2.prepareStatement("EXEC insert_customer @customer_name = ?,@customer_address = ?;");
        		statement12.setString(1, Customer_name1);
        		statement12.setString(2, customer_address1);
        		result = statement12.executeUpdate();
                System.out.println(result+ "Rows inserted" );
        		System.out.println("Enter product Id");
            	int prod_id1 = sc.nextInt();
            	System.out.println("Enter Product date");
            	sc.nextLine();
            	String pdate1 = sc.nextLine();
            	System.out.println("Enter time spent on the product");
            	String ptime1 = sc.nextLine();
            	System.out.println("Enter name of worker");
        		String Workername = sc.nextLine();
        		System.out.println("Enter name of tester");
        		String testername = sc.nextLine();
        		System.out.println("Enter name of Technical_staff");
        		String tech_staff_name = sc.nextLine();
        		final PreparedStatement statement13= connection2.prepareStatement("EXEC insert_prods @prod_id = ? ,@prod_date = ? ,@time_spent = ?,@Worker_name = ? ,@Tester_name = ?,@Technical_staff = ?");
        		statement13.setInt(1, prod_id1);
        		statement13.setString(2, pdate1);
        		statement13.setString(3, ptime1);
        		statement13.setString(4, Workername);
        		statement13.setString(5, testername);
        		statement13.setString(6, tech_staff_name); 
        		result = statement13.executeUpdate();
                System.out.println(result+ "Rows inserted" );
        		final PreparedStatement statement14= connection2.prepareStatement("EXEC insert_make @cust_name = ? ,@prod_id = ? ,@complaint_id = ?");
        		statement14.setString(1, Customer_name1);
        		statement14.setInt(2, prod_id1);
        		statement14.setInt(3, complaint_id);
        		result = statement14.executeUpdate();
                System.out.println(result+ "Rows inserted" );
        		
            	
            	
            	
            	break;
            case "6" :
            	System.out.println("Enter Accident Id:");
            	int accident_id = sc.nextInt();
            	System.out.println("Enter Accident date : ");
            	sc.nextLine();
            	String accident_date = sc.nextLine();
            	System.out.println("Enter no_of_workdays");
            	int workdays = sc.nextInt();
            	final Connection connection3= DriverManager.getConnection(URL);
                System.out.println("Dispatching the query...");
                final PreparedStatement statement15 = connection3.prepareStatement("EXEC insert_accidents @accident_id = ? , @date_of_accident = ? ,@no_of_workdays = ?");
                statement15.setInt(1, accident_id);
                statement15.setString(2, accident_date);
                statement15.setInt(3, workdays);
                result = statement15.executeUpdate();
                System.out.println("Enter product Id");
            	int prod_id2 = sc.nextInt();
            	System.out.println("Enter Product date");
            	sc.nextLine();
            	String pdate2 = sc.nextLine();
            	System.out.println("Enter time spent on the product");
            	String ptime2 = sc.nextLine();
            	final PreparedStatement statements= connection3.prepareStatement("EXEC insert_prods1 @prod_id = ? ,@prod_date = ? ,@time_spent = ?");
        		statements.setInt(1, prod_id2);
        		statements.setString(2, pdate2);
        		statements.setString(3, ptime2);
        		result = statements.executeUpdate();
                System.out.println(result+ "Rows inserted" );
                System.out.println("Enter Employee name");
        		String staffname = sc.nextLine();
        		System.out.println("Enter Address of the employee");
        		String emp_address = sc.nextLine();
        		System.out.println("Enter salary of the employee");
        		double emp_salary = sc.nextDouble();
         final PreparedStatement statementnew = connection3.prepareStatement("EXEC insert_employee @e_name =?, @e_address =?, @salary =?;");
                    					statementnew.setString(1, staffname);
                    					statementnew.setString(2, emp_address);
                    					statementnew.setDouble(3, emp_salary);
                    					 int output = statementnew.executeUpdate();
                    					System.out.println(output+ "Rows inserted" );
                    					final String Staff_options = "\"\\nWhich staff you want to add values to? \\n\" +\r\n"
                    							+ "            \"1) Technical Staff; \\n\" + \r\n"
                    							+ "            \"2) Workers; \\n\" + \r\n"
                    							+ "            \"3) Quality_Controllers";
                    				System.out.println(Staff_options);
			                        System.out.println("Enter your choice:\n");
			                        int choice1 = sc.nextInt();
			                        if(choice1 == 1)
			                        {
			                 
			                    		System.out.println("Enter Technical position");
			                    		sc.nextLine();
			                    		String tech_position = sc.nextLine();
			                    		final PreparedStatement statement23 = connection3.prepareStatement("EXEC insert_tech_staff @tech_staff_name = ?, @tech_position =?;");
			                            statement23.setString(1,staffname);
			                            statement23.setString(2, tech_position);
			                            output= statement23.executeUpdate();
			                            System.out.println(output+ "Rows inserted" );
			                            System.out.println("Enter degree: ");
			                            String degree = sc.nextLine();
			                    		final PreparedStatement statement24 = connection3.prepareStatement("EXEC insert_tech_staff_degrees @tech_staff_name = ?, @degree =?;");
			                    		statement24.setString(1,staffname);
			                    		statement24.setString(2, degree);
			                    		output = statement24.executeUpdate();
			                            System.out.println(output+ "Rows inserted" );
			                            
			                        	final PreparedStatement statement21= connection3.prepareStatement("EXEC insert_have @Worker_name  = ? ,@prod_id = ? ,@accident_id = ?");
			                        	statement21.setString(1, staffname);
			                        	statement21.setInt(2, prod_id2);
			                        	statement21.setInt(3, accident_id);
			                        	result = statement21.executeUpdate();
			                            System.out.println(result+ "Rows inserted" );
			                        	
			                            
			                            
			                          

			                    		
			                        }
			                        if(choice1==2)
			                        {
			       
			                    		System.out.println("Enter maximum number of products");
			                    		int number = sc.nextInt();
			                    		final PreparedStatement statement25 = connection3.prepareStatement("EXEC insert_workers @worker_name = ?, @no_of_products =?;");
			                    		statement25.setString(1,staffname);
			                    		statement25.setInt(2, number);
			                    		result = statement25.executeUpdate();
			                            System.out.println(result+ "Rows inserted" );
			                            final PreparedStatement statement22= connection3.prepareStatement("EXEC insert_have @worker_name  = ? ,@prod_id = ? ,@accident_no = ?");
			                        	statement22.setString(1, staffname);
			                        	statement22.setInt(2, prod_id2);
			                        	statement22.setInt(3, accident_id);
			                        	result = statement22.executeUpdate();
			                            System.out.println(result+ "Rows inserted" );
			                        }
                
                
            	
            	break;
            case "7":
            	final String Product_choice = "\"\\nWhich products date and time you want to retreive? \\n\" +\r\n"
						+ "            \"1) Product 1; \\n\" + \r\n"
						+ "            \"2) Product 2; \\n\" + \r\n"
						+ "            \"3) Product 3";
            			System.out.println("Enter your product Choice");
            			int prod_choice = sc.nextInt();
            			if(prod_choice == 1)
            			{
            				final Connection connection4= DriverManager.getConnection(URL);
                            System.out.println("Dispatching the query...");
                            Statement stmt = connection4.createStatement();
                            ResultSet rs;
                 
                            rs = stmt.executeQuery("SELECT prod_date, Time_spent from Products s,Product1 p where s.prod_id = p.prod_id");
                            while ( rs.next() ) {
                                String Date_of_product1 = rs.getString("prod_date");
                                System.out.println(Date_of_product1);
                                String Time_of_product1 = rs.getString("Time_spent");
                                System.out.println(Time_of_product1);
            			}
                            if(prod_choice ==2)
                            {
                            	 System.out.println("Dispatching the query...");
                                 stmt = connection4.createStatement();
                      
                                 rs = stmt.executeQuery("SELECT prod_date, Time_spent from Products s,Product2 p where s.prod_id = p.prod_id");
                                 while ( rs.next() ) {
                                     String Date_of_product2 = rs.getString("prod_date");
                                     System.out.println(Date_of_product2);
                                     String Time_of_product2 = rs.getString("Time_spent");
                                     System.out.println(Time_of_product2);
                            }
                                 if(prod_choice == 3)
                                 {
                                	 System.out.println("Dispatching the query...");
                                     stmt = connection4.createStatement();
                          
                                     rs = stmt.executeQuery("SELECT prod_date, Time_spent from Products s,Product3 p where s.prod_id = p.prod_id");
                                     while ( rs.next() ) {
                                         String Date_of_product3 = rs.getString("prod_date");
                                         System.out.println(Date_of_product3);
                                         String Time_of_product3 = rs.getString("Time_spent");
                                         System.out.println(Time_of_product3);
                                 }
                                 }
            			}}
            				break;
            case "8" : 
            	final Connection connection4= DriverManager.getConnection(URL);
                System.out.println("Dispatching the query...");
                Statement stmt = connection4.createStatement();
                ResultSet rs;
     
                rs = stmt.executeQuery("SELECT prod_id from Products p, Workers w where p.Worker_name = w.Worker_name");
                while ( rs.next() ) {
                    String Product_id= rs.getString("prod_id");
                    System.out.println(Product_id);
                    
			}
            	
            	break;
            case "9":
            	final Connection connection5= DriverManager.getConnection(URL);
                System.out.println("Dispatching the query...");
                 stmt = connection5.createStatement();
              
     
                rs = stmt.executeQuery("SELECT COUNT(s.prod_id) AS count  from Products p ,Checks s,Quality_controller q , Got g where s.prod_id = p.prod_id and p.Tester_name = s.Tester_name  and g.prod_id = p.prod_id");
                while ( rs.next() ) {
                    String Product_id= rs.getString("count");
                    System.out.println(Product_id);
                }
            	break;
            case "10":
            	final Connection connection6= DriverManager.getConnection(URL);
                System.out.println("Dispatching the query...");
                 stmt = connection6.createStatement();
              
     
                rs = stmt.executeQuery("SELECT sum(cost) as totalcost FROM Account a , Requests r,Repairs p,Product3 pd,Product3_Account pa3 where r.prod_id = p.prod_id and pd.Account3_no = pa3.Account3_no and pa3.Account3_no = a.Account_no\r\n"
                		+ " ");
                while ( rs.next() ) {
                    String Total_cost= rs.getString("totalcost");
                    System.out.println(Total_cost);
                }
            	break;
            case "11":
            	final Connection connection7= DriverManager.getConnection(URL);
                System.out.println("Dispatching the query...");
                 stmt = connection7.createStatement();
              
     
                rs = stmt.executeQuery("SELECT c.customer_name  as Customer from Customer c , Purchases p , Product2 p2 where c.customer_name = p.Customer_name and p.prod_id = p2.prod_id and p2.color_of_prod = 'red' ORDER BY c.customer_name");
                while ( rs.next() ) {
                    String Customername= rs.getString("Customer");
                    System.out.println(Customername);
                }
            	break;
            case "12":
            	System.out.println("Enter Salary");
            	   
                final int empsalary = sc.nextInt();
            	final Connection connection8= DriverManager.getConnection(URL);
            	
                System.out.println("Dispatching the query...");
        		final PreparedStatement statementemp = connection8.prepareStatement("EXEC compare_Salary @empsalary = ?"); 
        		statementemp.setInt(1, empsalary);
        		final ResultSet resultSet = statementemp.executeQuery();
                System.out.println("Output:");
                while (resultSet.next()) {
   				 System.out.println(String.format("%s",
   						 resultSet.getString(1)));
   				 System.out.println(String.format("%d",
   						 resultSet.getInt(2)));
   				 
                }
                
            	break;
            case "13" :
            	final Connection connection9= DriverManager.getConnection(URL);
                System.out.println("Dispatching the query...");
                 stmt = connection9.createStatement();
              
     
                rs = stmt.executeQuery("SELECT COUNT(no_of_workdays) as totalworkdays from Accidents a, Repairs r,Got g,Faces f WHERE r.prod_id = g.prod_id and f.Accident_no = a.Accident_no");
                while ( rs.next() ) {
                    String totalworkdays= rs.getString("totalworkdays");
                    System.out.println(totalworkdays);
                }
            	break;
            case "14":
            	final Connection connection10= DriverManager.getConnection(URL);
                System.out.println("Dispatching the query...");
                 stmt = connection10.createStatement();
              
     
                rs = stmt.executeQuery("SELECT Avg(cost) as avgcost FROM Account a ,  Products p,Tracks t where YEAR(prod_date) = '2001';");
                while ( rs.next() ) {
                    String avg_product= rs.getString("avgcost");
                    System.out.println(avg_product);
                }
            	break;
            case "15":
            	final Connection connectiond= DriverManager.getConnection(URL);
                System.out.println("Dispatching the query...");
                 stmt = connectiond.createStatement();
              
     
                PreparedStatement rs1 = connectiond.prepareStatement("DELETE FROM Accidents where accident_date  BETWEEN '2002-08-09' and '2002-10-07'\r\n");
                result = rs1.executeUpdate();
                System.out.println(result +"row deleted");
                
                

            	break;
            case "16":
            	System.out.println("Enter file name:");
                final String f_name = sc.next();
                File F = new File("C:\\Users\\Likitha\\Desktop\\DBMS\\Individual project\\" +f_name);
                BufferedReader b= new BufferedReader(new FileReader(F));
                
                String st;
    			//Importing each row until the file is empty

    			while ((st = b.readLine()) != null ) { 
    			String Employee[] = st.split(",");
    			String name = Employee[0];
    			String address = Employee[1];
    			int sal = Integer.parseInt(Employee[2]);
    			System.out.println("Connecting to the database...");
                
                try (final Connection connectionfile = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connectionfile.prepareStatement(
                        		"EXEC insert_employee @e_name =?, @e_address =?, @salary =?;")) {
                        // Populate the query template with the data collected from the user
                        statement.setString(1, name);
                        statement.setString(2, address);
                        statement.setInt(3, sal);

                        System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        int  e_result= statement.executeUpdate();
                        System.out.println(e_result+" employee imported");
                    }}}
                
    			
            	break;
            case "17":
            	
            	
            	System.out.println("Enter file name:");
            	sc.nextLine();
                final String filename = sc.nextLine();
        		
        		System.out.println("Enter color");
   
                final String color = sc.nextLine();
            	FileWriter f=new FileWriter("C:\\Users\\Likitha\\Desktop\\DBMS\\Individual project\\"+filename);

               // FileWriter f=new FileWriter("E:\\DBMS\\indi_project\\"+fi);
                
                System.out.println("Connecting to the database...");
                
                try (final Connection connectionexport = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connectionexport.prepareStatement(
                        		"EXEC select_customer @color = ?")) {
                        // Populate the query template with the data collected from the user
                        statement.setString(1, color);
                        
                        
                        
                        System.out.println("Dispatching the query...");
                        // Actually execute the query
                        final ResultSet resultSet_7 = statement.executeQuery();
                        System.out.println("Output:");
                        while (resultSet_7.next()) {
           				 	f.write(String.format("%s,",
           						 resultSet_7.getString(1)));
                        }
                        f.close();
                    }
                }                        
            	break;
        		
                case "18": // Do nothing, the while loop will terminate upon the next iteration
                    System.out.println("Exiting! Good-bye!");
                    break;
                default: // Unrecognized option, re-prompt the user for the correct one
                    System.out.println(String.format(
                        "Unrecognized option: %s\n" + 
                        "Please try again!", 
                        option));
                    break;
            }
        }

        sc.close(); // Close the scanner before exiting the application
    }
}
