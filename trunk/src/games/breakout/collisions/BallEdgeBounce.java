package games.breakout.collisions;

import games.breakout.Breakout;
import games.breakout.sprites.Ball;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

/**
 * Collision group for bouncing balls off the edge of the screen.
 * 
 * 
 * @author Misha
 */
public class BallEdgeBounce extends EdgeCollisionGroup
{    
    @Override
    public void collidedTop (Sprite s)
    {
        ((Ball) s).bounceDown();
    }


    @Override
    public void collidedRight (Sprite s)
    {
        ((Ball) s).bounceLeft();
    }


    @Override
    public void collidedLeft (Sprite s)
    {
        ((Ball) s).bounceRight();
    }


    @Override
    public void collidedBottom (Sprite s)
    {
        s.setActive(false);
        
        Breakout.eventManager.fireEvent(this, "Game.BallLost");
    }

}
