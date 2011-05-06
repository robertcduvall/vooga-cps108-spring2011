package games.leapingSquirrel.collisions;

import games.leapingSquirrel.LeapingSquirrelGame;
import games.leapingSquirrel.sprites.Platform;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;

public class PlatformGround extends EdgeCollisionGroup<Platform>
{

    @Override
    public void collidedBottom (Platform p)
    {
        p.setActive(false);
        LeapingSquirrelGame.myEventManager.fireEvent(this, "Platform.Destroy", p.getClass());
        
    }

    @Override
    public void collidedLeft (Platform s)
    {
        //Do nothing
        ;        
    }

    @Override
    public void collidedRight (Platform s)
    {
        //Do nothing
        ;
        
    }

    @Override
    public void collidedTop (Platform s)
    {
        //Do nothing
        ;
    }

}
