import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainGUIApplication extends Application 
{
    Player player1 = new Player(10, 290, 150, 269, 5);
	
    static int width = 864;
    static int height = 575;
	
    private double xObstacle = 900;
    private double yObstacle = 450;

    // How much to adjust position of obstacle
    // each frame.
    private double positionAdjustmentFactor = 30;
	
    final double lengthObstacle = 100;
    final double heightObstacle = 125;
	
    static Image chair;
    static Image background;
    static Image obstacle;
    static Image life;

    // Will act as the main game clock. It is used to fire events at specific intervals, and
    // start events at certain times in the game by referencing timestamps corresponding to the
    // game clock. Starting the game clock begins the game. The game clock runs at 30fps, and
    // the format for a timestamp is as follows: 00:00:00;minutes:seconds:frames.
    private GameTimer mainTimer = new GameTimer();

    // A KeyEvent that makes the Player object given as an argument jump, when the event is fired.
    private JumpEvent playerJump = new JumpEvent(player1);
        
    public Boolean hasHit()
    {
	double obstacleLeftEdge = xObstacle;
	double obstacleRightEdge = (xObstacle + lengthObstacle);
	double obstacleUpperEdge = yObstacle;
	double obstacleLowerEdge = (yObstacle + heightObstacle);
    		
	double playerLeftEdge = player1.getXPosition();
	double playerRightEdge = (player1.getXPosition() + player1.getPlayerLength());
	double playerLowerEdge = (player1.getYPosition() + player1.getPlayerHeight());

	if ((playerLeftEdge <= obstacleRightEdge) && (playerLeftEdge >= obstacleLeftEdge))
	    {
		if ((playerLowerEdge <= obstacleLowerEdge) && (playerLowerEdge >= obstacleUpperEdge))
		    {
			return true;
		    }
    			
		else
		    {
			return false;
		    }
	    }
    		
	else if ((playerRightEdge <= obstacleRightEdge ) && (playerRightEdge >= obstacleLeftEdge))
	    {	
		if ((playerLowerEdge <= obstacleLowerEdge) && (playerLowerEdge >= obstacleUpperEdge))
		    {
			return true;
		    }
    			
		else
		    {
			return false;
		    }
	    }
    		
	else
	    {
		return false;
	    }
    }
    public static void main(String[] args) {
	launch(args);
    }
	
    /* 
     * The following two methods help pull the images needed to display. These methods were taken from:
     * https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
     */
	
    private static void loadGraphics()
    {
    	background = new Image(getResource("Lockers.jpeg"));
        chair = new Image(getResource("OfficeChair.png"));
        obstacle = new Image(getResource("Backpack.png"));
        life = new Image(getResource("Wheels.png"));
    }

    private static String getResource(String filename)
    {
        return MainGUIApplication.class.getResource(filename).toString();
    }
    
    public void start(Stage primaryStage) throws Exception
    {
	loadGraphics();
	    
	Group root = new Group();
	Scene scene = new Scene(root);
	Canvas canvas = new Canvas(width, height);
	GraphicsContext gc = canvas.getGraphicsContext2D();
			        				
	scene.setOnKeyPressed(playerJump);
               	
	// Update screen and check for collisions. Set start to 0 seconds of gameClock,
	// and set interval to 1 frame. Therefore event will fire indefinitely, once
	// every frame, starting at time 0.
     	mainTimer.addPersistentEvent("00:00:00","00:00:01",new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event){
		    xObstacle -= positionAdjustmentFactor;
				
		    if( xObstacle <= -200 )
			{
			    xObstacle += 1100;
			    // positionAdjustmentFactor -= positionAdjustmentFactor;
			}
				
		    gc.drawImage(background, 0, 0);
		    gc.drawImage(chair, player1.getXPosition(), player1.getYPosition());
		    gc.drawImage(obstacle, xObstacle, yObstacle);

		    for( int i = player1.getPlayerHealth() - 1; i >= 0; i-- )
			gc.drawImage(life, 810 - (i*50), 10);
				
		    if( hasHit() == true)
			{
			    xObstacle += 1100;
			    player1.takeDamage();
			}
				
		    if( player1.getPlayerHealth() == 0)
			{
			    System.exit(0);
			}
		}
	    });

	mainTimer.startGameTimer();

	root.getChildren().add(canvas);
	primaryStage.setTitle("HIS RIGHTFUL PLACE");
	primaryStage.setScene(scene);
	primaryStage.show();
    }
}
