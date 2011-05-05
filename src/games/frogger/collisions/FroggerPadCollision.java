package games.frogger.collisions;

import games.frogger.Frogger;
import games.frogger.sprites.Frog;
import games.frogger.sprites.Pad;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class FroggerPadCollision extends BasicCollisionGroup<Frog, Pad> {

	@Override
	public void collided(Frog s1, Pad s2) {
		s1.setActiveFrog(false);
		s2.setActive(false);
		
		Frogger.myEventManager.fireEvent(this, "Game.NextFrog");
		
	}

}
