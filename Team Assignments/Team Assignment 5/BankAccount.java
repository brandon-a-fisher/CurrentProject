 
/**
 * A class maintaining the data for a Bank Account, including Customer, balance,
 * and overdraftAmount.
 */
public class BankAccount {

    private double balance;
    private double overdraftAmount;
    private Customer currentCustomer;

    /**
     * The following constructor acts as the default constructor that takes in no
     * arguments.
     */
    public BankAccount() {
	currentCustomer = new Customer();
	setBalance(0);
	setOverdraftAmount(100);
    }

    /**
     * Here, the bank account constructor takes in both a customer and an initial
     * balance.
     *
     * @param newCustomer
     *            Customer to whom the BankAccount belongs.
     * @param initialBalance
     *            Starting balance of the BankAccount.
     */
    public BankAccount(Customer newCustomer, double initialBalance) {
	this();
	setCustomer(newCustomer);
	setBalance(initialBalance);
    }

    /**
     * This constructor only takes in a customer.
     *
     * @param newCustomer
     *            Customer to whom the BankAccount belongs
     */
    public BankAccount(Customer newCustomer) {
	this(newCustomer, 0);
    }

    /**
     * This constructor only takes in an initial balance.
     *
     * @param initialBalance
     *            Starting balance of the BankAccount.
     */
    public BankAccount(double initialBalance) {
	this();
	setBalance(initialBalance);
    }

    /**
     * depositAmount is added to your balance if it is a value greater than zero.
     *
     * @param depositAmount
     *            The amount to deposit in the account.
     */
    public void deposit(double depositAmount) {
	if (depositAmount > 0 && depositAmount != Double.POSITIVE_INFINITY && !(Double.isNaN(depositAmount))) {
	    setBalance(getBalance() + depositAmount);
	} else {
	    System.out.println("Tried to deposit a negative or zero amount." + '\n' + "Please enter a positive value.");
	}
    }

    /**
     * Allows you to withdraw as long as you do not exceed (balance +
     * overdraftAmount)
     *
     * @param withdrawAmount
     *            The amount to withdraw from the account.
     */
    public void withdraw(double withdrawAmount) {
	if (withdrawAmount <= (balance + overdraftAmount) && withdrawAmount > 0
	    && withdrawAmount != Double.POSITIVE_INFINITY && !(Double.isNaN(withdrawAmount))) {
	    setBalance(getBalance() - withdrawAmount);
	} else {
	    System.out.println("I'm sorry you have exceeded your overdraft" + '\n'
			       + "amount or you've tried to withdraw a negative" + '\n' + "or zero amount.");
	}
    }

    /**
     * Returns the balance of a BankAccount object.
     *
     * @return Current balance of the account
     */
    public double getBalance() {
	return balance;
    }

    /**
     * Returns the overdraftAmount of a BankAccount object.
     *
     *@return An integer representing the current overdraftAmount of the BankAccount.
     */
    public double getOverdraftAmount() {
	return overdraftAmount;
    }

    /**
     * Returns a copy of the Current Customer.
     *
     * @return Current customer that owns the account.
     */
    public Customer getCustomer() {
	Customer currentCustomerCopy = new Customer(currentCustomer);
	return currentCustomerCopy;
    }

    /**
     * Sets the Current Customer.
     *
     * @param newCustomer
     *            New customer who owns this account.
     *
     */
    public void setCustomer(Customer newCustomer) {
	currentCustomer.setName(newCustomer.getName());
	currentCustomer.setID(newCustomer.getID());
    }

    /**
     * Changes the overdraftAmount of a BankAccount object.
     *
     * @param newOverdraftAmount
     *            New maximum overdraft amount of account.
     */
    public void setOverdraftAmount(double newOverdraftAmount) {
	if (!(Double.isNaN(newOverdraftAmount)) && newOverdraftAmount != Double.NEGATIVE_INFINITY
	    && newOverdraftAmount != Double.POSITIVE_INFINITY) {
	    overdraftAmount = newOverdraftAmount;
	}
    }

    /**
     * Changes the balance of a BankAccount object.
     *
     * @param newBalance
     *            New balance of account.
     */
    public void setBalance(double newBalance) {
	if (!(Double.isNaN(newBalance)) && newBalance != Double.POSITIVE_INFINITY
	    && newBalance != Double.NEGATIVE_INFINITY) {
	    balance = newBalance;
	}
    }
}
