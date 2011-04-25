package games.asteroids;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class AllAsteroidsDestroyed implements IGoal
{
    private LevelManager myLevelManager;
    private VoogaPlayField myPlayField;
    
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
        return (myPlayField.getSpriteGroup("asteroid").size() == 0);
    }


    @Override
    public void progress ()
    {
        myLevelManager.loadNextLevel();
    }


    @Override
    public void setupGoal (LevelManager manager, VoogaPlayField playfield)
    {
        myLevelManager = manager;
        myPlayField = playfield;
    }

}
