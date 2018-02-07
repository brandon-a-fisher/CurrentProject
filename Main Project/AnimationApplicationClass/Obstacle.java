public class Obstacle
{
	
	/*
	 * Here, the variables of the obstacle are initiated.
	 */
	private boolean isAlive = true;
	
	private int xValue = 30;
	private int yValue;

	private int length;
	private int height;
	
	/*
	 * This is the default constructor. It creates an obstacle of size one for length
	 *   and height.
	 */
	public Obstacle() 
	{
		yValue = 1;
				
		length = 1;
		height = 1;
	}
	
	/*
	 * The following constructor allows for the maximum amount of customization. It allows
	 *   the creation of an obstacle with a new yValue, height, and length.
	 */
	public Obstacle( int newYValue, int newLengthValue, int newHeightValue )
	{
		yValue = newYValue;
		
		length = newLengthValue;
		height = newHeightValue;
	}
	
	/*
	 * The final constructor only allows for the change of the yValue, while the height and
	 *   length stay at one.
	 */
	public Obstacle( int newYValue )
	{
		yValue = newYValue;
		
		length = 1;
		height = 1;
	}
	
	/*
	 * moveObstaclePosition moves the obstacle to the left by one unit each time it
	 *   is called.
	 */
	public void moveObstaclePosition()
	{
		xValue -= 1;
	}
	
	/*
	 * The following two getter methods return the xValue, yValue, and isAlive of the obstacle.
	 */
	public int getObstacleXPosition()
	{
		return xValue;
	}
	
	public int getObstacleYPosition()
	{
		return yValue;
	}

	public boolean getIsAlive()
	{
		return isAlive;
	}
	
	/*
	 * hitObstacle checks whether or not the player has hit the obstacle and returns a
	 *   value of true or false accordingly.
	 */
	public boolean hitObstacle( int xPos, int yPos )
	{
		if ( (xValue == xPos) && (yValue == yPos ) )
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	/*
	 * obstacleIsGone checks whether or not the obstacle has passed the screen and returns a
	 *  value of true or false accordingly.
	 */
	public boolean obstacleIsGone()
	{
		if ( xValue <= 0 )
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	/*
	 * finalCheck checks both whether the obstacle is gone or if it's hit the player and
	 *  changes the value of is alive to either true or false accordingly.
	 */
	public void finalCheck(int xPos, int yPos)
	{
		if ( obstacleIsGone() || hitObstacle(xPos, yPos) )
		{
			isAlive = false;
		}
		
		else
		{
			isAlive = true;
		}
	}
}
