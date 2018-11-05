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
	
    while (run) 
    {
        System.out.print("Please log-in as either an C)lerk or a M)anager (Enter 'Q' at any time to quit): ");
        String cmd = in.nextLine();
        
        if (cmd.equalsIgnoreCase("Q"))
        {
            run = false;
        }
        else if (cmd.equalsIgnoreCase("C"))
        {
        	Scanner in2 = new Scanner (System.in); 
        	System.out.printf("\nEnter Clerk ID: ");
            String n = in2.nextLine();  //or integer n, I think either way would work - @dazobler
            File input = new File("EmployeeDB(Text).txt");
                if (n.matches(".*[0-9].*")) 
                {
                	boolean run2 = true;
                	System.out.println("Clerk success");
                	//int index = Collections.binarySearch(byClerk, new Item(n, null));
                	//System.out.printf("Hello %s.  ", byClerk.get(index).getValue());
                    //while (run2) {
                    //    Class2.Method() returns that allows clerk to conduct sales, update info, etc.;
                    //}
                }
                else if(n.equalsIgnoreCase("Q"))
                {
                	in2.close();
                    System.out.println("");
                }
                else { in2.close(); System.out.println("That Clerk does not exist.  Please try again.");}
                
        }
        
        else if (cmd.equalsIgnoreCase("M"))
        {
        	Scanner in2 = new Scanner(System.in);
            System.out.println("Enter Manager ID: ");
            
            String n = in2.nextLine(); //or integer n, I think either way would work - @dazobler  
            
            if (n.matches(".*[0-9].*")) 
            {
            boolean run2 = true;
        	System.out.println("Manager success");
            
            //if (Class.Method(n) for checking if manager ID exists returns true) 
            //    {
                //int index = Collections.binarySearch(byManager, new Item(n, null));
                //System.out.printf("Hello %s.  ", byManager.get(index).getValue());
                //    while (run2) {
                //        Class2.Method() returns that allows Manager to conduct sales, update info, etc.;
                //    }
                }
                else if(n.equalsIgnoreCase("Q"))
                {
                    in2.close();
                	run = false;
                }
                else { in2.close(); System.out.println("That Manager does not exist.");}
        
        }
    }
    in.close();
}
}
