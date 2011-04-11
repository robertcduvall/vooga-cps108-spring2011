package collisions;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;


/**
 * A boundary collision check which stops a sprite if it reaches a boundary
 */
public class WallStopCollision extends CollisionBounds
{
    public WallStopCollision (Background background)
    {
        super(background);
    }

    public void collided (Sprite collidedSprite)
    {
        revertPosition1();
    }
}
