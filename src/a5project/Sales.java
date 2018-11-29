package a5project;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sales extends a5project {
	
	public static void salesMenu() throws Exception
	{
		boolean loop = true;
		while(loop)
		{
			System.out.printf("\nS)ell Figure  L)ookup Price  B)ack: ");
			String option = in.next();
			//Quit sales menu, return to employee menu
			if (option.equalsIgnoreCase("B")) {	loop = false; }
			
	        else if (option.equalsIgnoreCase("Q")) {
	        	Login.logOut(loop);
	        }
        
			//Product Menu
			else if (option.equalsIgnoreCase("S"))
			{       
				System.out.printf("\nEnter Product ID: ");
				int pid = in.nextInt();
				System.out.printf("\nEnter Quantity: ");
				int qty = in.nextInt();
				System.out.printf("\nEnter Customer ID: ");
				int cid = in.nextInt();
				sellFig(pid, qty, cid);
			}
            
			//Retrieve store price
			else if(option.equalsIgnoreCase("L")) {
				System.out.printf("\nEnter Product Name: ");
				String pname = in.next();
				priceLookup(pname);
			}
		}
	}//end of sales menu
	
	//Method to conduct sale and update transaction table, Orderline, for reports
	public static void sellFig(int pid, int qty, int cid) throws SQLException 
	{
	    Connection conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery("SELECT * FROM Inventory WHERE ProductID="+pid);
		result.next();
		int q = result.getInt("Quantity");
		int r = result.getInt("Price");
		double s = qty * r;
		if (q >= qty && q > 0) {
		stat.execute("UPDATE Inventory SET Quantity=Quantity-"+qty+" WHERE ProductID="+pid);
		
		stat.execute("INSERT INTO Orderline VALUES (DEFAULT, CURRENT_DATE, "+cid+", "+pid+", "+r+", "+qty+", "+s+")"); 
		
		stat.execute("UPDATE Customers SET Sales=Sales+1 WHERE CustomerID="+cid); 
		}
		else { System.out.println("Sale cannot be completed.  There is only "+q+" of that figure remaining."); }
		stat.close();
		System.out.printf("\nThe total amount due is %d.", s);
	}
	
	//Method to lookup price for a product
	public static void priceLookup(String pname) throws SQLException
	{
	    try(Connection conn = SimpleDataSource.getConnection();){
		Statement stat = conn.createStatement();	
		ResultSet query = stat.executeQuery("SELECT * FROM Inventory WHERE ProductName LIKE '%"+pname+"%'");
		query.next();
		System.out.println("The price of "+query.getString("ProductName")+" is $"+query.getDouble("Price")+" (PID: "+query.getInt("ProductID")+").  There are "+query.getInt("Quantity")+" in stock.");
		stat.close();}
	    catch(SQLException format) {
	    	System.out.println("No result.  Perhaps try capitalizing the first letter?");
	    }
	}
}