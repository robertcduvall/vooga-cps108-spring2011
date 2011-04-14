package vooga.player;

public abstract class Player
{
	/**
	 * @author Kevin Tao and Andrea Scripa
	 */
	
	//How to deal with AI?
    
    //Set up events listener - tells us about key inputs
    //On the action performed, update all sprites associated with this player.
    //Have an "add" method with ArrayList that Sprites can add themselves to.
    //Maybe do another ArrayList with 1's and 0's to know which sprites should be active
    
    
    // pass the playfield and an instance of event manager (don't need to create our own)
            // or an instance of VoogaGame - they have the same methods
    
    // Game state tells us when to add ourselves. (Listen to them!)
    // Put player on playfield.
    
    // Set up a meeting with stats.
    
    // Or allow sprite to be null...
    
    // Have player have more than one sprite?
    // One "player" dynamically controls 2 players?  switch() method.
    
    //Events as strings - can dynamically switch between them.
	
	protected int playerId;
	private long timeOfLastChange;
	private long timeOfLastChangeQuery;
	private static int nextPlayerId = 1;
	
	public Player(){
		playerId = nextPlayerId;
		nextPlayerId ++;
	}
	
	public abstract int compareTo(Player p);
	
	public abstract boolean equals(Player p);
	
	// Requested by the Replay group.
	public boolean hasChanged(){
		
		if(timeOfLastChange > timeOfLastChangeQuery){
			timeOfLastChangeQuery = System.nanoTime();
			return true;
		}else{
			timeOfLastChangeQuery = System.nanoTime();
			return false;
		}
	}
	
	public void changeOccurred(){
		timeOfLastChange = System.nanoTime();
	}
	
	// decide on names for new level starting or keyboard input
	
	//destroy method or player
	// think in terms of events and in terms of who's going to fire it.
	
	//write up a design document. put on the wiki.
}
