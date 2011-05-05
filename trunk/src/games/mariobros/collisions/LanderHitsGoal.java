package games.mariobros.collisions;

import games.mariobros.sprites.GoalBlock;
import games.mariobros.sprites.Lander;
import vooga.collisions.collisionManager.BasicCollisionGroup;

/**
 * 
 * @author Ethan Goh
 *
 */
public class LanderHitsGoal extends BasicCollisionGroup<Lander, GoalBlock>
{

	@Override
	public void collided(Lander lander, GoalBlock block)
	{
		System.out.println(lander.getAbsoluteSpeed());
		if (lander.getAbsoluteSpeed() > 0.1)
		{
			lander.explode();
		}
		else
		{
			System.out.println("HAHAHA");
			lander.setSafe(true);
		}
	}

}
