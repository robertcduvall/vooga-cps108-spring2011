package games.frogger.collisions;

import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class FroggerVehicleCollision extends BasicCollisionGroup<Sprite, Sprite> {

	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		
	}

}
