import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.ParallelTransition;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;

/**
 * An of instance of this class creates a group of timers that persistently fire
 * EventHandler action events at specified frame intervals--based on  a game running 
 * at 60fps. All timers run parallel, and only a master timeline, in which all
 * the timers are stored, has to be started to begin firing events.
 */
public class GameTimer{
    
    // Any new Timelines created with addPersistentEvent()
    // become children of masterTimer. When we start
    // masterTimer, the children Timelines run in parallel,
    // firing events whenever they reach the end of their KeyFrame,
    // then restarting the cycle.
    private ParallelTransition masterTimer = new ParallelTransition();
    
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
	double keyFrameDuration = (1000/60)*(double)fireEventEveryFrames;
	
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

    }
}
