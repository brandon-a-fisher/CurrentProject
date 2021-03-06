package game;

public class Items {
	private boolean isAlive = true;

	private int xValue = 900;
	private int yValue;

	final int length;
	final int height;

	public Items( int newYValue, int newLength, int newHeight )
	{
		setYPosition( newYValue );
		
		length = newLength;
		height = newHeight;
	}

	public void moveItem()
	{
		xValue -= 30;
	}

	public void setXPosition( int newXValue )
	{
		xValue = newXValue;
	}

	public int getXPosition()
	{
	    return xValue;
	}

	public void setYPosition( int newYValue )
	{
		yValue = newYValue;
	}

	public int getYPosition()
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