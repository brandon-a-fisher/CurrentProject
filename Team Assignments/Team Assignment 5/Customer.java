/**
 * A class for maintiaing data pertaining to Customers. Including their name and
 * ID.
 */
public class Customer {
    private String name;
    private int customerID;

    /**
     * The following constructor acts as the default constructor that does not take
     * in any arguments.
     */
    public Customer() {
	setName("");
	setID(0);
    }

    /**
     * Here, the constructor takes in an initial name and an initial ID.
     *
     * @param initialName
     *            A String representing the customer name.
     * @param initialID
     *            A String representing the customer Id
     */
    public Customer(String initialName, int initialID) {
	setName(initialName);
	setID(initialID);
    }

    /**
     * Here, the constructor takes in an initial name.
     *
     * @param initialName
     *            A String representing the customer name.
     */
    public Customer(String initialName) {
	this(initialName, 0);
    }

    /**
     * Here, the constructor takes in an initial ID
     *
     * @param initialID
     *            A String representing the customer Id
     */
    public Customer(int initialID) {
	this("", initialID);
    }

    /**
     * This constructor will take in the information of a previous customer, acting
     * as a copy.
     *
     * @param oldCustomer
     *            A reference to a Customer object.
     */
    public Customer(Customer oldCustomer) {
	setName(oldCustomer.getName());
	setID(oldCustomer.getID());
    }

    /**
     * Getter method for Customer name.
     *
     * @return String representing Customer name.
     */
    public String getName() {
	return name;
    }

    /**
     * Getter method for Customer ID.
     *
     * @return Integer representing Customer ID.
     */
    public int getID() {
	return customerID;
    }

    /**
     * Setter method for Customer name.
     *
     * @param newName
     *            String representing new Customer name.
     */
    public void setName(String newName) {
	name = newName;
    }

    /**
     * Setter method for Customer ID.
     *
     * @param newCustomerID
     *            Integer representing new Customer ID.
     */
    public void setID(int newCustomerID) {
	customerID = newCustomerID;
    }

    /**
     *
     * Returns a string containing a formatted output of the Customer name and
     * Customer ID.
     *
     * @return A string displaying the customer name and ID.
     */
    public String toString() {
	String customerString = "CustomerName: " + name + " , CustomerID: " + customerID;
	return customerString;
    }
}
