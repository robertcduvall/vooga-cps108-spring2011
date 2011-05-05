package games.missilecommand.collisions;

import vooga.collisions.collisionManager.BasicCollisionGroup;

import games.missilecommand.sprites.Bullet;
import games.missilecommand.sprites.Bomb;

/**
 * A collision between a bullet and a bomb. Simply destroys both.
 * @author Alex Lee (hl69)
 */
public class BulletBombCollision extends BasicCollisionGroup<Bullet, Bomb>
{

    @Override
    public void collided(Bullet s1, Bomb s2) 
    {
        s1.setActive(false);
        s2.setActive(false);
    }

}
