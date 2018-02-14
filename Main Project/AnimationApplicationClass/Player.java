
public class Player
{
	private int playerHealth = 5;
	private int xValue = 0;
	private int yValue = 0;

	public boolean IsAlive()
	{
		if(playerHealth<=0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public int getXPosition()
	{
		return xValue;
	}

	public int getYPosition()
	{
		return yValue;
	}

	private void TakeDamage()
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

	public void Move(char move)
	{
		
		switch(move)
		{
			case 'w':
				if(this.yValue < 10)
				{
					this.yValue = 10;
				}
				break;
			case 's':
				if(this.yValue > 0)
				{
					this.yValue = 0;
				}
				break;
			case 'd':
				if(this.yValue > 0)
				{
					this.yValue = 0;
				}
				break;
		}
	}
	public void checkObjectCollision()
	{
		Collectible[] activeCollectibleList = gameEngine.getActiveCollectibleList();
		Obstacle[] activeObstacleList = gameEngine.getActiveObstacleList();
		for(int index = 0; index < activeObstacleList.length; index++)
		{
			if((yValue == activeObstacleList[index].getYPosition()) &&
			  xValue == activeObstacleList[index].getXPosition())
			{
				TakeDamage();	
			}
		}
		
		for(int index = 0; index < activeCollectibleList.length; index++)
		{
			if((yValue == activeCollectibleList[index].getYPosition()) &&
			  xValue == activeCollectibleList[index].getXPosition())
			{
				Heal();	
			}
		}
	}
}
