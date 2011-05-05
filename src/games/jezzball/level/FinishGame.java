package games.jezzball.level;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class FinishGame implements IGoal{
    private LevelManager myLevelManager;
    private VoogaPlayField myPlayField;
    
    @Override
    public boolean checkCompletion(LevelManager levelManager) {
        return (myPlayField.getSpriteGroup("text").size()==0);
    }

    @Override
    public void progress() {
        myLevelManager.loadLevel(0);
    }

    @Override
    public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
        myLevelManager = manager;
        myPlayField = playfield;
        
    }

}
