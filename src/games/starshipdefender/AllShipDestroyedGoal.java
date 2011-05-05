package games.starshipdefender;

import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class AllShipDestroyedGoal implements IGoal
{
    private LevelManager myLevelManager;
    private VoogaPlayField myPlayField;
    
    @Override
    public boolean checkCompletion(LevelManager levelManager)
    {
        return (myPlayField.getSpriteGroup("enemyship").size() == 0);
    }

    @Override
    public void progress()
    {
        // TODO move to screen. then next level.
        try
        {
            myLevelManager.loadNextLevel();
        }
        catch (LevelException e)
        {
            System.out.println("You win!");
            myLevelManager.getCurrentGame().finish(); 
        }
        
    }

    @Override
    public void setupGoal(LevelManager manager, VoogaPlayField playfield)
    {
        myLevelManager = manager;
        myPlayField = playfield;
        
    }

}
