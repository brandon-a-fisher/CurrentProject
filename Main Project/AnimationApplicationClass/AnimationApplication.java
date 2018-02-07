// By Mark Tremblay

// 

import java.util.Arrays;
import java.util.Scanner;

/**
Version 27_8 February 6th
*/
public class AnimationApplication
{
	// These values set size of Arrays that contain active objects.
	private final int MAX_ACTIVE_OBSTACLES; 
	private final int MAX_ACTIVE_PLAYERS;
	private final int MAX_ACTIVE_COLLECTIBLES;
	
	// Will be mainly used to control instantiation in main method.
	private final int MAX_OBJECTS_GAME; 
	
	private int numActiveObstacles;
	private int numActivePlayers;
	private int numActiveCollectibles;
	
	// The following are the active object lists. They maintain references to objects that are currently
	// active in the game.
	Obstacle[] activeObstacleList; 
	Player[] activePlayerList;
	Collectible[] activeCollectibleList; 
	
	/**
	The following are the constructors for this class. 
	*/
	
	public AnimationApplication()
	{
		MAX_ACTIVE_OBSTACLES = 1;
		MAX_ACTIVE_PLAYERS = 1;
		MAX_ACTIVE_COLLECTIBLES = 1;
		MAX_OBJECTS_GAME = 3;
		
		numActiveObstacles = 0;
	    numActivePlayers = 0;
	    numActiveCollectibles= 0;
		
		activeObstacleList = new Obstacle[MAX_ACTIVE_OBSTACLES]; 
		activePlayerList = new Player[MAX_ACTIVE_PLAYERS];
		activeCollectibleList= new Collectible[MAX_ACTIVE_COLLECTIBLES]; 
	}
	
	public AnimationApplication(int maxObstacle, int maxPlayer, int maxCollectible, int maxObject)
	{
		
		MAX_ACTIVE_OBSTACLES = maxObstacle;
		MAX_ACTIVE_PLAYERS = maxPlayer;
		MAX_ACTIVE_COLLECTIBLES = maxCollectible;
		MAX_OBJECTS_GAME = maxObject;
		
		numActiveObstacles = 0;
	    numActivePlayers = 0;
	    numActiveCollectibles= 0;
		
		activeObstacleList = new Obstacle[MAX_ACTIVE_OBSTACLES]; 
		activePlayerList = new Player[MAX_ACTIVE_PLAYERS];
		activeCollectibleList= new Collectible[MAX_ACTIVE_COLLECTIBLES];
		
	}
	
	/**
	  *Places newly instantiated obstacles in the first free index of the activeObstacleList.
	  */
	private void addToActiveObstacleList(Obstacle myObject)
	{
		for(int index=0; index < activeObstacleList.length; index++)
		{
			if(activeObstacleList[index] == null)
			{
				activeObstacleList[index] = myObject;
				break;
			}
		}
	}
	
   /** 
	 * Places newly instantiated players in the first free index of the activePlayerList.
	 */
	private void addToActivePlayerList(Player myObject)
	{
		for(int index=0; index < activePlayerList.length; index++)
		{
			if(activePlayerList[index] == null)
			{
				activePlayerList[index] = myObject;
				break;
			}
		}
	}
	
	/** 
	  *Places newly instantiated collectibles in the first free index of the activeCollectibleList.
	  */
	private void addToActiveCollectibleList(Collectible myObject)
	{
		for(int index=0; index < activeCollectibleList.length; index++)
		{
			if(activeCollectibleList[index] == null)
			{
				activeCollectibleList[index] = myObject;
				break;
			}
		}
	}

	/** Prints out contents of one of the Active Object Lists corresponding 
	  * to the String that the method takes as an argument. Valid String
      * arguments are as follows: "Obstacle","Collectible", and "Player".
	  * Case matters.
	  */
	public void printActiveObjectList(String whichList)
	{
		boolean isObstacle = whichList.equals("Obstacle");
		boolean isPlayer = whichList.equals("Player");
		boolean isCollectible = whichList.equals("Collectible");
		
		Object[]listToPrint; // Object list allows me store any of my Active Object Lists in this variable.
		listToPrint = new Object[1]; // Arbitrary Array size. Just need to initialize
		String listName = ""; 
		String typeName = "";
		int numActive = 0;
		
		if(isObstacle)
		{
			listToPrint = activeObstacleList;
			listName = "activeObstacleList";
			typeName = "Obstacle";
			numActive = numActiveObstacles;
		}
		
		else if(isPlayer)
		{
			listToPrint = activePlayerList;
			listName = "activePlayerList";
			typeName = "Player";
			numActive = numActivePlayers;
			
		}
		
		else if(isCollectible)
		{
			listToPrint = activeCollectibleList;
			listName = "activeCollectibleList";
			typeName = "Collectible";
			numActive = numActiveCollectibles;
		}
		
		else
		{
			System.out.println("ERROR: Please enter a valid String argument for");
			System.out.println("the printActiveObjectList method: " + "'" + whichList + "'");
			System.out.println("is not a valid argument.");
			System.exit(0);
		}
		
		System.out.println("________________________________________________________");
		System.out.print(listName + ": ["); 
		for(int index = 0; index < listToPrint.length; index++) // Step through list and print.
		{
			if(listToPrint[index] != null) // If index not null print name.
			{	
				System.out.print(listToPrint[index].getClass().getName());
			}
			
			else // If index is null print that index as is.
			{
				System.out.print(listToPrint[index]);
			}
			
			if(index != listToPrint.length-1) // Check each time so we don't print comma for final index.
			{
				System.out.print(" , ");
			}
		}
		System.out.println("]");
		System.out.println("");
		
		System.out.println("Number of active " + typeName + "(s) is " +  numActive);
		System.out.println("________________________________________________________");
	}
	
	/** Instantiates an Obstacle at the specified x and y positions on the map. Takes
	  * two integers representing the x and y positions as arguments.
	  */
	public void makeObstacle(int ypos)
	{
		int maximumActive = MAX_ACTIVE_OBSTACLES;
		int currentActive = numActiveObstacles;
		
		if( currentActive < maximumActive && 
		    currentActive >= 0)
		{	
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
	  * Instantiates a Player at the specified x and y positions on the map. Takes
	  * two integers representing the x and y positions as arguments.
	  */
	public void makePlayer(int xpos, int ypos) 
	{
		int maximumActive = MAX_ACTIVE_PLAYERS;
		int currentActive = numActivePlayers;
		
		if( currentActive < maximumActive && 
		    currentActive >= 0)
		{	
			Player playerObject = new Player();
			numActivePlayers++;
			addToActivePlayerList(playerObject);
		}
	}
	
   /** Instantiates a Collectible at the specified x and y positions on the map. Takes
	 * two integers representing the x and y positions as arguments.
	 */
	public void makeCollectible(int yPos) 
	{
		int maximumActive = MAX_ACTIVE_COLLECTIBLES;
		int currentActive = numActiveCollectibles;
		
		if( currentActive < maximumActive && 
		    currentActive >= 0)
		{	
			Collectible collectibleObject = new Collectible(yPos);
			numActiveCollectibles++;
			addToActiveCollectibleList(collectibleObject);
		}
	}
	
	/**  
	  * Removes obstacle at specified index from activeObstacleList by putting a null value in its place. 
	  */
	private void deleteObstacle(int index) 
	{
		if (index < activeObstacleList.length && index >= 0)
		{
			if(activeObstacleList[index]!=null) // So we can't make numActivePlayers a negative value by
			{								    // making null indexes null and decrementing numActive.
				activeObstacleList[index] = null;
				numActiveObstacles = numActiveObstacles - 1;
			}
			
			else
			{
				System.out.println("ILLEGAL ACTION: Tried to delete reference at an empty index of the activeObstacleList");
				System.out.println("while using deleteObstacle()");
				System.exit(0);
			}
		}
		
		else
		{
			System.out.println("ILLEGAL ACTION: Tried to delete an index out of the range of activeObstacleList");
			System.out.println("while using deleteObstacle()");
			System.exit(0);
		}
	}
	
   /** 
	 * Removes player at specified index from activePlayerList by putting a null value in its place. 
	 */
	private void deletePlayer(int index) 
	{
		if (index < activePlayerList.length && index >= 0)
		{
			if(activePlayerList[index]!=null) // So we can't make numActivePlayers a negative value by
			{								  // making null indexes null and decrementing numActive.
				activePlayerList[index] = null;
				numActivePlayers = numActivePlayers - 1;
			}
			
			else
			{
				System.out.println("ILLEGAL ACTION: Tried to delete reference at an empty index of the activePlayerList");
				System.out.println("while using deletePlayer()");
				System.exit(0);
			}
		}	
		
		else
		{
			System.out.println("ILLEGAL ACTION: Tried to delete an index out of the range of activePlayerList");
			System.out.println("while using deletePlayer()");
			System.exit(0);
		}
	}
	
	/**  
	  * Removes obstacle at specified index from activeObstacleList by putting a null value in its place. 
	  */
	private void deleteCollectible(int index) 
	{
		if (index < activeCollectibleList.length && index >= 0)
		{
			if(activeCollectibleList[index]!=null) // So we can't make numActiveCollectibles a negative value by
												   // making null indexes null and decrementing numActive.
			{
				activeCollectibleList[index] = null;
				numActiveCollectibles = numActiveCollectibles - 1;
			}
			
			else
			{
				System.out.println("ILLEGAL ACTION: Tried to delete reference at an empty index of the activeCollectibleList");
				System.out.println("while using deleteCollectible()");
				System.exit(0);
			}
		}	
		
		else
		{
			System.out.println("ILLEGAL ACTION: Tried to delete an index out of the range of activeCollectibleList");
			System.out.println("while using deleteCollectible()");
			System.exit(0);
		}
	}
	
	/** 
	  * Checks all active Player objects for collisions with members of a valid active object type, as specified
	  * by a String argument, and takes necessary action if this is true. As of the current this method is not fully 
	  * implemented. Valid arguments for String include "Obstacle", and "Collectible". Case matters.
	  */
	private void registerCollisionPlayer(String activeObjectType)
	{	
		if(activeObjectType == "Obstacle")
		{
			if (numActivePlayers > 0 & numActiveObstacles > 0)
			{
				for(int playerIndex = 0; playerIndex < activePlayerList.length; playerIndex++) // Run through each index of activePlayerList
				{
					//getPlayer(playerIndex).hasCollisionObstacle <AnimationApplication>
					
										
				}
			}
		}
		
		else if(activeObjectType == "Collectible")
		{
			if (numActivePlayers > 0 & numActiveCollectibles > 0)
			{
				for(int playerIndex = 0; playerIndex < activePlayerList.length; playerIndex++) // Run through each index of activePlayerList
				{
					//getPlayer(playerIndex).hasCollisionCollectible <gameEngine> 
					
									
				}
			}
		}
		
		else
		{
			System.out.println("ERROR: Please enter a valid String argument for");
			System.out.println("the registerPlayerCollision method: " + "'" + activeObjectType + "'");
			System.out.println("is not a valid argument.");
			System.exit(0);	
		}
	}
	
	/** 
	  * Returns Player object at specified index in activePlayerList.
	  **/
	public Player getPlayer(int index)
	{
		return activePlayerList[index];
	}
	
	/** 
	  *Returns Obstacle object at specified index in activeObstacleList.
	  */
	public Obstacle getObstacle(int index)
	{
		return activeObstacleList[index];
	}
	
	/** 
	  * Returns Collectible object at specified index in activeCollectibleList.
	  */
	public Collectible getCollectible(int index)
	{
		return activeCollectibleList[index];
	}
	
	/** 
	  * Returns integer value of the number of active Collectibles.
	  * If none are active, return will be 0. Should be used to ensure
	  * proper input for stepThroughActive (ie: activeObject > 0).
	  */
	public int getNumCollectibles()
	{
		return numActiveCollectibles;
	}
	
	/** 
	  * Returns integer value of the number of active Obstacles.
	  * If none are active, output will be 0. Should be used to ensure
	  * proper input for stepThroughActive (ie: activeObject > 0).
	  */
	public int getNumObstacles()
	{
		return numActiveObstacles;
	}
	
	/** 
	  * Returns integer value of the number of active Players.
	  * If none are active, return will be 0. Should be used to ensure
	  * proper input for stepThroughActive (ie: activeObject > 0).
	  */
	public int getNumPlayers()
	{
		return numActivePlayers;
	}
	
	/** 
	  *Returns an integer corresponding to the size of the activeObstacleList.
	  */
	public int activeObstacleLength()
	{
		return MAX_ACTIVE_OBSTACLES;
	}
	
	/** 
	  *Returns an integer corresponding to the size of the activeCollectibleList
	  */
	public int activeCollectibleLength()
	{
		return MAX_ACTIVE_COLLECTIBLES;
	}
	
	/** 
	  *Returns an integer corresponding to the size of the activePlayerList
	  */
	public int activePlayerLength()
	{
		return MAX_ACTIVE_PLAYERS;
	}
	
	/**
	  * Steps through any activeObjectList given as a String
      *	argument and returns a list of the indexes which are not null
	  * (the ones which are active). An ERROR message appears
	  * and system exits if you try to step through a list with 
	  * only null elements. Can be used with getPlayer(), getObstacle()
      *	and getCollectible() to get access to active objects by generating 
	  * a list of active indexes corresponding to one of the activeObjectLists 
	  * and using those indexes as arguments. Valid arguments for this method 
	  * include "Obstacle", "Player", and "Collectible". Case matters.
	  */
	public int[] stepThroughActive(String whichList)
	{
		Object[] objectList;
		objectList = new Object[0]; // Array size is arbitrary just need to initialize.
		
		if(whichList.equals("Player"))
			objectList = activePlayerList;
		
		else if(whichList.equals("Obstacle"))
			objectList = activeObstacleList;
			
		else if(whichList.equals("Collectible"))
			objectList = activeCollectibleList;
		
		else
		{
			System.out.println("ERROR: Please enter a valid String argument for");
			System.out.println("the stepThroughActive method: " + "'" + whichList + "'");
			System.out.println("is not a valid argument.");
			System.exit(0);
		}
			
		int arraySize = 0;
		int[] activeIndexList;
		int activeIndexIndex = 0;
		
		// First we'll use a loop to check how many indexes
		// are actually active so we can set size of int[]
		// to store indexes.
		for(int objectIndex = 0; objectIndex < objectList.length; objectIndex++)
		{
			if(objectList[objectIndex]!=null)
			{
				arraySize++;
			}
		}
		
		activeIndexList = new int[arraySize];
		
		// Make sure there is at least one active object.
		if(arraySize <= 0)
		{
			System.out.println("ERROR: Tried to step through list with only");
			System.out.println("null indexes while using stepThroughActive()");
			System.exit(0);
		}
		
		// Now that we have the correct array size we'll run
		// through the object list again, but append the indexes
		// for active objects into the activeIndexList.
		for(int objectIndex = 0; objectIndex < objectList.length; objectIndex++)
		{
			if(objectList[objectIndex]!=null)
			{
				activeIndexList[activeIndexIndex] = objectIndex;
				activeIndexIndex++;
			}
		}

		return activeIndexList;
	}
	
	public static void main(String[]args)
	{
		
		
	}
}
