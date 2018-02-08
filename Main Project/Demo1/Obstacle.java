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
	public void moveObstacle()
	{
		xValue -= 1;
	}
	
	/*
	 * The following three getter methods return the xValue, yValue, and isAlive of the obstacle.
	 */
	public int getXPosition()
	{
		return xValue;
	}
	
	public int getYPosition()
	{
		return yValue;
	}

	public boolean getIsAlive()
	{
		return isAlive;
	}
	
	/*
	 * hitObstacle checks whether or not the player has hit the obstacle and returns a
	 *   value of true or false accordingly. It also changes the value of isAlive.
	 */
	public boolean overlapsWith( int xPos, int yPos )
	{
		if ( (xValue == xPos) && (yValue == yPos ) )
		{
			isAlive = false;
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	/*
	 * obstacleIsGone checks whether or not the obstacle has passed the screen and changes
	 *  the value of isAlive accordingly.
	 */
	public boolean obstacleGone()
	{
		if ( xValue < 0 )
		{
			isAlive = false;
			return true;
		}
		else
		{
			isAlive = true;
			return false;
		}
	}
}
