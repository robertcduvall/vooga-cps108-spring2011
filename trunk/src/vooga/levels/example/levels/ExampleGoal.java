package vooga.levels.example.levels;

import vooga.levels.example.main.CustomPlayField;


public class ExampleGoal implements IGoal
{
    CustomPlayField myPlayField;
    double startTime;


    public ExampleGoal (CustomPlayField pf)
    {
        myPlayField = pf;
        startTime = System.currentTimeMillis();
    }


    @Override
    public boolean checkCompletion ()
    {
        // This goal is set to complete 6 seconds after the level starts
        return (((System.currentTimeMillis() - startTime) / 1000) > 6);
    }


    @Override
    public void progress ()
    {
        LevelManager.getInstance().loadNextLevel();
    }
}
