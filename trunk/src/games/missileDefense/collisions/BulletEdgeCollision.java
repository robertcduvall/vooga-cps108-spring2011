package games.missileDefense.collisions;

import games.missileDefense.sprites.Bullet;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;
/**
 * 
 * @author johnegan
 *Used to get rid of bullets when they cant hit anything anymore.
 */
public class BulletEdgeCollision extends EdgeCollisionGroup<Bullet>
{

	@Override
	public void collidedTop(Bullet s) {
		s.setActive(false);
		System.out.println("gone");
	}

	@Override
	public void collidedRight(Bullet s) {
		s.setActive(false);
	}

	@Override
	public void collidedLeft(Bullet s) {
		s.setActive(false);
	}

	@Override
	public void collidedBottom(Bullet s) {
		s.setActive(false);
	}


}
