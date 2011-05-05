package games.pacman.collisions;

import games.pacman.sprites.players.PacMan;
import games.pacman.sprites.players.Players;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class PacmanEnemy extends BasicCollisionGroup<PacMan, Players> {


    
	@Override
	public void collided(PacMan pacman, Players player) {
		pacman.loseLife();
	}

}