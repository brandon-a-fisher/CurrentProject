/**
Assignment 4: Class SavingsAccount

This a subclass of BankAccount which receives an monthly interest rate.

Students: Ezra Guia, Wild Guevara, Brandon Fisher, Mark Tremblay
Professor: Leanne Wu
2018-02-14
*/

public class SavingsAccount extends BankAccount{
	private double annualInterestRate; // Assumes a Positive %
	private BankAccount bankAccount;

	SavingsAccount () {
		super ();
	}
	
	SavingsAccount (double rate) {
		super ();
		annualInterestRate = rate;
	}

	SavingsAccount(double balance, double rate) {
		super (balance);
		annualInterestRate = rate;
	}

	public void depositMonthlyInterest() {
		double interest = (this.getBalance() * (annualInterestRate / 100) );
		this.deposit(interest);
	}
	
	public void setAnnualInterestRate (double newRate) {
		annualInterestRate = newRate;
	}
	
	public double getAnnualInterestRate () {
		return annualInterestRate;
	}
}
