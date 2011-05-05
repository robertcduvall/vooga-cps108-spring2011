package games.pacman.collisions;

import games.pacman.sprites.players.Players;
import games.pacman.sprites.Wall;
import vooga.collisions.collisionManager.BasicCollisionGroup;

/**
 * A collision group for testing whether pacman/ghost is running into wall. 
 * 
 * @author DJ Sharkey
 *
 */
public class EnemyWall extends BasicCollisionGroup<Players, Wall>
{    
    /**
     * Handle a collision between an enemy and a wall.
     */
    @Override
    public void collided (Players player, Wall wall)
    {
    	player.collided(wall);
    	player.respondToWall();
    }

}
