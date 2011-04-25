package games.pong.collisions;

import games.pong.sprites.Ball;
import games.pong.sprites.Paddle;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class BallAndPaddleCollision extends BasicCollisionGroup<Ball, Paddle>{

	public BallAndPaddleCollision(SpriteGroup<Ball> s1, SpriteGroup<Paddle> s2) {
		super(s1, s2);
	}

	@Override
	public void collided(Ball s1, Paddle s2) {
		bounce(s1);	
	}

	private void bounce(Sprite s) {
		s.setHorizontalSpeed(-s.getHorizontalSpeed());
		//reverse horizontal velocity		
	}

}
