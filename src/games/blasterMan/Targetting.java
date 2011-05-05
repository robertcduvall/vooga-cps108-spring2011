package games.blasterMan;

import games.blasterMan.sprites.PlayerType;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.BasicComponent;

public class Targetting extends BasicComponent {
	protected PlayerType target;
	public Targetting(PlayerType target){
		this.target = target;
	}
	public Sprite getTarget(){
		return target;
	}
	@Override
	protected int compareTo(BasicComponent o) {
		return 0;
	}

	@Override
	protected Object[] getFieldValues() {
		return new Object[]{target};
	}

	@Override
	protected void setFieldValues(Object... fields) {
		target = (PlayerType)fields[0];
	}

}
