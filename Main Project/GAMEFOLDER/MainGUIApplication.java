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
    // Max obstacles and collectibles allowed in game at any point.
    private int maxObstacle = 2;
    private int maxCollectible = 2;

    // Controls obstacles and collectible insantiation and deletion.
    private AnimationApplication gameEngine = new AnimationApplication(maxObstacle,maxCollectible);

    // Lists maintaining active obstacle and collectibles so we can retrieve their references.
    private Obstacle[] activeObstacleList = gameEngine.getActiveObstacleList();
    private Collectible[] activeCollectibleList = gameEngine.getActiveCollectibleList();

    // Instantiations
    private Player player1 = new Player(10, 290, 150, 269, 5);

    // Width and height of canvas.
    private static int canvasWidth = 864;
    private static int canvasHeight = 575;

    // Standard length and height of an obstacle.
    private final double lengthObstacle = 100;
    private final double heightObstacle = 125;

    // Images for objects in game.
    private static Image chair;
    private static Image background;
    private static Image obstacle;
    private static Image life;

    // Will act as the main game clock. It is used to fire events at specific intervals, and
    // start events at certain times in the game by referencing timestamps corresponding to the
    // game clock. Starting the game clock begins the game. The game clock runs at 30fps, and
    // the format for a timestamp is as follows: 00:00:00;minutes:seconds:frames.
    private GameTimer mainTimer = new GameTimer();

    // A KeyEvent that makes the Player object given as an argument jump when the event is fired.
    private JumpEvent playerJump = new JumpEvent(player1);

    //Text output of what's happening in the game.
    private TextOutput printer = new TextOutput();
	
    public void hasHitObstacle()
    {
	for(int index = 0; index < maxObstacle; index++){
	    Obstacle currentObstacle = gameEngine.getObstacle(index);
	    if(currentObstacle!=null){
		double obstacleLeftEdge = currentObstacle.getXPosition();
		double obstacleRightEdge = (currentObstacle.getXPosition() + lengthObstacle);
		double obstacleUpperEdge = currentObstacle.getYPosition();
		double obstacleLowerEdge = (currentObstacle.getYPosition() + heightObstacle);
    		
		double playerLeftEdge = player1.getXPosition();
		double playerRightEdge = (player1.getXPosition() + player1.getPlayerLength());
		double playerLowerEdge = (player1.getYPosition() + player1.getPlayerHeight());

		if ((playerLeftEdge <= obstacleRightEdge) && (playerLeftEdge >= obstacleLeftEdge))
		    {
			if ((playerLowerEdge <= obstacleLowerEdge) && (playerLowerEdge >= obstacleUpperEdge))
			    {
				gameEngine.deleteObstacle(index);
				player1.takeDamage();
			    }
		    }
    		
		else if ((playerRightEdge <= obstacleRightEdge ) && (playerRightEdge >= obstacleLeftEdge))
		    {	
			if ((playerLowerEdge <= obstacleLowerEdge) && (playerLowerEdge >= obstacleUpperEdge))
			    {
				gameEngine.deleteObstacle(index);
				player1.takeDamage();
			    }
    			
		    }
	    }
	}
    }
    	
    public void hasHitCollectible()
    {
	for(int index = 0; index < maxObstacle; index++){
	   Collectible currentObstacle = gameEngine.getCollectible(index);
	    if(currentObstacle!=null){
		double obstacleLeftEdge = currentObstacle.getXPosition();
		double obstacleRightEdge = (currentObstacle.getXPosition() + lengthObstacle);
		double obstacleUpperEdge = currentObstacle.getYPosition();
		double obstacleLowerEdge = (currentObstacle.getYPosition() + heightObstacle);
    		
		double playerLeftEdge = player1.getXPosition();
		double playerRightEdge = (player1.getXPosition() + player1.getPlayerLength());
		double playerLowerEdge = (player1.getYPosition() + player1.getPlayerHeight());

		if ((playerLeftEdge <= obstacleRightEdge) && (playerLeftEdge >= obstacleLeftEdge))
		    {
			if ((playerLowerEdge <= obstacleLowerEdge) && (playerLowerEdge >= obstacleUpperEdge))
			    {
				gameEngine.deleteCollectible(index);
				player1.heal();
			    }
		    }
    		
		else if ((playerRightEdge <= obstacleRightEdge ) && (playerRightEdge >= obstacleLeftEdge))
		    {	
			if ((playerLowerEdge <= obstacleLowerEdge) && (playerLowerEdge >= obstacleUpperEdge))
			    {
				gameEngine.deleteCollectible(index);
				player1.heal();
			    }
    			
		    }
	    }
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
	Canvas canvas = new Canvas(canvasWidth, canvasHeight);
	GraphicsContext gc = canvas.getGraphicsContext2D();
			        				
	scene.setOnKeyPressed(playerJump);

	// Update screen and check for collisions every frame.
     	mainTimer.addPersistentEvent("00:00:00","00:00:01",new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event){
		    // Draw background.
		    gc.drawImage(background, 0, 0);

		    // Place Player and Player health on screen.
		    gc.drawImage(chair, player1.getXPosition(), player1.getYPosition());
		    for( int i = player1.getPlayerHealth() - 1; i >= 0; i-- )
			gc.drawImage(life, 810 - (i*50), 10);

		    // Update obstacle on screen.
		    for(Obstacle currentObstacle : activeObstacleList){
			if(currentObstacle != null)
			    gc.drawImage(obstacle, currentObstacle.getXPosition(), currentObstacle.getYPosition());
		    }

		    // Update collectible on screen.
		    for(Collectible currentObstacle : activeCollectibleList){
			if(currentObstacle != null)
			    gc.drawImage(life, currentObstacle.getXPosition(), currentObstacle.getYPosition());
		    }

		    // Move obstacles.
		    gameEngine.updateActiveObstacles();
		    gameEngine.updateActiveCollectibles();
		    		    
		    // Check for collisions.
		    hasHitObstacle();
		    hasHitCollectible();

		    // Game over if player health is zero.
		    if( player1.getPlayerHealth() == 0)
			{
			    System.exit(0);
			}
		}
	    });

	// Create a new obstacle every second.
	mainTimer.addPersistentEvent("00:00:00","00:01:00",new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event){
		    gameEngine.makeObstacle(450);
		}
	    });

	// Create a new collectible every two seconds and 15 frames.
	mainTimer.addPersistentEvent("00:00:00","00:02:15",new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event){
		    gameEngine.makeCollectible(525);
		}
	    });
	
	// Print out text every few frames, representing what's happening in game..
	mainTimer.addPersistentEvent("00:00:00","00:00:05",new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event){
		    printer.PrintActivePositions(gameEngine,player1);
		}
	    });
	
	mainTimer.startGameTimer();

	root.getChildren().add(canvas);
	primaryStage.setTitle("HIS RIGHTFUL PLACE");
	primaryStage.setScene(scene);
	primaryStage.show();
    }
}
