package games.leapingSquirrel.collisions;

import games.leapingSquirrel.LeapingSquirrelGame;
import games.leapingSquirrel.sprites.Squirrel;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;

public class SquirrelGround extends EdgeCollisionGroup<Squirrel>
{

    @Override
    public void collidedBottom (Squirrel s)
    {
        //Squirrel dies (but not really - just reset)
        if (s.HAS_STARTED)
        {
            s.setActive(false);
            LeapingSquirrelGame.myEventManager.fireEvent(this, "Game.NewSquirrel", s.getClass()); 
        }
        else
        {
            s.setVerticalSpeed(0);
        }
    }

    @Override
    public void collidedLeft (Squirrel s)
    {
        s.setHorizontalSpeed(0);
    }

    @Override
    public void collidedRight (Squirrel s)
    {
        s.setHorizontalSpeed(0);
    }

    @Override
    public void collidedTop (Squirrel s)
    {
        s.setVerticalSpeed(0);   
    }
}
