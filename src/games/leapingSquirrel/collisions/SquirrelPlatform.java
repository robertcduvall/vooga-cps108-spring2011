package games.leapingSquirrel.collisions;

import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class SquirrelPlatform extends BasicCollisionGroup<Sprite, Sprite> 
{

    @Override
    public void collided (Sprite s1, Sprite s2)
    {   
        s1.setVerticalSpeed(-0.6);
        s1.accelerate(0.1);   
    }
}
