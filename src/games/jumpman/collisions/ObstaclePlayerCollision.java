package games.jumpman.collisions;

import games.jumpman.JumpMan;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class ObstaclePlayerCollision extends BasicCollisionGroup<Sprite, Sprite> {

	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		
		JumpMan.myEventManager.fireEvent(this, "Game.SpawnPlayer");
		
	}

}
