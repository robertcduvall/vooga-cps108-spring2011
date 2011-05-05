package games.patterson_game.collisions;

import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

/**
 * Makes a sprite disappear once it crosses the screen boundaries
 * 
 * @author Andrew
 */
public class DisappearingEdgeCollision extends EdgeCollisionGroup
{

    @Override
    public void collidedTop (Sprite s)
    {
        s.setActive(false);
    }

    @Override
    public void collidedRight (Sprite s)
    {
        s.setActive(false);        
    }

    @Override
    public void collidedLeft (Sprite s)
    {
        s.setActive(false);        
    }

    @Override
    public void collidedBottom (Sprite s)
    {
        s.setActive(false);        
    }

}
