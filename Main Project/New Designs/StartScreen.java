package game;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
 
public class StartScreen extends Application {
	final int canvasWidth = 700;
	final int canvasHeight = 300;
	
	/*
	 * Initializing jumpCommand to being the up arrow key.
	 */
	static String jumpCommand = "UP";
	
    /*
     * getJumpCommand() will be useful for JumpEvent to determine if the input given is the one the user
     * initially said they would use.
     */
    public static String getJumpCommand()
    {
    		return jumpCommand;
    }
	
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
	
    public void start(Stage primaryStage) throws Exception
    {
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//This will make the background color of the window purple. 
		gc.setFill( Color.PURPLE );
		gc.fillRect(0, 0, canvasWidth, canvasHeight );
		
		//Here, the title is made with a gradient scale.
		Stop[] stops = new Stop[] { new Stop(0, Color.RED), new Stop(1, Color.YELLOW) };
		LinearGradient myGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
		LinearGradient myGradient2 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
		gc.setFill( myGradient );
		gc.setStroke( myGradient2 );
		gc.setLineWidth(1.5);
		Font theFont = Font.font( "Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 60 );
		gc.setFont( theFont );
		gc.fillText( "HIS RIGHTFUL PLACE", 30, 60 );
		gc.strokeText( "HIS RIGHTFUL PLACE", 30, 60 );
		
		/*
		 * newJumpCommand takes in the key the user wants to use to jump.
		 */
		TextField newJumpCommand = new TextField("SET JUMP BUTTON");
		newJumpCommand.setLayoutX(290);
		newJumpCommand.setLayoutY(120);
		newJumpCommand.setPrefSize(140, 20);
		
		/*
		 * startButton will be used for the user to initialize the game.
		 */
		final Button startButton = new Button( "START GAME" );
		startButton.setLayoutX(300);
		startButton.setLayoutY(170);
		startButton.setPrefSize(120, 50);
		
		/*
		 * The following is the event that happens when the startButton is clicked.
		 */
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Event)
			{
				jumpCommand = newJumpCommand.getText();
				jumpCommand = jumpCommand.toUpperCase();
				
				/*
				 * To ensure only one character is given for jumpCommand we check if the letter is in the
				 * alphabet, and if it is only one character long. Code from the following stackoverflow
				 * post was used to help check these conditions:
				 * 
				 * https://stackoverflow.com/questions/5238491/check-if-string-contains-only-letters
				 * 
				 * "UP" and "SPACE" are also allowed for the jump button.
				 */
				if( (( jumpCommand.matches("[A-Z]+") ) && ( jumpCommand.length() == 1 ))
					|| ( jumpCommand.equals("UP"))
					|| ( jumpCommand.equals("SPACE")) )
				{
					/*
					 * Here, the program starts up MainGUIApplication and closes the primary stage of
					 * StartScreen, ensuring there's only one screen open at a time. Code from the following
					 * stackoverflow post was used to help ensure movements from GUI to GUI:
					 * 
					 * https://stackoverflow.com/questions/25873769/launch-javafx-application-from-another-class
					 */
					MainGUIApplication newMain = new MainGUIApplication();
					try {
						newMain.start( MainGUIApplication.mainGUIStage );
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					primaryStage.close();
				}
				
				/*
				 * If the user inputs a command that does not work for jumping the screen prompts the user, telling
				 * them the input is incorrect.
				 */
				else
				{
					newJumpCommand.setText("WRONG INPUT");
				}
				
			}
		});
		
		root.getChildren().add(canvas);
		root.getChildren().add(startButton);
		root.getChildren().add(newJumpCommand);
		primaryStage.setTitle("HIS RIGHTFUL PLACE");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
  } 