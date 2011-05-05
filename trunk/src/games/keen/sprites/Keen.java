package games.keen.sprites;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.spritebuilder.components.physics.PhysicsVelocityC;

@SuppressWarnings("serial")
public class Keen extends MonsterSprite {
	private VoogaGame game;
	
	private static final double KEEN_SPEED = 1.5;
	
	public Keen(VoogaGame game, int x, int y) {
		super(game, "keen", x, y);
		
		super.addComponent(new PhysicsVelocityC());
		
		this.game = game;
		bindKeys();
		super.setAnimation(0, Direction.EAST);
	}
	
	private void bindKeys() {
		game.registerEventHandler("Input.User.MoveLeft", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				move(Direction.WEST);
			}
		});
		game.registerEventHandler("Input.User.MoveRight", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				move(Direction.EAST);
			}
		});
	}
	
	private void move(Direction d) {
		if(d.equals(Direction.EAST)) {
			super.moveX(KEEN_SPEED);
		}
		else if(d.equals(Direction.WEST)) {
			super.moveX(-KEEN_SPEED);
		}
	}
}
