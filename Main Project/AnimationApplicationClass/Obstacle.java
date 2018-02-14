public class Obstacle extends Items
{
	private static int generalHeight = 1;
	private static int generalLength = 1;
	
	public Obstacle( int YValue, int height, int length )
	{
		super( YValue, height, length );
	}
	
	public Obstacle( int YValue )
	{
		super( YValue, generalHeight, generalLength );
	}
	
	public void collision( item[] itemArray, Player player )
	{
		if ( super.checkHit(itemArray, player) )
			player.TakeDamage();
	}
}
