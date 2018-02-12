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

/**
Constructor using only the base class
*/
	SavingsAccount () {
		super ();
	}

/**
Constructor using the base class with a given rate
*/
	SavingsAccount (double rate) {
		if (rate > 0) {
			super ();
			annualInterestRate = rate;
		}
		else
			System.println("Error");
		}
	}

/**
Constructor using only the base class with a given rate and balance
*/
	SavingsAccount(double balance, double rate) {
		super (balance);
		annualInterestRate = rate;
	}

/**
Calculates the interest and deposits that amount to the current balance
*/
	public void depositMonthlyInterest() {
		double interest = (this.getBalance() * (annualInterestRate / 100) );
		this.deposit(interest);
	}

/**
Sets an annual interest rate
*/
	public void setAnnualInterestRate (double newRate) {
		annualInterestRate = newRate;
	}

/**
Returns the annual interest rate
*/
	public double getAnnualInterestRate () {
		return annualInterestRate;
	}
}
