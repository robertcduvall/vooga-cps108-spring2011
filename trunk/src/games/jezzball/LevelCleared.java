package games.jezzball;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class LevelCleared implements IGoal{

    private LevelManager myLevelManager;
    private VoogaPlayField myPlayField;
    private double totalTiles;
    private int xDimension = 10;
    private int yDimension = 10;
    
    @Override
    public boolean checkCompletion(LevelManager levelManager) {
        return (((double)myPlayField.getSpriteGroup("wall").size()-2*xDimension-2*yDimension+4)>(xDimension-1)*(yDimension-1)*0.75);
    }

    @Override
    public void progress() {
        System.out.println("WIN!");
        System.out.println(((double)myPlayField.getSpriteGroup("wall").size()));
        System.exit(0);
        
    }

    @Override
    public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
        myLevelManager = manager;
        myPlayField = playfield;
    }

}
