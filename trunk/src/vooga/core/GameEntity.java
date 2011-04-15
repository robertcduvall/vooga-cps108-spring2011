package vooga.core;

import java.util.ArrayList;
import vooga.sprites.improvedsprites.Sprite;

/**
 * @author Andrea Scripa
 * 
 * GameEntity adds a level of abstraction between Player and its Sprites.  This prevents
 * Sprite from making decisions about input it receives (Sprites should be followers; not
 * deciders) and allows for better organization of multiple types of Sprites under a single
 * Player.
 * 
 * However, GameEntity doesn't need to be connected to a Player.  It can also just be an 
 * object within a game (like a barrier or a door) that has a Sprite and exhibits
 * behavior.
 */
public abstract class GameEntity
{
    private ArrayList<Sprite> mySprites = new ArrayList<Sprite>();
    
    /**
     * Allows a Sprite to add itself to GameEntity's list of Sprites.
     */
    public void addSprite(Sprite s)
    {
        mySprites.add(s);
    }
    
    /**
     * GameEntity is passed a string command from Player (if it is connected to one) and 
     * decides what to do with it.  Since Players can contain more than one type of entity, 
     * it could be that this entity wants to ignore the command.  Otherwise, it will fire
     * some new event or may call methods on the Sprites it contains to do something.
     */
    public abstract String interpretCommand(String command);
    
    /**
     * GameEntity listens specifically for events it cares about that is not considered
     * input (ex: crashing into a wall).
     */
    public abstract void addEventListeners();
}
