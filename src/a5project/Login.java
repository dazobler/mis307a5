package a5project;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
	
	private static Scanner in3;
    /*
    //First 2 methods reads from employee text file.  Last 2 methods read from Apache Derby Database.
    */
	//Log-in using text files for verification
	public static boolean logInC(String n, boolean clearance) throws FileNotFoundException {
		clearance = false;	//change to local variable?
        File input = new File("Employees.txt");
        in3 = new Scanner(input);
        String k = "";
        
        while (in3.hasNextLine()) {
        	k = in3.nextLine();
        	if (k.contains(n) && k.contains("C")) {
        			clearance = true;
        		}
        }
        //End of read from text file.
        
        if (clearance) {
        		return true;//System.out.println("Success C3");
            }
        else if(n.equalsIgnoreCase("Q")) {
        	System.out.println("Quitting log-in.  Returning to Main Menu.");
        	return false;
        }
        else { System.out.println("That Clerk does not exist.  Exiting log-in."); } 
        return false;
	}
	
	//Log-in using text files for verification
	public static boolean logInM(String n, boolean clearance) throws FileNotFoundException {
		clearance = false;	
        File input = new File("Employees.txt");
        in3 = new Scanner(input);
        String k = "";
        
        while (in3.hasNextLine()) {
        	k = in3.nextLine();
        	if (k.contains(n) && k.contains("M")) {
        			clearance = true;
        		}
        }
        
        if (clearance) 
            {
        		return true;
            }
        else if(n.equalsIgnoreCase("Q"))
            {
        		System.out.println("Quitting log-in.  Returning to Main Menu.");
        		return false;
        	}
        else
        	{
        	System.out.println("That Manager does not exist.  Exiting log-in.");
        	return false;
        	} 
	}
	
	//Generic method to exit program
	public static void logOut(boolean run) throws FileNotFoundException, SQLException, InterruptedException {
		System.out.print("Exiting program.");
		Thread.sleep(200);
		Customer.SQLsaveFile();
		System.out.print(".");
		Thread.sleep(200);
		FiguresDB.printSupplier();
		System.out.print(".");
		Thread.sleep(200);
		FiguresDB.printInventory();
		System.out.print(".");
		Thread.sleep(200);
		Reports.printDailySales();
		System.out.println("\nProgram stopped.  Have a good day, my dude.");
		//run = false;
		System.exit(0);
	}
	
	//Log-in using Derby as verification
	public static boolean logIC(int n, boolean clearance) throws SQLException {
	    clearance = false;
		try(Connection conn = SimpleDataSource.getConnection()){
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery("SELECT EmployeeID FROM Employees WHERE EmployeeID="+n+" AND EmployeeType = 'C'");
		result.next();
		if (result.getInt("EmployeeID") == n) {
		clearance = true;
		stat.close();
		return clearance;
		}
		else {stat.close(); return clearance;}
	}		catch (SQLException notThere){
			System.out.println("That employee doesn't exist, you naughty biscuit.  This is getting logged.");
			return false;
			}
	}
	
	//Log-in using Derby as verification
	public static boolean logIM(int n, boolean clearance) throws SQLException {
	    clearance = false;
		try(Connection conn = SimpleDataSource.getConnection();){
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery("SELECT EmployeeID FROM Employees WHERE EmployeeID="+n+" AND EmployeeType = 'M'");
		result.next();
		
		if (result.getInt("EmployeeID") == n) {
			clearance = true;
			stat.close();
			return clearance;
		}
		else {stat.close(); return clearance;}
	}		catch (SQLException notThere){
			System.out.println("That employee doesn't exist, you naughty biscuit.  This is getting logged.");
			return false;
			}
	}
}