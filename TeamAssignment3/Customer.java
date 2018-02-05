public class Customer
{
	private String name;
	private int customerID ;
	
  /**
	* The following are the constructors for the Customer
	*/
	public Customer()
	{
		name = "No Name";
		customerID  = 0;
	}
	
	public Customer(String initialName, int initialID)
	{
		name = initialName;
		customerID = initialID;
	}
	
	public Customer(Customer oldCustomer)
	{
		name = oldCustomer.getName();
		customerID = oldCustomer.getID();
	}
	/** 
 	  * The following four methods are the getter and setter methods for
	  * the instance variables name and customerID.
	  */
	public String getName()
	{
		return name;
	}
	
	public int getID()
	{
		return customerID;
	}
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public void setCustomerID(int newCustomerID)
	{
		customerID = newCustomerID;
	}
	
	/**
	  *Returns a string containing a formatted output of the Customer
	  *name and Customer ID.
	  */
	public String toString()
	{
		String customerString = "CustomerName: " + name + " , CustomerID: " + customerID;
		return customerString;
	}
	
	public static void main(String[]args)
	{

	}
}
