package games.jumpman.collisions;

import games.jumpman.JumpMan;
import games.jumpman.sprites.Player;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class FinishPlayerCollision extends BasicCollisionGroup<Player, Sprite> {

	@Override
	public void collided(Player s1, Sprite s2) {
		
		JumpMan.myEventManager.fireEvent(this, "Game.LevelComplete");
		
	}

}
