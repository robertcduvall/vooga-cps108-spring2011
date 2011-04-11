package collisions;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;


/**
 * A boundary collision check which bounces the sprite off of the walls
 */
public class WallBounceCollision extends CollisionBounds
{
    public WallBounceCollision (Background background)
    {
        super(background);
    }

    public void collided (Sprite collidedSprite)
    {
        if (isCollisionSide(BOTTOM_COLLISION) || isCollisionSide(TOP_COLLISION))
        {
            revertPosition1();
            collidedSprite.setSpeed(collidedSprite.getHorizontalSpeed(),
                                    collidedSprite.getVerticalSpeed() * -1);
        }
        if (isCollisionSide(LEFT_COLLISION) || isCollisionSide(RIGHT_COLLISION))
        {
            revertPosition1();
            collidedSprite.setSpeed(collidedSprite.getHorizontalSpeed() * -1,
                                    collidedSprite.getVerticalSpeed());
        }
    }
}
