package vooga.collisions.collisionManager.boundaries;

import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.collisions.collisionManager.boundaries.*;

public abstract class EdgeCollisionGroup<T extends Sprite> extends BasicCollisionGroup<EdgeSprite, T> {


	@Override
	public void setCollisionGroup(SpriteGroup<EdgeSprite> group1,
			SpriteGroup<T> group2) {
		super.setCollisionGroup(new EdgeSpriteGroup("edgeGroup", group2.getBackground()), group2);
	}

	@Override
	public void collided(EdgeSprite s1, T s2) {
		
		switch(s1.getEdgeID()){
			case EdgeSpriteGroup.RECTANGLE_TOP: if (s2.getVerticalSpeed() < 0) collidedTop(s2);break;
			case EdgeSpriteGroup.RECTANGLE_RIGHT: if (s2.getHorizontalSpeed() > 0) collidedRight(s2);break;
			case EdgeSpriteGroup.RECTANGLE_LEFT: if (s2.getHorizontalSpeed() < 0) collidedLeft(s2);break;
			case EdgeSpriteGroup.RECTANGLE_BOTTOM: if (s2.getVerticalSpeed() > 0) collidedBottom(s2);break;
		}
	}

	public abstract void collidedTop(T s);
	public abstract void collidedRight(T s);
	public abstract void collidedLeft(T s);
	public abstract void collidedBottom(T s);
	
	public EdgeCollisionGroup() {
		super();
	}

	public EdgeCollisionGroup(SpriteGroup<T> s2) {
		super(new EdgeSpriteGroup("edgeGroup", s2.getBackground()), s2);
	}



}
