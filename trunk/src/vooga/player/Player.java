package vooga.player;

import com.golden.gamedev.object.Sprite;

import java.util.ArrayList;
import vooga.core.VoogaGame;

public abstract class Player
{
	/**
	 * @author Andrea Scripa
	 */
    
    //Store its wrapper
	
    //Write up a design document. put on the wiki.
    //Decide on names for new level starting or keyboard input
    
    //Set up events listener - tells us about key inputs
    //On the action performed, update all sprites associated with this player.
    //Have an "add" method with ArrayList that Sprites can add themselves to.
    
    // Game state tells us when to add ourselves. (Listen to them!)
    // Put player on playfield.
    
    //Events as strings - can dynamically switch between them.
	
	protected int playerId;
	private static int nextPlayerId= 1;
	private boolean active = true;
	private ArrayList<Sprite> mySprites = new ArrayList<Sprite>();
	private VoogaGame gameInstance;
	
	public Player(){
		playerId = nextPlayerId;
		nextPlayerId ++;
	}
	
	//Alternately, pass the playfield and an instance of EventManager.
	//DJ is supposed to get back to me on this.
	public void getGame(VoogaGame g)
	{
	    gameInstance = g;
	}
	
	public abstract void addEventListeners();
	
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
	
	public boolean getActive()
	{
	    return this.active;
	}
	
	public void setActive(boolean b)
	{
	    this.active = b;
	}
}
