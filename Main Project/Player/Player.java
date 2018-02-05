public class Player
{
	private int playerHealth = 5;
	private int xValue;
	private int yValue;
	private boolean isAlive;

	public Player()
	{
		xValue = 0;
		yValue = 10;
		isAlive = true;
	}
	
	public Player(int setX, int setY)
	{
		xValue = setX;
		yValue = setY;
		isAlive = true;
	}
	
	public boolean getIsAlive()
	{
		return isAlive;
	}

	public void TakeDamage()
	{
		playerHealth -= 1;
	}

	public void Heal()
	{
		if(playerHealth < 5)
		{
			playerHealth += 1;
		}
	}
	
	public void Jump()
	{
		pass;
	}
	
	public getXPos()
	{
		return xValue;
	}
	
	public getYPos()
	{
		return yValue;
	}
}
