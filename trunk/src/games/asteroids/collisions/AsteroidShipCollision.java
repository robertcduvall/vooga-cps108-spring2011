package games.asteroids.collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class AsteroidShipCollision extends BasicCollisionGroup
{

    @Override
    public void collided (Sprite asteroid, Sprite ship)
    {
        Asteroid myasteroid = (Asteroid) asteroid;
        myasteroid.explode();
        
        Ship myship = (Ship) ship;
        myship.damage();
    }

}
