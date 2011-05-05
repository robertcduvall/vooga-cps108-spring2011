package games.blasterMan.sprites;

import games.blasterMan.Targetting;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.IComponent;

@SuppressWarnings("serial")
public class Monster extends Sprite{
	protected VoogaGame game;
	public Monster(VoogaGame game) {
		super();
		this.game = game;
		initEvents();
	}

	public Monster(VoogaGame game, BufferedImage image, double x, double y,
			IComponent... comps) {
		super(image, x, y, comps);
		this.game = game;
		initEvents();
	}

	public Monster(VoogaGame game, BufferedImage image, double x, double y) {
		super(image, x, y);
		this.game = game;
		initEvents();
	}

	public Monster(VoogaGame game, BufferedImage image) {
		super(image);
		this.game = game;
		initEvents();
	}

	public Monster(VoogaGame game, double x, double y) {
		super(x, y);
		this.game = game;
		initEvents();
	}
	
	public void attack() {
		PlayerType myTarget = (PlayerType)getComponent(Targetting.class).getTarget();
		double dx = myTarget.getX() - this.getX();
		double dy = myTarget.getY() - this.getY();
		double mag = Math.sqrt(dx*dx + dy*dy);
		this.setSpeed( 0.2 * (dx/mag), 0.2 * (dy/mag));
	}
	
	public void initEvents(){
		this.game.addEveryTurnEvent("Method.MonsterAttack", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				attack();
			}
		});
	}
}
