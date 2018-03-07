import java.util.Scanner;

/**
 * A new instance of this class is an AnimationApplication that handles the
 * instantiation, deletion, and organization of game objects; including
 * Obstacles and Collectibles. References to active objects are
 * maintained in arrays corresponding to objects that the AnimationApplication
 * has instantiated; using make methods to do so. There are also getter methods
 * defined to pass data from the objects referenced in the arrays to
 * other classes. 
 */
public class AnimationApplication {

    // These values set size of Arrays that contain active objects.
    private final int MAX_ACTIVE_OBSTACLES;
    private final int MAX_ACTIVE_COLLECTIBLES;

    private int numActiveObstacles;
    private int numActiveCollectibles;

    // The following are the active object lists. They maintain references
    // to objects that are currently active in the game.
    private Obstacle[] activeObstacleList;
    private Collectible[] activeCollectibleList;

    /**
     * This is a default constructor: the maximum number of each object type that
     * can be instantiated in the game is set to 1, and the total maximum number of
     * active objects is set to 3.
     */
    public AnimationApplication() {
	MAX_ACTIVE_OBSTACLES = 1;
	MAX_ACTIVE_COLLECTIBLES = 1;

	numActiveObstacles = 0;
	numActiveCollectibles = 0;

	activeObstacleList = new Obstacle[MAX_ACTIVE_OBSTACLES];
	activeCollectibleList = new Collectible[MAX_ACTIVE_COLLECTIBLES];
    }

    /**
     * This constructor takes arguments for the integer values of max active objects
     * for the following types: Player, Obstacle, and Collectible respectively, as
     * well as an integer value for the maximum number of objects allowed in the
     * game; these values will set the size for the active object lists
     * corresponding to the above types.
     *
     * @param maxObstacle
     *            Maximum number of obstacles allowed in the game. Sets size of
     *            corresponding active object list.
     * @param maxCollectible
     *            Maximum number of collectibles allowed in the game. Sets size of
     *            corresponding active object list.
     *
     */
    public AnimationApplication(int maxObstacle, int maxCollectible) {

	MAX_ACTIVE_OBSTACLES = maxObstacle;
	MAX_ACTIVE_COLLECTIBLES = maxCollectible;

	activeObstacleList = new Obstacle[MAX_ACTIVE_OBSTACLES];
	activeCollectibleList = new Collectible[MAX_ACTIVE_COLLECTIBLES];
    }

    /**
     * Places newly instantiated obstacles in the first free index of the
     * activeObstacleList. Used in makeObstacle().
     */
    private void addToActiveObstacleList(Obstacle myObject) {
	for (int index = 0; index < activeObstacleList.length; index++) {
	    if (activeObstacleList[index] == null) {
		activeObstacleList[index] = myObject;
		break;
	    }
	}
    }

    /**
     * Places newly instantiated collectibles in the first free index of the
     * activeCollectibleList. Used in makeCollectible().
     */
    private void addToActiveCollectibleList(Collectible myObject) {
	for (int index = 0; index < activeCollectibleList.length; index++) {
	    if (activeCollectibleList[index] == null) {
		activeCollectibleList[index] = myObject;
		break;
	    }
	}
    }

    /**
     * Instantiates an Obstacle at the specified y coordinate on the screen. The x
     * coordinate is set to a default in the Obstacle class. Adds reference to new
     * Obstacle to the activeObstacleList. Increments number active for this object
     * type.
     *
     * @param ypos
     *            An integer representing a y co-ordinate on the screen.
     */
    public void makeObstacle(int ypos) {
	int maximumActive = MAX_ACTIVE_OBSTACLES;
	int currentActive = numActiveObstacles;

	if (currentActive < maximumActive && currentActive >= 0) {
	    Obstacle obstacleObject = new Obstacle(ypos, 100, 125);
	    numActiveObstacles++;
	    addToActiveObstacleList(obstacleObject);
	}
    }
    
    /**
     * Instantiates a Collectible at the specified y coordinate on the screen. The x
     * coordinate is set to a default in the Collectible class. Adds reference to
     * new Collectible to the activeCollectibleList. Increments number active for
     * this object type.
     *
     * @param yPos
     *            An integer representing a y co-ordinate on the screen.
     */
    public void makeCollectible(int yPos) {
	int maximumActive = MAX_ACTIVE_COLLECTIBLES;
	int currentActive = numActiveCollectibles;

	if (currentActive < maximumActive && currentActive >= 0) {
	    Collectible collectibleObject = new Collectible(yPos);
	    numActiveCollectibles++;
	    addToActiveCollectibleList(collectibleObject);
	}
    }

    /**
     * Deletes an Obstacle at a specified index of the activeObstacleList. A null
     * value takes the place of the reference to the object in the
     * activeObstacleList, thus removing all references to the object. Decrements
     * number active for this object type.
     *
     * @param index
     *            An integer index corresponding to a reference in the
     *            activeObstacleList.
     */
    public void deleteObstacle(int index) {
	if (index < activeObstacleList.length && index >= 0) {
	    // So we can't make numActivePlayers a negative value by
	    // making null indexes null and decrementing numActive.
	    if (activeObstacleList[index] != null) {
		activeObstacleList[index] = null;
		numActiveObstacles = numActiveObstacles - 1;
	    } else {
		System.out.println("ILLEGAL ACTION: Tried to delete reference " + '\n' + "at an empty index of the "
				   + '\n' + "activeObstacleList" + "while using " + '\n' + "deleteObstacle()");
		System.exit(0);
	    }
	} else {
	    System.out.println("ILLEGAL ACTION: Tried to delete an index out " + '\n'
			       + "of the range of activeObstacleList " + '\n' + "while using deleteObstacle()");
	    System.exit(0);
	}
    }

    /**
     * Deletes a Collectible at a specified index of the activeCollectibleList. A
     * null value takes the place of the reference to the object in the
     * activeCollectibleList, thus removing all references to the object. Decrements
     * number active for this object type.
     *
     * @param index
     *            An integer index corresponding to a reference in the
     *            activeCollectibleList.
     */
    public void deleteCollectible(int index) {
	// So we can't make numActiveCollectibles a negative value by
	// making null indexes null and decrementing numActive.
	if (index < activeCollectibleList.length && index >= 0) {
	    if (activeCollectibleList[index] != null) {
		activeCollectibleList[index] = null;
		numActiveCollectibles = numActiveCollectibles - 1;
	    } else {
		System.out.println("ILLEGAL ACTION: Tried to delete reference " + '\n' + "at an empty index of the "
				   + '\n' + "activeCollectibleList while using " + '\n' + "deleteCollectible()");
		System.exit(0);
	    }
	} else {
	    System.out.println("ILLEGAL ACTION: Tried to delete an index out " + '\n'
			       + "of the range of activeCollectibleList while " + '\n' + "using deleteCollectible()");
	    System.exit(0);
	}
    }

    /**
     * Returns Obstacle reference at specified index in activeObstacleList.
     *
     * @param index
     *            An integer index corresponding to a reference in the
     *            activeObstacleList.
     * @return A reference to an active Obstacle object.
     */
    public Obstacle getObstacle(int index) {
	return activeObstacleList[index];
    }

    /**
     * Returns Collectible reference at specified index in activeCollectibleList.
     *
     * @param index
     *            An integer index corresponding to a reference in the
     *            activeCollectibleList.
     * @return A reference to an active Collectible object.
     */
    public Collectible getCollectible(int index) {
	return activeCollectibleList[index];
    }

    /** Returns a list corresponding to the list of active Obstacles.
     *
     * @return A copy of the activeObstacleList
     */
    public Obstacle[] getActiveObstacleList()
    {
	return activeObstacleList;
    }

    /** Returns a list corresponding to the list of active Collectibles.
     *
     * @return A copy of the activeCollectibleList
     */
    public Collectible[] getActiveCollectibleList()
    {
	return activeCollectibleList;
    }
    
    /**
     * Returns integer value of the number of active Collectible. If none are
     * active, return will be 0.
     *
     * @return An integer corresponding to the number of active Collectible.
     */
    public int getNumCollectibles() {
	return numActiveCollectibles;
    }

    /**
     * Returns integer value of the number of active Obstacle. If none are active,
     * return will be 0.
     *
     * @return An integer corresponding to the number of active Obstacle.
     */
    public int getNumObstacles() {
	return numActiveObstacles;
    }

    /**
     * Moves position of active obstacles by an increment of 1.
     */
    public void updateActiveObstacles() {
	Obstacle[] activeList = getActiveObstacleList();
	Obstacle currentObstacle = new Obstacle(0);

	if (numActiveObstacles > 0) // Make sure there are more than 0 active.
	    {
		// Move all obstacles in list.
		for (int index = 0; index < activeList.length; index++) {
		    if(activeList[index]!=null){
			currentObstacle = getObstacle(index);
			if(currentObstacle.getXPosition() <= -200)
			    deleteObstacle(index);
			else
			    currentObstacle.moveItem();
		    }
		}
	    }
    }

    /**
     * Moves position of active collectibles by an increment of 1.
     */
    public void updateActiveCollectibles() {
	Collectible[] activeList = getActiveCollectibleList();
	Collectible currentCollectible = new Collectible(0);

	if (numActiveCollectibles > 0) // Make sure there are more than 0 active.
	    {
		// Move all Collectibles in list.
		for (int index = 0; index < activeList.length; index++) {
		    if(activeList[index]!=null){
			currentCollectible = getCollectible(index);
			if(currentCollectible.getXPosition() <= -200)
			    deleteCollectible(index);
			else
			    currentCollectible.moveItem();
		    }
		}
	    }
    }
}


