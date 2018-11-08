package a5project;

import java.io.*;
//import java.sql.*;
import java.util.*;

/**
 * MIS-307-A5 project for building a small Inventory Management Program to handle retail tasks at a Hobby Shop.
 * @authors ajensen, dtan, dazobler
 */

public class a5project {
	
	public static void main (String[] args) throws IOException
	{
	Scanner in = new Scanner(System.in);
	boolean run = true;
	boolean clearance = false;
    while (run) 
    {
        System.out.print("Please log-in as either an C)lerk or a M)anager (Enter 'Q' at any time to quit): ");
        String cmd = in.nextLine();
        
        if (cmd.equalsIgnoreCase("Q"))
        {
            run = false;
            Login.logOut(run);
        }
        else if (cmd.equalsIgnoreCase("C"))
        {
        	System.out.printf("\nEnter Clerk ID: ");
            String n = in.nextLine();
        	if (Login.logInC(n, clearance)) {
        		//other methods start going here
        	}
        }
        
        else if (cmd.equalsIgnoreCase("M"))
        {
        	System.out.printf("\nEnter Manager ID: ");
            String n = in.nextLine();
            if (Login.logInM(n, clearance)) {
        		//manager methods start here
        	}
        }
    }
    in.close();
}
}
