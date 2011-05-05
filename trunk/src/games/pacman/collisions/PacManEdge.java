package games.pacman.collisions;

import games.pacman.sprites.PacMan;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;

public class PacManEdge extends EdgeCollisionGroup<PacMan> {

	@Override
	public void collidedTop(PacMan s) {
		s.setLocation(s.getX(),1);
	}

	@Override
	public void collidedRight(PacMan s) {
		System.out.println("right");
		System.out.println(s.getBackground().getWidth());
		s.setLocation(s.getBackground().getWidth()-s.getCollisionShape().getWidth()-1, s.getY());
	}

	@Override
	public void collidedLeft(PacMan s) {
		s.setLocation(1, s.getY());
	}

	@Override
	public void collidedBottom(PacMan s) {
		s.setLocation(s.getX(),s.getBackground().getHeight()-s.getCollisionShape().getHeight()-1);
	}
	
}

