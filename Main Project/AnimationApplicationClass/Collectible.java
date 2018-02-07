/**
Creates an object with its length and height an x and y position aIt moves to
the left of the screen. It checks if it overlaps with the player in the game or
if the object if off-screen.
*/
public class Collectible {

	private boolean isAlive;
	private int xValue;
	private int yValue;
	private int length;
	private int height;

/**
Constuctor requiring a y value to spawn.
*/
	public Collectible(int newYValue) {
		yValue = newYValue;
		xValue = 30;
		height = 1;
		length = 1;
	}

/**
Returns the y position.
*/
	public int getYPosition() {
		return yValue;
	}

/**
Returns the y position.
*/
	public int getXPosition() {
		return xValue;
	}

/**
Returns a boolean of the isAlive.
*/
	public boolean getIsAlive() {
		return isAlive;
	}

/**
Returns true if the objects overlaps the given x and y positions and false if
not.
*/
	public boolean overlapsWith(int yPos, int xPos) {
		if (xPos == this.xValue && yPos == this.yValue) {
			isAlive = false;
			return true;
		}
		else
			return false;
	}

/**
Returns true if the collectible goes off-screen based on the x position.
*/
	public boolean collectibleGone() {
		if (xValue < 0 )
			return true;
		else
			return false;
	}

/**
Moves the collectible towards the left.
*/
	public void moveCollectible(){
		xValue --;
	}
}
