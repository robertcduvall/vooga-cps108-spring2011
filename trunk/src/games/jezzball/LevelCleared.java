package games.jezzball;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class LevelCleared implements IGoal{

    private LevelManager myLevelManager;
    private VoogaPlayField myPlayField;
    private double totalTiles;
    
    @Override
    public boolean checkCompletion(LevelManager levelManager) {
        return (((double)myPlayField.getSpriteGroup("tiles").size())/totalTiles <0.25);
    }

    @Override
    public void progress() {
        System.exit(0);
        
    }

    @Override
    public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
        myLevelManager = manager;
        myPlayField = playfield;
        totalTiles = myPlayField.getSpriteGroup("tiles").size();
    }

}
