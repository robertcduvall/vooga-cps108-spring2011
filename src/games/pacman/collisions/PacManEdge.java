package games.pacman.collisions;

import games.pacman.sprites.PacMan;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;

public class PacManEdge extends EdgeCollisionGroup<PacMan> {

	@Override
	public void collidedTop(PacMan s) {
		placePacMan(s);
	}

	@Override
	public void collidedRight(PacMan s) {
		placePacMan(s);
	}

	@Override
	public void collidedLeft(PacMan s) {
		placePacMan(s);
	}

	@Override
	public void collidedBottom(PacMan s) {
		placePacMan(s);
	}
	
	private void placePacMan(PacMan s) {
		s.setAbsoluteSpeed(0);
		s.move(-1*s.getWidth()/2*Math.cos(s.getAngle()),
    			-1*s.getHeight()/2*Math.sin(s.getAngle()));	}

}
