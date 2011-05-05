package games.jumper.collisions;

import games.jumper.Jumper;
import games.jumper.sprites.Avatar;
import games.jumper.sprites.Spikes;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class AvatarToSpikes extends BasicCollisionGroup<Avatar,Spikes>{

	/**public AvatarToSpikes(){
		super();
		
	}
	public AvatarToSpikes(SpriteGroup<Avatar> s1, SpriteGroup<Spikes> s2){
		super(s1,s2);
		System.out.println("hello");
	}*/
	@Override
	public void collided(Avatar s1, Spikes s2) {
		System.out.println("hello");
		Jumper.myEventManager.fireEvent(this, "Game.Avatar.Died");
	}

}