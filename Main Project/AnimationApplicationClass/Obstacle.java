public class Obstacle
{
	
	/**
	  * Here, the variables of the obstacle are initiated.
	  */
	private boolean isAlive = true;
	
	private int xValue = 30;
	private int yValue;

	private int length;
	private int height;
	
	public Obstacle()
	{
		yValue = 1;
				
		length = 1;
		height = 1;
	}
	
	public Obstacle( int newYValue, int newLengthValue, int newHeightValue )
	{
		yValue = newYValue;
		
		length = newLengthValue;
		height = newHeightValue;
	}
	
	public Obstacle( int newYValue )
	{
		yValue = newYValue;
		
		length = 1;
		height = 1;
	}
	
	public void moveObstaclePosition()
	{
		xValue -= 1;
	}
	
	public int getObstacleXPosition()
	{
		return xValue;
	}
	
	public int getObstacleYPosition()
	{
		return yValue;
	}
	
	private boolean hitObstacle( int xPos, int yPos )
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
	
	private boolean obstacleIsGone()
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
	
	public boolean getIsAlive()
	{
		return isAlive;
	}
	
}
