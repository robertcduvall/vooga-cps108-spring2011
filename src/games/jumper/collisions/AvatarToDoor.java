package games.jumper.collisions;

import games.jumper.sprites.Avatar;
import games.jumper.sprites.Door;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class AvatarToDoor extends BasicCollisionGroup<Avatar, Door >{

	@Override
	public void collided(Avatar s1, Door s2) {
		System.out.println(s1.getX());
		System.out.println(s1.getY());
		s2.setActive(false);
	}

}