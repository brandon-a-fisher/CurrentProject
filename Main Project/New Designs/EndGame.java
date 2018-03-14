package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class EndGame extends Application {
	static Stage endStage = new Stage();
	
	final int canvasWidth = 400;
	final int canvasHeight = 200;
	
	
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
	
    public void start(Stage primaryStage) throws Exception
	{
    		endStage = primaryStage;
    		
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.setFill( Color.PURPLE );
		gc.fillRect(0, 0, canvasWidth, canvasHeight );
		
		Stop[] stops = new Stop[] { new Stop(0, Color.RED), new Stop(1, Color.YELLOW) };
		LinearGradient myGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
		LinearGradient myGradient2 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
		gc.setFill( myGradient );
		gc.setStroke( myGradient2 );
		gc.setLineWidth(1.5);
		Font theFont = Font.font( "Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 60 );
		gc.setFont( theFont );
		gc.fillText( "GAME OVER", 20, 60 );
		gc.strokeText( "GAME OVER", 20, 60 );
		
		/*
		 * newGame button allows the user to restart the game.
		 */
		final Button newGame = new Button("PLAY AGAIN");
		newGame.setLayoutX(150);
		newGame.setLayoutY(100);
		newGame.setPrefSize(100, 20);
		
		/*
		 * If the newGame button is clicked, a brand new instance of MainGUIApplication is created
		 * and run.
		 */
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Event)
			{
				MainGUIApplication GUI = new MainGUIApplication();
				try {
					GUI.start( MainGUIApplication.mainGUIStage );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				primaryStage.close();
			}
		});
		
		/*
		 * quitGame button will allow the user to completely quit out of the game if wanted.
		 */
		final Button quitGame = new Button("QUIT");
		quitGame.setLayoutX(150);
		quitGame.setLayoutY(150);
		quitGame.setPrefSize(100, 20);
		
		quitGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Event)
			{
				System.exit(0);
			}
		});
			
		root.getChildren().add(canvas);
		root.getChildren().add(newGame);
		root.getChildren().add(quitGame);
		primaryStage.setTitle("HIS RIGHTFUL PLACE");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
