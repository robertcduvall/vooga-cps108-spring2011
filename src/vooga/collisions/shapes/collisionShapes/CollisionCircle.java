package vooga.collisions.shapes.collisionShapes;

import java.awt.Shape;

import vooga.collisions.shapes.regularShapes.Circle;

public class CollisionCircle extends Circle implements Shape, ICollisionShape
{

	@Override
	public boolean intersects(ICollisionShape s) {
		// TODO Auto-generated method stub
		return false;
	}
}
