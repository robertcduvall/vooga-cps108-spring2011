package games.arrow.collisions;

import games.arrow.sprites.Arrow;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.PermAccelerationC;

public class ArrowEdgeCollision extends EdgeCollisionGroup<Arrow> {

	@Override
	public void collidedTop(Arrow s) {
	}

	@Override
	public void collidedRight(Arrow s) {
		destroyArrow(s);
	}

	@Override
	public void collidedLeft(Arrow s) {
		destroyArrow(s);
	}

	@Override
	public void collidedBottom(Arrow s) {
		destroyArrow(s);
	}

	private void destroyArrow(Arrow s) {
		s.setAbsoluteSpeed(0);
		s.removeComponent(PermAccelerationC.class);
		getGroup2().remove(s);
		
	}


}
