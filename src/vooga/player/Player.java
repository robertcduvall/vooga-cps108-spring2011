package vooga.player;

import com.golden.gamedev.object.Sprite;

import java.util.ArrayList;

public abstract class Player
{
	/**
	 * @author Kevin Tao and Andrea Scripa
	 */
	
    //Write up a design document. put on the wiki.
    //Decide on names for new level starting or keyboard input
    
    //Set up events listener - tells us about key inputs
    //On the action performed, update all sprites associated with this player.
    //Have an "add" method with ArrayList that Sprites can add themselves to.
    //Maybe do another ArrayList with 1's and 0's to know which sprites should be active
    
    
    // pass the playfield and an instance of event manager (don't need to create our own)
            // or an instance of VoogaGame - they have the same methods
    
    // Game state tells us when to add ourselves. (Listen to them!)
    // Put player on playfield.
    
    // Or allow sprite to be null...
    
    // Have player have more than one sprite?
    // One "player" dynamically controls 2 players?  switch() method.
    
    //Events as strings - can dynamically switch between them.
	
	protected int playerId;
	private long timeOfLastChange;
	private long timeOfLastChangeQuery;
	private static int nextPlayerId= 1;
	private boolean active = true;
	private ArrayList<Sprite> mySprites = new ArrayList<Sprite>();
	
	public Player(){
		playerId = nextPlayerId;
		nextPlayerId ++;
	}
	
	public abstract void updateStats();
	
	public void addSprite(Sprite s)
	{
	    mySprites.add(s);
	}
	
	public void updateSprites(String command)
	{
	    for(Sprite s : mySprites)
	    {
	        //Need a performCommand method in Sprite that uses reflection to interpret
	        //which method to call so the command will be performed.
	        // s.performCommand(command);
	    }
	}
	
	//Developer decides how to map events to commands that are sent to the Sprites.
	//Introduces a level of abstraction: this way events can come from multiple sources 
	//but still map to the same command.
	public abstract String eventToCommand(String event);
	
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
	
	public boolean getActive()
	{
	    return this.active;
	}
	
	public void setActive(boolean b)
	{
	    this.active = b;
	}
}
