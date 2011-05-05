package games.bigfish;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
import vooga.sprites.improvedsprites.Sprite;

public class BiggerThanTheRest implements IGoal{

    private VoogaPlayField myPlayfield;
    private LevelManager myLevelManager;
    
	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		for(Sprite player : myPlayfield.getSpriteGroup("playerFish").getSprites()){
			if(((PlayerFish)player).getSize()>6)
				return true;
		}
		return false;
	}

	@Override
	public void progress() {
		System.out.println("YOU WIN!");
        myLevelManager.getCurrentGame().finish();
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
        this.myPlayfield = playfield;
        this.myLevelManager = manager;
	}

}
