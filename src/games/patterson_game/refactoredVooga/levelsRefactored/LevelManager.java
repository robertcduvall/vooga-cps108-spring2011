package games.patterson_game.refactoredVooga.levelsRefactored;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.core.VoogaState;
import games.patterson_game.refactoredVooga.core.event.EventManager;
import games.patterson_game.refactoredVooga.levelsRefactored.IGoal.GoalStatus;
import games.patterson_game.refactoredVooga.reflection.Reflection;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.spritegroups.SpriteGroup;
import games.patterson_game.refactoredVooga.util.buildable.components.IComponent;
import java.awt.Graphics2D;
import java.util.*;


/**
 * A manger that facilitates movement between levels, stores information
 * regarding the overall state of the levels and maintains the user's position/
 * progress in the game. Additionally, this manager provides convenience methods in
 * order to easily modify the current playfield
 * 
 * @author Andrew Patterson
 * @author Wesley Brown
 * @version 1.7
 * @since 1.0
 */
public class LevelManager implements VoogaState
{
    /** The default name for the player group */
    private static final String DEFAULT_PLAYER_GROUP_NAME = "default player";

    /** A map of level numbers to array of [levelFilePath, levelType] */
    private Map<Integer, String[]> myLevelOrderMap;

    /** The total number of levels in this game */
    private int myNumOfLevels;

    /** The total number of levels completed */
    private int myNumOfLevelsCompleted;

    /** The players for this game.  These sprite persist between levels */
    private Collection<SpriteGroup<Sprite>> myPlayers;

    /** The currently running <code>VoogaGame</code>*/
    private VoogaGame myGame;

    /** The current, active level for this game */
    private AbstractLevel myActiveLevel;

    /** Past playingfields that have been used in the game */
    private Collection<AbstractLevel> myPastLevels;
    
    /** The entire game's goal */
    private IGoal myGameGoal;


    /**
     * Sets the level map and vooga game and creates an empty default player group
     * 
     * @param game the <code>VoogaGame</code> whose levels this is managing
     */
    @SuppressWarnings("unchecked")
    public LevelManager (VoogaGame game)
    {
        this(game, new SpriteGroup<Sprite>(DEFAULT_PLAYER_GROUP_NAME));
    }

    
    /**
     * Sets the level map, the vooga game AND the players
     * 
     * @param game the <code>VoogaGame</code> whose levels this is managing
     * @param players a series of SpriteGroups containing the persistent players for this game
     */
    public LevelManager(VoogaGame game, SpriteGroup<Sprite>... players)
    {
        myGame = game;
        myPlayers = new ArrayList<SpriteGroup<Sprite>>();
        myPlayers.addAll(Arrays.asList(players));
        myLevelOrderMap = myGame.getResourceManager().getLevelMap();
        myNumOfLevels = myLevelOrderMap.size();
        myPastLevels = new HashSet<AbstractLevel>();
    }


    /**
     * Attempts to the load level with a specified id. Checks to see if the
     * level being loaded is of the same type any previously played levels. If
     * so, the old level's <code>playingfield</code> is used so that
     * <code>CollisionManagers</code>and <code>SpriteGroups</code> do not have
     * to be re-specified in the XML file.
     * 
     * @param id the id of the level to load
     */
    public void loadLevel (int id)
    {
        if(checkGameGoal()) return; //Before loading a level check if the game is finished
        if (!myLevelOrderMap.containsKey(id)) throw LevelException.NON_EXISTANT_LEVEL;
        String desiredLevelPath = myLevelOrderMap.get(id)[0];
        String desiredLevelType = myLevelOrderMap.get(id)[1];

        // Cycle through all past levels and see if any of them are of the requested type
        for (AbstractLevel pastLevel : myPastLevels)
        {
            String pastLevelClass = pastLevel.getClass().getName();
            if (pastLevelClass.equalsIgnoreCase(desiredLevelType))
            {
                myActiveLevel = pastLevel;
                myActiveLevel.clearUnusedObjects();
                myActiveLevel.parseXMLFile(desiredLevelPath, id);
                myActiveLevel.loadLevel();
                return;
            }
        }

        // If no pre-existing level of the correct type exists, create a new instance
        try { 
            myActiveLevel = ((AbstractLevel) Reflection.createInstance(desiredLevelType, myPlayers, myGame));
        } catch (Exception e) {
            throw LevelException.LEVEL_CREATION_ERROR;
        }
  
        try {
            myActiveLevel.parseXMLFile(desiredLevelPath, id);
        } catch (Exception e) {
            throw LevelException.LEVEL_PARSING_ERROR;
        }
        
        try {
            myActiveLevel.loadLevel();
            myPastLevels.add(myActiveLevel);
        } catch (Exception e) {
            throw LevelException.LEVEL_LOADING_ERROR;
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
     * Retrieves the highest running level's id
     */
    public int getCurrentLevel ()
    {
        if (myActiveLevel == null) return -1;
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
     * Adds 1 to the number of levels completed
     */
    public void updateNumOfLevelsCompleted ()
    {
        myNumOfLevelsCompleted++;
    }
 

    /**
     * Returns the total number of levels
     */
    public int getNumOfLevels ()
    {
        return myNumOfLevels;
    }


    /**
     * Adds a random <code>Sprite</code> from the active leve's <code>SpritePool</code>
     * 
     * @return the <code>Sprite</code> which was just added to the <code>playingfield</code> or <code>null</code> if there is no active level
     */
    public Sprite addRandomSprite ()
    {
        if (myActiveLevel == null) return null;
        return myActiveLevel.addRandomSprite();
    }


    /**
     * Adds a new <code>Sprite</code> of the specified type to the <code>playingfield</code>. 
     * 
     * @param type the class of the <code>Sprite</code> to add
     * @return the <code>Sprite</code> which was just added to the playingfield or <code>null</code> if there is no active level
     */
    public Sprite addSpriteFromPool (String type)
    {
        if (myActiveLevel == null) return null;
        return myActiveLevel.addSpriteFromPool(type);
    }


    /**
     * Creates a sprite based on an archetype, a coordiate and (if desired) additional components
     * 
     * @param type the archetype of the <code>Sprite</code> that you wish to create
     * @param x the x-coordinate of the <code>Sprite</code>
     * @param y the y-coordinate of the <code>Sprite</code>
     * @param components any additional components for this <code>Sprite</code>
     * @return the newly created <code>Sprite</code> or <code>null</code>
     */
    public Sprite addArchetypeSprite (String type, int x, int y, IComponent ... components)
    {
        if (myActiveLevel == null) return null;
        return myActiveLevel.addArchetypeSprite(type, x, y, components);
    }


    /**
     * Changes the <code>playfield's</code> <code>Background</code> to the next one in a sequence
     * of <code>Backgrounds</code>.
     */
    public void useNextBackground ()
    {
        if (myActiveLevel != null)
        {
            myActiveLevel.addBackground();
        }
    }


    /**
     * Plays the next music file from a sequence of music files.
     */
    public void useNextMusic ()
    {
        if (myActiveLevel != null)
        {
            myActiveLevel.addMusic();
        }
    }


    /**
     * Adds a player to the player <code>SpriteGroup</code>. The change will
     * immediately be reflected on the <code>playfield</code>
     * 
     * @param player a <code>SpriteGroup</code> representing a new player
     */
    public void addPlayer (SpriteGroup<Sprite> player)
    {
        if(player == null) return;
        myPlayers.add(player);
    }


    /**
     * Sets the level order map and overrides any existing levels in this manager
     * 
     * @param level order the level order mapping level number to an array of the form [levelFilePath, levelType]
     */
    public void setLevelOrder (Map<Integer, String[]> levelOrder)
    {
        myLevelOrderMap = levelOrder;
        myNumOfLevels = myLevelOrderMap.size();
    }
    
    
    /**
     * Adds a new level to the level order map, or overrides an existing level
     * if one with the specified levelNumber already exists
     * 
     * @param levelNumber the new level's number
     * @param attributes an array of the form [levelFilePath, levelType] for the new level
     */
    public void addToLevelOrderMap(int levelNumber, String[] attributes)
    {
        myLevelOrderMap.put(levelNumber,attributes);
        myNumOfLevels = myLevelOrderMap.size();
    }
    
    
    /**
     * Sets the current goal for the level
     * 
     * @param goal Goal object defined in the XML file
     */
    public void setGameGoal (IGoal goal)
    {
        goal.setupGoal(myGame);
        myGameGoal = goal;
    }
    
    
    /**
     * Checks the goal for the entire game
     * 
     * @return returns true if the game is finished; false if it is not.
     */
    private boolean checkGameGoal ()
    {
        if (myGameGoal == null) return false;
        GoalStatus status = myGameGoal.checkCompletion();
        if (status == GoalStatus.INCOMPLETE) return false;
        else if (status == GoalStatus.COMPLETE) myGameGoal.progress();
        else myGameGoal.fail();
        return true;
    }
    
    
    /**
     * Gets the <code>EventManager</code> for this <code>GameState</code>.
     * 
     * @return this <code>LevelManager's</code> <code>EventManager</code>
     */
    @Override
    public EventManager getEventManager ()
    {
        return myGame.getEventManager();
    }


    /**
     * Updates the active level
     * 
     * @param elapsedTime
     */
    public void update (long elapsedTime)
    {
        if (myActiveLevel != null)
        {    
            myActiveLevel.update(elapsedTime);        
        }    
    }


    /**
     * Renders the active level
     * 
     * @param g the graphics which will be used to render
     */
    public void render (Graphics2D g)
    {
        if (myActiveLevel != null)
        {
            myActiveLevel.render(g);
        }
    }
}
