package a5project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Employees extends a5project {
	
	public static void Clerk() throws FileNotFoundException {
		boolean run = true;
		while (run) {
	        System.out.printf("\nM)ake Sale  C)ustomer Menu  Q)uit): ");
	        String cmd = in.nextLine();
	        
	        if (cmd.equalsIgnoreCase("Q"))
	        {
	            run = false;
	        }
	        else if (cmd.equalsIgnoreCase("M"))
	        {
	        	System.out.println("Make Sale success");	
	        	//other methods start going here
	        	}
	        	        
	        else if (cmd.equalsIgnoreCase("C"))
	        {
	        	System.out.printf("Customer success");
	        	//other methods start going here
	        }
		}

	 }
	
	public static void Manager() throws FileNotFoundException {
		boolean run = true;
		while (run) {
	        System.out.printf("R)eports Menu\nA)dd Customer\nF)igures Menu\nQ)uit): ");
	        String cmd = in.nextLine();
	        
	        if (cmd.equalsIgnoreCase("Q"))
	        {
	            run = false;
	        }
	        else if (cmd.equalsIgnoreCase("R"))
	        {
	        	System.out.println("Reports success");	
	        	//other methods start going here
	        	}
	        	        
	        else if (cmd.equalsIgnoreCase("C"))
	        {
	        	System.out.printf("Customer success");
	        	//other methods start going here
	        }
	        else if (cmd.equalsIgnoreCase("F"))
	        {
	        	System.out.printf("Figures success");
	        	//other methods start going here
	        }
		}
	 }

	/*public static void logOut(boolean run) {
		System.out.println("Exiting program.");
		run = false;
	}*/
}

