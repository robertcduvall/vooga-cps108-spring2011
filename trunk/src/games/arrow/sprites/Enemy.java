package games.arrow.sprites;

import games.arrow.ArrowGame;
import games.breakout.Breakout;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.sprites.spritebuilder.components.collisions.CollisionShapeC;
import vooga.sprites.spritebuilder.components.physics.MasslessPhysicsC;

public class Enemy extends GoodSprite {

	private boolean amDead;
	

	public Enemy(BufferedImage image, int x, int y) {
		super(image, x, y);
		this.setHorizontalSpeed((Math.random()-.5)*0.1);
//    	this.setSize(getHeight()/4,getWidth()/4);
		this.addComponent(new CollisionPolygonC(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, getWidth(), getHeight()), 3)));
		setDead(false);
	}

	public void die() {
		this.addComponent(new MasslessPhysicsC());
		setDead(true);
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		super.render(g, x, y);
		this.getCollisionShape().render(g);
	}

	public void setDead(boolean amDead) {
		this.amDead = amDead;
	}

	public boolean isDead() {
		return amDead;
	}
	

}
