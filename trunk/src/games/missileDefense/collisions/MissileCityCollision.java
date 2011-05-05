package games.missileDefense.collisions;

import games.missileDefense.sprites.City;
import games.missileDefense.sprites.Missile;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class MissileCityCollision extends BasicCollisionGroup<Missile, City>
{

	@Override
	public void collided(Missile s1, City s2) 
	{
		s1.setActive(false);
		s2.damage();
	}

}
