package games.leapingSquirrel.goals;

import games.leapingSquirrel.LeapingSquirrelGame;
import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class TimeCompleted implements IGoal
{

    private LevelManager myLevelManager;
    private VoogaPlayField myPlayfield;
    
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
        //TODO: Set up a timer
        return false;
    }

    @Override
    public void progress ()
    {
        myLevelManager.updateNumOfLevelsCompleted();
        myPlayfield.clearPlayField();
        if (myLevelManager.getNumOfLevelsCompleted() == myLevelManager.getNumOfLevels()) {
            myLevelManager.getCurrentGame().finish();
        }
        LeapingSquirrelGame.myEventManager.fireEvent(this, "Game.NewSquirrel");
        
        try{
            myLevelManager.loadLevel(myLevelManager.getCurrentLevel().getId() + 1);
        } catch (LevelException e){
            System.out.println("Well done hero!  You win!");
            myLevelManager.getCurrentGame().finish();
        }
    }

    @Override
    public void setupGoal (LevelManager manager, VoogaPlayField playfield)
    {
        myLevelManager = manager;
        myPlayfield = playfield;
    }
}
