import java.util.Scanner;

/**
  * Version 30
  * 1. Auto formatted using netBeans IDE
  */
public class AnimationApplication {
    // These values set size of Arrays that contain active objects.

    private final int MAX_ACTIVE_OBSTACLES;
    private final int MAX_ACTIVE_PLAYERS;
    private final int MAX_ACTIVE_COLLECTIBLES;

    // Will be mainly used to control instantiation in main method.
    private final int MAX_OBJECTS_GAME;

    private int numActiveObstacles;
    private int numActivePlayers;
    private int numActiveCollectibles;

    // The following are the active object lists. They maintain references 
    // to objects that are currently active in the game.
    Obstacle[] activeObstacleList;
    Player[] activePlayerList;
    Collectible[] activeCollectibleList;

    /**
     * The following are the constructors for this class.
     */
    public AnimationApplication() {
        MAX_ACTIVE_OBSTACLES = 1;
        MAX_ACTIVE_PLAYERS = 1;
        MAX_ACTIVE_COLLECTIBLES = 1;
        MAX_OBJECTS_GAME = 3;

        numActiveObstacles = 0;
        numActivePlayers = 0;
        numActiveCollectibles = 0;

        activeObstacleList = new Obstacle[MAX_ACTIVE_OBSTACLES];
        activePlayerList = new Player[MAX_ACTIVE_PLAYERS];
        activeCollectibleList = new Collectible[MAX_ACTIVE_COLLECTIBLES];
    }

    public AnimationApplication(int maxPlayer, int maxObstacle,
                                int maxCollectible, int maxObject) {

        MAX_ACTIVE_OBSTACLES = maxObstacle;
        MAX_ACTIVE_PLAYERS = maxPlayer;
        MAX_ACTIVE_COLLECTIBLES = maxCollectible;
        MAX_OBJECTS_GAME = maxObject;

        numActiveObstacles = 0;
        numActivePlayers = 0;
        numActiveCollectibles = 0;

        activeObstacleList = new Obstacle[MAX_ACTIVE_OBSTACLES];
        activePlayerList = new Player[MAX_ACTIVE_PLAYERS];
        activeCollectibleList = new Collectible[MAX_ACTIVE_COLLECTIBLES];
    }

    /**
     * Places newly instantiated obstacles in the first free index of the
     * activeObstacleList.
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
     * Places newly instantiated players in the first free index of the
     * activePlayerList.
     */
    private void addToActivePlayerList(Player myObject) {
        for (int index = 0; index < activePlayerList.length; index++) {
            if (activePlayerList[index] == null) {
                activePlayerList[index] = myObject;
                break;
            }
        }
    }

    /**
     * Places newly instantiated collectibles in the first free index of the
     * activeCollectibleList.
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
     * Prints out contents of one of the Active Object Lists corresponding to
     * the String that the method takes as an argument. Valid String arguments
     * are as follows: "Obstacle","Collectible", and "Player". Case matters.
     */
    public void printActiveObjectList(String whichList) {
        boolean isObstacle = whichList.equals("Obstacle");
        boolean isPlayer = whichList.equals("Player");
        boolean isCollectible = whichList.equals("Collectible");

        // Object list allows me store any of my Active Object Lists in this 
        // variable
        Object[] listToPrint; 
        
        listToPrint = new Object[1]; 
        String listName = "";
        String typeName = "";
        int numActive = 0;

        if (isObstacle) {
            listToPrint = activeObstacleList;
            listName = "activeObstacleList";
            typeName = "Obstacle";
            numActive = numActiveObstacles;
        } else if (isPlayer) {
            listToPrint = activePlayerList;
            listName = "activePlayerList";
            typeName = "Player";
            numActive = numActivePlayers;
        } else if (isCollectible) {
            listToPrint = activeCollectibleList;
            listName = "activeCollectibleList";
            typeName = "Collectible";
            numActive = numActiveCollectibles;
        } else {
            System.out.println("ERROR: Please enter a valid String argument " + '\n'
                               + "for the printActiveObjectList method: " + "'" + '\n'
                               + whichList + "'" + "is not a valid argument.");
            System.exit(0);
        }

        System.out.println("_________________________________________________");
        System.out.print(listName + ": [");
        // Step through list and print.
        for (int index = 0; index < listToPrint.length; index++)
        {
            if (listToPrint[index] != null) // If index not null print name.
            {
                System.out.print(listToPrint[index].getClass().getName());
            } else // If index is null print that index as is.
            {
                System.out.print(listToPrint[index]);
            }
            // Check each time so we don't print comma for final index.
            if (index != listToPrint.length - 1) 
            {
                System.out.print(" , ");
            }
        }
        System.out.println("]");
        System.out.println("");

        System.out.println("Number of active " + typeName + "(s) is " + '\n' 
                            + numActive);
        System.out.println("_________________________________________________");
    }

    /**
     * Instantiates an Obstacle at the specified x and y positions on the map.
     * Takes two integers representing the x and y positions as arguments.
     */
    public void makeObstacle(int ypos) {
        int maximumActive = MAX_ACTIVE_OBSTACLES;
        int currentActive = numActiveObstacles;

        if (currentActive < maximumActive
                && currentActive >= 0) {
            Obstacle obstacleObject = new Obstacle(ypos, 1, 1);
            numActiveObstacles++;
            addToActiveObstacleList(obstacleObject);

            // NOTE: Since obstacleObject is local to this method,
            // when this method is complete the only reference
            // to the new Obstacle object is the index we set it to in the
            // Obstacle[] instance variable activeObstacleList. Therefore
            // when we remove this reference in deleteObstacle, Java will
            // reallocate the memory for the unreferenced object, preventing
            // garbage data building up. Same goes for other make methods.
        }
    }

    /**
     * Instantiates a Player at the specified x and y positions on the map.
     * Takes two integers representing the x and y positions as arguments.
     */
    public void makePlayer(int xpos, int ypos) {
        int maximumActive = MAX_ACTIVE_PLAYERS;
        int currentActive = numActivePlayers;

        if (currentActive < maximumActive
                && currentActive >= 0) {
            Player playerObject = new Player();
            numActivePlayers++;
            addToActivePlayerList(playerObject);
        }
    }

    /**
     * Instantiates a Collectible at the specified x and y positions on the map.
     * Takes two integers representing the x and y positions as arguments.
     */
    public void makeCollectible(int yPos) {
        int maximumActive = MAX_ACTIVE_COLLECTIBLES;
        int currentActive = numActiveCollectibles;

        if (currentActive < maximumActive
                && currentActive >= 0) {
            Collectible collectibleObject = new Collectible(yPos);
            numActiveCollectibles++;
            addToActiveCollectibleList(collectibleObject);
        }
    }

    /**
     * Removes obstacle at specified index from activeObstacleList by putting a
     * null value in its place.
     */
    private void deleteObstacle(int index) {
        if (index < activeObstacleList.length && index >= 0) {
            // So we can't make numActivePlayers a negative value by
            // making null indexes null and decrementing numActive.
            if (activeObstacleList[index] != null) 
            {					   
                activeObstacleList[index] = null;
                numActiveObstacles = numActiveObstacles - 1;
            } else {
                System.out.println("ILLEGAL ACTION: Tried to delete reference " + '\n'
                                   + "at an empty index of the " + '\n'
                                   + "activeObstacleList" + "while using " + '\n'
                                   + "deleteObstacle()");
                System.exit(0);
            }
        } else {
            System.out.println("ILLEGAL ACTION: Tried to delete an index out " + '\n'
                               + "of the range of activeObstacleList " + '\n'
                               + "while using deleteObstacle()");
            System.exit(0);
        }
    }

    /**
     * Removes player at specified index from activePlayerList by putting a null
     * value in its place.
     */
    private void deletePlayer(int index) {
        if (index < activePlayerList.length && index >= 0) {
            // So we can't make numActivePlayers a negative value by
            // making null indexes null and decrementing numActive.
            if (activePlayerList[index] != null) 
            {								  
                activePlayerList[index] = null;
                numActivePlayers = numActivePlayers - 1;
            } else {
                System.out.println("ILLEGAL ACTION: Tried to delete reference " + '\n'
                                   + "at an empty index of the " + '\n'
                                   + "activePlayerList while using " + '\n'
                                   + "deletePlayer()");
                System.exit(0);
            }
        } else {
            System.out.println("ILLEGAL ACTION: Tried to delete an index out " + '\n'
                               + "of the range of activePlayerList while using " + '\n'
                               + "deletePlayer()");
            System.exit(0);
        }
    }

    /**
     * Removes obstacle at specified index from activeObstacleList by putting a
     * null value in its place.
     */
    private void deleteCollectible(int index) {
        // So we can't make numActiveCollectibles a negative value by
        // making null indexes null and decrementing numActive.
        if (index < activeCollectibleList.length && index >= 0) {
            if (activeCollectibleList[index] != null) 
            {
                activeCollectibleList[index] = null;
                numActiveCollectibles = numActiveCollectibles - 1;
            } else {
                System.out.println("ILLEGAL ACTION: Tried to delete reference " + '\n'
                                   + "at an empty index of the " + '\n'
                                   + "activeCollectibleList while using " + '\n'
                                   + "deleteCollectible()");
                System.exit(0);
            }
        } else {
            System.out.println("ILLEGAL ACTION: Tried to delete an index out " + '\n'
                               + "of the range of activeCollectibleList while " + '\n'
                               + "using deleteCollectible()");
            System.exit(0);
        }
    }

    /**
     * Returns Player object at specified index in activePlayerList.
	  *
     */
    public Player getPlayer(int index) {
        return activePlayerList[index];
    }

    /**
     * Returns Obstacle object at specified index in activeObstacleList.
     */
    public Obstacle getObstacle(int index) {
        return activeObstacleList[index];
    }

    /**
     * Returns Collectible object at specified index in activeCollectibleList.
     */
    public Collectible getCollectible(int index) {
        return activeCollectibleList[index];
    }

    /**
     * Returns integer value of the number of active Collectibles. If none are
     * active, return will be 0. Should be used to ensure proper input for
     * stepThroughActive (ie: activeObject > 0).
     */
    public int getNumCollectibles() {
        return numActiveCollectibles;
    }

    /**
     * Returns integer value of the number of active Obstacles. If none are
     * active, output will be 0. Should be used to ensure proper input for
     * stepThroughActive (ie: activeObject > 0).
     */
    public int getNumObstacles() {
        return numActiveObstacles;
    }

    /**
     * Returns integer value of the number of active Players. If none are
     * active, return will be 0. Should be used to ensure proper input for
     * stepThroughActive (ie: activeObject > 0).
     */
    public int getNumPlayers() {
        return numActivePlayers;
    }

    /**
     * Returns an integer corresponding to the size of the activeObstacleList.
     */
    public int activeObstacleLength() {
        return MAX_ACTIVE_OBSTACLES;
    }

    /**
     * Returns an integer corresponding to the size of the activeCollectibleList
     */
    public int activeCollectibleLength() {
        return MAX_ACTIVE_COLLECTIBLES;
    }

    /**
     * Returns an integer corresponding to the size of the activePlayerList
     */
    public int activePlayerLength() {
        return MAX_ACTIVE_PLAYERS;
    }

    /**
     * Steps through any activeObjectList given as a String argument and returns
     * a list of the indexes which are not null (the ones which are active). An
     * ERROR message appears and system exits if you try to step through a list
     * with only null elements. Can be used with getPlayer(), getObstacle() and
     * getCollectible() to get access to active objects by generating a list of
     * active indexes corresponding to one of the activeObjectLists and using
     * those indexes as arguments. Valid arguments for this method include
     * "Obstacle", "Player", and "Collectible". Case matters.
     */
    public int[] stepThroughActive(String whichList) {
        Object[] objectList;
        // Array size is arbitrary just need to initialize.
        objectList = new Object[0]; 

        if (whichList.equals("Player")) {
            objectList = activePlayerList;
        } else if (whichList.equals("Obstacle")) {
            objectList = activeObstacleList;
        } else if (whichList.equals("Collectible")) {
            objectList = activeCollectibleList;
        } else {
            System.out.println("ERROR: Please enter a valid String argument " + '\n'
                               + "for the stepThroughActive method: " + "'" + '\n'
                               + whichList + "'" + "is not a valid argument.");
            System.exit(0);
        }

        int arraySize = 0;
        int[] activeIndexList;
        int activeIndexIndex = 0;

        // First we'll use a loop to check how many indexes
        // are actually active so we can set size of int[]
        // to store indexes.
        for (int objectIndex = 0; objectIndex < objectList.length; objectIndex++) {
            if (objectList[objectIndex] != null) {
                arraySize++;
            }
        }

        activeIndexList = new int[arraySize];

        // Make sure there is at least one active object.
        if (arraySize <= 0) {
            System.out.println("ERROR: Tried to step through list with only" + '\n'
                              + "null indexes while using stepThroughActive()");
            System.exit(0);
        }

        // Now that we have the correct array size we'll run
        // through the object list again, but append the indexes
        // for active objects into the activeIndexList.
        for (int objectIndex = 0; objectIndex < objectList.length; objectIndex++) {
            if (objectList[objectIndex] != null) {
                activeIndexList[activeIndexIndex] = objectIndex;
                activeIndexIndex++;
            }
        }

        return activeIndexList;
    }

    /**
     * Moves position of active obstacles.
     */
    private void moveActiveObstacles() {
        int[] activeObstacleIndex = new int[0];
        Obstacle currentObstacle = new Obstacle(0);

        if (numActiveObstacles > 0) // Make sure there are more than 0 active.
        {
            // Get indexes of active obstacles.
            activeObstacleIndex = stepThroughActive("Obstacle");

            // Move obstacles at those indexes.
            for (int index = 0; index < activeObstacleIndex.length; index++) {
                currentObstacle = getObstacle(index);
                currentObstacle.moveObstacle();
            }
        }
    }

    /**
     * Moves position of active collectibles.
     */
    private void moveActiveCollectibles() {
        int[] activeCollectibleIndex = new int[0];
        Collectible currentCollectible = new Collectible(0);

        if (numActiveCollectibles > 0) // Make sure there are more than 0 active.
        {
            // Get indexes of active collectibles.
            activeCollectibleIndex = stepThroughActive("Collectible");

            // Move collectibles at those indexes.
            for (int index = 0; index < activeCollectibleIndex.length; index++) {
                currentCollectible = getCollectible(index);
                currentCollectible.moveCollectible();
            }
        }
    }

    /**
     * The following are a few notes about the main method implementation for
     * DEMO 1.
     *
     * 1. The demo will consist of a series of screen outputs corresponding to
     * active object positions at each imagined frame of the game, followed by a
     * prompt for user input that that will be used to control player movement.
     *
     * 2. Our game grid has a 30x10 nodes.
     *
     * 3. Because obstacles and collectibles will always start at x co-ordinate
     * 30 (the right side of the screen), we will only decide their y
     * co-ordinate when instantiating.
     *
     * 4. Player movement will be restricted to jumping between two positions:
     * (0,10) and (0,0).
     *
     * 5. Every frame, active Obstacles and Collectibles will move one step
     * closer to the left boundary of the grid, but because collision detection
     * has not been yet implemented, they will continue moving past the left
     * boundary into negative co-ordinates.
     */
    public static void main(String[] args) {
        AnimationApplication gameEngine = new AnimationApplication(2, 2, 2, 6);

        TextOutput printer = new TextOutput();
        Scanner keyboard = new Scanner(System.in);

        // Instantiate objects for Demo 1.
        gameEngine.makePlayer(0, 0);
        gameEngine.makeObstacle(0);
        gameEngine.makeObstacle(5);
        gameEngine.makeCollectible(3);
        gameEngine.makeCollectible(2);

        // The first instantiated player will always be
        // be at index 0 therefore we can grab its reference 
        // directly.
        Player playerOne = gameEngine.getPlayer(0); 

        char userInput; // Will be used to handle input for player movements.

        while (playerOne.IsAlive()) // Run game as long as playerOne is alive.
        {

            // Game Instructions
            System.out.println("");
            System.out.println("Enter 'w' to move Player Up.");
            System.out.println("Enter 's' to move Player Down.");
            System.out.println("Enter any other character to remain still.");

            // Print out current positions of active objects.
            System.out.println("");
            printer.PrintActivePositions(gameEngine);

            // Move NPC active objects.
            gameEngine.moveActiveCollectibles();
            gameEngine.moveActiveObstacles();

            // Get user input for player movements.
            System.out.println("");
            System.out.println("");
            System.out.print("YOUR MOVE:");
            userInput = keyboard.next().charAt(0);
            playerOne.Move(userInput);

            // To separate from next frame output.
            System.out.println("____________________________________________");
        }
    }
}
