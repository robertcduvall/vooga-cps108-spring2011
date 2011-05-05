package games.archer.powerups.powerupcomps;

import games.archer.sprites.IPowerupable;
import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.BasicComponent;

public abstract class PowerupC extends BasicComponent {

	@Override
	protected Object[] getFieldValues() {
		return new Object[]{myGame};
	}

	@Override
	protected void setFieldValues(Object... fields) {
		myGame = (VoogaGame) fields[0];
	}


	private VoogaGame myGame;
	
	public PowerupC() {
	}
	
	public PowerupC(BasicComponent bc) {
		super(bc);
	}

	@Override
	protected int compareTo(BasicComponent o) {
		return 0;
	}

	
	public abstract void applyPowerup(Sprite s);

}
