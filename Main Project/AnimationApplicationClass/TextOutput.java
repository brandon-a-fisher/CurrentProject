
public class TextOutput
{
	public void PrintActivePositions(AnimationApplication gameEngine)
	{

		if(gameEngine.getNumPlayers() > 0)
		{
			Player[] activePLayerList = gameEngine.getActivePlayerList();
			for(int index = 0; index < activePLayerList.length; index++)
			{
				if(activePLayerList[index] != null)
				{
					System.out.print("Player " + index + "Health: " + activePLayerList[index].getPlayerHealth());
				}
			}
		}

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
			}
		}
	}
}
