import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * An instance of this class creates a GUI to manipulate and display data in a
 * Bank Account or Savings Account. A user can deposit or withdraw money, and
 * review their balance afterwards.
 */
public class BankApplication extends Application {

    private Customer currentCustomer = new Customer("Johnny Cash", 4782);
    private SavingsAccount currentAccount = new SavingsAccount(currentCustomer, 150.0);

    /**
     * Main method.
     *
     * @param args
     *            ...
     */
    public static void main(String[] args) {
	launch(args);
    }

    /**
     * Start method, Creates GUI for BankApplication.
     * 
     * @param primaryStage
     *            ...
     */
    public void start(Stage primaryStage) throws Exception {

	// Prepare objects for GUI. Will display Buttons, Labels, and TextFields
	// on GridPane.
	GridPane root = new GridPane();
	Scene scene = new Scene(root, 400, 400);

	Label customerNameLabel = new Label("Customer Name: " + currentCustomer.getName());
	Label customerIDLabel = new Label("Account ID: " + currentCustomer.getID());
	Label balanceLabel = new Label("Current Balance: $" + currentAccount.getBalance());

	TextField depositTextField = new TextField("Amt to deposit");
	TextField withdrawTextField = new TextField("Amt to withdraw");

	Button executeButton = new Button("Execute");

	// Inner class to handle executeButton click event. Should get Strings
	// from text fields, determine their validity, and reset them if they
	// aren't valid, or convert them to doubles and deposit or
	// withdraw money from the account--in that order--if they are valid.
	// Then reset fields and update balanceLabel to reflect new balance.
	executeButton.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
		    // Get user input from text fields.
		    String depositInput = depositTextField.getText();
		    String withdrawInput = withdrawTextField.getText();

		    // Will use these Scanner objects to make sure user input
		    // text can be cast to a Double type before attempting to
		    // do so.
		    Scanner checkDeposit = new Scanner(depositInput);
		    Scanner checkWithdraw = new Scanner(withdrawInput);

		    // Deposit value in account if value is valid.
		    if (checkDeposit.hasNextDouble()) {
			Double depositValue = Double.parseDouble(depositInput);
			currentAccount.deposit(depositValue);
			depositTextField.setText("Amt to deposit");
		    } else {
			depositTextField.setText("Amt to deposit");
		    }

		    // Withdraw value from account if value is valid.
		    if (checkWithdraw.hasNextDouble()) {
			Double withdrawValue = Double.parseDouble(withdrawInput);
			currentAccount.withdraw(withdrawValue);
			withdrawTextField.setText("Amt to withdraw");
		    } else {
			withdrawTextField.setText("Amt to withdraw");
		    }
		    // Update balance label to reflect account changes.
		    balanceLabel.setText("Current Balance: $" + currentAccount.getBalance());
		}
	    });

	// Place Labels, Buttons and TextFields on GridPane.
	root.add(customerNameLabel, 1, 1, 1, 1);
	root.add(customerIDLabel, 1, 2, 1, 1);
	root.add(depositTextField, 1, 3, 1, 1);
	root.add(withdrawTextField, 2, 3, 1, 1);
	root.add(executeButton, 1, 4, 1, 1);
	root.add(balanceLabel, 1, 5, 1, 1);

	// Set Scene and display.
	primaryStage.setTitle("Bank Application");
	primaryStage.setScene(scene);
	primaryStage.show();
    }
}
