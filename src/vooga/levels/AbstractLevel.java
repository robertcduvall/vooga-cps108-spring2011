package vooga.levels;

import java.util.Collection;
import java.util.Queue;
import vooga.core.VoogaGame;
import vooga.levels.util.LevelParser;
import vooga.levels.util.PoolDeferredConstructor;
import vooga.player.Player;
import vooga.sprites.improvedsprites.Sprite;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;


/**
 * A basic level object which gets the majority of its contents from an
 * associated XML file. More complex level initializations can be accomplished
 * by writing the loadLevel() method.
 * 
 * @author Andrew Patterson & Wesley Brown
 */
public abstract class AbstractLevel extends PlayField implements Comparable<AbstractLevel>
{
    /** The XML parser which is used for reading the level file and creating objects based on the data */
    private LevelParser myLevelParser;

    /** The vooga game for which this is a level for */
    private VoogaGame myGame;

    /** The goal which this level must reach in order to progress */
    private IGoal myGoal;

    /** A queue of all the backgrounds for this level - read in from the XML file  */
    private Queue<Background> myBackgrounds;

    /** A sprite pool of all the sprites from the XML file */
    private SpritePool mySpritePool;

    /** A queue of all the music for this level - read in from the XML file */
    private Queue<String> myMusic;

    /** This level's current id; could change each time a new XML file is read */
    private int myId;

    /** This level's players */
    private Collection<Sprite> myPlayers; //TODO: switch back to player


    public AbstractLevel (Collection<Sprite> players, VoogaGame game)
    {
        myGame = game;
        myPlayers = players;
    }


    /**
     * The key method that the LevelManager will call when loading a level. The
     * implementation of this method will determine how a level initializes.
     */
    public abstract void loadLevel ();


    /**
     * Parses the XML file, storing its contents within the level
     * Will be called by level manager before loadLevel is called
     * 
     * @param name of XML file for this level
     */
    public void parseXMLFile (String fileName)
    {
        myLevelParser = new LevelParser(this);
        mySpritePool = new SpritePool();
        myLevelParser.parse(fileName);
    }
    

    /**
     * Checks if the current level's goal has been achieved
     * 
     * @return the level's completion status
     */
    public boolean checkCompletion ()
    {
        if (myGoal.checkCompletion())
        {
            myGoal.progress();
            return true;
        }
        return false;
    }


    /**
     * Places all sprites from the initial condition pool onto the playingfield
     */
    protected void addAllSprites ()
    {
        mySpritePool.addAllSprites();
    }


    /**
     * Takes all sprites of a specific type and places them onto the playingfield
     * 
     * @param type of sprite to add
     */
    protected void addAllSprites (String type)
    {
        mySpritePool.addAllSprites(type);
    }


    /**
     * Takes one sprite of the specified type and places it onto the playingfield
     * 
     * @param type of the sprite you wish to initialize
     */
    protected void addSprite (String type)
    {
        mySpritePool.addSprite(type);
    }


    /**
     * Creates a new sprite of the specified type, with the specified parameters
     * and places it onto the playingfield. Note that this method does NOT draw
     * from the sprite pool
     * 
     * @param type
     * @param paramaters
     */
    public void addSprite (String type, Object ... paramaters)
    {
        //TODO
        //myLevelParser.createNewSpriteOfArchetype(spriteArchetype);
    }


    /**
     * Takes a random sprite from the pool and places it onto the playingfield
     */
    public void addRandomSprite ()
    {
        myLevelParser.addRandomSprite();
    }


    /**
     * If one exists, plays the next music track for this file
     */
    protected void addMusic ()
    {
        myGame.playMusic(myMusic.remove());
    }
    
    
    /**
     * Sets the playingfield's background
     */
    protected void addBackground ()
    {
        if (myBackgrounds.size() > 0)
        {
            setBackground(myBackgrounds.remove());
        }
    }

    
    /**
     * Adds a player onto this level
     * 
     * @param player to add
     */
    protected void addPlayer (Sprite p) //TODO: change back to player
    {
        myPlayers.add(p);
    }


    /**
     * Sets this level's sprite pool; called by LevelParser
     * 
     * @param spritePool for this level
     */
    public void addToPool (PoolDeferredConstructor poolObject)
    {
        mySpritePool.addToPool(poolObject);
    }

    /**
     * Sets the background queue for this level
     * Called by LevelParser
     * 
     * @param background files for this level
     */
    public void setBackgroundPool (Queue<Background> backgrounds)
    {
        myBackgrounds = backgrounds;
    }

    /**
     * Sets the music queue for this level; called by LevelParser
     * @param music files for this leve
     */
    public void setMusicPool (Queue<String> music)
    {
        myMusic = music;
    }

    /**
     * Returns this level's id
     * 
     * @return level's id
     */
    public int getId ()
    {
        return myId;
    }


    /**
     * If it exists, returns the sprite group of the specified name. If it
     * doesn't exist, a new sprite group of the specified name is created, added
     * to the playingfield and returned
     * 
     * @return a sprite group of the specified name
     */
    @Override
    public SpriteGroup getGroup (String groupName)
    {
        SpriteGroup[] allGroups = getGroups();
        for (SpriteGroup currentGroup : allGroups)
        {
            if (currentGroup.getName().equals(groupName))
            {
                return currentGroup;
            }
        }
        SpriteGroup newGroup = new SpriteGroup(groupName);
        addGroup(newGroup);
        return newGroup;
    }
    
    
    /**
     * Returns this level's players
     * 
     * @return all the players for this level
     */
    public Collection<Player> getPlayers()
    {
        return myPlayers;
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
