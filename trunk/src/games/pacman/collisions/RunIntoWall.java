package games.pacman.collisions;

import games.pacman.sprites.PacMan;
import games.pacman.sprites.Wall;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;

/**
 * A collision group for testing whether pacman/ghost is running into wall. 
 * 
 * @author DJ Sharkey
 *
 */
public class RunIntoWall extends BasicCollisionGroup<PacMan, Wall>
{
    int num=0;
    
    /**
     * Handle a collision between a ball and a block.
     */
    @Override
    public void collided (PacMan pacman, Wall wall)
    {
    	//this.pixelPerfectCollision=true;
    	System.out.println(pacman.getAngle());
    	pacman.setAbsoluteSpeed(0);
       num++;
    }

}
