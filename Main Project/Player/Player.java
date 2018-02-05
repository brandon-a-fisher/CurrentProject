public class Player
{
	private int playerHealth = 5;
	private int xPos = 0;
	private int yPos = 0;

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
}
