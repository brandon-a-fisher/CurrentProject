public class Collectible extends Items
{
	// private static int length = 1;
	// private static int height = 1;

	public Collectible( int YValue, int height, int length )
	{
		super( YValue, length, height );
	}

	public Collectible( int YValue )
	{
		int length = 1;
		int height = 1;
		super( YValue, length, height );
	}

	// public void collision( Items[] itemArray, Player player )
	// {
	// 	if ( super.checkHit(itemArray, player) )
	// 		player.Heal();
	// }
}
