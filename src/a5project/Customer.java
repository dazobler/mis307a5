package a5project;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;

/**
 * Customer class with methods that can look up Customer information and add/remove/update entries.
 * @authors ajensen, dtan, dazobler
 */
public class Customer extends a5project {
	//Construct ArrayLists to hold information for customers
    public static ArrayList<Integer> Customer_Account_Number = new ArrayList<Integer>();
    public static ArrayList<String> First_Name = new ArrayList<String>(); 
    public static ArrayList<String> Last_Name = new ArrayList<String>(); 
    public static ArrayList<String> Membership_Status = new ArrayList<String>();
    
    //Reads from input file located in the program folder
    public static void readFile() throws FileNotFoundException {  
	// Construct the Scanner and PrintWriter objects for reading and writing
	String inputFileName = "Customers.txt"; 
	File inputFile = new File(inputFileName);
	Scanner ins = new Scanner(inputFile); //changed from in to ins in order to avoid confusion in program between scanner established in main menu and this class.
            
	while (ins.hasNextLine()){
            Customer_Account_Number.add(ins.nextInt());
            ins.nextLine();
            First_Name.add(ins.nextLine());
            Last_Name.add(ins.nextLine());
            Membership_Status.add(ins.nextLine());
        }
	ins.close();  
    }
    /**
     * Prints all customer info to a text file 
     */
    public static void customerMenu() throws Exception {
    	boolean run = true;
		while (run) {
	        System.out.printf("\nA)dd Customer  U)pdate Customer  R)emove Customer  G)et Customer Info  L)oyal Customers  P)rint to File  B)ack: "); 
	        String cmd = in.nextLine();
	        
	        //Return to main menu
	        if (cmd.equalsIgnoreCase("B")) { run = false; }
	        
	        else if (cmd.equalsIgnoreCase("Q")) {
	        	Login.logOut(run);
	        }
	        
	        //Add customer to DB/Text file
	        else if (cmd.equalsIgnoreCase("A")) {
	        	//addCustomer();
	        	SQLaddCustomer();
	        }
	        
	        //Modify customer information	        
	        else if (cmd.equalsIgnoreCase("U")) {
	        	System.out.println("Enter customer ID to update: ");
	        	int acct = in.nextInt();
	        	//updateCustomer(acct);
	        	SQLupdateCustomer(acct);
	        }
	        
	        //Remove customer
	        else if (cmd.equalsIgnoreCase("R")) {
	        	System.out.println("Enter customer ID to remove: ");
	        	int acct = in.nextInt();
	        	//removeCustomer(acct);
	        	SQLremoveCustomer(acct);
	        }
	        
	        //Find customer info
	        else if (cmd.equalsIgnoreCase("G")) {
	        	System.out.println("Enter customer ID to lookup: ");
	        	int acct = in.nextInt();
	        	//getCustomer(acct);
	        	SQLgetCustomer(acct);
	        }
	        
	        //Return all loyal customers
	        else if (cmd.equalsIgnoreCase("L")) {
	        	SQLgetCustomerMembership();
	        }
	        
	        //Print Customers to text file
	        else if(cmd.equalsIgnoreCase("P")) {
	        	SQLsaveFile();
	        }
		}
	 }  //end of CustomerMenu()

    /**
     * Methods for this program that read from text files.
     */
    public static void getCustomers(){
        for (int i = 0; i < Customer_Account_Number.size(); i++){
            System.out.printf("%12s%12s%12s%12s\n", Customer_Account_Number.get(i), First_Name.get(i), Last_Name.get(i), Membership_Status.get(i));
        }
        System.out.println();
    }
    /**
     * Returns customer info from given customer account number
     * @param account_number
     */
    public static void getCustomer(int account_number){
        int index_found = 0;
        boolean found = false;
            for (int i = 0; i < Customer_Account_Number.size(); i++){
            if (Customer_Account_Number.get(i) == (account_number)){
                index_found = i;
                System.out.printf("%12s%12s%12s%12s\n", Customer_Account_Number.get(index_found), First_Name.get(index_found), Last_Name.get(index_found), Membership_Status.get(index_found));
                found = true;
            }
        }
        if (!found){
            System.out.println("Customer does not exist.");
        }
        System.out.println();
    }
    
    /**
     * Adds a customer to the Customer database
     */
    public static void addCustomer(){
        Scanner in2 = new Scanner(System.in);
        System.out.print("Please enter the account number (Example: 100_):  ");
        int one = in2.nextInt();
        System.out.print("Please enter the customer's first name (Example: Austin):  ");
        String two = in2.next();
        System.out.print("Please enter the customer's last name (Example: Jensen):  ");
        String three = in2.next();
        System.out.print("Please enter the customer's membership status (Example: Y or N):  ");
        String four = in2.next();
        
        Customer_Account_Number.add(one);
        First_Name.add(two);
        Last_Name.add(three);
        Membership_Status.add(four);
  
        in2.close();
        System.out.println("Customer has been entered into the database.");
        System.out.println();
    }
    
    /**
     * Removes a customer from the Customer database
     * @param account_number
     */
    public static void removeCustomer(int account_number){
        int index_found = 0;
        boolean found = false;
            for (int i = 0; i < Customer_Account_Number.size(); i++){
            if (Customer_Account_Number.get(i) == (account_number)){
                index_found = i;
                Customer_Account_Number.remove(i);
                First_Name.remove(i);
                Last_Name.remove(i);
                Membership_Status.remove(i);
                found = true;
                System.out.println("Customer has been removed.");
            }
        }
        if (!found){
            System.out.println("Customer does not exist.");
        }
        System.out.println();
    }
    
    /**
     * Updates a customer's information in the Customer database e.g. change membership status
     */
    public static void updateCustomer(int account_number){
        int index_found = 0;
        boolean found = false;
            for (int i = 0; i < Customer_Account_Number.size(); i++){
                if (Customer_Account_Number.get(i) == (account_number)){
                index_found = i;
                
                Scanner in3 = new Scanner(System.in);
                System.out.print("Please enter the new customer's first name (Example: Austin):  ");
                String two = in3.next();
                System.out.print("Please enter the new customer's last name (Example: Jensen):  ");
                String three = in3.next();
                System.out.print("Please enter the new customer's membership status (Example: Y or N):  ");
                String four = in3.next();
        
                First_Name.set(index_found, two);
                Last_Name.set(index_found, three);
                Membership_Status.set(index_found, four);
                
                found = true;
                System.out.println("Customer has been updated.");
                in3.close();
            }
        }
            
        if (!found){
            System.out.println("Customer does not exist.");
        }
        System.out.println();
    }
    
    /**
     * Returns all customers enrolled in the loyalty program.
     */
    public static void getCustomerMembership(){
            for (int i = 0; i < Customer_Account_Number.size(); i++){
            if (Membership_Status.get(i).equals("Y")){
                System.out.printf("%12s%12s%12s%12s\n", Customer_Account_Number.get(i), First_Name.get(i), Last_Name.get(i), Membership_Status.get(i));
            }
        }
        System.out.println();
    }
    
    /**
     * 
     */
    public static void saveFile() throws FileNotFoundException
    {
        String filename = "Customers.txt";
        PrintWriter file = new PrintWriter(filename);
        
        for (int i = 0; i < Customer_Account_Number.size(); i++){
            file.println(Customer_Account_Number.get(i));
            file.println(First_Name.get(i));
            file.println(Last_Name.get(i));
            file.println(Membership_Status.get(i));
        }
        file.close();
        System.out.println("File has been saved.");
        System.out.println();
    }

    /**
     * SQL methods from here on out.
     */
    public static void SQLgetCustomers() throws SQLException {
        Connection conn = SimpleDataSource.getConnection();
        Statement stat = conn.createStatement();
    	ResultSet result = stat.executeQuery("SELECT * FROM Customers");
    	ResultSetMetaData rsmd = result.getMetaData();

    	int columnsNumber = rsmd.getColumnCount();
    	
    	for(int i = 1; i <= columnsNumber; i++){
    		System.out.print(rsmd.getColumnLabel(i) + " "); //Print one element of a row 		
    	}
    	
    	// Iterate through the data in the result set and display it. 
    	while (result.next()) {
    	//Print one row          
    	for(int i = 1 ; i <= columnsNumber; i++){
    		System.out.print(result.getString(i) + " "); //Print one element of a row
    		}
    	System.out.println();//Move to the next line to print the next row.           
    	}
    }
    /**
     * Returns customer info from given customer account number
     * @param account_number
     */
    public static void SQLgetCustomer(int account_number) throws SQLException {
    	Connection conn = SimpleDataSource.getConnection();
        Statement stat = conn.createStatement();
    	ResultSet result = stat.executeQuery("SELECT * FROM Customers WHERE CustomerID="+account_number);

    	ResultSetMetaData rsmd = result.getMetaData();
    	
    	int columnsNumber = rsmd.getColumnCount();
    	
    	for(int i = 1; i <= columnsNumber; i++){
    		System.out.print(rsmd.getColumnLabel(i) + " "); //Print one element of a row 		
    	}
    	System.out.println();
    	// Iterate through the data in the result set and display it. 
    	while (result.next()) {
    	//Print one row          
    	for(int i = 1; i <= columnsNumber; i++){
    		System.out.print(result.getString(i) + " "); //Print one element of a row
    		}
    	System.out.println();//Move to the next line to print the next row.           
    	}    	
    }
    
    /**
     * Adds a customer to the Customer database
     */
    public static void SQLaddCustomer() throws SQLException{
        System.out.print("Please enter the new customer's first name (Example: Austin):  ");
        String two = in.next();
        System.out.print("Please enter the new customer's last name (Example: Jensen):  ");
        String three = in.next();
        System.out.print("Please enter the new customer's membership status (Example: Y or N):  ");
        String four = in.next();
        
        Connection conn = SimpleDataSource.getConnection();
        Statement stat = conn.createStatement();

        stat.execute("INSERT INTO Customers VALUES(DEFAULT, '"+two+" "+three+"', '"+four+"', 0)");
        
        stat.close();
        System.out.println("Customer has been entered into the database.");
        }
    
    /**
     * Removes a customer from the Customer database
     * @param account_number
     */
    public static void SQLremoveCustomer(int account_number) throws SQLException{
    	Connection conn = SimpleDataSource.getConnection();
        Statement stat = conn.createStatement();
        
        stat.execute("DELETE FROM Customers WHERE CustomerID="+account_number);
        stat.close();
    }
    
    /**
     * Updates a customer's information in the Customer database e.g. change membership status
     */
    public static void SQLupdateCustomer(int account_number) throws SQLException {
    	Connection conn = SimpleDataSource.getConnection();
        Statement stat = conn.createStatement();
        System.out.print("Please update the customer's first name (Example: Austin):  ");
        String two = in.next();
        //if null, enter old name
        System.out.print("Please update the new customer's last name (Example: Jensen):  ");
        String three = in.next();
        //if null, enter old name
        System.out.print("Please update the new customer's membership status (Example: Y or N):  ");
        String four = in.next();
        //if null, enter old status
        stat.execute("UPDATE Customers SET CustomerName='"+two+" "+three+"', Loyalty='"+four+" WHERE CustomerID="+account_number);
        
        stat.close();
    }
    
    /**
     * Returns all customers enrolled in the loyalty program.
     */
    public static void SQLgetCustomerMembership() throws SQLException {
    	Connection conn = SimpleDataSource.getConnection();
        Statement stat = conn.createStatement();
    	ResultSet result = stat.executeQuery("SELECT CustomerID, CustomerName FROM Customers WHERE Loyalty='Y'");
    	ResultSetMetaData rsmd = result.getMetaData();

    	int columnsNumber = rsmd.getColumnCount();
    	
    	for(int i = 1; i <= columnsNumber; i++){
    		System.out.print(rsmd.getColumnLabel(i) + " "); //Print one element of a row 		
    	}
    	System.out.println();
    	// Iterate through the data in the result set and display it. 
    	while (result.next()) {
    	//Print one row          
    	for(int i = 1; i <= columnsNumber; i++){
    		System.out.print(result.getString(i) + " "); //Print one element of a row
    		}
    	System.out.println();//Move to the next line to print the next row.           
    	}
    }
    
    /**
     * Saves Customer information to a text document.
     */
    public static void SQLsaveFile() throws FileNotFoundException, SQLException
    {
	    Connection conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();	
		ResultSet result = stat.executeQuery("SELECT * FROM Customers");
		ResultSetMetaData rsmd = result.getMetaData();

    	int columnsNumber = rsmd.getColumnCount();      
        PrintWriter out = new PrintWriter("Customers.txt"); // output file set up
    	
    	for(int i = 1; i <= columnsNumber; i++){
    		out.print(rsmd.getColumnLabel(i)+" ");
    		}  	
    	out.println();
    	
    	// Iterate through the data in the result set and display it. 
    	while (result.next()) {
    	//Print one row          
    	for(int i = 1; i <= columnsNumber; i++){
    		out.print(result.getString(i)+" ");
    		}
    	out.println();//Move to the next line to print the next row.           
    	}
		stat.close();
		out.close();
        
        //System.out.println("File has been saved.");
    }
}