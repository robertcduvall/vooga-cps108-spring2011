package games.asteroids.collisions;

import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.collisions.collisionManager.boundaries.EdgeSprite;
import vooga.sprites.improvedsprites.Sprite;

public class ShipEdgeCollision extends EdgeCollisionGroup {

	@Override
	public void collidedTop(Sprite s) {
		s.setSpeed(s.getHorizontalSpeed(), -s.getVerticalSpeed());
	}

	@Override
	public void collidedRight(Sprite s) {
		s.setSpeed(-s.getHorizontalSpeed(), s.getVerticalSpeed());
	}

	@Override
	public void collidedLeft(Sprite s) {
		
		s.setSpeed(-s.getHorizontalSpeed(), s.getVerticalSpeed());
	}

	@Override
	public void collidedBottom(Sprite s) {
		s.setSpeed(s.getHorizontalSpeed(), -s.getVerticalSpeed());
	}


}
