package games.pacman.collisions;

import games.pacman.sprites.players.PacMan;
import games.pacman.sprites.Wall;
import vooga.collisions.collisionManager.BasicCollisionGroup;

/**
 * A collision group for testing whether pacman/ghost is running into wall. 
 * 
 * @author DJ Sharkey
 *
 */
public class RunIntoWall extends BasicCollisionGroup<PacMan, Wall>
{    
    /**
     * Handle a collision between a ball and a block.
     */
    @Override
    public void collided (PacMan pacman, Wall wall)
    {
    	pacman.collided(wall);
    }

}
