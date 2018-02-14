public class Collectible extends Items
{
	private static int generalHeight = 1;
	private static int generalLength = 1;
	
	public Collectible( int YValue, int height, int length )
	{
		super( YValue, height, length );
	}
	
	public Collectible( int YValue )
	{
		super( YValue, generalHeight, generalLength );
	}
	
	public void collision( Items[] itemArray, Player player )
	{
		if ( super.checkHit(itemArray, player) )
			player.Heal();
	}
}
