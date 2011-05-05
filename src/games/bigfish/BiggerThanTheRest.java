package games.bigfish;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
import vooga.sprites.improvedsprites.Sprite;

public class BiggerThanTheRest implements IGoal{

    private VoogaPlayField playfield;
    private LevelManager manager;
    
	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		for(Sprite player : playfield.getSpriteGroup("playerFish").getSprites()){
			if(((PlayerFish)player).getSize()>6)
				return true;
		}
		return false;
	}

	@Override
	public void progress() {
		System.out.println("YOU WIN!");
		System.exit(0);
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
        this.playfield = playfield;
        this.manager = manager;
	}

}
