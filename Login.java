package a5project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login extends a5project {
	
	private static Scanner in3;

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
	
	public static void logOut(boolean run) {
		System.out.println("Exiting program.");
		run = false;
	}
}
