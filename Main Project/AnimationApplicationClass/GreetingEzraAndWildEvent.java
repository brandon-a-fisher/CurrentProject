import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/** 
 * A class designed to test the GameTimer class implementation in
 * GUIApp.java. Specifies an event that greets Ezra and Wild.
 */
public class GreetingEzraAndWildEvent implements EventHandler<ActionEvent>{
    
    @Override
    public void handle(ActionEvent event)
    {
	System.out.println("Hey Ezra");
	System.out.println("Hey Wild");
    }
}
		       
