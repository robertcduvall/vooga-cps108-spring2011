package games.mariobros.collisions;

import games.mariobros.MarioBros;
import games.mariobros.sprites.Koopa;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class KoopaHitsFloor extends EdgeCollisionGroup
{    
    @Override
    public void collidedTop (Sprite s)
    {
        ((Koopa) s).bounceDown();
    }


    @Override
    public void collidedRight (Sprite s)
    {
        ((Koopa) s).bounceLeft();
    }


    @Override
    public void collidedLeft (Sprite s)
    {
        ((Koopa) s).bounceRight();
    }


    @Override
    public void collidedBottom (Sprite s)
    {
        s.setActive(false);
        
        MarioBros.eventManager.fireEvent(this, "Game.BallLost");
    }

}
