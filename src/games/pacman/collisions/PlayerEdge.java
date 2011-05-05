package games.pacman.collisions;

import games.pacman.sprites.players.Players;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;

public class PlayerEdge extends EdgeCollisionGroup<Players> {

	@Override
	public void collidedTop(Players s) {
		s.setLocation(s.getX(),1);
s.changeAngle();
	}

	@Override
	public void collidedRight(Players s) {
		s.setLocation(s.getBackground().getWidth()-s.getCollisionShape().getWidth()-1, s.getY());
		s.changeAngle();
	}

	@Override
	public void collidedLeft(Players s) {
		s.setLocation(1, s.getY());
		s.changeAngle();
	}

	@Override
	public void collidedBottom(Players s) {
		s.setLocation(s.getX(),s.getBackground().getHeight()-s.getCollisionShape().getHeight()-1);
		s.changeAngle();
	}
	
}

