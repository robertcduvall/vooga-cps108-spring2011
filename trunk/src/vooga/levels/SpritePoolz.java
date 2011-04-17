package vooga.levels;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;
import com.golden.gamedev.object.Sprite;

public class SpritePoolz
{
    
    //private Map<String, ArrayList<PoolObject>> mySprites;

    public SpritePool (AbstractLevel abstractLevel)
    {
        // TODO Auto-generated constructor stub
    }

    public void addAllSprites ()
    {
        // TODO Auto-generated method stub
        
    }
    
    public void addAllSprites(String type)
    {
        
    }
    
    
}

//
//for (ArrayList<Sprite> currentSpriteList : mySprites.values())
//{
//    for (Sprite currentSprite : currentSpriteList)
//    {
//        add(currentSprite);
//    }
//}

Class<?> requestedClass;
try
{
    requestedClass = Class.forName(className);
}
catch (ClassNotFoundException e)
{
    throw LevelException.NON_EXISTANT_SPRITE;
}
add(mySprites.get(requestedClass).remove(0));




Random generator = new Random();
ArrayList<Sprite> myRandSpriteType = mySprites.get(generator.nextInt(mySprites.size()));
add(myRandSpriteType.get(generator.nextInt(myRandSpriteType.size())));    


public void addSprite (String className)
{
    // TODO Auto-generated method stub
    
}

public void addRandomSprite ()
{
    // TODO Auto-generated method stub
    
}