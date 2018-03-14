package game;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;

// Version 8
public class JumpEvent implements EventHandler<KeyEvent> {
    private final double frameDuration = GameTimer.getFrameDuration(); // In millisecconds

    // Reference for player, and yCoordinate value
    // from player. Reference taken in by constructor
    // as argument.
    private Player player1;
    private double yCoordinate;

    // Controls if keypress activates jump animation. If true
    // we cannot activate another jump until the last is done.
    private Boolean isJump = false;
    
    private String jumpButton = StartScreen.getJumpCommand();
    
    // Max height player can jump, and positon of ground is Min height.
    private final int maxHeight = 100; 
    private final int minHeight = 290; 

    // Speed of jump and fall. Length of hover between jump and fall.
    private final double jumpSpeed = 30; 
    private final double fallSpeed = 12;
    private final double hoverLength = 5; // In frames

    // To set cycle durations of jump and fall timelines.
    private final Duration jumpCycleDuration = Duration.millis(frameDuration/jumpSpeed);
    private final Duration fallCycleDuration = Duration.millis(frameDuration/fallSpeed);
    private final Duration hoverCycleDuration = Duration.millis(frameDuration*hoverLength);

    // Timelines that play jump and fall animation.
    private Timeline jumpTimeline = new Timeline();
    private Timeline fallTimeline = new Timeline();

    // Keyframes to set cycle duration of timelines, and
    // action on end of each cycle.
    private KeyFrame up;
    private KeyFrame down;

    public JumpEvent(Player newPlayer){
	player1 = newPlayer;
	yCoordinate = player1.getYPosition();

	// Timelines will play until maxHeight or minHeight
	// is reached.
	jumpTimeline.setCycleCount(Timeline.INDEFINITE);
      	fallTimeline.setCycleCount(Timeline.INDEFINITE);

	// Set hover between jump and fall animations.
	fallTimeline.setDelay(hoverCycleDuration);

	// Create keyFrames that update player positions for jump and fall
	// each cycle of corresponding timelines, and add them to those timelines.
	up = new KeyFrame(jumpCycleDuration, new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
		    if(yCoordinate > maxHeight){
			player1.moveUp(1);
			yCoordinate = player1.getYPosition();
		    }else{
			// Once we've reached max height stop
			// jump animation and start fall animation.
			jumpTimeline.stop();
			fallTimeline.play();
		    }
		}
	    });
	down = new KeyFrame(fallCycleDuration, new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
		    if(yCoordinate <  minHeight){
		        player1.moveDown(1);
			yCoordinate = player1.getYPosition();
		    }else{
			// Once we've reached min Height
			// stop fall animation and allow
			// a new jump animation to be activated
			// on keypress.
			fallTimeline.stop();
			isJump = false;
		    }
		}
	    });
	jumpTimeline.getKeyFrames().add(up);
	fallTimeline.getKeyFrames().add(down);
    }

    @Override
    public void handle(KeyEvent event){
	// Checks if correct key is pressed, and
	// whether or not the character is already
	// jumping. Animation doesn't start if these
	// conditions arent't met.
	if ( event.getCode().toString().equals(jumpButton) 
		&& isJump == false)
	    {
		// Set jump state to true so we can't
		// start a second jump animation.
		isJump = true;
		jumpTimeline.play();
	    }
    }
}