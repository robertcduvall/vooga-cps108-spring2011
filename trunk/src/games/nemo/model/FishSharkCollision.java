package games.nemo.model;

import games.nemo.model.rounds.GameRound;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * Collision manager that detects collisions between nemo and sharks
 * @author Yin
 *
 */
public class FishSharkCollision extends BasicCollisionGroup 
{
	public FishSharkCollision(SpriteGroup s1, SpriteGroup s2)
	{
		this.setCollisionGroup(s1, s2);
		this.pixelPerfectCollision = true;
		this.checkCollision();
	}

	/**
	 * If collided, game status is set as LOSS
	 */
	@Override
	public void collided(Sprite s1, Sprite s2) {
		GameRound.setLoss();
	}
}
