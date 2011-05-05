package games.archer.sprites.components;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.util.buildable.components.BasicComponent;
import vooga.util.math.LineMath;

public class MouseRotateC extends BasicComponent implements ISpriteUpdater{

	VoogaGame myGame;
	public MouseRotateC() {
	}

	public MouseRotateC(VoogaGame game) {
		myGame = game;
	}
	
	public MouseRotateC(BasicComponent bc) {
		super(bc);
	}

	@Override
	protected int compareTo(BasicComponent o) {
		// no need to compare
		return 0;
	}

	@Override
	protected Object[] getFieldValues() {
		return new Object[]{myGame};
	}

	@Override
	protected void setFieldValues(Object... fields) {
		myGame = (VoogaGame) fields[0];
	}

	@Override
	public void update(Sprite s, long elapsedTime) {
		s.setAngle(LineMath.findDirection(myGame.getMouseX()-s.getCenterX(), myGame.getMouseY()-s.getCenterY())-90);
	}

}
