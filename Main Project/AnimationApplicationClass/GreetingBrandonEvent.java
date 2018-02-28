import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/** 
 * A class designed to test the GameTimer class implementation in
 * GUIApp.java. Specifies an event that greets Brandon.
 */
public class GreetingBrandonEvent implements EventHandler<ActionEvent>{
    
    @Override
    public void handle(ActionEvent event)
    {
	System.out.println("Hey Brandon");
    }

    
}
		       
