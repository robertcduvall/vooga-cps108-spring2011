package games.frogger.collisions;

import games.frogger.Frogger;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class FroggerVehicleCollision extends BasicCollisionGroup<Sprite, Sprite> {

	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		
		Frogger.myEventManager.fireEvent(this, "Game.NextFrog");
		
	}

}
