import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * MIS-307-A5 project for building a small Inventory Management Program to handle retail tasks at a Hobby Shop.
 * @authors ajensen, dtan, dazobler
 */

public class a5project {
    boolean run = true;
    
    while (run) 
    {
        System.out.println("Please log-in as either an C)lerk or a M)anager.  Enter 'Q' at any time to quit.");
        String cmd = in.nextLine();
        if (cmd.equalsIgnoreCase("Q"))
        {
            run = false;
        }
        else if (cmd.equalsIgnoreCase("C"))
        {
            System.out.println("Enter Clerk ID: ");
            String n = in.nextLine();  //or integer n, I think either way would work - @dazobler
                /* if (Class.Method(n) for checking if clerk ID exists returns true) 
                {
                int index = Collections.binarySearch(byClerk, new Item(n, null));
                System.out.printf("Hello %s.  ", byClerk.get(index).getValue());
                    while (run2) {
                        Class2.Method() returns that allows clerk to conduct sales, update info, etc.;
                    }
                }
                else if(n.equalsIgnoreCase("Q"))
                {
                    run = false;
                }
                else { System.out.println("That Clerk does not exist.  Please try again.");}
                */
        }
        
        else if (cmd.equalsIgnoreCase("M"))
        {
            System.out.println("Enter Manager ID: ");
            String n = in.nextLine();  //or integer n, I think either way would work - @dazobler
                /* if (Class.Method(n) for checking if manager ID exists returns true) 
                {
                int index = Collections.binarySearch(byManager, new Item(n, null));
                System.out.printf("Hello %s.  ", byManager.get(index).getValue());
                    while (run2) {
                        Class2.Method() returns that allows Manager to conduct sales, update info, etc.;
                    }
                }
                else if(n.equalsIgnoreCase("Q"))
                {
                    run = false;
                }
                else { System.out.println("That Manager does not exist.");}
        */
        }
}
