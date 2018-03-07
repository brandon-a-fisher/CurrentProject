import javafx.scene.image.Image;

public class Player
{
    private int playerHealth = 5;
    private double xValue = 0;
    private double yValue = 0;
    private double length = 0;
    private double height = 0;

    public Player(double x, double y, double newLength, double newHeight, int pHealth)
    {
	playerHealth = pHealth;
	xValue = x;
	yValue = y;
	length = newLength;
	height = newHeight;
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
	
    public void setPlayerLength(double newLength)
    {
	length = newLength;
    }
	
    public void setPlayerHeight(double newHeight)
    {
	height = newHeight;
    }

}
