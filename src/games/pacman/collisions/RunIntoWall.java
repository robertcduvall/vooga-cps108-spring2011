package games.pacman.collisions;

import games.pacman.sprites.PacMan;
import games.pacman.sprites.AbstractWall;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;

/**
 * A collision group for testing whether pacman/ghost is running into wall. 
 * 
 * @author DJ Sharkey
 *
 */
public class RunIntoWall extends BasicCollisionGroup<PacMan, AbstractWall>
{    
    /**
     * Handle a collision between a ball and a block.
     */
    @Override
    public void collided (PacMan pacman, AbstractWall wall)
    {
    	//this.pixelPerfectCollision=true;
    	///System.out.println(pacman.getAngle());
    	pacman.setAbsoluteSpeed(0);
    /*	pacman.move(-1*pacman.getWidth()/2*Math.cos(Math.toRadians(pacman.getAngle())),
    			-1*pacman.getHeight()/2*Math.sin(Math.toRadians(pacman.getAngle())));*/
    	pacman.move(-1*100*Math.cos(Math.toRadians(pacman.getAngle())),
    			-1*100*Math.sin(Math.toRadians(pacman.getAngle())));
    }

}
