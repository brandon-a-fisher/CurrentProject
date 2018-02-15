
public class TextOutput
{
	public void PrintActivePositions(AnimationApplication gameEngine)
	{

		if(gameEngine.getNumPlayers() > 0)
		{
			Player[] activePlayerList = gameEngine.getActivePlayerList();
			for(int index = 0; index < activePlayerList.length; index++)
			{
				if(activePlayerList[index] != null)
				{
					System.out.print("Player " + index + " (x, y):" + activePlayerList[index].getXPosition() +
					" " + activePlayerList[index].getYPosition() + "Health: " + activePlayerList[index].getPlayerHealth());
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
