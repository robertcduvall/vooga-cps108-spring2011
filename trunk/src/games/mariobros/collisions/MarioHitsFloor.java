package games.mariobros.collisions;

import games.mariobros.sprites.Floor;
import games.mariobros.sprites.Mario;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;

public class MarioHitsFloor extends BasicCollisionGroup<Mario, Floor>
{
    /**
     * Stomp.
     */
    @Override
    public boolean areCollide(Mario p, Floor e)
    { 	
    	return CollisionManager.isPixelCollide(p.getX(), p.getY(), p.getImage(), e.getX(), e.getY(), e.getImage());
    }
    
    @Override
    public void collided (Mario p, Floor e)
    {
        p.setAngle(360-p.getAngle());
        e.setActive(false);
    }
}
