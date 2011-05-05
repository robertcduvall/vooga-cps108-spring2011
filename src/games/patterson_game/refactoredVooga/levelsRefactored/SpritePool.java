package games.patterson_game.refactoredVooga.levelsRefactored;

import games.patterson_game.refactoredVooga.levelsRefactored.util.PoolDeferredConstructor;
import vooga.sprites.improvedsprites.Sprite;
import java.util.*;


/**
 * A storage area for sprite constructors which may eventually be used to
 * construct a <code>Sprite</code> and add it to the level. All sprite
 * constructors in this pool have been fully defined in the XML file.
 * 
 * @author Andrew Patterson
 * @author WesleyBrown
 * @version 1.7
 * @since 1.5
 */
public class SpritePool
{
    /** A map of sprite types to instances of these sprites (in constructor form) */
    private TreeMap<String, ArrayList<PoolDeferredConstructor>> mySprites;

    /**
     * Creates an empty sprite pool
     */
    public SpritePool ()
    {
        mySprites = new TreeMap<String, ArrayList<PoolDeferredConstructor>>();
    }

    
    /**
     * Takes all the sprites from this pool, constructs them and
     * lets the sprite constructor add them to the playingfield
     */
    public void addAllSpritesFromPool ()
    {
        if (mySprites.values().size() == 0) return;
        for(Collection<PoolDeferredConstructor> currentConstructorCollection : mySprites.values())
        {
            if(currentConstructorCollection.size() == 0) continue;
            for(PoolDeferredConstructor currentConstructor : currentConstructorCollection)
            {
                currentConstructor.construct();
            }
        }
    }
    
    
    /**
     * Takes all sprites of a specific type from this pool, constructs them and
     * lets the sprite constructor add them to the playingfield
     * 
     * @param type the type of sprites that you wish to add to construct
     */
    public void addAllSpritesFromPool (String type)
    {
        Collection<PoolDeferredConstructor> constructorsOfType = mySprites.get(type);
        if(constructorsOfType == null) return;
        for(PoolDeferredConstructor currentConstructor : constructorsOfType)
        {
            currentConstructor.construct();
        }
    }
    
    
    /**
     * Takes one <code>Sprite</code> of a specific type from this pool, constructs it and
     * lets the <code>Sprite</code> constructor add it to the playingfield
     * 
     * @param type the type of <code>Sprite</code> that you wish to construct
     * @return the <code>Sprite</code> which was added
     */
    public Sprite addSpriteFromPool (String type)
    {
        ArrayList<PoolDeferredConstructor> constructorsOfType = mySprites.get(type);
        if(constructorsOfType == null) return null;
        return constructorsOfType.remove(0).construct();
    }
    
    
    /**
     * Adds a sprite constructor to the pool, sorting it accordingly;
     * Called by <code>LevelParser</code>
     * 
     * @param sprite constructor to add
     */
    public void addToPool (PoolDeferredConstructor poolObject)
    {
        String spriteType = poolObject.getTargetName();        
        if(!mySprites.containsKey(spriteType))
        {
            mySprites.put(spriteType, new ArrayList<PoolDeferredConstructor>());            
        }
        mySprites.get(spriteType).add(poolObject);        
    }


    /**
     * Gets a random sprite constructor from the initial condition pool, constructs it
     * and adds it to the playfield.
     * 
     * @return the newly constructed sprite
     */
	public Sprite addRandomSprite() 
	{
		Set<String> spriteConstructors = mySprites.keySet();
		String[] spriteConstructorArray = (String[]) spriteConstructors.toArray();
		int spriteConstructorIndex = new Random().nextInt(spriteConstructors.size());
		return addSpriteFromPool(spriteConstructorArray[spriteConstructorIndex]);
	}  
	
	
	/**
	 * Clears the sprite pool
	 */
	public void clear()
	{
	    mySprites.clear();
	}
}