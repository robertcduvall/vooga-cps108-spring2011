package games.pacman.collisions;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Block;
import games.pacman.sprites.Dot;
import games.pacman.sprites.PacMan;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class PacmanDot extends BasicCollisionGroup<PacMan, Dot> {


    
	@Override
	public void collided(PacMan pacman, Dot dot) {
		getGroup2().remove(dot);
	}

}
