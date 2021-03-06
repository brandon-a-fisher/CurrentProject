
public class TextOutput
{
	public int[] activePlayers = new int[0];
	public int[] activeObstacles = new int[0];
	public int[] activeCollectibles = new int[0];

	public void PrintActivePositions(AnimationApplication gameEngine)
	{
		
		if(gameEngine.getNumPlayers() > 0)
		{
			activePlayers = gameEngine.stepThroughActive("Player");
			for(int index = 0; index < activePlayers.length; index++)
			{
				System.out.print("Player" + index + "(x, y): " + gameEngine.getPlayer(activePlayers[index]).getXPosition() +
				" " + gameEngine.getPlayer(activePlayers[index]).getYPosition() + " ");
			}
		}
		
		System.out.println();
		
		if(gameEngine.getNumObstacles() > 0)
		{
			activeObstacles = gameEngine.stepThroughActive("Obstacle");
			for(int index = 0; index < activeObstacles.length; index++)
			{
				System.out.print("Obstacle" + index + "(x, y): " + gameEngine.getObstacle(activeObstacles[index]).getXPosition() +
				" " + gameEngine.getObstacle(activeObstacles[index]).getYPosition() + " ");
			}
		}
		
		System.out.println();
		
		if(gameEngine.getNumCollectibles() > 0)
		{
			activeCollectibles = gameEngine.stepThroughActive("Collectible");
			for(int index = 0; index < activeCollectibles.length; index++)
			{
				System.out.print("Collectible" + index + "(x, y): " + gameEngine.getCollectible(activeCollectibles[index]).getXPosition() +
				 " " + gameEngine.getCollectible(activeCollectibles[index]).getYPosition() + " ");
			}
		}
	}
}
