
public class TextOutput
{
    public void PrintActivePositions(AnimationApplication gameEngine, Player currentPlayer)
    {

	// Print current Player
	System.out.println("Player(x, y): " + currentPlayer.getXPosition() +
			   " " + currentPlayer.getYPosition() + " ");
	
	// Print current Obstacles
	System.out.println();
	if(gameEngine.getNumObstacles() > 0)
	    {

		Obstacle[] activeObstacleList = gameEngine.getActiveObstacleList();
		for(int index = 0; index < activeObstacleList.length; index++)
		    {
			if(activeObstacleList[index] != null)
			    {
				System.out.print("Obstacle " + index + "(x, y): " + activeObstacleList[index].getXPosition() +
						 " " + activeObstacleList[index].getYPosition() + " ");
			    }
		    }
	    }

	// Print current Collectibles.
	System.out.println();

	if(gameEngine.getNumCollectibles() > 0)
	    {
		Collectible[] activeCollectibleList = gameEngine.getActiveCollectibleList();
		for(int index = 0; index < activeCollectibleList.length; index++)
		    {
			if(activeCollectibleList[index] != null)
			    {
				System.out.print("Collectible " + index + "(x, y): " + activeCollectibleList[index].getXPosition() +
						 " " + activeCollectibleList[index].getYPosition() + " ");
			    }
			System.out.println();
		    }
	    }
	//Separate 1 input from next
	System.out.println("_______________________________________");
    }
}
