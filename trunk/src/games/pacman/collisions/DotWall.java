package games.pacman.collisions;

import games.pacman.sprites.Dot;
import games.pacman.sprites.PacMan;
import games.pacman.sprites.Wall;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class DotWall  extends BasicCollisionGroup<Dot, Wall>{
		
	@Override
	public void collided(Dot dot, Wall wall) {
		getGroup1().remove(dot);
	}

}
