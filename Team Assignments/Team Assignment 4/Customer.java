/**
Assignment 4: Class Customer

This program creates a customer ID attached to a name

Students: Ezra Guia, Wild Guevara, Brandon Fisher, Mark Tremblay
Professor: Leanne Wu
2018-02-14
*/

public class Customer {
	private String name;
	private int customerID;
	
/**
The following constructor acts as the default constructor that does not take in any arguments.
*/
	public Customer() {
		name = "No Name";
		customerID  = 0;
	}
	
/**
Here, the constructor takes in an initial name and an initial ID.
*/
	public Customer(String initialName, int initialID) {
		name = initialName;
		customerID = initialID;
	}
	
/**
This constructor will take in the information of a previous customer, acting as a copy.
*/
	public Customer(Customer oldCustomer) {
		name = oldCustomer.getName();
		customerID = oldCustomer.getID();
	}

/** 
Returns the name
*/
	public String getName() {
		return name;
	}

/**
Returns the ID
*/
	public int getID() {
		return customerID;
	}
	
/**
Sets the name
*/
	public void setName(String newName) {
		name = newName;
	}
	
/**
Sets the ID
*/
	public void setCustomerID(int newCustomerID) {
		customerID = newCustomerID;
	}
	
/**
Returns a string containing a formatted output of the Customer name and Customer ID.
*/
	public String toString() {
		String customerString = "CustomerName: " + name + " , CustomerID: " + customerID;
		return customerString;
	}
}
