public class Obstacle {
	private static int length = 1;	//The length of the obstacle stays the same throughout, and is initiated as being 1 here. It's the
	private static int height = 1;	//  same for the height, which is used to check whether the user jumped above or not.
	
	private static int xValue = 10;	//Here, the xValue is initiated at the end of the screen.
	
	//This will make the obstacle move 1 block closer to the player.
	private void moveObstaclePosition()
	{								//IMPORTANT: Later on these two functions of moveObstaclePosition
		xValue -= 1;						//  and getObstaclePosition can be put together.
	}
	
	//This will return the xValue of the obstacle.
	private int getObstaclePosition()
	{
		return xValue;
	}
	
	//This will check whether the player hit the obstacle, returning booleans accordingly.
	private boolean hitObstacle( int xPos, int yPos )
	{
		if ( (xValue == xPos) && (height == yPos ) )
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
