package vooga.levels;

import java.util.*;
import vooga.levels.util.LevelParser;
import vooga.levels.util.PoolDeferredConstructor;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.util.buildable.components.BasicComponent;
import vooga.collisions.collisionManager.CollisionManager;



/**
 * A basic level object which gets the majority of its contents from an
 * associated XML file. More complex level initializations can be accomplished
 * by writing the loadLevel() method.
 * 
 * @author Andrew Patterson & Wesley Brown
 */
public abstract class AbstractLevel extends VoogaPlayField implements Comparable<AbstractLevel>
{
    /** The XML parser which is used for reading the level file and creating objects based on the data */
    private LevelParser myLevelParser;

    /** The vooga game for which this is a level for */
    private Game myGame; //TODO: switch to voogagame (using Game is a temporary changed used for testing)

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
    private Collection<Sprite> myPlayers;


    public AbstractLevel (Collection<Sprite> players, Game game)
    {
        myGame = game;
        myPlayers = players;
        mySpriteGroups = new ArrayList<SpriteGroup>();
        myCollisionManagers = new ArrayList<CollisionManager>();
    }


    /**
     * The key method that the LevelManager will call when loading a level. The
     * implementation of this method will determine how a level initializes.
     * When writing this method, the convenience of the AbstractLevel class
     * should be called in order to ensure that objects are properly added to
     * the playingfield. For example, addAllSprites() and addBackground() should
     * be called if a level requires all the sprites to be initialized from the
     * pool and if the first background is desired.
     */
    public abstract void loadLevel ();


    /**
     * Parses the XML file, storing its contents within the level
     * Will be called by level manager before loadLevel is called
     * 
     * @param name of XML file for this level
     * @param id of the level whose file you want to read
     */
    public void parseXMLFile (String fileName, int id)
    {
        myLevelParser = new LevelParser(this, myGame);
        mySpritePool = new SpritePool();
        myLevelParser.parse(fileName);
        myId = id;
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
    protected void addAllSpritesFromPool ()
    {
        mySpritePool.addAllSpritesFromPool();
    }


    /**
     * Takes all sprites of a specific type and places them onto the playingfield
     * 
     * @param type of sprite to add
     */
    protected void addAllSpritesFromPool (String type)
    {
        mySpritePool.addAllSpritesFromPool(type);
    }


    /**
     * Takes one sprite of the specified type from the pool and places it onto the playingfield
     * 
     * @param type of the sprite you wish to initialize
     * @return the sprite which was just added to the playingfield
     */
    protected Sprite addSpriteFromPool (String type)
    {
        return mySpritePool.addSpriteFromPool(type);
    }


    /**
     * Creates a new sprite of the specified type, with the specified parameters
     * and places it onto the playingfield. Note that this method does NOT draw
     * from the sprite pool, but instead uses the constructor.
     * 
     * @param archetype of the sprite that you wish to create
     * @param desired x coordinate of the sprite
     * @param desired y coordinate of the sprite
     * @param desired components for the sprite
     * @return the newly created sprite
     */
    public Sprite addNewSprite (String type, int x, int y, BasicComponent... components)
    {
        //myLevelParser.createNewSpriteOfArchetype(spriteArchetype);
    	return null;
    }


    /**
     * Takes a random sprite from the pool and places it onto the playingfield
     * 
     * @return the sprite which was just added to the playingfield
     */
    public Sprite addRandomSprite ()
    {
        return mySpritePool.addRandomSprite();
    }


    /**
     * If one exists, plays the next music track for this file
     */
    protected void addMusic ()
    {
        if(myMusic.size() > 0)
        {
            myGame.playMusic(myMusic.remove());
        }
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
    protected void addPlayer (Sprite p)
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
     * 
     * @param music files for this level
     */
    public void setMusicPool (Queue<String> music)
    {
        myMusic = music;
    }

    /**
     * Sets the current goal for the level
     * @param goal Goal object defined in the XML file
     */
    public void setGoal (IGoal goal)
    {
    	myGoal = goal;
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
     * Returns this level's players
     * 
     * @return all the players for this level
     */
    public Collection<Sprite> getPlayers()
    {
        return myPlayers;
    }
    
    
    @Override
    public void update (long elapsedTime)
    {
        super.update(elapsedTime);
        checkCompletion();      
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
