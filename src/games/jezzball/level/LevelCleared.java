package games.jezzball.level;

import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class LevelCleared implements IGoal{

    private LevelManager myLevelManager;
    private VoogaPlayField myPlayField;
    private int xDimension = 15;
    private int yDimension = 15;
    
    @Override
    public boolean checkCompletion(LevelManager levelManager) {
        return (((double)myPlayField.getSpriteGroup("wall").size()-2*xDimension-2*yDimension+4)>(xDimension-1)*(yDimension-1)*0.75);
    }

    @Override
    public void progress() {
        myLevelManager.loadNextLevel();

    }

    @Override
    public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
        myLevelManager = manager;
        myPlayField = playfield;
    }

}
