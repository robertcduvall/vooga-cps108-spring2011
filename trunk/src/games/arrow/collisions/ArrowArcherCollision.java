package games.arrow.collisions;

import games.arrow.sprites.Archer;
import games.arrow.sprites.Arrow;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class ArrowArcherCollision extends BasicCollisionGroup<Arrow, Archer> {

	@Override
	public void collided(Arrow s1, Archer s2) {
//		s1.setActive(false);
		s2.die();
	}


}
