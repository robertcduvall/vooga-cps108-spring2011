package games.mariobros.collisions;

import games.mariobros.sprites.Lander;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

/**
 * 
 * @author Ethan Goh
 *
 */
public class LanderEdgeHit extends EdgeCollisionGroup
{

	@Override
	public void collidedRight(Sprite s)
	{
		((Lander) s).explode();
	}

	@Override
	public void collidedLeft(Sprite s)
	{
		((Lander) s).explode();
	}

	@Override
	public void collidedBottom(Sprite s)
	{
		((Lander) s).explode();
	}

	@Override
	public void collidedTop(Sprite s)
	{

	}

}
