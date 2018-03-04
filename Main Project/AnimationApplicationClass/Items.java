public class Items {
	private boolean isAlive = true;

	private int xValue = 30;
	private int yValue;

	private int length;
	private int height;

	/**
	* Constructor for items displayed on the screen.
	* @param newYValue the given Y coordinate to be used on screen
	* @param newLength the length of the object
	* @param newHeight the height of the object
	*/
	public Items( int newYValue, int newLength, int newHeight )
	{
		yValue = newYValue;
		length = newLength;
		height = newHeight;
	}

	/**
	* Moves the current item towards left of the screen.
	*/
	public void moveItem()
	{
		xValue--;
	}

	/**
	* Sets the X coordinate
	* @param newXValue the x value used for the object
	*/
	public void setXPosition( int newXValue )
	{
		xValue = newXValue;
	}

	/**
	* Gets the X coordinate
	* @return the x value of the item.
	*/
	public int getXPosition()
	{
		return xValue;
	}
	
	/**
	* Sets the Y coordinate
	* @param newYValue the x value used for the object
	*/
	public void setYPosition( int newYValue )
	{
		yValue = newYValue;
	}

	/**
	* Gets the Y coordinate
	* @return the y value
	*/
	public int getYPosition()
	{
		return yValue;
	}
	
	/**
	* Gets the length
	* @return the length
	*/
	public int getLength()
	{
		return length;
	}

	/**
	* Gets the height
	* @return the height
	*/
	public int getHeight()
	{
		return height;
	}

	/**
	* Sets whether or not an item is still displayed (alive) on screen or not
	* @param newAlive boolean indicating if it's alive or not
	*/
	public void setIsAlive( boolean newAlive )
	{
		isAlive = newAlive;
	}

	/**
	* Gets the boolean of item if it is still displayed on screen
	* @return the boolean if it is displayed or not.
	*/
	public boolean getIsAlive()
	{
		return isAlive;
	}

	// /**
	// * Gets the boolean of item if it is still displayed on screen
	// * @return the boolean if it is displayed or not.
	// */
 //    public boolean checkHit(Items[] itemArray, Player player)
 //    {
 //    		if ( itemArray.length > 0 )
 //    		{
 //    			for (int item = 0; item < itemArray.length; item++)
 //    			{
 //    				if ( player.getYPosition() == itemArray[item].getYPosition() &&
 //    						player.getXPosition() == itemArray[item].getXPosition() )

 //    					itemArray[item].setIsAlive( false );
 //        				return true;
 //    			}
	// 			return true;
 //    		}
	// 		return false;
 //    }

	/**
	* Checks if the x value if the item is still on screen.
	* @return the boolean if it is gone from the screen or not
	*/
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
