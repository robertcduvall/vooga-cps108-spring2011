package games.patterson_game.refactoredVooga.collisions.collisionManager.boundaries;

import games.patterson_game.refactoredVooga.collisions.collisionManager.BasicCollisionGroup;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.spritegroups.SpriteGroup;

public abstract class EdgeCollisionGroup extends BasicCollisionGroup<EdgeSprite, Sprite> {


	@Override
	public void setCollisionGroup(SpriteGroup<EdgeSprite> group1,
			SpriteGroup<Sprite> group2) {
		super.setCollisionGroup(new EdgeSpriteGroup("edgeGroup", group2.getBackground()), group2);
	}

	@Override
	public void collided(EdgeSprite s1, Sprite s2) {
		
		switch(s1.getEdgeID()){
			case EdgeSpriteGroup.RECTANGLE_TOP: if (s2.getVerticalSpeed() < 0) collidedTop(s2);break;
			case EdgeSpriteGroup.RECTANGLE_RIGHT: if (s2.getHorizontalSpeed() > 0) collidedRight(s2);break;
			case EdgeSpriteGroup.RECTANGLE_LEFT: if (s2.getHorizontalSpeed() < 0) collidedLeft(s2);break;
			case EdgeSpriteGroup.RECTANGLE_BOTTOM: if (s2.getVerticalSpeed() > 0) collidedBottom(s2);break;
		}
	}

	public abstract void collidedTop(Sprite s);
	public abstract void collidedRight(Sprite s);
	public abstract void collidedLeft(Sprite s);
	public abstract void collidedBottom(Sprite s);
	
	public EdgeCollisionGroup() {
		super();
	}

	public EdgeCollisionGroup(SpriteGroup<Sprite> s2) {
		super(new EdgeSpriteGroup("edgeGroup", s2.getBackground()), s2);
	}



}
