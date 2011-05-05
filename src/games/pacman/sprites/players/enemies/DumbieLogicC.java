package games.pacman.sprites.players.enemies;

import games.patterson_game.refactoredVooga.util.buildable.components.IComponent;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.sprites.spritebuilder.components.basic.TargetC;
import vooga.util.buildable.components.BasicComponent;

public class DumbieLogicC extends BasicComponent implements ISpriteUpdater
{

    @Override
    public void update (Sprite s, long elapsedTime)
    {
    }

	@Override
	protected int compareTo(BasicComponent o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Object[] getFieldValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setFieldValues(Object... fields) {
		// TODO Auto-generated method stub
		
	}

}
