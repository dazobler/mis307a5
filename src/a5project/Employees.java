package a5project;

//import java.io.File;
//import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Employees extends a5project {
	
	//Verify Clerk log in
	public static void Clerk() throws Exception, SQLException {
		boolean run = true;
		while (run) {
	        System.out.printf("\nM)ake Sale  C)ustomer Menu  B)ack: ");
	        String cmd = in.nextLine();
	        
	        //Return to main menu
	        if (cmd.equalsIgnoreCase("B")) { run = false; }
	        
	        else if (cmd.equalsIgnoreCase("Q")) {
	        	Login.logOut(run);
	        }
	        
	        //Make a sale using sales methods
	        else if (cmd.equalsIgnoreCase("M"))
	        {
	        	Sales.salesMenu();	
	        }
	        
	        //Add, update, remove customers	        
	        else if (cmd.equalsIgnoreCase("C"))
	        {
	        	Customer.customerMenu();
	        }       
		}
	 }  //end of Clerk()
	
	//Verify Manager log in
	public static void Manager() throws Exception {
		boolean run = true;
		while (run) {
	        System.out.printf("\nR)eports Menu  C)ustomers Menu  F)igures Menu  B)ack: "); 
	        String cmd = in.nextLine();
	        
	        //Returns to Main Menu
	        if (cmd.equalsIgnoreCase("B")) { run = false; }
	        
	        //Generates reports
	        else if (cmd.equalsIgnoreCase("R"))
	        {
	        	Reports.reportsMenu();
	        }
	        
	        //Add, update, remove figures
	        else if (cmd.equalsIgnoreCase("F"))
	        {
	        	FiguresDB.figuresMenu();
	        }
	        else if (cmd.equalsIgnoreCase("C"))
	        {
	        	Customer.customerMenu();  	
	        }
		}
	 }//End of Manager()
}

