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

    // NOTE: The following  Timeline implementation is based on the one found on page 735 of
    // the course text: JAVA an Introduction to Problem Solving and Programming by Walter Savitch.
	
    // To create the gameloop we will create a Timeline object: timeline, consisting of only the
    // following KeyFrame: baseFrame, which is to have a duration of 0.016 seconds. We
    // will repeatedly play the Timeline of this single frame over and over again to simulate
    // consecutive frames; each "new frame" occuring  0.016 seconds after the last. This will
    // generate a gameLoop that can be updated 60 times a second by triggering an <ActionEvent>
    // every time a "newframe" begins.
	
    // The second argument of baseFrame is of type EventHandler<ActionEvent>.
    // This way, every time the timeline plays the KeyFrame baseFrame, the ActionEvent
    // specified by the argument will fire, causing the listener to update the game based
    // on whatever actions we've told it to take.

    // In this case we use the event handler GameLoopTest, which causes the famous pop culture phrase
    // "Hey Brandon" to be printed in the terminal once a frame.
    private KeyFrame baseFrame = new KeyFrame(Duration.seconds(0.016), new GameLoopTest());

    // Place single Keyframe baseFrame in timeline.
    private Timeline timeline = new Timeline(baseFrame); 

    public static void main(String[]args){
	launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
	
	Group root = new Group();
	Canvas canvas = new Canvas(300,300);

	root.getChildren().add(canvas);

	Scene scene = new Scene(root, 300, 300);

	// Makes sure the timeline runs repeatedly once it starts.
	timeline.setCycleCount(Timeline.INDEFINITE);
	
	// Start the timeline and therefore the gameloop.
	timeline.play();
	
	primaryStage.setTitle("HisRightfulPlace");
	primaryStage.setScene(scene);
	primaryStage.show();
    }
}
