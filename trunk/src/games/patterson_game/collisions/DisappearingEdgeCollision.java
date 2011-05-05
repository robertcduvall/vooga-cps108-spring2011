package games.patterson_game.collisions;

import games.patterson_game.refactoredVooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;

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
