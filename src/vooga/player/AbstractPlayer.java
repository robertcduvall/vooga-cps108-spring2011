package vooga.player;

public abstract class AbstractPlayer implements Cloneable
{
	/**
	 * @author Kevin Tao and Andrea Scripa
	 * 
	 * This is the most general player, that all other players derive from.
	 */
	
	/*
	 * If needed, one extension is to add a history of commands and events
	 */
    // pass the playfield and an instance of event manager (don't need to create our own)
            // or an instance of VoogaGame - they have the same methods
    // Game state tells us when to add ourselves. (Listen to them!)
    // Events tells us about key inputs
    // Put player on playfield.
    // We'll still probably have to do some calling of update and render
    // Set up a meeting with stats.
    
    // Team commands - apply to one or to all?
    
    // Default player a tiny see-through sprite?
    // Or allow sprite to be null...
    
    // Have player have more than one sprite?
    // One "player" dynamically controls 2 players?  switch() method.
    
    //Events as strings - can dynamically switch between them.
    
    //Need to meet with Stats group and integrate our class with Julian's things
	
	protected int playerId;
	private long timeOfLastChange;
	private long timeOfLastChangeQuery;
	private static int nextPlayerId = 1;
	
	public AbstractPlayer(){
		playerId = nextPlayerId;
		nextPlayerId ++;
	}
	
	public abstract int compareTo(AbstractPlayer p);
	
	public abstract AbstractPlayer clone();  //Taken care of by Julian
	
	public abstract boolean equals(AbstractPlayer p);
	
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
	
//	public boolean isActive()
//	{
//	    return true;
//	}
	
	// decide on names for new level starting or keyboard input
	
	//destroy method or player
	//each player as a mini team (team of 1)
	// think in terms or events and in terms of who's going to fire it.
	
	//write up a design document. put on the wiki.
	
	// how does player interact with level?
	// how does pac man eat his dots?  that's deep...
	
}
