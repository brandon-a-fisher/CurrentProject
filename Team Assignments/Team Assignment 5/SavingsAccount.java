
/**
 * A class maintaing data for a Savings Account. Is a subclass of BankAccount.
 * Can calculate monthly interest based on an annual interest rate and add this
 * interest to the account balance.
 **/
public class SavingsAccount extends BankAccount {
    private double annualInterestRate; // a percentage

    /**
     * Default constructor. Sets initial balance and initial interest rate to zero.
     */
    public SavingsAccount() {
	super();
	setAnnualInterestRate(0);
    }

    /**
     * Constructor takes arguments for customer and initial balance. Default
     * constructor is called for interest value.
     *
     * @param newCustomer
     *            Customer who will own this account.
     * @param initialBalance
     *            Starting Balance of account.
     */
    public SavingsAccount(Customer newCustomer, double initialBalance) {
	super(newCustomer, initialBalance);
	setAnnualInterestRate(0);
    }

    /**
     * Constructor takes arguments for initial balance and initial annual interest.
     *
     * @param initialBalance
     *            Starting balance of account.
     * @param initialAnnualInterestRate
     *            Starting annual interest rate of account.
     */
    public SavingsAccount(double initialBalance, double initialAnnualInterestRate) {
	super(initialBalance);
	setAnnualInterestRate(initialAnnualInterestRate);
    }

    /**
     * Calculates monthly interest earned on the account balance and adds it to it.
     */
    public void depositMonthlyInterest() {

	if (getBalance() > 0) {
	    double currentBalance = getBalance();
	    double monthlyInterestRate = (getAnnualInterestRate() / 100) / 12;
	    double monthlyInterest = currentBalance * monthlyInterestRate;
	    double newBalance = currentBalance + monthlyInterest;
	    setBalance(newBalance);
	}
    }

    /**
     * Returns a value for the annual interest rate of the savings account
     *
     * @return The annual interest rate of this account.
     */
    public double getAnnualInterestRate() {
	return annualInterestRate;
    }

    /**
     * Sets the annual interest rate.
     *
     * @param newRate
     *            Sets a new annual interest rate for the account.
     */
    public void setAnnualInterestRate(double newRate) {
	if (newRate >= 0 && !(Double.isNaN(newRate)) && newRate != Double.POSITIVE_INFINITY)
	    annualInterestRate = newRate;
	else
	    System.out.println("Invalid input or tried to set interest rate" + '\n' + "to a negative value.");
    }

    /**
     * Main method
     *
     * @param args
     *            ...
     */
    public static void main(String[] args) {
    }
}
