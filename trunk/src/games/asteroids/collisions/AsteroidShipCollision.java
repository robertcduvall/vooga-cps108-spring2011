package games.asteroids.collisions;

import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;


public class AsteroidShipCollision extends BasicCollisionGroup
{

    @Override
    public void collided (Sprite s1, Sprite s2)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean areCollide (Sprite s1, Sprite s2)
    {
        return false;
    }
}
