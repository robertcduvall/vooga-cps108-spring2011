package games.blasterMan.sprites;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.sprites.spritebuilder.components.basic.TargetC;
import vooga.sprites.spritebuilder.components.basic.TargetableC;
import vooga.util.buildable.components.IComponent;

@SuppressWarnings("serial")
public class Slime extends Monster{

	public Slime(VoogaGame game) {
		super(game);
		this.game = game;
	}

	public Slime(VoogaGame game, BufferedImage image, double x, double y,
			IComponent... comps) {
		super(game, image, x, y, comps);
		this.game = game;
	}

	public Slime(VoogaGame game, BufferedImage image, double x, double y) {
		super(game, image, x, y);
		this.game = game;
	}

	public Slime(VoogaGame game, BufferedImage image) {
		super(game, image);
		this.game = game;
	}

	public Slime(VoogaGame game, double x, double y) {
		super(game, x, y);
		this.game = game;
	}

	@Override
	public void attack() {
		PlayerType myTarget = (PlayerType)getComponent(TargetC.class).getTarget();
		myTarget.getComponent(TargetableC.class).target();
		double dx = myTarget.getX() - this.getX();
		double dy = myTarget.getY() - this.getY();
		
		this.setSpeed(0.2, 180 * Math.tan(dy/dx) / Math.PI);
	}
	

}
