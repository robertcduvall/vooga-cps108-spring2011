package vooga.levels;

import java.util.*;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;


/**
 * A basic level object which gets the majority of its contents from an
 * associated XML file. More complex level initializations can be accomplished
 * by writting the loadLevel() method.
 * 
 * @author Andrew Patterson & Wesley Brown
 */


//Need to declare all sprite groups and collision managers here
public abstract class AbstractLevel extends PlayField implements Comparable<AbstractLevel> 
{
    /** The game for which this is a level for */
    private Game myGame;   
    
    /** The id/level number for this object */
    //private int myId;
    
    /** The goal which this level must reach in order to progress */
    private IGoal myGoal;
    
    /** A queue of all the backgrounds for this level - read in from the XML file */
    private ArrayList<Background> myBackgrounds;
    
    /** A map of sprite type to all the instances of that type read from the XML file */
    private TreeMap<Class<?>, ArrayList<Sprite>> mySprites;
    
    /** A queue of all the music for this level - read in from the XML file */
    private ArrayList<String> myMusic;


    public AbstractLevel (String filePath, int id, PlayField pf, Game g)
    {
        myGame = g;
        myFilePath = filePath;
        myId = id;
        mySprites = new TreeMap<Class<?>, ArrayList<Sprite>>();
        LevelBuilder levelBuilder = new LevelBuilder();
        levelBuilder.buildLevel(myFilePath);
    }


    /**
     * The key method that the LevelManager will call when loading a level.
     * The implementation of this method will determine how a level initializes.
     */
    
    public abstract void loadLevel (String filePath);


    /**
     * Checks if the current level's goal has been achieved
     */
    public void checkCompletion ()
    {
        if(myGoal.checkCompletion()) myGoal.progress();
    }


    /**
     * Sets the playingfield's background
     */
    protected void addBackground ()
    {
        setBackground(myBackgrounds.remove(0));
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
                add(currentSprite);
            }
        }
    }
    
    protected void addAllSprites(String type);


    /**
     * Places all sprites of the specified type on the playingfield
     * 
     * @param className of the sprite you wish to initialize
     */
    protected void addSprite (String className)
    {
        add(getSprite(className));
    }
    
    protected void addSprite(Sprite);


    /**
     * Begins this level's music track
     */
    protected void addMusic ()
    {
        myGame.playMusic(myMusic.remove(0));
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
    
    //
    protected addPlayer(Player);
}
