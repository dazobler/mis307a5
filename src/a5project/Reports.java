package a5project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



public class Reports extends a5project {
	
	public static void reportsMenu() throws Exception
	{
		boolean loop = true;
		while(loop)
		{
            System.out.printf("\nSelect D)aily Sales  Date-R)ange Report  T)otal Sales  B)ack:  ");
            String option = in.next();
            
            //Quit reports menu, return to employee menu
            if (option.equalsIgnoreCase("B")) {	loop = false; }
            
	        else if (option.equalsIgnoreCase("Q")) {
	        	Login.logOut(loop);
	        }
                        
            //Generate Daily Sales Report
            else if (option.equalsIgnoreCase("D"))
            {
            	dailySales();
            }
            
            else if (option.equalsIgnoreCase("R")) {
            	System.out.printf("\nEnter start date (YYYY-MM-DD): ");
            	String start = in.next();
            	System.out.printf("\nEnter end date (YYYY-MM-DD): ");
            	String end = in.next();
            	rangeSales(start,end);
            }
            //Return to next higher menu
            else if(option.equalsIgnoreCase("T")) {
                totalSales();
      		}
		
		}
	}//End of reportsMenu
	
	//Method to get daily sales from DB
	public static void dailySales() throws SQLException, FileNotFoundException {
	    Connection conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();	
		ResultSet result = stat.executeQuery("SELECT * FROM Orderline WHERE TransactionDate=CURRENT_DATE"); //how to lookup today's date for conditional statement?
		ResultSetMetaData rsmd = result.getMetaData();

    	int columnsNumber = rsmd.getColumnCount();

    	System.out.println("");
  
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
		stat.close();
	}
	
	public static void printDailySales() throws SQLException, FileNotFoundException {
	    Connection conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();	
		ResultSet result = stat.executeQuery("SELECT * FROM Orderline WHERE TransactionDate=CURRENT_DATE"); //how to lookup today's date for conditional statement?
		ResultSetMetaData rsmd = result.getMetaData();

    	int columnsNumber = rsmd.getColumnCount();
    	
    	PrintWriter out = new PrintWriter("Daily Sales Report "+java.time.LocalDate.now()); // output file set up
    	System.out.println("");
  
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
    	System.out.println();//Move to the next line to print the next row.           
    	}
		stat.close();
		out.close();
	}
	
	//Get all sales that cover a period of time
	public static void rangeSales(String start, String end) throws SQLException, FileNotFoundException {
	    Connection conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();	
		ResultSet result = stat.executeQuery("SELECT * FROM Orderline WHERE TransactionDate BETWEEN '"+start+"' AND '"+end+"'");
		ResultSetMetaData rsmd = result.getMetaData();

    	int columnsNumber = rsmd.getColumnCount();
       	
    	PrintWriter out = new PrintWriter("Custom Range Sales Report ("+start+" to "+end+")"); // output file set up
    	
    	for(int i = 1; i <= columnsNumber; i++){
    		//System.out.print(rsmd.getColumnLabel(i) + " "); //Print one element of a row
    		out.print(rsmd.getColumnLabel(i)+" ");
    	}
    	//System.out.println();
    	out.println();
    	
    	// Iterate through the data in the result set and display it. 
    	while (result.next()) {
    	//Print one row          
    	for(int i = 1; i <= columnsNumber; i++){
    		//System.out.print(result.getString(i) + " "); //Print one element of a row
    		out.print(result.getString(i)+" ");
    		}
    	System.out.println();//Move to the next line to print the next row.    
    	out.println();
    	}
		stat.close();
		out.close();
		System.out.println("Custom Range Sales Report file has been generated.  Check your main folder.");
	}
	
	//Get all sales ever
	public static void totalSales() throws SQLException, FileNotFoundException {
	    Connection conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();	
		ResultSet result = stat.executeQuery("SELECT Orderline.TransactionDate, Orderline.ProductID, Inventory.ProductName, Orderline.Price, Orderline.Quantity, "
				+ "Orderline.TotalPrice FROM Orderline INNER JOIN Inventory ON Orderline.ProductID=Inventory.ProductID");
		ResultSetMetaData rsmd = result.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
    	
		PrintWriter out = new PrintWriter("Total Sales Report "+java.time.LocalDate.now()); // output file set up

    	for(int i = 1; i <= columnsNumber; i++){
    		//System.out.print(rsmd.getColumnLabel(i) + " "); //Print one element of a row
    		out.print(rsmd.getColumnLabel(i)+" ");
    		}
    	//System.out.println();
    	out.println();
		
    	// Iterate through the data in the result set and display it. 
    	while (result.next()) {
    	//Print one row          
    	for(int i = 1; i <= columnsNumber; i++){
    		//System.out.print(result.getString(i) + " "); //Print one element of a row
    		out.print(result.getString(i)+" ");
    		}
    	System.out.println();//Move to the next line to print the next row.     
    	out.println();
    	}
       	out.close();
		stat.close();
		System.out.println("Total Sales Report file has been generated.  Check your main folder.");
	}
	
	//Find out what the most popular product being sold is
	public static void getPopular() throws SQLException {
		Connection conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();
		//start from here.
	}
}