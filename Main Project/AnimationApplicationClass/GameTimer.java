import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.animation.ParallelTransition;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;

// Version 3
// 1. Made framerate 100fps to deal with decimal inaccuracy so we deal with only
//    whole numbers.
// 2. Made currentFrame instance variable long so it can hold huge integers.
// 3. added addLimitedEvent method to add an event that fires consistently until
//    a certain period in seconds elapses.
// 4. Added game timer pause and stop methods.

/**
 * An of instance of this class creates a group of timers that persistently fire
 * EventHandler action events at specified frame intervals--based on  a game running 
 * at 100fps. All timers run parallel, and only a masterTimer, in which all
 * the timers are stored, has to be started to begin firing events.
 */
public class GameTimer{

    // Set it to 100 so 1 frame is 10ms; therefore we deal with only whole numbers.
    private static final int framesPerSecond = 100; 
                                              
    //Duration is in milliseconds. 1000 milliseconds is 1 second, so 1 frame is 10ms.
    private static final double singleFrameDurationMilli = 1000/framesPerSecond;

    // Frames elapsed after mainTimer starts. Stores int value of current frame.
    // Starts at frame one.
    private long currentFrame;

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
    public long getCurrentFrame(){
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

	//Keyframe that sets length of cycle.
	newEventTimer.getKeyFrames().add(new KeyFrame(Duration.millis(singleFrameDurationMilli*fireEventEveryFrames),event));

	//Place timeline in masterTimeline.
	masterTimer.getChildren().add(newEventTimer);
    }

    /**
      * Adds a newEventTimer to the masterTimer that will fire a selected event at 
      * every specified frame interval, but only for a specified period of time in
      * seconds.
      *
      *@param fireEventEveryFrames
      *                Integer representing the frame interval before the event is fired.
      *@param secondsSeriesLasts
      *                Integer representing how long event will consitently fire for.
      *@param event
      *         An event that takes place when the frame interval has ended.
      */

    public void addLimitedEvent(int fireEventEveryFrames,int secondsSeriesLasts, EventHandler<ActionEvent> event){
	// Test to make sure number of seconds is >= 0
	if (secondsSeriesLasts <= 0){
	    System.out.println("ERROR: Parameter that takes Number of seconds before ending an event series"+
			       "when using GameLoopTimer.addLimitedEvent, requires an argument" +
			       "that's a positive integer greater than 0. " + secondsSeriesLasts +
			       " is not a valid argument.");
	    System.exit(0);
	}
	
	// Create event as persistent event first.
	addPersistentEvent(fireEventEveryFrames,event);
	
	// Get event that was just added to masterTimer.
	int eventIndex = masterTimer.getChildren().size()- 1;
	Animation newEventTimer = masterTimer.getChildren().get(eventIndex);

	int timerCycleDuration = (int)singleFrameDurationMilli*fireEventEveryFrames;
	int numberOfCycles = (secondsSeriesLasts*framesPerSecond)/(timerCycleDuration/10);

	newEventTimer.setCycleCount(numberOfCycles);

	//FOR DEBUGGING
	newEventTimer.setOnFinished(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
		    //It prints the frame after the animation
		    //ends, so we subtract 1 to get frame when
		    //it ended.
		    //System.out.println(getCurrentFrame()- 1);   
		}
	//FOR DEBUGGING
	//System.out.println(timerCycleDuration);
	//System.out.println(numberOfCycles);

	    });	
    }
     
    /**
     * Starts the masterTimer, and events begin to fire at specified intervals.
     */
    public void startGameTimer(){	
	masterTimer.play();
    }

    public void stopGameTimer(){
	masterTimer.stop();
    }

    public void pauseGameTimer(){
	masterTimer.pause();
    }

    public static void main(String[]args){
    }
}
