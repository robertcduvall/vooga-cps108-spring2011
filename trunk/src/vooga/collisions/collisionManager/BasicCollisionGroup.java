package vooga.collisions.collisionManager;

import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.BoundingBox;
import vooga.collisions.shapes.collisionShapes.ICollisionShape;
import vooga.sprites.spritegroups.BasicSpriteGroup;

public abstract class BasicCollisionGroup 
{
	protected ArrayList<BasicSpriteGroup> spriteGroups;
	
	private ShapeFactory shapeFactory = new ShapeFactory();
	
	public boolean pixelPerfectCollision;
	
	public BasicCollisionGroup()
	{
		//empty constructor
	}

	
	public void checkCollision(Sprite s1, Sprite s2)
	{
		//TODO: woo!
	}
}
