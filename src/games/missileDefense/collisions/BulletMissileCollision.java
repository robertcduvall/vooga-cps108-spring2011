package games.missileDefense.collisions;

import games.missileDefense.sprites.Bullet;
import games.missileDefense.sprites.Missile;
import vooga.collisions.collisionManager.BasicCollisionGroup;
/**
 * 
 * @author johnegan
 *pretty self explanatory
 */
public class BulletMissileCollision extends BasicCollisionGroup<Bullet, Missile>
{

	@Override
	public void collided(Bullet s1, Missile s2) 
	{
		s1.setActive(false);
		s2.setActive(false);
	}

}
