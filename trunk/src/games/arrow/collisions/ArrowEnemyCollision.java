package games.arrow.collisions;

import games.arrow.sprites.Arrow;
import games.arrow.sprites.Enemy;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class ArrowEnemyCollision extends BasicCollisionGroup<Arrow, Enemy>{

	@Override
	public void collided(Arrow s1, Enemy s2) {
		s1.setActive(false);
		s2.setSpeed(0,.1);
		//TODO Increase Score
	}

}
