package games.missileDefense.collisions;

import games.missileDefense.sprites.Gun;
import games.missileDefense.sprites.Missile;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class MissileGunCollision extends BasicCollisionGroup<Missile, Gun>
{

	@Override
	public void collided(Missile s1, Gun s2) 
	{
		s1.setActive(false);
		s2.damage();
	}

}
