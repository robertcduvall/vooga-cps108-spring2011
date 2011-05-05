package games.jumper.collisions;

import games.jumper.Jumper;
import games.jumper.sprites.Avatar;
import games.jumper.sprites.Spikes;
import vooga.collisions.collisionManager.BasicCollisionGroup;
/**
 * Collision detection for Avatar and spikes
 * @author Charlie Hatcher
 *
 */
public class AvatarToSpikes extends BasicCollisionGroup<Avatar,Spikes>{

	/**
	 * If the collision is occurred, fire the event to notify the game the player has
	 * died.
	 */
	@Override
	public void collided(Avatar s1, Spikes s2) {
		Jumper.myEventManager.fireEvent(this, "Game.Avatar.Died");
	}

}