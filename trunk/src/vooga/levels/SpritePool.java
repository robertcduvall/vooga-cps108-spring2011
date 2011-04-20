package vooga.levels;

import java.util.*;
import vooga.sprites.improvedsprites.Sprite;
import vooga.levels.util.PoolDeferredConstructor;


/**
 * A storage area for sprite constructors which will eventually be added to the
 * level. All sprite constructors in this pool have been fully defined in the
 * XML file
 * 
 * @author Andrew Patterson
 */
public class SpritePool
{
    /** A map of sprite types to instances of these sprites (in constructor form) */
    private Map<String, ArrayList<PoolDeferredConstructor>> mySprites;

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
        for(Collection<PoolDeferredConstructor> currentConstructorCollection : mySprites.values())
        {
            for(PoolDeferredConstructor currentConstructor : currentConstructorCollection)
            {
                currentConstructor.construct();
            }
        }
    }
    
    
    /**
     * Takes all sprites of a specific type from this pool, constructs them and
     * lets the sprite constructor add them to the playingfield
     */
    public void addAllSpritesFromPool (String type)
    {
        Collection<PoolDeferredConstructor> constructorsOfType = mySprites.get(type);
        for(PoolDeferredConstructor currentConstructor : constructorsOfType)
        {
            currentConstructor.construct();
        }
    }
    
    
    /**
     * Takes one sprite of a specific type from this pool, constructs it and
     * lets the sprite constructor add it to the playingfield
     * @return 
     */
    public Sprite addSpriteFromPool (String type)
    {
        ArrayList<PoolDeferredConstructor> constructorsOfType = mySprites.get(type);
        return constructorsOfType.remove(0).construct();
    }
    
    
    /**
     * Adds a sprite constructor to the pool, sorting it accordingly;
     * Called by levelParser
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
     * @return 
     */
	public Sprite addRandomSprite() {
		Set<String> spriteConstructors = mySprites.keySet();
		String[] scKey = (String[]) spriteConstructors.toArray();
		int scIndex = new Random().nextInt(spriteConstructors.size());
		return addSpriteFromPool(scKey[scIndex]);
	}  
}