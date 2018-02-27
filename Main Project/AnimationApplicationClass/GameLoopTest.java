import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/** 
 * A class designed to test the gameLoop implementation in
 * GUIApp.java. Specifies an event handler to be put in 
 * the Keyframe baseFrame variable of that class as an 
 * argument. Will say "Hey Brandon" upon triggering.
 */
public class GameLoopTest implements EventHandler<ActionEvent>{
    
    @Override
    public void handle(ActionEvent event)
    {
	System.out.println("Hey Brandon");
    }

    
}
		       
