package games.asteroids.collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class AsteroidBulletCollision extends BasicCollisionGroup
{

    @Override
    public void collided (Sprite asteroid, Sprite bullet)
    {
        Asteroid myasteroid = (Asteroid) asteroid;
        myasteroid.explode();
        
        Bullet mybullet = (Bullet) bullet;
        mybullet.destroy();

    }

}
