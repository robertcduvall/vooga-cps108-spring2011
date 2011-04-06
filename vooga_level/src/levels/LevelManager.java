package levels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import reflection.Reflection;


/**
 * A manger that facilitates movement between levels, stores information
 * regarding the overall state of the levels and maintains the user�s position/
 * progress in the game    
 * 
 * @author Andrew Patterson & Wesley Brown
 */
public class LevelManager
{
    private static final String LEVEL_ORDER_FILE = "level_resources/LevelOrder";
    private static LevelManager myInstance;
    
    private Map<Integer, String> myLevelOrderMap;
    private AbstractLevel myCurrentLevel;
    private int myNumOfLevels;
    private int myNumOfLevelsCompleted;


    /**
     * Restrict access to private LevelManager constructor by singleton pattern
     * Map level names/classes to level order
     */
    private LevelManager ()
    {
        myLevelOrderMap = new HashMap<Integer, String>();
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
     * Get access to level-related data through this singleton of LevelManager
     * 
     * @return A singleton instance of LevelManager
     */
    public static LevelManager getInstance ()
    {
        if (myInstance == null) myInstance = new LevelManager();
        return myInstance;
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
            requestedLevel = ((AbstractLevel) Reflection.createInstance(levelName, levelName+id, id));
            requestedLevel.loadLevel();
        }
        catch (Exception e)
        {
            throw LevelException.LEVEL_LOADING_ERROR;
        }
        myCurrentLevel = requestedLevel;
    }


    /**
     * Loads the level that comes after the current level
     */
    public void loadNextLevel ()
    {
        loadLevel(myCurrentLevel.getId() + 1);
    }


    /**
     * Loads the level that came before the current level
     */
    public void loadPreviousLevel ()
    {
        loadLevel(myCurrentLevel.getId() - 1);
    }


    /**
     * Checks if the current level is complete
     * 
     * @return the current level's completion status
     */
    public boolean isLevelComplete ()
    {
        boolean completionStatus = myCurrentLevel.isComplete();
        if (completionStatus == true) myNumOfLevelsCompleted++;
        return completionStatus;
    }


    /**
     * Retrieves the currentLevel's Id
     */
    public int getCurrentLevel ()
    {
        return myCurrentLevel.getId();
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

}
