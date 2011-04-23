package games.pong.collisions;

import games.pong.sprites.Ball;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class BallAndPaddleCollision extends BasicCollisionGroup{

	@Override
	public void collided(Sprite s1, Sprite s2) {
		if(s1 instanceof Ball) 
			bounce(s1);
		else //s2 instanceof Ball
			bounce(s2);		
	}

	private void bounce(Sprite s) {
		s.setHorizontalSpeed(-s.getHorizontalSpeed());
		//reverse horizontal velocity
		
		s.setVerticalSpeed(-s.getVerticalSpeed());
		//reflect vertical velocity
		
	}

}
