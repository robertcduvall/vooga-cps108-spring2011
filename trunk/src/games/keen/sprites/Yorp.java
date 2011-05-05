package games.keen.sprites;

import games.keen.KeenGame;
import vooga.resources.Direction;

@SuppressWarnings("serial")
public class Yorp extends MonsterSprite {
	private enum State {
		MOVING_LEFT,
		MOVING_RIGHT,
		DEAD
	}

	private static final double SPEED = 1.5;
	
	private State myState;
	
	public Yorp(KeenGame game, int x, int y) {
		super(game, "yorp", x, y);
		myState = null;
		setState(State.MOVING_LEFT);
	}
	
	private void setState(State state) {
		if(state == myState) return;
		
		switch(state) {
		case MOVING_LEFT:
			super.setAnimation(0, Direction.WEST);
			break;
		case MOVING_RIGHT:
			super.setAnimation(0, Direction.EAST);
			break;
		case DEAD:
			super.setAnimation(2, Direction.NORTH);
		}
		myState = state;
	}
	
	public void setAnimation(String animation) {
		
	}
	@Override
	public void update(long elapsedTime) {
		double dx = 0;
		
		switch(myState) {
		case MOVING_LEFT:
			dx = -SPEED;
			break;
		case MOVING_RIGHT:
			dx = SPEED;
			break;
		case DEAD:
			dx = 0;
		}
		moveX(dx);
		super.update(elapsedTime);
	}
	
	/*
	@Override
	public void handleSideCollision() {
		if(myState == State.DEAD) return;
		
		switch(myState) {
		case MOVING_LEFT:
			setState(State.MOVING_RIGHT);
			break;
		case MOVING_RIGHT:
			setState(State.MOVING_LEFT);
		}
	}
	*/
	
	@Override
	public void kill() {
		setState(State.DEAD);
		super.kill();
	}
}
