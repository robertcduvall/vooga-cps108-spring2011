package games.jumper.collisions;

import games.jumper.sprites.Avatar;
import games.jumper.sprites.Door;
import vooga.collisions.collisionManager.BasicCollisionGroup;
/**
 * Collision detection for the avatar and the door.
 * @author Charlie Hatcher
 *
 */
public class AvatarToDoor extends BasicCollisionGroup<Avatar, Door >{

	/**
	 * If the collision occurs, set the door to null, that is how the goal checks to see
	 * if the goal has been accomplished.
	 */
	@Override
	public void collided(Avatar s1, Door s2) {
		s2.setActive(false);
	}

}