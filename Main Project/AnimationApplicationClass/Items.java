public class Items {
	private boolean isAlive;

	private double xValue;
	private double yValue;

	private double length;
	private double height;

	private char type;

	public Items( double newXValue, double newYValue, double newLength, double newHeight, char type )
	{
		isAlive = true;

		xValue = newXValue;
		yValue = newYValue;

		length = newLength;
		height = newHeight;

		this.type = Character.toLowerCase(type);
	}

	public void moveItem()
	{
		xValue--;
	}

	public void setXPosition( double newXValue )
	{
		xValue = newXValue;
	}

	public double getXPosition()
	{
		return xValue;
	}

	public void setYPosition( double newYValue )
	{
		yValue = newYValue;
	}

	public double getYPosition()
	{
		return yValue;
	}

	public void setIsAlive( boolean newAlive )
	{
		isAlive = newAlive;
	}

	public boolean getIsAlive()
	{
		return isAlive;
	}

    public boolean checkHit(Items[] itemArray, Player player)
    {
    		if ( itemArray.length > 0 )
    		{
    			for (int item = 0; item < itemArray.length; item++)
    			{
    				if ( player.getYPosition() == itemArray[item].getYPosition() &&
    						player.getXPosition() == itemArray[item].getXPosition() )

    					itemArray[item].setIsAlive( false );
        				return true;
    			}
				return true;
    		}
			return false;
    }

	public boolean checkGone()
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
