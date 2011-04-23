package vooga.levels;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.util.buildable.components.BasicComponent;

import vooga.core.VoogaGame;
import vooga.reflection.Reflection;


/**
 * A manger that facilitates movement between levels, stores information
 * regarding the overall state of the levels and maintains the user’s position/
 * progress in the game. Additionally, this provides convenience methods in order
 * to easily modify the current playingfield.
 * 
 * @author Andrew Patterson & Wesley Brown
 */
public class LevelManager
{
    private static final String LEVEL_ORDER_FILE = "src/vooga/levels/example2/resources/levelorder.txt";
    private static final String LEVEL_CLASS_PATH_PREFIX = "vooga.levels.example2.";
    
    /** A map of level number to the associated XML file; initially set during level manager construction */
    private Map<Integer, String> myLevelOrderMap;

    /** The total number of levels */
    private int myNumOfLevels;

    /** The total number of levels completed */
    private int myNumOfLevelsCompleted;

    /** The players for this game */
    private SpriteGroup<Sprite> myPlayers;

    /** The currently running game */
    private VoogaGame myGame;

    /** The current, active level for this game */
    private AbstractLevel myActiveLevel;

    /** Past playingfields that have been used in the game */
    private Collection<AbstractLevel> myPastLevels;

    
    /**
     * Sets the level map, players and vooga game
     * 
     * @param voogaGame whose levels this is managing
     * @param players for this game (persistent)
     */
    public LevelManager (VoogaGame game, SpriteGroup<Sprite> players)
    {
        myGame = game;
        myLevelOrderMap = new HashMap<Integer, String>();
        myPastLevels = new HashSet<AbstractLevel>();
        myPlayers = players;
        
        // Reads in and sets the level order
        try
        {
            Scanner in = new Scanner(new File(LEVEL_ORDER_FILE));
            int levelNumber = 0;
            while(in.hasNextLine())
            {
                myLevelOrderMap.put(levelNumber,in.nextLine());
                myNumOfLevels ++;
                levelNumber ++;
            }
        }
        catch (FileNotFoundException e)
        {
            throw LevelException.NON_EXISTANT_LEVEL_ORDER;
        }
    }

    
    /**
     * Loads the first level specified in the level order file.
     * This is the same as calling loadLevel(0)
     */
    public void start()
    {
        loadLevel(0);
    }


    /**
     * Attempts to load level with specified id. Checks to see if the level
     * being loaded is of the same type any previously played levels. If so, the
     * old level's playingfield is used so that level managers and collision
     * groups do not have to be re-specified in the XML file.
     * 
     * @param id representing level to load
     */
    public void loadLevel (int id)
    {
        if (!(myLevelOrderMap.containsKey(id))) throw LevelException.NON_EXISTANT_LEVEL;
        String levelFileName = myLevelOrderMap.get(id);

        // 1st item is the class type of the level
        // 2nd is the user defined name which in essence is a comment        
        String[] filePathArray = levelFileName.split("\\_");
        String desiredLevelType = filePathArray[0];

        // Cycle through all past levels and see if any of them are of the requested type
        for(AbstractLevel pastLevel : myPastLevels)
        {
            String pastLevelClass = pastLevel.getClass().getName();
            pastLevelClass = pastLevelClass.substring(0, pastLevelClass.indexOf(".")); //Gets rid of ".class"
            if(pastLevelClass.equals(desiredLevelType))
            {
                myActiveLevel = pastLevel;
                myActiveLevel.clearUnusedObjects();
                myActiveLevel.parseXMLFile(levelFileName, id);
                myActiveLevel.loadLevel();
                return;
            }
        }
        
        //If no pre-existing level of the correct type exists, create a new instance
          try { 
              myActiveLevel = ((AbstractLevel) Reflection.createInstance(LEVEL_CLASS_PATH_PREFIX + desiredLevelType, myPlayers, myGame)); }
          catch (Exception e) { throw LevelException.LEVEL_CREATION_ERROR; }
          try { 
              myActiveLevel.parseXMLFile(levelFileName, id); } 
          catch (Exception e) { throw LevelException.LEVEL_PARSING_ERROR; }
          try {
              myActiveLevel.loadLevel(); 
              myPastLevels.add(myActiveLevel); }
          catch (Exception e) { throw LevelException.LEVEL_LOADING_ERROR; }
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
     * 
     * @return the sprite which was just added to the playingfield
     */
    public Sprite addRandomSprite ()
    {
        return myActiveLevel.addRandomSprite();
    }


    /**
     * Add a new sprite of the specified type to the playingfield. The sprite is
     * taken from the lowest running level sprite pool.
     * 
     * @param type of Sprite to add
     * @return the sprite which was just added to the playingfield
     */
    public Sprite addSpriteFromPool (String type)
    {
        return myActiveLevel.addSpriteFromPool(type);
    }

    /**
     * Creates a sprite based on an archetype, and additional components
     * 
     * @param archetype of the sprite that you wish to create
     * @param x coordinate of the sprite
     * @param y coordinate of the sprite
     * @param Additional components for this sprite
     * @return the newly created sprite
     */
    public Sprite addArchetypeSprite(String type, int x, int y, BasicComponent... components)
    {
        return myActiveLevel.addArchetypeSprite(type, x, y, components);
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
     * Adds a player to the player group
     * Change will immediately be reflected on the playingfield
     * 
     * @param player sprite to add
     */
    public void addPlayer(Sprite player)
    {
        myPlayers.add(player);
    }
    
    
    /**
     * Sets the level order map
     * 
     * @param the new level order mapping level number to the associated XML file path
     */
    public void setLevelOrder(Map<Integer,String> levelOrder)
    {
        myLevelOrderMap = levelOrder;
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
     * 
     * @param The graphics which will be used to render
     */
    public void render (Graphics2D g)
    {
        myActiveLevel.render(g);
    }
}
