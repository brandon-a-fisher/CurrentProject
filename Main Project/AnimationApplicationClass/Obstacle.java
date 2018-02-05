package obstacle;

public class Obstacle {
			
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
	
	private void moveObstaclePosition()
	{
		xValue -= 1;
	}
	
	private int getObstacleXPosition()
	{
		return xValue;
	}
	
	private int getObstacleYPosition()
	{
		return yValue;
	}
	
	private void setObstacleY( int newY )
	{
		yValue = newY;
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
}
