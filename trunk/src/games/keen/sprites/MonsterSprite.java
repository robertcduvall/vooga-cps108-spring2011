package games.keen.sprites;

import games.keen.KeenGame;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.AnimatedSprite;

@SuppressWarnings("serial")
public class MonsterSprite extends LevelSprite {

	protected boolean isDead;
	private VoogaGame game;
	private String name;
	private State state;
	private double oldX, oldY;
	
	private static class State {
		private int imageIndex;
		private Direction direction;
		
		public State(int imageIndex, Direction direction) {
			this.imageIndex = imageIndex;
			this.direction = direction;
		}
		
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof State)) return false;
			State s = (State) o;
			return s.imageIndex == imageIndex && s.direction.equals(direction);
		}
	}
	
	public MonsterSprite(KeenGame game, String name, int x, int y) {
		super(game, x, y);
		
		oldX = x;
		oldY = y;
		
		this.game = game;
		this.name = name;
		state = null;
		
		super.getAnimationTimer().setDelay(100);
		super.setAnimate(true);
		super.setLoopAnim(true);
	}
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
	
	protected void setAnimation(int imageIndex, Direction direction) {
		if(state != null && state.equals(new State(imageIndex, direction))) return;
		
		super.setImages(game.getImageLoader().getAnimation(name, imageIndex, direction));
		
		state = new State(imageIndex, direction);
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public void kill() {
		isDead = true;
	}
	
	public void revertPosition() {
		setX(getOldX());
		setY(getOldY());
	}
}
