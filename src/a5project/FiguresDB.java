package a5project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @authors Axell, Derek, Austin
 */
public class FiguresDB extends a5project {

    //private int safetyStock = 5; //For future implementation

    	//build in methods to call	
    	public static void figuresMenu() throws Exception
    	{
    		boolean loop = true;
    		while(loop)
    		{
                System.out.printf("\nSelect P)roducts  S)Suppliers  B)ack:  ");//Might just change this to one big menu.  Yes/no?
                String option = in.next();
                
                //Quit figures menu, return to employee menu
                if (option.equalsIgnoreCase("B")) {	loop = false; }
                
    	        else if (option.equalsIgnoreCase("Q")) {
    	        	Login.logOut(loop);
    	        }
                
                //Product Menu
                else if (option.equalsIgnoreCase("P"))
                {
                	boolean run = true;
                	while(run) {
                	System.out.print("\nSelect A)dd a figure  R)etrieve Web Price  G)et Product Info  P)rint product table  "
                			+ "U)pdate Figure  B)ack: ");
                    String select = in.next();
                    
                    //Return to next higher menu
                    if(select.equalsIgnoreCase("B")) {	run = false; }
                    
                    //Add a figure
                    else if(select.equalsIgnoreCase("A")) {
                    	System.out.println("Enter Product Name: ");
                    	String pname = in.next();
                    	System.out.println("Enter Quantity: ");
                    	int qty = in.nextInt();
                    	System.out.println("Enter price: ");
                    	double price = in.nextDouble();
                    	System.out.println("Enter Supplier ID: ");
                    	int sid = in.nextInt();
                    	addFigure(pname, qty, price, sid);
                    }
                    
                    //Retrieve price
                    else if(select.equalsIgnoreCase("R")) {
                    	System.out.println("Enter Product ID: ");
                    	int pid = in.nextInt();
                    	System.out.println("Work In Progress. The toy associated with "+pid+" will be compared soon.");
                    	//webSearch(pid);
                    }
                    
                    //Get product information
                    else if(select.equalsIgnoreCase("G")) {
                    	System.out.println("Enter Product ID: ");
                    	int pid = in.nextInt();
                    	getProduct(pid);
                    }
                    
                    //Print product table
                    else if(select.equalsIgnoreCase("P")) {
                    	printInventory();
                    }
                    
                    //Print product table
                    else if(select.equalsIgnoreCase("U")) {
                    	System.out.printf("\nEnter Product ID: ");
                    	int pid = in.nextInt();
                    	System.out.print("\nEnter new Quantity: ");
                    	int qty = in.nextInt();
                    	System.out.printf("\nEnter new Price: ");
                    	double price = in.nextDouble();
                    	modProduct(pid, qty, price);
                    }
                	}
                }
                
                else if(option.equalsIgnoreCase("S")){
                	boolean run = true;
                	while(run) {
                        System.out.println("Select A)dd supplier  L)ist Suppliers  G)et quantity  U)pdate supplier R)emove supplier  B)ack: ");
                        String select = in.next();
                        
                        //Back
                        if(select.equalsIgnoreCase("B")) { run = false; }
                        
                        //Add supplier
                        else if (select.equalsIgnoreCase("A")){
                        	System.out.println("Enter New Supplier Name: ");
                        	String sname = in.next();
                        	addSupplier(sname);                        	
                        }
                        
                        //Find Supplier
                        else if (select.equalsIgnoreCase("L")){
                        	printSupplier();                        	
                        }
                        
                        //Get stock
                        else if (select.equalsIgnoreCase("G")){
                        	System.out.println("Enter Product ID: ");
                        	int pid = in.nextInt();
                        	getQuantity(pid);                        	
                        }
                        
                        //Update supplier
                        else if (select.equalsIgnoreCase("U")){
                        	System.out.println("Enter Supplier ID to update: ");
                        	int sid = in.nextInt();
                        	System.out.println("Enter updated name of Supplier: ");
                        	String sname = in.next();
                        	modSupplier(sid, sname);                        	
                        }
                        
                        //Remove supplier
                        else if (select.equalsIgnoreCase("A")){
                        	System.out.println("Enter Supplier ID to remove: ");
                        	int sid = in.nextInt();
                        	delSupplier(sid);                        	
                        }
                    }
                }
          		}
    		//End of figuresMenu
    	}
        
    	public static void addFigure(String pname, int qty, double price, int sid) throws SQLException
    	{
    	    Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();	
    		stat.execute("INSERT INTO Inventory VALUES('"+pname+"',"+qty+","+price+","+sid+")");
    		stat.close();
        }
    	
    	public static void webSearch(int pid) throws SQLException {
    		
    		Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();
    		ResultSet result = stat.executeQuery("SELECT * FROM Inventory WHERE ProductID="+pid);
    		result.next();
    		String name = result.getString("ProductName");
    		double r = result.getInt("Price");
    		
    		
    		double wr = 1;
    		String name2 = "";
    		
    		if (r > wr) {
    			System.out.println("The store price of "+name+" is superior at $"+r+"!  What a deal!");

    		}
    		else if (r == wr) {
    			System.out.println("The price is the same in store and online.  Might as well buy from us since you're already here.");
    		}
    		else { 
    			System.out.println("The online price of "+name2+" is better at $"+r+".  It must be counterfeit..."); }
    		stat.close();
    	}
    	
    	public static void getProduct(int pid) throws SQLException {
    		Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();	
    		ResultSet result = stat.executeQuery("SELECT * FROM Inventory WHERE ProductID ="+pid+"");
    		ResultSetMetaData rsmd = result.getMetaData();

        	int columnsNumber = rsmd.getColumnCount();
        	// Iterate through the data in the result set and display it.
        	for(int i = 1; i <= columnsNumber; i++){
        		System.out.print(rsmd.getColumnLabel(i) + " "); //Print one element of a row
        	}
        	System.out.println();

        	while (result.next()) {
        	//Print one row          
        	for(int i = 1; i <= columnsNumber; i++){
        		System.out.print(result.getString(i) + " "); //Print one element of a row
        		}
        	System.out.println();//Move to the next line to print the next row.           
        	}
    		stat.close();
    	}
    	
    	public static void modProduct(int pid, int qty, double price) throws SQLException {
    	    Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();	
    		stat.execute("UPDATE Inventory SET Quantity="+qty+", Price="+price+" WHERE ProductID="+pid+"");
    		stat.close();
    	}
    	
    	public static void printInventory() throws SQLException, FileNotFoundException {
    	    Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();	
    		ResultSet result = stat.executeQuery("SELECT * FROM Inventory");
    		ResultSetMetaData rsmd = result.getMetaData();

        	int columnsNumber = rsmd.getColumnCount();
        	
        	// Iterate through the data in the result set and display it. 
        	PrintWriter out = new PrintWriter("Inventory.txt"); // output file set up
        	
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
        	out.println();
        	}
    		stat.close();
    		out.close();
    	}
    	    
    	public static void getSupplier(int sid) throws SQLException {
    	    Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();	
    		ResultSet result = stat.executeQuery("SELECT * FROM Supplier WHERE SupplierID ="+sid+"");
    		ResultSetMetaData rsmd = result.getMetaData();

        	int columnsNumber = rsmd.getColumnCount();
        	// Iterate through the data in the result set and display it.
        	for(int i = 1; i <= columnsNumber; i++){
        		System.out.print(rsmd.getColumnLabel(i) + " "); //Print one element of a row
        	}
        	System.out.println();

        	while (result.next()) {
        	//Print one row          
        	for(int i = 1; i <= columnsNumber; i++){
        		System.out.print(result.getString(i) + " "); //Print one element of a row
        		}
        	System.out.println();//Move to the next line to print the next row.           
        	}
    		stat.close();
     	}
     
    	public static void addSupplier(String sname) throws SQLException{
    	    Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();	
    		stat.execute("INSERT INTO Supplier VALUES ('"+sname+"')");
    		stat.close();
    	}
    	
    	/**
    	 * Print supplier list to file.
    	 * @throws SQLException
    	 * @throws FileNotFoundException
    	 */
    	public static void printSupplier() throws SQLException, FileNotFoundException {
    	    Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();	
    		ResultSet result = stat.executeQuery("SELECT * FROM Supplier");
    		ResultSetMetaData rsmd = result.getMetaData();

        	int columnsNumber = rsmd.getColumnCount();

        	PrintWriter out = new PrintWriter("Suppliers.txt"); // output file set up
        	
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
        	out.println();
        	}
    		stat.close();
    		out.close();
    	}
     
    	public static void getQuantity(int pid) throws SQLException {
    	    Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();	
    		ResultSet table = stat.executeQuery("SELECT ProductName, Quantity FROM Inventory WHERE ProductID ="+pid);
    		System.out.println(table);
    		stat.close();
    	}
    	
    	public static void modSupplier(int sid, String sname) throws SQLException {
    	    Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();	
    		stat.execute("UPDATE Supplier SET SupplierName='"+sname+"' WHERE SupplierID="+sid+"");
    		stat.close();
    	}
    	
    	public static void delSupplier(int sid) throws SQLException {
    	    Connection conn = SimpleDataSource.getConnection();
    		Statement stat = conn.createStatement();	
    		stat.execute("DELETE FROM Supplier WHERE SupplierID="+sid+"");
    		stat.close();
    	}   	
}