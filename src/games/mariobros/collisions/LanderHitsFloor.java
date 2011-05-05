package games.mariobros.collisions;

import games.mariobros.sprites.Block;
import games.mariobros.sprites.Lander;
import vooga.collisions.collisionManager.BasicCollisionGroup;

/**
 * 
 * @author Ethan Goh
 *
 */
public class LanderHitsFloor extends BasicCollisionGroup<Lander, Block>
{
	@Override
	public void collided(Lander lander, Block block)
	{
		System.out.println(lander.getAbsoluteSpeed());
		lander.explode();
	}

}
