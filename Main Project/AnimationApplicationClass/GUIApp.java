import javafx.application.Application;

import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Scene;
import javafx.scene.Group;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;

public class GUIApp extends Application{

    public static void main(String[]args){
	launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
	
	Group root = new Group();
	Canvas canvas = new Canvas(300,300);

	root.getChildren().add(canvas);

	Scene scene = new Scene(root, 300, 300);

	// Initialize timer to generate events at
	// certain in game frames. Runs at 60fps.
	GameTimer mainTimer = new GameTimer();
	
	// Will print greeting to Brandon every 60 frames
	// (or one second),once game timer is started.
	mainTimer.addPersistentEvent(60,new GreetingBrandonEvent());

	// Will print a greeting to Ezra and Wild once every
	// 300 frames(or five seconds), once mainTimer is
	// started.
	mainTimer.addPersistentEvent(60*5,new GreetingEzraAndWildEvent());
	
	//Start the GameTimer so events can begin firing.
	mainTimer.startGameTimer();
	
	primaryStage.setTitle("HisRightfulPlace");
	primaryStage.setScene(scene);
	primaryStage.show();
    }
}
