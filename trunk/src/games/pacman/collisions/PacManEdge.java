package games.pacman.collisions;

import games.pacman.sprites.PacMan;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;

public class PacManEdge extends EdgeCollisionGroup<PacMan> {

	@Override
	public void collidedTop(PacMan s) {
		placePacMan(s, s.getX(), s.getY()+s.getHeight()/2);
	}

	@Override
	public void collidedRight(PacMan s) {
		placePacMan(s, s.getX()-s.getWidth()/2,s.getY());
	}

	@Override
	public void collidedLeft(PacMan s) {
		placePacMan(s, s.getX()+s.getWidth()/2,s.getY());
	}

	@Override
	public void collidedBottom(PacMan s) {
		placePacMan(s,s.getX(), s.getY()-s.getHeight()/2);
	}
	
	private void placePacMan(PacMan s, double x, double y) {
		s.setAbsoluteSpeed(0);
		s.setLocation(x,y);		
	}

}
