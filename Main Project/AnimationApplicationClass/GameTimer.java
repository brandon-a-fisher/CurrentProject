import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.ParallelTransition;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;

// Version 2
// 1. Added constants to control fps and single frame duration in milliseconds.
// 2. Added a frameCounter to count frames and getCurrentFrame method.
// 3. Added static methods to return constants for frame duration and fps, and
//    static methods to convert second and minutes values to frames based on
//    framerate of timer, and return integer of that value.
// 4. Updated javadoc.

/**
 * An of instance of this class creates a group of timers that persistently fire
 * EventHandler action events at specified frame intervals--based on  a game running 
 * at 60fps. All timers run parallel, and only a masterTimer, in which all
 * the timers are stored, has to be started to begin firing events.
 */
public class GameTimer{

    private static final int framesPerSecond = 60; //

    //Duration is in milliseconds. 1000 milliseconds is 1 second.
    private static final double singleFrameDurationMilli = 1000/(double)framesPerSecond;;

    // Frames elapsed after mainTimer starts. Stores int value of current frame.
    // Starts at frame one.
    private int currentFrame;

    // The following timer will start counting frames once masterTimer starts,
    // updating the current frame count in currentFrame.
    private Timeline frameCounter;
    private KeyFrame frameCounterEvent;
    
    // Any new Timelines created with addPersistentEvent()
    // become children of masterTimer. When we start
    // masterTimer, the children Timelines run in parallel,
    // firing events whenever they reach the end of their KeyFrame,
    // then restarting the cycle.
    private ParallelTransition masterTimer;

    /**
     * Default constructor doesn't take any arguments.
     */
    public GameTimer(){
	currentFrame = 1; // Starts at 1 not 0, otherwise we'll always be a frame behind.
      	masterTimer = new ParallelTransition();

	// Prepare frameCounter to update currentFrame every duration of one frame.
	frameCounter = new Timeline();
	frameCounterEvent = new KeyFrame(Duration.millis(singleFrameDurationMilli), new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
		    //System.out.println(getCurrentFrame());
		    currentFrame++;
		}
	    });
	frameCounter.setCycleCount(Timeline.INDEFINITE);
	frameCounter.getKeyFrames().add(frameCounterEvent);
	masterTimer.getChildren().add(frameCounter);
    }
    
    /**
     * Returns a double representing, in milliseconds, the duration of a single
     * frame of a GameTimer object.
     * 
     *@return an double representing, in milliseconds, the duration of a single frame.
     */
    public static double getFrameDuration(){
	return singleFrameDurationMilli;
    }
    
    /**
     * Returns an integer representing the number of fps of a GameTimer object.
     *
     *@return a double representing the number of fps of a GameTimer object.
     */
    public static int getFramesPerSecond(){
	return framesPerSecond;
    }
    
    /**
     * Static method that converts a number of seconds to frames at specified fps.
     *
     *@param integer representing a number of seconds.
     *@return integer representing a number of frames input seconds translates to.
     */
    public static int convertSecondsToFrames(int numberOfSec){
	int frames  = 0;
	if (numberOfSec >= 0)
	    frames = numberOfSec*framesPerSecond;
	return frames;
    }

    /**
     * Static method that converts a number of minutes to frames at specified fps.
     *
     *@param integer representing a number of minutes.
     *@return integer representing a number of frames input minutes translates to.
     */
    public static int convertMinutesToFrames(int numberOfMinutes){
	int frames  = 0;
	if (numberOfMinutes >= 0)
	    frames = numberOfMinutes*60*framesPerSecond;
	return frames;
    }

    /**
     * Returns an integer representing the current frame of the
     * mainTimer.
     */
    public int getCurrentFrame(){
	return currentFrame;
    }
    
    /**
      * Adds a newEventTimer to the masterTimer that will fire a selected event at 
      * every specified frame interval.
      *
      *@param fireEventEveryFrames
      *                Integer representing the frame interval before the event is fired.
      *@param event
      *         An event that takes place when the frame interval has ended.
      */
    public void addPersistentEvent(int fireEventEveryFrames, EventHandler<ActionEvent> event){
	// Test to make sure number of frames is >= 0
	if (fireEventEveryFrames <= 0){
	    System.out.println("ERROR: Parameter that takes Number of frames before firing an event"+
			       "when using GameLoopTimer.addTimeline, requires an argument" +
			       "that's a positive integer greater than 0. " + fireEventEveryFrames +
			       " is not a valid argument.");
	    System.exit(0);
	}

	// Create newEventTimer and ensure it cycles until we stop it.
	Timeline newEventTimer = new Timeline();
      	newEventTimer.setCycleCount(Timeline.INDEFINITE);

	// Choose length of timeline KeyFrame we repeat. One frame at 60fps is 
	// approx 0.016ms so we multiply fireEventEveryFrames by 0.016ms to get a
	// duration equivalent to the number of frames we've specified.
	double keyFrameDuration = (singleFrameDurationMilli)*(double)fireEventEveryFrames;
	
	//Add KeyFrame that fires event, event fires at end of KeyFrame duration.
	newEventTimer.getKeyFrames().add(new KeyFrame(Duration.millis(keyFrameDuration),event));

	//Place timeline in masterTimeline.
	masterTimer.getChildren().add(newEventTimer);
    }
     
    /**
     * Starts the masterTimer, and events begin to fire at specified intervals.
     */
    public void startGameTimer(){	
	masterTimer.play();
    }

    public static void main(String[]args){

	GameTimer g1 = new GameTimer();
	System.out.println(GameTimer.getFrameDuration());
    }
}
