public class BankAccount
{
	
	private double balance; 
	private double overdraftAmount; 
	private Customer currentCustomer;
	
	/**
	  * The following are the constructors for BankAccont.
	  */
	public BankAccount()
	{
		balance = 0; 
		overdraftAmount = 100; 
		currentCustomer = new Customer();
	}
	
	public BankAccount(Customer initialCustomer, double initialBalance)
	{
		currentCustomer = new Customer(initialCustomer);
		balance = initialBalance;
		overdraftAmount = 100;
	}
	
	public BankAccount(Customer initialCustomer)
	{
		currentCustomer = new Customer(initialCustomer);
		balance = 0;
		overdraftAmount = 100;
	}
	
	public BankAccount(double initialBalance)
	{
		currentCustomer = new Customer();
		balance = initialBalance;
		overdraftAmount = 100;
	}
	
	
	/**
	  *depositAmount is added to your balance if it is a value greater than zero.
	  */
	public void deposit(double depositAmount) 
	{
		if(depositAmount > 0)
		{
			balance = balance+depositAmount;
		}
		else
		{
			System.out.println("Tried to deposit a negative or zero amount. Please enter a positive value.");
		}
	}
	
	/**
	  * Allows you to withdraw as long as you do not exceed (balance + overdraftAmount)
	  */
	public void withdraw(double withdrawAmount) 
	{
		if(withdrawAmount<=(balance+overdraftAmount)&&(withdrawAmount>0))
		{
			balance = balance-withdrawAmount;
		}
		else
		{
			System.out.println("I'm sorry you have exceeded your overdraft amount or you've tried to");
			System.out.println("withdraw a negative or zero amount.");
		}
		
	}
	
	/**
	  * Returns the balance of a BankAccount object.
	  */
	public double getBalance() 
	{
		return balance;
	}
	
	/**
	  * Returns the Current Customer.
	  */
	public Customer getCustomer()
	{
		return currentCustomer;
	}
	
	/**
	  * Changes the overdraftAmount of a BankAccount object.
	  */
	
	public void setOverdraftAmount(double newOverdraftAmount) 
	{
		overdraftAmount = newOverdraftAmount;
	}
	
	/**
	  * Changes the balance of a BankAccount object.
	  */
	public void setBalance(double newBalance)
	{
		balance = newBalance;
	}
	
	public static void main(String[]args)
	{	
	
	}
}
