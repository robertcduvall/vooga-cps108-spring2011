package games.breakout;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class BlocksCleared implements IGoal
{   
    private VoogaPlayField playfield;
    
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
        return playfield.getSpriteGroup("block").getActiveSprite() == null;
    }


    @Override
    public void progress ()
    {
        System.out.println("You win!");
    }


    @Override
    public void setupGoal (LevelManager manager, VoogaPlayField playfield)
    {
        this.playfield = playfield;
    }

}
