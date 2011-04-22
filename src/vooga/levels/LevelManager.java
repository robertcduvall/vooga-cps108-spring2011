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
 * progress in the game.
 * 
 * @author Andrew Patterson & Wesley Brown
 */
public class LevelManager
{
    private static final String LEVEL_ORDER_FILE = "src/vooga/levels/example2/resources/levelorder.txt";
    
    /** A map of level number to the associated XML file */
    private Map<Integer, String> myLevelOrderMap;

    /** The total number of levels */
    private int myNumOfLevels;

    /** The total number of levels completed */
    private int myNumOfLevelsCompleted;

    /** The players for this level */
    private SpriteGroup myPlayers;

    /** The current running game */
    private VoogaGame myGame;

    /** The current, active level for this game */
    private AbstractLevel myActiveLevel;

    /** Past playingfields that have been used in the game */
    private Collection<AbstractLevel> myPastLevels;

    /**
     * Maps level names/classes to level order
     */
    public LevelManager (VoogaGame game, SpriteGroup players)
    {
        myGame = game;
        myLevelOrderMap = new HashMap<Integer, String>();
        myPastLevels = new HashSet<AbstractLevel>();
        myPlayers = players;
        
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

        // Cycle through all past levels and see if any of them are of the requested type
        for(AbstractLevel pastLevel : myPastLevels)
        {
            String pastLevelClass = pastLevel.getClass().getName();
            pastLevelClass = pastLevelClass.substring(0, pastLevelClass.indexOf(".")); //Gets rid of ".class"
            if(pastLevelClass.equals(levelDef[0]))
            {
                pastLevel.loadLevel();
                myActiveLevel = pastLevel;
                return;
            }
        }
        
        //If no pre-existing level of the correct type exists, create a new instance
//        try
//        {
            myActiveLevel = ((AbstractLevel) Reflection.createInstance("vooga.levels.example2."+levelDef[0], myPlayers, myGame));
            myActiveLevel.loadLevel();
            myPastLevels.add(myActiveLevel);
//        }
//        catch (Exception e)
//        {
//            throw LevelException.LEVEL_LOADING_ERROR;
//        }
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
    public Sprite addSpriteArchetype(String type, int x, int y, BasicComponent... components)
    {
        return myActiveLevel.addNewSprite(type, x, y, components);
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
     * Change will immediately be refelcted on the playingfield
     * 
     * @param player sprite to add
     */
    public void addPlayer(Sprite player)
    {
        myPlayers.add(player);
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
