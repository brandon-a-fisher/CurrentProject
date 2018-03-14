package game;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainGUIApplication extends Application 
{
	// ************THIS IS NEW
	static Stage mainGUIStage = new Stage();

    // Max obstacles and collectibles allowed in game at any point.
    int maxObstacle = 2;
    int maxCollectible = 1;

    // Controls obstacles and collectible instantiation and deletion.
    AnimationApplication gameEngine = new AnimationApplication(maxObstacle,maxCollectible);

    // Lists maintaining active obstacle and collectibles so we can retrieve their references.
    Obstacle[] activeObstacleList = gameEngine.getActiveObstacleList();
    Collectible[] activeCollectibleList = gameEngine.getActiveCollectibleList();

    // Instantiate player.
    Player player1 = new Player(10, 290);

    // Width and height of canvas.
    static int width = 864;
    static int height = 575;

    // Standard length and height of an obstacle.
    final double lengthObstacle = 100;
    final double heightObstacle = 125;

    // Images for objects in game.
    static Image chair;
    static Image background;
    static Image obstacle;
    static Image life;
    static Image tape;

    // Will act as the main game clock. It is used to fire events at specific intervals, and
    // start events at certain times in the game by referencing timestamps corresponding to the
    // game clock. Starting the game clock begins the game. The game clock runs at 30fps, and
    // the format for a timestamp is as follows: 00:00:00;minutes:seconds:frames.
    private GameTimer mainTimer = new GameTimer();

    // A KeyEvent that makes the Player object given as an argument jump when the event is fired.
    private JumpEvent playerJump = new JumpEvent(player1);

    //Text output of what's happening in the game.
    TextOutput printer = new TextOutput();
	
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
	for(int index = 0; index < maxCollectible; index++){
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
        tape = new Image(getResource("Tape.png"));
    }

    private static String getResource(String filename)
    {
        return MainGUIApplication.class.getResource(filename).toString();
    }
    
    public void start(Stage primaryStage) throws Exception
    {
	loadGraphics();
	
	
	
	mainGUIStage = primaryStage;			//*****THIS IS NEW***********
	
	
	
	
	Group root = new Group();
	Scene scene = new Scene(root);
	Canvas canvas = new Canvas(width, height);
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
			    gc.drawImage(tape, currentObstacle.getXPosition(), currentObstacle.getYPosition());
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
		    		EndGame endG = new EndGame();
		    		try {
						endG.start( EndGame.endStage );
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		primaryStage.close();
		    		mainTimer.stopGameTimer();
		}
	    }});

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