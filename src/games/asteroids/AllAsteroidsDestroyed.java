package games.asteroids;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;

public class AllAsteroidsDestroyed implements IGoal
{
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
        return false;
    }


    @Override
    public void progress ()
    {
        return;
    }

}
