package games.jumper.collisions;

import games.jumper.sprites.Avatar;
import games.jumper.sprites.Block;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class AvatarToBricks extends BasicCollisionGroup<Avatar,Block>{

	/**
	 * Sets the avatar's y position to the position of the block, this is designed
	 * to treat the blocks as the ground
	 */
	@Override
	public void collided(Avatar s1, Block s2) {
		s1.setY(s2.getCenterY());
	}

}