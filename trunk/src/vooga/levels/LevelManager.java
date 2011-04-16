package vooga.levels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.levels.example.reflection.Reflection;
import vooga.player.Player;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;


/**
 * A manger that facilitates movement between levels, stores information
 * regarding the overall state of the levels and maintains the user’s position/
 * progress in the game.
 * 
 * @author Andrew Patterson & Wesley Brown
 */
public class LevelManager
{
    private static final String LEVEL_ORDER_FILE = "level_resources/LevelOrder";
    /** The vooga game for this level */
    
    /** A map of level number to the associated XML file */
    private Map<Integer, String> myLevelOrderMap;
    
    /** A set of the current levels */
    private TreeSet<AbstractLevel> myCurrentLevels;
    
    /** The total number of levels */
    private int myNumOfLevels;
    
    /** The total number of levels completed */
    private int myNumOfLevelsCompleted;
    
    /** The playingfield which levels will add to */
    private PlayField myPlayField;
    
    /** The players for this level */
    private Collection<Player> myPlayer;


    /**
     * Maps level names/classes to level order
     */
    //TODO No longer a singleton
    public LevelManager (VoogaGame g, Collection<Player> players)
    {
        myLevelOrderMap = new HashMap<Integer, String>();
        myCurrentLevels = new TreeSet<AbstractLevel>();
        Scanner in;
        try
        {
            in = new Scanner(new File(LEVEL_ORDER_FILE));
        }
        catch (FileNotFoundException e)
        {
            throw LevelException.NON_EXISTANT_LEVEL_ORDER;
        }
        int levelNumber = 0;
        while (in.hasNext())
        {
            myLevelOrderMap.put(levelNumber, in.next());
            levelNumber++;
        }
        myNumOfLevels = levelNumber;
    }


    /**
     * Attempts to load level with specified id
     * 
     * @param id representing level to load
     */
    public void loadLevel (int id)
    {

        String levelName = myLevelOrderMap.get(id);
        if (levelName == null) throw LevelException.NON_EXISTANT_LEVEL;
        AbstractLevel requestedLevel;
        try
        {
            requestedLevel = ((AbstractLevel) Reflection.createInstance(levelName, levelName + id, id, myPlayField));
            requestedLevel.loadLevel();
        }
        catch (Exception e)
        {
            throw LevelException.LEVEL_LOADING_ERROR;
        }
        myCurrentLevels.add(requestedLevel);
    }


    /**
     * Loads the level that comes after the current level
     */
    public void loadNextLevel ()
    {
        loadLevel(myCurrentLevels.last().getId() + 1);
    }


    /**
     * Loads the level that came before the current level
     */
    public void loadPreviousLevel ()
    {
        loadLevel(myCurrentLevels.last().getId() - 1);
    }


    /**
     * Checks if the current level is complete
     */
    public void checkLevelCompletion ()
    {
        for (AbstractLevel currentLevel : myCurrentLevels)
        {
            currentLevel.checkCompletion();
        }
        myNumOfLevelsCompleted++;
    }


    /**
     * Retrieves the highest running level's id
     */
    public int getCurrentLevel ()
    {
        return myCurrentLevels.last().getId();
    }


    /**
     * Returns number of levels completed
     */
    public int getNumOfLevelsCompleted ()
    {
        return myNumOfLevelsCompleted;
    }


    /**
     * Returns the total number of levels
     */
    public int getNumOfLevels ()
    {
        return myNumOfLevels;
    }


    /**
     * Adds a random sprite from the lowest running level pool
     */
    public void addRandomSprite ()
    {
        myCurrentLevels.first().addRandomSprite();
    }


    /**
     * Returns a Sprite from the lowest running level sprite pool
     * 
     * @return a random sprite
     */
    public Sprite getRandomSprite ()
    {
        return myCurrentLevels.first().getRandomSprite();
    }


    /**
     * Add a new sprite of the specified type to the playingfield. The sprite is
     * taken from the lowest running level sprite pool.
     * 
     * @param type of Sprite to add
     */
    public void addNewSprite (String type)
    {
        myCurrentLevels.first().addSprite(type);
    }
    
    //Adds sprite back into playingfield
    public void addSprite(Sprites s);


    /**
     * Add a new sprite of the specified type to the playingfield. The sprite is
     * taken from the lowest running level sprite pool.
     * 
     * @param type of Sprite to return
     * @return sprite of the specified type
     */
    public Sprite getNewSprite (String type)
    {
        return myCurrentLevels.first().getSprite(type);
    }


    /**
     * Changes the playingfield background to the next background in a sequence
     * of backgrounds. The background is taken from the lowest running level.
     */
    public void useNextBackground ()
    {
        myCurrentLevels.first().addBackground();
    }


    /**
     * Plays the next music file from a sequence of music files. The music is
     * taken from the lowest running level.
     */
    public void useNextMusic ()
    {
        myCurrentLevels.first().addMusic();
    }
    
    update();
    render();
}
