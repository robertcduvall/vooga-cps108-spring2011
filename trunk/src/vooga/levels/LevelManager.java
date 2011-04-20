package vooga.levels;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.Game;

import vooga.reflection.Reflection;
import vooga.sprites.improvedsprites.Sprite;


/**
 * A manger that facilitates movement between levels, stores information
 * regarding the overall state of the levels and maintains the user’s position/
 * progress in the game.
 * 
 * @author Andrew Patterson & Wesley Brown
 */
public class LevelManager
{
    /** A map of level number to the associated XML file */
    private Map<Integer, String> myLevelOrderMap;

    /** The total number of levels */
    private int myNumOfLevels;

    /** The total number of levels completed */
    private int myNumOfLevelsCompleted;

    /** The players for this level */
    private Collection<Sprite> myPlayers; //TODO: change to sprite

    /** The current running game */
    private Game myGame; // TODO: change to voogagame

    /** The current, active level for this game */
    private AbstractLevel myActiveLevel;

    /**
     * Maps level names/classes to level order
     */
    public LevelManager (Game game, Collection<Sprite> players)
    {
        myGame = game;
        myLevelOrderMap = new HashMap<Integer, String>();
    }

    /**
     * Loads the first level specified in the level order file.
     */
    public void start()
    {
    	loadLevel(0);
    }
    
    /**
     * Attempts to load level with specified id. Checks to see if the level
     * being loaded is of the same type as the current level. If so, it
     * maintains the current instance and populates the instance with the new
     * level content.
     * 
     * @param id representing level to load
     */
    public void loadLevel (int id)
    {
        if (!(myLevelOrderMap.containsKey(id))) throw LevelException.NON_EXISTANT_LEVEL;
        String levelFileName = myLevelOrderMap.get(id);

        // 1st item is the class type of the level
        // 2nd is the user defined name which in essence is a comment        
        String[] levelDef = levelFileName.split("\\_");
        String activeLevelClass = myActiveLevel.getClass().getName();
        activeLevelClass = activeLevelClass.substring(0, activeLevelClass.indexOf(".")); //Gets rid of ".class"
        if (activeLevelClass.equals(levelDef[0]))
        {
            myActiveLevel.loadLevel();
        }
        else
        {
            try
            {
                myActiveLevel = ((AbstractLevel) Reflection.createInstance(levelDef[0], myPlayers, myGame));
                myActiveLevel.loadLevel();
            }
            catch (Exception e)
            {
                throw LevelException.LEVEL_LOADING_ERROR;
            }
        }
    }


    /**
     * Loads the level that comes after the current level
     */
    public void loadNextLevel ()
    {
        loadLevel(myActiveLevel.getId() + 1);
    }


    /**
     * Loads the level that came before the current level
     */
    public void loadPreviousLevel ()
    {
        loadLevel(myActiveLevel.getId() - 1);
    }


    /**
     * Checks if the current level is complete
     */
    public void checkLevelCompletion ()
    {
        if (myActiveLevel.checkCompletion()) myNumOfLevelsCompleted++;
    }


    /**
     * Retrieves the highest running level's id
     */
    public int getCurrentLevel ()
    {
        return myActiveLevel.getId();
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
        myActiveLevel.addRandomSprite();
    }


    /**
     * Add a new sprite of the specified type to the playingfield. The sprite is
     * taken from the lowest running level sprite pool.
     * 
     * @param type of Sprite to add
     */
    public void addSpriteFromPool (String type)
    {
        myActiveLevel.addSpriteFromPool(type);
    }


    /**
     * Changes the playingfield background to the next background in a sequence
     * of backgrounds. The background is taken from the lowest running level.
     */
    public void useNextBackground ()
    {
        myActiveLevel.addBackground();
    }


    /**
     * Plays the next music file from a sequence of music files. The music is
     * taken from the lowest running level.
     */
    public void useNextMusic ()
    {
        myActiveLevel.addMusic();
    }
    
    
    /**
     * 
     */
    public void addToLevelMap(int levelNumber, String levelFilePath)
    {
        myLevelOrderMap.put(levelNumber, levelFilePath);
        myNumOfLevels++;
    }


    /**
     * Updates the level (which is a playingfield)
     * 
     * @param elapsedTime
     */
    public void update (long elapsedTime)
    {
        myActiveLevel.update(elapsedTime);
    }

    
    /**
     * Renders the level (which is a playingfield)
     */
    public void render (Graphics2D g)
    {
        myActiveLevel.render(g);
    }
}
