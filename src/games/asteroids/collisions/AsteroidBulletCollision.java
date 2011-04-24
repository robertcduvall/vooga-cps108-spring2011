package games.asteroids.collisions;

import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.intersections.PolygonPolygonFinder;
import vooga.sprites.improvedsprites.Sprite;


public class AsteroidBulletCollision extends BasicCollisionGroup
{

    @Override
    public void collided (Sprite s1, Sprite s2)
    {
        System.out.println("Collided!"+s1+" "+s2);
        s1.setActive(false);
        s2.setActive(false);
    }


    @Override
    public boolean areCollide (Sprite s1, Sprite s2)
    {
        if (s1.isActive() && s2.isActive() && s1 != s2) // FIXME
        {
            PolygonPolygonFinder finder = new PolygonPolygonFinder();
            return finder.areIntersecting(s1.getCollisionShape(),
                                          s2.getCollisionShape());
        }
        return false;
    }
}
