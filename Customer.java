package mis307.a5.project;
import java.io.*;
import java.sql.ResultSet;
import java.util.*;
import java.util.Scanner;
/**
 * Customer class with methods that can look up Customer information and add/remove/update entries.
 * @authors ajensen, dtan, dazobler
 */
public class Customer extends Login {
    public static ArrayList<Integer> Customer_Account_Number = new ArrayList<Integer>();
    public static ArrayList<String> First_Name = new ArrayList<String>(); 
    public static ArrayList<String> Last_Name = new ArrayList<String>(); 
    public static ArrayList<String> Membership_Status = new ArrayList<String>();
        
    public static void readFile() throws FileNotFoundException
    {  
	// Construct the Scanner and PrintWriter objects for reading and writing
	String inputFileName = "Customers.txt"; 
	File inputFile = new File(inputFileName);
	Scanner in = new Scanner(inputFile);
            
	while (in.hasNextLine()){
            Customer_Account_Number.add(in.nextInt());
            in.nextLine();
            First_Name.add(in.nextLine());
            Last_Name.add(in.nextLine());
            Membership_Status.add(in.nextLine());
            
        }
	in.close();
    }     
             
    /**
     * Prints all customer info to a text file 
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
     * Updates a customer’s information in the Customer database eg. change membership status
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
    public static void saveFile() throws FileNotFoundException{
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
}