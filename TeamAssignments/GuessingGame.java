import java.util.Scanner;
import java.util.Random;

public class GuessingGame {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		Random rand = new Random();
		
		int aiGuess = rand.nextInt(20) +1;
		System.out.println("I'm thinking of a number between 1 and 20.");
		
		while (true) {
			System.out.print("What is your guess: ");
			int userGuess = keyboard.nextInt();

			if (userGuess < aiGuess) {
				System.out.println("Too low");
			} else if (userGuess > aiGuess) {
				System.out.println("Too high");
			} else {
				System.out.println("That's the number I was thinking of! Well done.");
				break;
			}
		}
	}
}

// Requirements:
// Create a Java program that runs a guessing game.  The computer chooses a random number between 1 and 20.  The user continues providing guesses until they have guessed the number.  After each guess, let the user know if their guess was correct, too low or too high. The execution of your program may look as follows:
// I'm thinking of a number between 1 and 20.
// What is your guess: 10
// Too high
// What is your guess: 5
// Too low
// What is your guess: 8
// Too high
// What is your guess: 7
// That's the number I was thinking of! Well done.
// Additional Information:
// Before the program starts, print the random number to aid testing and debugging.
// Try to figure out how to generate random numbers in Java by doing a Google search.  If you have trouble deciphering the information you find, use the course discussion board to get further clarification.
// Once you have your program running, do some error checking.  Check to make sure the user entered a number and that it is a number between 1 and 20.
// Submission:
// Submit your code to the dropbox for the first team assignments.  You can find this under Assessment -> Dropbox and look for the Team Assignment 1 folder.  Only one team member has to submit for the team.  You can submit multiple times.  The last submission is the submission that will be graded. 
// The dropbox for the assignment also contains a grading rubric.  Make sure to check you meet all the rubric criteria before submission.  There are additional criteria related to legibility and documentation that are not listed in the requirements.