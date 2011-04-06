package levels;

import java.util.ArrayList;
import java.util.Collection;
import other.CustomGame;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;

/**
 * @author Wesley Brown & Andrew Patterson
 */
public abstract class AbstractLevel
{
    private String myFilePath;
    private int myId;
    private Goal myGoal;
    private Background myBackground;
    private PlayField myPlayField;
    private Collection<Sprite> mySprites;
    private String myMusic;

    public AbstractLevel(String filePath, int id)
    {
        myFilePath = filePath;
        myId = id;
        mySprites = new ArrayList<Sprite>();
        LevelBuilder levelBuilder = new LevelBuilder();
        levelBuilder.buildLevel(myFilePath);
    }

    
    /**
     * The key method that the LevelManager will call when loading a level
     */
    public abstract void loadLevel ();


    /**
     * Checks if the current level's goal has been achieved
     * 
     * @return the status of the level's goal
     */
    public boolean isComplete ()
    {
        return myGoal.isComplete();
    }


    /**
     * Sets the view's background
     * 
     * @return
     */
    protected void initializeBackground ()
    {
        myPlayField.setBackground(myBackground);
    }


    /**
     * Place all previously read sprites on the playingfield
     */
    protected void initializeAllSprites ()
    {
        for(Sprite currentSprite : mySprites)
        {
            myPlayField.add(currentSprite);
        }
    }


    /**
     * Places all sprites of the specified type on the playingfield
     * 
     * @param className of the sprite you wish to initialize
     */
    protected void initializeSprite (String className)
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
        for(Sprite currentSprite : mySprites)
        {
            // TODO verify that the next line works/ is correct
            if(currentSprite.getClass().equals(requestedClass))
            myPlayField.add(currentSprite);
        }
    }


    /**
     * Begins this level's music track
     */
    protected void initializeMusic ()
    {
        CustomGame.getInstance().playMusic(myMusic);
    }
    
    
    /**
     * Returns this level's id number
     * 
     * @return level number
     */
    public int getId ()
    {
        return myId;
    }
}
