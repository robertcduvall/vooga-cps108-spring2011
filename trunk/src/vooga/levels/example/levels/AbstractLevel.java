package vooga.levels.example.levels;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.TreeMap;
import vooga.levels.example.main.CustomGame;
import vooga.levels.example.main.CustomPlayField;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;


/**
 * A basic level object which, by default, initializes all sprites, music and
 * backgrounds found in the associated XML file. If more complex level
 * initialization is required (conditional for example) level can be extended
 * and the initializeLevel() method can be overriden.
 * 
 * @author Andrew Patterson & Wesley Brown
 */
public abstract class AbstractLevel implements Comparable<AbstractLevel>
{
    private String myFilePath;
    private int myId;
    protected IGoal myGoal;
    protected Queue<Background> myBackgrounds;
    protected CustomPlayField myPlayField;
    protected TreeMap<Class<?>, ArrayList<Sprite>> mySprites;
    protected Queue<String> myMusic;


    public AbstractLevel (String filePath, int id, CustomPlayField pf)
    {
        myPlayField = pf;
        myFilePath = filePath;
        myId = id;
        mySprites = new TreeMap<Class<?>, ArrayList<Sprite>>();
    }


    /**
     * The key method that the LevelManager will call when loading a level
     */
    public abstract void loadLevel ();


    /**
     * Checks if the current level's goal has been achieved
     */
    public void checkCompletion ()
    {
        if(myGoal.checkCompletion()) myGoal.progress();
    }


    /**
     * Sets the view's background
     */
    protected void addBackground ()
    {
        myPlayField.setBackground(myBackgrounds.poll());
    }


    /**
     * Place all previously read sprites on the playingfield
     */
    protected void addAllSprites ()
    {
        for (ArrayList<Sprite> currentSpriteList : mySprites.values())
        {
            for (Sprite currentSprite : currentSpriteList)
            {
                myPlayField.add(currentSprite);
            }
        }
    }


    /**
     * Places all sprites of the specified type on the playingfield
     * 
     * @param className of the sprite you wish to initialize
     */
    protected void addSprite (String className)
    {
        myPlayField.add(getSprite(className));
    }


    /**
     * Begins this level's music track
     */
    protected void addMusic ()
    {
        CustomGame.getInstance().playMusic(myMusic.poll());
    }


    /**
     * Gets this level's id number
     * 
     * @return level number
     */
    public int getId ()
    {
        return myId;
    }


    /**
     * Adds a random sprite of a random type, removing it from the sprite pool
     */
    public void addRandomSprite ()
    {
        myPlayField.add(getRandomSprite());
    }


    /**
     * Gets a random sprite of a random type, removing it from the sprite pool
     * 
     * @return a random sprite
     */
    public Sprite getRandomSprite ()
    {
        Random generator = new Random();
        ArrayList<Sprite> myRandSpriteType = mySprites.get(generator.nextInt(mySprites.size()));
        return myRandSpriteType.get(generator.nextInt(myRandSpriteType.size()));
    }


    /**
     * Gets the first sprite of the given type from the sprite pool
     * 
     * @param type of sprite to return
     * @return the first sprite of the given type
     */
    public Sprite getSprite (String className)
    {
        Class<?> requestedClass;
        try
        {
            requestedClass = Class.forName(className);
        }
        catch (ClassNotFoundException e)
        {
            throw LevelException.NON_EXISTANT_SPRITE;
        }
        return mySprites.get(requestedClass).remove(0);
    }


    /**
     * Compares a level based on its id
     */
    @Override
    public int compareTo (AbstractLevel other)
    {
        if (other != null)
        {
            AbstractLevel otherLevel = (AbstractLevel) other;
            return myId - otherLevel.getId();
        }
        return -1;
    }


    /**
     * Levels are equal if their level id is equal
     */
    @Override
    public boolean equals (Object other)
    {
        if (other != null && other instanceof AbstractLevel) return myId == ((AbstractLevel) other).getId();
        else return false;
    }
}
