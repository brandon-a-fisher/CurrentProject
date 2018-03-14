package game;

public class Player
{
    final double length = 150;
    final double height = 269;
    
    private int playerHealth = 5;
    private double xValue = 0;
    private double yValue = 0;

    public Player(double x, double y)
    {
	setXPosition( x );
	setYPosition( y );
    }

    public int getPlayerHealth()
    {
	return playerHealth;
    }

    public double getXPosition()
    {
	return xValue;
    }

    public double getYPosition()
    {
	return yValue;
    }

    public void takeDamage()
    {
	playerHealth -= 1;
    }

    public void heal()
    {
	if(playerHealth < 5)
	    {
		playerHealth += 1;
	    }
    }

    // Must increment by 1 so we don't break
    // JumpEvent class. Otherwise boolean checks
    // won't work.
    public void moveUp(int increment){
	if(increment >= 0)
	   yValue -= increment;
    }

    public void moveDown(int increment){
	if(increment >= 0)
	    yValue += increment;
    }
    
    public double getPlayerLength()
    {
	return length;
    }

    public double getPlayerHeight()
    {
	return height;
    }

    public void setXPosition(double xPos)
    {
	xValue = xPos;
    }

    public void setYPosition(double yPos)
    {
	yValue = yPos;
    }
	
}