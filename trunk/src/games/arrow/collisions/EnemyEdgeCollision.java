package games.arrow.collisions;

import games.arrow.sprites.Enemy;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class EnemyEdgeCollision extends EdgeCollisionGroup<Enemy> {

	@Override
	public void collidedTop(Enemy s) {
		s.setSpeed(s.getHorizontalSpeed(), -s.getVerticalSpeed()*2);
	}

	@Override
	public void collidedRight(Enemy s) {
		s.setSpeed(-s.getHorizontalSpeed(), s.getVerticalSpeed());
	}

	@Override
	public void collidedLeft(Enemy s) {
		s.setSpeed(-s.getHorizontalSpeed(), s.getVerticalSpeed());
	}

	@Override
	public void collidedBottom(Enemy s) {
		System.out.println("COllided!");
		s.setAbsoluteSpeed(0);		
		s.setActive(false);
	}


}
