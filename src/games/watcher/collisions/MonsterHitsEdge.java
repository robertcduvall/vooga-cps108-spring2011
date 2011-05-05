package games.watcher.collisions;

import games.watcher.sprites.Monster;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

/**
 * A collision group for monster to wall collisions.
 * 
 * @author Conrad Haynes
 *
 */
@SuppressWarnings("unchecked")
public class MonsterHitsEdge extends EdgeCollisionGroup
{
	public void collidedTop (Sprite s)
    {
        ((Monster) s).bounceDown();
    }

	public void collidedRight (Sprite s)
    {
        ((Monster) s).bounceLeft();
    }


    public void collidedLeft (Sprite s)
    {
        ((Monster) s).bounceRight();
    }


    public void collidedBottom (Sprite s)
    {
    	((Monster) s).bounceUp();
    }
}
