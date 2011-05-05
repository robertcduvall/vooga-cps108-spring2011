package games.missileDefense.collisions;

import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class MissileEdgeCollision extends EdgeCollisionGroup
{

	@Override
	public void collidedTop(Sprite s) {
		// do nothing
		
	}

	@Override
	public void collidedRight(Sprite s) {
		s.setActive(false);
	}

	@Override
	public void collidedLeft(Sprite s) {
		s.setActive(false);
	}

	@Override
	public void collidedBottom(Sprite s) {
		s.setActive(false);
	}


}
