package vooga.levels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import vooga.levels.util.LevelParser;
import vooga.levels.util.PoolDeferredConstructor;
import com.golden.gamedev.object.Sprite;

public class SpritePool
{
    private Map<String, ArrayList<PoolDeferredConstructor>> mySprites;
    private LevelParser myLevelParser;

    public SpritePool (LevelParser parser)
    {
        mySprites = new TreeMap<String, ArrayList<PoolDeferredConstructor>>();
        myLevelParser = parser;
    }

    public void addAllSprites ()
    {
        for(Collection<PoolDeferredConstructor> currentConstructorCollection : mySprites.values())
        {
            for(PoolDeferredConstructor currentConstructor : currentConstructorCollection)
            {
                currentConstructor.construct();
            }
        }
    }
    
    public void addAllSprites(String type)
    {
        Collection<PoolDeferredConstructor> constructorsOfType = mySprites.get(type);
        for(PoolDeferredConstructor currentConstructor : constructorsOfType)
        {
            currentConstructor.construct();
        }
    }
    
    public void addSprite (String type)
    {
        ArrayList<PoolDeferredConstructor> constructorsOfType = mySprites.get(type);
        constructorsOfType.remove(0).construct();
    }
    
    public void addToPool(PoolDeferredConstructor poolObject)
    {
        String spriteType = poolObject.getTargetName();        
        if(!mySprites.containsKey(spriteType))
        {
            mySprites.put(spriteType, new ArrayList<PoolDeferredConstructor>());            
        }
        mySprites.get(spriteType).add(poolObject);        
    }  
}

////
////for (ArrayList<Sprite> currentSpriteList : mySprites.values())
////{
////    for (Sprite currentSprite : currentSpriteList)
////    {
////        add(currentSprite);
////    }
////}
//
//Class<?> requestedClass;
//try
//{
//    requestedClass = Class.forName(className);
//}
//catch (ClassNotFoundException e)
//{
//    throw LevelException.NON_EXISTANT_SPRITE;
//}
//add(mySprites.get(requestedClass).remove(0));
//
//
//
//

//
//


