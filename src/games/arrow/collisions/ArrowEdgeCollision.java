package games.arrow.collisions;

import games.arrow.sprites.Arrow;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.PermAccelerationC;

public class ArrowEdgeCollision extends EdgeCollisionGroup<Arrow> {

	@Override
	public void collidedTop(Arrow s) {
		s.setAbsoluteSpeed(0);
	}

	@Override
	public void collidedRight(Arrow s) {
		//do nothing
	}

	@Override
	public void collidedLeft(Arrow s) {
		//do nothing
	}

	@Override
	public void collidedBottom(Arrow s) {
		s.setAbsoluteSpeed(0);
		s.removeComponent(PermAccelerationC.class);
		getGroup2().remove(s);
	}


}
