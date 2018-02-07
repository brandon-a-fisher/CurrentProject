/**
Creates an object with its length and height an x and y position aIt moves to
the left of the screen. It checks if it overlaps with the player in the game or
if the object if off-screen.
*/

public class Collectible {

	private int xValue;
	private int yValue;
	private int length;
	private int height;

	public Collectible(int newYValue) {
		yValue = newYValue;
		xValue = 30;
		height = 1;
		length = 1;
	}

	public int getYPosition() {
		return yValue;
	}

	public int getXPosition() {
		return xValue;
	}

	public boolean overlapsWith(int yPos, int xPos) {
		if (xPos == this.xValue && yPos == this.yValue)
			return true;
		else
			return false;
	}

	public void moveCollectible(){
		xValue --;
	}
}
