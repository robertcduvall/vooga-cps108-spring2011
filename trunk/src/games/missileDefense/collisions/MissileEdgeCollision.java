package games.missileDefense.collisions;

import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;
/**
 * 
 * @author johnegan
 *so we can get rid of missles that fall off the map
 */
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
