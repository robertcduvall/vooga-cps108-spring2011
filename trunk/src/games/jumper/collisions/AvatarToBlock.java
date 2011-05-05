package games.jumper.collisions;

import games.jumper.sprites.Avatar;
import games.jumper.sprites.Block;
import vooga.collisions.collisionManager.BasicCollisionGroup;

/**
 * Collision detection between the Avatar and the blocks.
 * @author Charlie Hatcher
 *
 */
public class AvatarToBlock extends BasicCollisionGroup<Avatar, Block >{

   /**
    * Stops the avatar from being affected by gravity if the collision occurs
    */
	@Override
	public void collided(Avatar avatar, Block block) {
		/*
		 * TODO: Set it up so that this only occurs if the avatar collides with the 
		 * top of the block. If the sprite hits the sides or bottom, it should fall 
		 * back down,
		 */
		avatar.stopMoving();
	}


	
	
	
}