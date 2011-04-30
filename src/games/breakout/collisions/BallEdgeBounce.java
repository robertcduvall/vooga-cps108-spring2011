package games.breakout.collisions;

import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class BallEdgeBounce extends EdgeCollisionGroup
{
    @Override
    public void collidedTop (Sprite s)
    {
        s.setVerticalSpeed(-s.getVerticalSpeed());
    }


    @Override
    public void collidedRight (Sprite s)
    {
        s.setHorizontalSpeed(-s.getHorizontalSpeed());
    }


    @Override
    public void collidedLeft (Sprite s)
    {
        s.setHorizontalSpeed(-s.getHorizontalSpeed());
    }


    @Override
    public void collidedBottom (Sprite s)
    {
        // TODO lose the game
    }

}
