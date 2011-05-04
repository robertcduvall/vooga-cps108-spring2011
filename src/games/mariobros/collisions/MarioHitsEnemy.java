package games.mariobros.collisions;

import games.mariobros.sprites.Enemy;
import games.mariobros.sprites.Mario;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;

public class MarioHitsEnemy extends BasicCollisionGroup<Mario, Enemy>
{
    /**
     * Stomp.
     */
    @Override
    public boolean areCollide(Mario p, Enemy e)
    {
    	
    	return CollisionManager.isPixelCollide(p.getX(), p.getY(), p.getImage(), e.getX(), e.getY(), e.getImage());
    }
    
    @Override
    public void collided (Mario p, Enemy e)
    {
        p.setAngle(360-p.getAngle());
        e.setActive(false);
    }
}
