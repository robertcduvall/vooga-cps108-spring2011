package vooga.levels;

import com.golden.gamedev.object.PlayField;


public class ExampleGoal implements IGoal
{
    PlayField myPlayField;


    public ExampleGoal (PlayField pf)
    {
        myPlayField = pf;
    }


    @Override
    public boolean checkCompletion ()
    {
        return false;
    }


    @Override
    public void progress ()
    {
        LevelManager.getInstance().loadNextLevel();
    }
}
