package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.util.ArrayList;
import javafx.util.Duration;
import java.util.Scanner;

// Version 6
// 1. Fixed some casting issues with double and long when making keyframes.
// 2. Added Duration object instance variable: frame duration to instance variables.
// 3. When stopGameTimer() is called, the gameClock is now also reset to zero, so
//    everything works properly if we start timers again.

/**
 * An of instance of this class is a GameTimer that runs at 30fps, designed to fire events
 * at specific points during the game. Events added to this timer will be fired at selected
 * frame intervals, either indefinitely or for a limited time; meaning they'll stop firing after 
 * reaching a chosen time stamp on the game clock. A delay can also be set to prevent an event from 
 * firing before a specified period of time. Arguments involving time need to be given as Strings in
 * the format 00:00:00; minutes:seconds:frames.
 */
public class GameTimer{

    // Frames per second is set to 30.
    private static final int framesPerSecond = 30; 
                                              
    //Duration is in milliseconds. 1 frame is 1000ms/30.
    private static final double singleFrameDurationMilli = 1000/framesPerSecond;
    private Duration frameDuration = Duration.millis(singleFrameDurationMilli);

    // Units elapsed after gameClock starts. Used to populate gameClock.
    private long currentFrame;
    private long currentSecond;
    private long currentMinute;

    // The current timestamp of the gameClock. Is updated each frame by the frameCounter.
    // Is in format minutes:seconds:frames.
    private String gameClock = "00:00:00";

    // The following timer will start counting frames once the GameTimer starts;
    // updating the gameClock and frameCount. It also stops timers when they
    // reach the frame they were set to stop when they were created. Frame count
    // counts the total number of frames elapsed.
    private Timeline frameCounter;
    private KeyFrame frameCounterEvent;
    private long frameCount;

    // Any timers created are added to timerList. We use method startGameTimer()
    // to start all of the timers in the list at the same time, and stopGameTimer()
    // to stop all of the timers at once. timerListWhenToStop controls when to stop
    // timers by storing frame values to stop them in indexes corresponding to indexes
    // in timerList.
    private ArrayList<Timeline> timerList;
    private ArrayList<Long> timerListWhenToStop;

    /**
     * Default constructor doesn't take any arguments.
     */
    public GameTimer(){
	currentFrame = 0; 
	currentSecond = 0;
	currentMinute = 0;
	gameClock = "00:00:00";
	
	timerList = new ArrayList<Timeline>(10);
	timerListWhenToStop = new ArrayList<Long>(10);
	
	// Prepare frameCounter to update gameClock and frameCount, and check if timers need to be stopped.
	// Does this every frame.
	frameCounter = new Timeline();
	frameCount = 0;
	frameCounterEvent = new KeyFrame(frameDuration, new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
		    // Stop any timers that have reached their
		    // critical frame.
		    for(int index = 0;index<timerList.size();index++){
			if(timerListWhenToStop.get(index) == frameCount){
			    timerList.get(index).stop();
			    //FORDEBUGGING Prints out timeStamp when a timer
			    //stops
			    //System.out.println(getGameClock());
			}
		    }
		    // Update gameClock and frameCount
		    currentFrame++;
		    frameCount++;
		    if(currentFrame == framesPerSecond){
			currentFrame = 0;
			currentSecond++;
			if(currentSecond == 60){
			    currentSecond = 0;
			    currentMinute++;
			}			
		    }
		    gameClock = currentMinute+":"+currentSecond+":"+currentFrame;
		    //FOR DEBUGGING. Prints out clock in terminal every frame.
		    //System.out.println(getGameClock());
		    //System.out.println(frameCount);
		}
	    });
	frameCounter.setCycleCount(Timeline.INDEFINITE);
	frameCounter.getKeyFrames().add(frameCounterEvent);

	// Add frameCounter to timerList so it starts with
	// other timers when startGameTimer()is called.
	timerList.add(frameCounter);
	timerListWhenToStop.add((long)-1);
    }
    
    /**
     * Returns a String representing the current timestamp of the gameClock.
     *
     * @return String of current timestamp of gameClock in format 00:00:00; minutes:seconds:frames.
     */
    public String getGameClock(){
	return gameClock;
    }
    
    /**
      * Adds a timer to the masterTimer that will fire an event that was given as 
      * an argument every frame interval until the masterTimer is stopped. A timeStamp
      * must be given to set the start and interval; in the format 00:00:00; minutes:seconds:frames.
      *
      *@param interval
      *           String timestamp representing the frame interval that elapses before every
      *           firing of the event; in the format 00:00:00; minutes:seconds:frames
      *
      *@param start
      *           String timestamp representing which time on the gameClock you'd like the
      *           event to start; in the format 00:00:00; minutes:seconds:frames.
      *
      *@param event
      *           The action event to fire.
      */
    public void addPersistentEvent(String start, String interval,  EventHandler<ActionEvent> event){
	//Parse timestamps.
	long intervalInFrames = GameTimer.parseTimeStamp(interval);
	long startInFrames = GameTimer.parseTimeStamp(start);

	// Create timer and ensure it cycles until we stop it. 
	// Set length of a cycle based on intervalInFrames. Event
	// will fire at end of each cycle. Also set delay, and event
	// to fire.
	Timeline timer = new Timeline();
	timer.setCycleCount(Timeline.INDEFINITE);
	timer.setDelay(Duration.millis(singleFrameDurationMilli*(double)startInFrames));
	timer.getKeyFrames().add(new KeyFrame(Duration.millis(singleFrameDurationMilli*(double)intervalInFrames),event));

	//Add timer to timerList,and set stop value to -1, because
	//it runs persistently.
	timerList.add(timer);
	timerListWhenToStop.add((long)-1);
    }

    /**
      * Adds a timer to the masterTimer that will fire an event that was given as 
      * an argument every frame interval until a certain period of time has elapsed. 
      * A timeStamp must be given to set the start, end, and interval; in the format 
      * 00:00:00; minutes:seconds:frames.
      *
      *@param interval
      *           String timestamp representing the frame interval that elapse before every
      *           firing of the event; in the format 00:00:00; minutes:seconds:frames
      *
      *@param start
      *           String timestamp representing which time on the gameClock you'd like the
      *           event to start firing every interval; in the format 00:00:00; minutes:seconds:frames. 
      *
      *@param end
      *           String timestamp representing which time on the gameClock you'd like the event
      *           to stop firing every frame interval; in the format 00:00:00; minutes:seconds:frames.
      *
      *@param event
      *           The action event to fire.
      */
    public void addLimitedEvent(String start, String end, String interval, EventHandler<ActionEvent> event){
	// Create timer as a persistent timer first, and then grab it from timerList.
	addPersistentEvent(start,interval,event);
	int timerIndex = timerList.size()- 1;
	Timeline subTimer = timerList.get(timerIndex);

	// Figure out how many frames before the timer needs
	// to stop.
	long framesBeforeStart = GameTimer.parseTimeStamp(start);
	long framesBeforeEnd = GameTimer.parseTimeStamp(end);

	// Change the value in the list to the number of frames that need
	// to elapse before you want the frameCounter to stop this timer.
	timerListWhenToStop.set(timerIndex,framesBeforeEnd);
    }
     
    /**
     * Starts all the timers at the same time; a timer delaying
     * only if a delay is set when adding a new one.
     */
    public void startGameTimer(){
	for(Timeline timer:timerList){
	    if(frameCount == 0){
	    timer.play();
	    }else{
		System.out.println("ERROR: Timers couldn't be started, because of potential drift.");
		System.exit(0);
	    }
	}
    }
    
    /**
     * Stops all the timers, and resets game clock.
     */
    public void stopGameTimer(){
	for(Timeline timer:timerList){
	    timer.stop();
	    //FOR DEBUGGING
	    //System.out.println(getGameClock());
	}
	gameClock = "00:00:00";
    }
    
    /**
     * Returns a double representing, in milliseconds, the duration of a single
     * frame of an instance of the GameTimer class.
     * 
     *@return a double representing, in milliseconds, the duration of a single frame.
     */
    public static double getFrameDuration(){
	return singleFrameDurationMilli;
    }
        
    /**
     * Static method that converts a number of seconds to frames at specified framesPerSecond
     *
     *
     *@param numberOfSec integer representing a number of seconds.
     *@return integer representing a number of frames input seconds translates to.
     */
    public static int convertSecondsToFrames(int numberOfSec){
	int frames  = 0;
	if (numberOfSec >= 0)
	    frames = numberOfSec*framesPerSecond;
	return frames;
    }
    
    /**
     * Static method that converts a number of minutes to frames at specified framesPerSecond.
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
     * Takes a String representing a time stamp in the format 00:00:00;
     * minutes:seconds:frames, and converts it into a number of frames
     * based on the fps the GameTimer class is set to (100fps). Returns
     * a long value of that number of frames.
     *
     *@param timeStamp
     *            String representing a timeStamp in the format 00:00:00;
     *            minutes:seconds:frames.
     *
     *@return A long corresponding to the number of frames the timeStamp
     *        converts to.
     */
    public static long parseTimeStamp(String timeStamp){	
	int firstColon = timeStamp.indexOf(58);
	int secondColon = timeStamp.indexOf(58,firstColon+1);

	//Make sure there are characters following and before each colon, and that there
	//are at least two colons in the timeStamp.
	if(firstColon == -1  || firstColon == 0 || 
	   secondColon == -1 || secondColon == (timeStamp.length() - 1)||
	   secondColon == firstColon+1){
	    System.out.println("ERROR: Could not parse timeStamp. Colons incorrectly placed.");
	    System.exit(0);
	}

	//Get minutes,seconds and frames using colon indexes to retrieve subStrings.
	String minutes = timeStamp.substring(0,firstColon);
	String seconds = timeStamp.substring(firstColon+1,secondColon);
	String frames = timeStamp.substring(secondColon+1,(timeStamp.length()));
	int numMinutes = 0;
	int numSeconds = 0;
	int numFrames = 0;
	Scanner checkMinutes = new Scanner(minutes);
	Scanner checkSeconds = new Scanner(seconds);
	Scanner checkFrames = new Scanner(frames);

	//Make sure values input are integers.
	if(!(checkMinutes.hasNextInt())||
	   !(checkSeconds.hasNextInt())||
	   !(checkFrames.hasNextInt())){
	    System.out.println("ERROR: Could not parse timeStamp. One or more values are not" +
                               "integers");
	    System.exit(0);
	}
	// If they are get those integers.
	else{
	    numMinutes = Integer.parseInt(minutes);
	    numSeconds = Integer.parseInt(seconds);
	    numFrames = Integer.parseInt(frames);
	}
	//Make sure values input are in the correct range.
	if(numMinutes < 0 ||
	   numSeconds < 0 || numSeconds >= 60 ||
	   numFrames < 0 || numFrames >= framesPerSecond){
	    System.out.println("ERROR: Could not parse timeStamp. One or more values is too large" +
                               " or negative");
	    System.exit(0);
	}
	// Convert timeStamp into frames.
	long totalFrames = GameTimer.convertMinutesToFrames(numMinutes)+
	    GameTimer.convertSecondsToFrames(numSeconds)+numFrames;
	return totalFrames;
    }
    
    public static void main(String[]args){
	GameTimer testTimer = new GameTimer();
	System.out.println(GameTimer.parseTimeStamp("60:43:26"));
    }
}