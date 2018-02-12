/**
Assignment 4: Class SavingsAccount

This a subclass of BankAccount which receives an monthly interest rate.

Students: Ezra Guia, Wild Guevara, Brandon Fisher, Mark Tremblay
Professor: Leanne Wu
2018-02-14
*/

public class SavingsAccount {
	private double annualInterestRate;
	private BankAccount bankAccount;

	SavingsAccount(double balance, double rate) {
		bankAccount = new BankAccount (balance);
		annualInterestRate = rate;
	}

	public void depositMonthlyInterest() {
		double interest = (bankAccount.getBalance() *
							(annualInterestRate / 100) );
		bankAccount.deposit(interest);
	}
}