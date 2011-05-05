package games.mariobros.collisions;

import games.mariobros.sprites.Asteroid;
import games.mariobros.sprites.Lander;
import vooga.collisions.collisionManager.BasicCollisionGroup;

/**
 * 
 * @author Ethan Goh
 *
 */
public class LanderHitsAsteroid extends BasicCollisionGroup<Lander, Asteroid>
{
	@Override
	public void collided(Lander lander, Asteroid block)
	{
		System.out.println(lander.getAbsoluteSpeed());
		lander.explode();
	}

}
