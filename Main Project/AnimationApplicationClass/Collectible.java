import java.util.Random;

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