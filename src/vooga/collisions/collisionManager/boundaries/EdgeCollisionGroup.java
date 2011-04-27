package vooga.collisions.collisionManager.boundaries;

import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.collisions.collisionManager.boundaries.*;

public abstract class EdgeCollisionGroup extends BasicCollisionGroup<EdgeSprite, Sprite> {


	public EdgeCollisionGroup() {
		super();
	}

	public EdgeCollisionGroup(SpriteGroup<Sprite> s2) {
		super(new EdgeSpriteGroup("edgeGroup", s2.getBackground()), s2);
	}



}
