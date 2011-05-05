package games.missileDefense.collisions;

import games.missileDefense.sprites.City;
import games.missileDefense.sprites.Missile;
import vooga.collisions.collisionManager.BasicCollisionGroup;
/**
 * 
 * @author johnegan
 * missiles and cities don't get along...their relationship is...explosive
 */
public class MissileCityCollision extends BasicCollisionGroup<Missile, City>
{

	@Override
	public void collided(Missile s1, City s2) 
	{
		s1.setActive(false);
		s2.damage();
	}

}
