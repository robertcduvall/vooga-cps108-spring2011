package vooga.viewTeam.refactored;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public abstract class NewColGroup extends CollisionGroup{

	@Override
	public void collided(Sprite s1, Sprite s2) {
		this.collidedNew((NewSprite) s1, (NewSprite) s2);		

	}
	
	public abstract void collidedNew(NewSprite inner, NewSprite outer);


}
