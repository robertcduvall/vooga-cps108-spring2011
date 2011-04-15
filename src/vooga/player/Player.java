package vooga.player;

import java.util.ArrayList;
import vooga.core.GameEntity;


/**
 * A developer can make one or more specialized players for his game (ex: PacManPlayer),
 * which should extend this abstract class.
 * 
 * The wrappers for this class were designed such that Player can implement more than 
 * one.  This would allow a developer both to give a Player AI characteristics and to
 * allow the Player to be controlled by user input.
 * 
 * Note: Most events should be listened for in Player's wrappers or GameEntities; however
 * it may be appropriate to add event listeners directly to Player if the developer wishes
 * to change out a player's AI functionality during the game, for example.  One could also 
 * do this to remove a wrapper and its functionality part-way through the game.
 * 
 */
public abstract class Player
{
	/**
	 * @author Andrea Scripa
	 */
	
	public static enum PlayerType
	{
	    HUMAN_PLAYER,
	    AI_PLAYER,
	    NETWORK_PLAYER
	}
    
    private ArrayList<GameEntity> myEntities = new ArrayList<GameEntity>();
	private ArrayList<AbstractPlayerWrapper> myWrappers = 
	    new ArrayList<AbstractPlayerWrapper>();
	
	public Player(){}
	
	/**
     * Method to establish a listener to the game state. This is useful for knowing
     * when to add Player's sprite(s) to the playfield because this occurs at the beginning 
     * of the game or at the start of a new level.
     */
	public abstract void gameStateListener();
	
	/**
     * Method to update the stats a given Player may have.  It is useful both for updating
     * the Player's record of its own stats and for sending these stats to the 
     * vooga.stats package for further processing and/or storage.
     */
	public abstract void updateStats();
	
	/**
     * Adds a GameEntity (a group of Sprites with same properties and same purpose in the 
     * game) to Player.
     * A Player always has one or more GameEntities, but not all GameEntities must be 
     * associated with a Player.
     */
	public void addEntity(GameEntity ge)
	{
	    myEntities.add(ge);
	}
	
	/**
     * Sends a command (which is found by parsing input to the Player) to all of Player's
     * GameEntities.  These Entities can then decide what to do with this command and if
     * it should be sent on to their Sprites.
     */
	public void updateEntities(String command)
	{
	    for(GameEntity ge : myEntities)
	    {
	        ge.interpretCommand(command);
	    }
	}
	
	/**
     * Returns all the GameEntities associate with Player.
     */
	public ArrayList<GameEntity> getEntities()
	{
	    return myEntities;
	}
	
	/**
     * This method is called in the constructor of each type of wrapper.  When a new wrapper 
     * is made for Player, it is stored in myWrappers so that it can be kept track of.  
     */
	public void storeWrapper(AbstractPlayerWrapper w)
	{
	    myWrappers.add(w);
	}
	
	/**
     * Returns all the wrappers associate with Player.
     */
	public ArrayList<AbstractPlayerWrapper> getWrappers()
	{
	    return myWrappers;
	}
	
	/**
     * Returns the type (HUMAN_PLAYER, AI_PLAYER, or NETWORK_PLAYER) of a given wrapper
     * that Player holds.
     */
	public PlayerType getWrapperType(AbstractPlayerWrapper w)
	{
	    return w.type;
	}
}
