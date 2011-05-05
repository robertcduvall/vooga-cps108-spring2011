package games.pacman.collisions;

import games.pacman.sprites.players.Players;
import games.pacman.sprites.Wall;
import vooga.collisions.collisionManager.BasicCollisionGroup;

/**
 * A collision group for testing whether pacman is running into wall. 
 * 
 * @author DJ Sharkey
 *
 */
public class PacmanWall extends BasicCollisionGroup<Players, Wall>
{    
    /**
     * Handle a collision between a ball and a block.
     */
    @Override
    public void collided (Players player, Wall wall)
    {
    	player.collided(wall);
    }

}
