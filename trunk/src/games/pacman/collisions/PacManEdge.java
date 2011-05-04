package games.pacman.collisions;

import games.pacman.sprites.PacMan;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;

public class PacManEdge extends EdgeCollisionGroup<PacMan> {

	@Override
	public void collidedTop(PacMan s) {
System.out.println("edge Top");		
	}

	@Override
	public void collidedRight(PacMan s) {
		System.out.println("edge Right");		
	}

	@Override
	public void collidedLeft(PacMan s) {
		System.out.println("edge Left");		
	}

	@Override
	public void collidedBottom(PacMan s) {
		System.out.println("edge Bottom");		
	}

}
