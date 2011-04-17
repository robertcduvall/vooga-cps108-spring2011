package vooga.levels;

import java.util.*;

import vooga.core.VoogaGame;
import vooga.levels.util.LevelParser;
import vooga.player.Player;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


/**
 * A basic level object which gets the majority of its contents from an
 * associated XML file. More complex level initializations can be accomplished
 * by writing the loadLevel() method.
 * 
 * @author Andrew Patterson & Wesley Brown
 */

//Need to declare all sprite groups and collision managers here
public abstract class AbstractLevel extends PlayField implements Comparable<AbstractLevel> 
{
    /** The XML parser which is used for reading the level file and creating objects based on the data */
    private LevelParser myLevelParser;
    
    /** The vooga game for which this is a level for */
    private VoogaGame myGame;   
    
    /** The goal which this level must reach in order to progress */
    private IGoal myGoal;
    
    /** A queue of all the backgrounds for this level - read in from the XML file */
    private Queue<Background> myBackgrounds;
    
    /** A map of sprite type to all the instances of that type read from the XML file */
    private TreeMap<Class<?>, ArrayList<Sprite>> mySprites;
    
    /** A queue of all the music for this level - read in from the XML file */
    private Queue<String> myMusic;
    
    /** This level's current id; will change each time a new xml file is read */
    private int myId;


    public AbstractLevel (Collection<Player> players, VoogaGame game )
    {
        myGame = game;
        mySprites = new TreeMap<Class<?>, ArrayList<Sprite>>();
    }


    /**
     * The key method that the LevelManager will call when loading a level.
     * The implementation of this method will determine how a level initializes.
     */
    public abstract void loadLevel ();
    
    public void parseXMLFile(String fileName)
    {
        myLevelParser = new LevelParser(this);
        myLevelParser.parse(fileName);
        //TODO implement
    }

    /**
     * Checks if the current level's goal has been achieved
     */
    public boolean checkCompletion ()
    {
        if(myGoal.checkCompletion()) myGoal.progress();
    }


    /**
     * Sets the playingfield's background
     */
    protected void addBackground ()
    {
        if(myBackgrounds.size() > 0){
            setBackground(myBackgrounds.remove());
        }
    }


    /**
     * Places all sprites from the initial condition pool onto the playingfield
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
    
    /**
     * Places all sprites of a specific type from the pool onto the playingfield
     * @param type
     */
    protected void addAllSprites(String type);


    /**
     * Places one sprite of the specified type onto the playingfield
     * 
     * @param className of the sprite you wish to initialize
     */
    protected void addSprite (String className)
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
        add(mySprites.get(requestedClass).remove(0));
    }
    
    public void addSprite(String spriteArchetype, Object...paramaters)
    {
        myLevelParser.createNewSpriteOfArchetype(spriteArchetype);
    }


    /**
     * Adds a random sprite from the pool onto the playingfield, removing it from the sprite pool
     */
    public void addRandomSprite ()
    {
        Random generator = new Random();
        ArrayList<Sprite> myRandSpriteType = mySprites.get(generator.nextInt(mySprites.size()));
        add(myRandSpriteType.get(generator.nextInt(myRandSpriteType.size())));    
    }

    /**
     * Begins this level's music track
     */
    protected void addMusic ()
    {
        myGame.playMusic(myMusic.remove(0));
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
    
    public int getId()
    {
        return myId;
    }
    
    public void setSpritePool();
    
    public void setBackgroundPool();
    
    public void setMusicPool();
    
    @Override
    public SpriteGroup getGroup(String groupName)
    {
        SpriteGroup[] allGroups = getGroups();
        for(SpriteGroup currentGroup : allGroups)
        {
            if(currentGroup.getName().equals(groupName))
            {
                return currentGroup;
            }
        }
        SpriteGroup newGroup = new SpriteGroup(groupName);
        addGroup(newGroup);
        return newGroup;
    }
}
