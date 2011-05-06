package games.leapingSquirrel.collisions;

import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class SquirrelBonus extends BasicCollisionGroup<Sprite, Sprite>
{

    @Override
    public void collided (Sprite s1, Sprite s2)
    {
        s2.setActive(false);
        // TODO: Increment score by 20
        // TODO: Special case if level 2
    }
    
}
