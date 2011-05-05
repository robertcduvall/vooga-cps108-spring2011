package games.patterson_game.refactoredVooga.levelsRefactored;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.core.event.EventManager;
import games.patterson_game.refactoredVooga.levelsRefactored.IGoal.GoalStatus;
import games.patterson_game.refactoredVooga.levelsRefactored.util.LevelParser;
import games.patterson_game.refactoredVooga.levelsRefactored.util.PoolDeferredConstructor;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.util.buildable.components.IComponent;
import java.util.*;
import vooga.physics.PhysicsManager;
import vooga.physics.VoogaPhysicsMediator;
import com.golden.gamedev.object.Background;


/**
 * Represents one type of level. The majority of the level information is read
 * in from an XML file and more complex level initializations can be
 * accomplished in the loadLevel() method.
 * 
 * @author Andrew Patterson
 * @author Wesley Brown
 * @version 1.7
 * @since 1.0
 */
public abstract class AbstractLevel extends VoogaPlayField implements Comparable<AbstractLevel>
{
    /** The XML parser that is used for reading the level file and creating objects based on the data */
    private LevelParser myLevelParser;

    /** The <code>VoogaGame</code> for which this is a level for */
    protected VoogaGame myGame;

    /** A map of this level's goals to their completion status */
    protected Map<IGoal, Boolean> myGoals;

    /** A queue of all the backgrounds for this level - read in from the XML file  */
    private Queue<Background> myBackgrounds;

    /** A sprite pool of all the sprites for this level - read in from the XML file */
    private SpritePool mySpritePool;

    /** A queue of all the music for this level - read in from the XML file */
    private Queue<String> myMusic;
    
    /** The resource bundle for this level */
    private Bundle myBundle;

    /** This level's current id; will change each time a new XML file is read */
    protected int myId;
    
    /** This level's <code>EventManager */
    protected EventManager myEventManager;
    
    /** The physics manager which stores the forces which affect all objects in the level. */
    private PhysicsManager myPhysics;


    /**
     * Initializes an level that contains players and empty background, music,
     * goal and sprite pools
     * 
     * @param players the players for this level
     * @param game the <code>VoogaGame</code> for which this is a level
     */
    public AbstractLevel (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super();
        myGame = game;
        if (players != null)
            for(SpriteGroup<Sprite> currentPlayer : players)
                addSpriteGroup(currentPlayer);
        myBackgrounds = new LinkedList<Background>();
        myMusic = new LinkedList<String>();
        mySpritePool = new SpritePool();
        myGoals = new HashMap<IGoal, Boolean>();
        myEventManager = myGame.getEventManager();
        myPhysics = new PhysicsManager();
    }


    /**
     * The key method that the <code>LevelManager</code> will call when loading
     * a level. The implementation of this method will determine how a level
     * initializes. When writing this method, the convenience methods of the
     * <code>AbstractLevel</code> class should be called in order to ensure that
     * objects are properly added to the playingfield. For example,
     * <code>addAllSprites()</code> and <code>addBackground()</code> should be
     * called if a level requires all the sprites to be initialized from the
     * pool and if the first background is desired. Note, that
     * <code>parseXML(String fileName, int id)</code> should be called before
     * the level is attempted to be loaded.
     */
    public abstract void loadLevel ();


    /**
     * Parses the XML file, storing its contents within the level Will be called
     * by <code>LevelManager</code> before <code>loadLevel()</code> is called.
     * 
     * @param name of XML file for this level
     * @param id of the level whose file you are reading
     */
    public void parseXMLFile (String fileName, int id)
    {
        myLevelParser = new LevelParser(this, myGame);
        myLevelParser.parse(fileName);
        myId = id;
    }


    /**
     * Clears unused objects from the music, background, goal and
     * <code>SpritePool</code> so that they do not persist across levels. Will
     * be called by <code>LevelManager</code> before parseXML(...) is called.
     */
    public void clearUnusedObjects ()
    {
        myGoals = new HashMap<IGoal, Boolean>();
        mySpritePool = new SpritePool();
        myBackgrounds = new LinkedList<Background>();
        myMusic = new LinkedList<String>();
    }


    /**
     * Checks each of this level's goals and performs some action if needed
     */
    public void checkCompletion ()
    {
        if(myGoals == null) return;
        for(IGoal currentGoal : myGoals.keySet())
        {
            if(myGoals.get(currentGoal) == null || myGoals.get(currentGoal) == true) continue;
            GoalStatus status = currentGoal.checkCompletion();
            if(status == GoalStatus.COMPLETE)
            {
                currentGoal.progress();
                myGoals.put(currentGoal, true);
            }
            else if(status == GoalStatus.FAILED)
            {
                currentGoal.fail();
                myGoals.put(currentGoal, true);
            }
        }
    }


    /**
     * Places all sprites from the pool onto the playingfield
     */
    protected void addAllSpritesFromPool ()
    {
        mySpritePool.addAllSpritesFromPool();
    }


    /**
     * Takes all sprites of a specific type and places them onto the playingfield
     * 
     * @param type the type of sprite that you wish to add
     */
    protected void addAllSpritesFromPool (String type)
    {
        mySpritePool.addAllSpritesFromPool(type);
    }


    /**
     * Takes one <code>Sprite</code> of the specified type from the pool and places it onto the <code>playingfield</code>
     * 
     * @param type the type of the sprite that you wish to initialize
     * @return the sprite which was just added to the playingfield
     */
    protected Sprite addSpriteFromPool (String type)
    {
        return mySpritePool.addSpriteFromPool(type);
    }


    /**
     * Creates a new <code>Sprite</code> of the specified type, with the specified parameters
     * and places it onto the playingfield. Note that this method does NOT draw
     * from the sprite pool, but instead uses an archetype constructor.
     * 
     * @param type the archetype of the <code>Sprite</code> that you wish to create
     * @param x desired x-coordinate of the <code>Sprite</code>
     * @param y desired y-coordinate of the <code>Sprite</code>
     * @param components all of the components that are desired for this <code>Sprite</code>
     * @return the newly created <code>Sprite</code>
     */
    public Sprite addArchetypeSprite (String type, int x, int y, IComponent... components)
    {
        try {
            Sprite sprite = myLevelParser.makeSprite(type, x, y);
            sprite.addComponents(components);
            return sprite;
        } catch (Exception e) {}  // Silently fail  
        return null;
    }


    /**
     * Takes a random <code>Sprite</code> from the pool and places it onto the playingfield
     * 
     * @return the <code>Sprite</code> that was just added to the playingfield
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
     * Adds to this level's <code>SpritePool</code>. Called by <code>LevelParser</code>.
     * 
     * @param poolObject the new <code>PoolDeferredConstructor</code> for the <code>SpritePool</code>
     */
    public void addToSpritePool (PoolDeferredConstructor poolObject)
    {
        mySpritePool.addToPool(poolObject);
    }


    /**
     * Adds to the background queue for this level. Called by <code>LevelParser</code>.
     * 
     * @param background a new background for the background pool
     */
    public void addToBackgroundQueue (Background background)
    {
        myBackgrounds.add(background);
    }


    /**
     * Adds to the music queue for this level. Called by <code>LevelParser</code>.
     * 
     * @param music a new music filepath for the music pool
     */
    public void addToMusicQueue (String music)
    {
        myMusic.add(music);
    }


    /**
     * Adds to the goal collection for the level.
     * 
     * @param goal a goal object for this level
     */
    public void addGoal (IGoal goal)
    {
        goal.setupGoal(myGame);
        myGoals.put(goal,false);
    }


    /**
     * Returns this level's id (you can think of it as a level number)
     * 
     * @return level's id
     */
    public int getId ()
    {
        return myId;
    }
    
    
    /** 
     * Returns this level's resource bundle
     * 
     * @return this level's resouce bunlde
     */
    public Bundle getBundle ()
    {
        return myBundle;
    }
    
    
    /**
     * Returns the physics manager associated with this level
     */
    public PhysicsManager getPhysics()
    {
        return myPhysics;
    }


    /**
     * Updates the level and checks for goal completion
     * 
     * @param elapsedTime
     */
    @Override
    public void update (long elapsedTime)
    {
        VoogaPhysicsMediator.applyPhysicsToSpriteGroups(getAllSpriteGroups(), myPhysics, elapsedTime);
        super.update(elapsedTime);
        checkCompletion();     
        myEventManager.update(elapsedTime);
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
