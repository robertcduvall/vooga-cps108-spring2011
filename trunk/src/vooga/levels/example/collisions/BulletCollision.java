package collisions;

import sprites.Alien;
import sprites.Ship;
import weapons.AbstractWeapon;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


/**
 * A collision manager which checks for damage between bullets, ships & aliens
 */
public class BulletCollision extends BasicCollisionGroup
{
    public void collided (Sprite b, Sprite t)
    {
        if (b instanceof AbstractWeapon)
        {
            AbstractWeapon bullet = (AbstractWeapon) b;
            if (t instanceof Alien)
            {
                bullet.performDamage(t);
            }

            if (t instanceof Ship)
            {
                Ship ship = (Ship) t;
                ship.takeDamage(bullet.getDamage());
            }
            bullet.remove(); // By default, bullets are removed when they hit something.
                             //  The tornado is the exception
        }
    }
}
