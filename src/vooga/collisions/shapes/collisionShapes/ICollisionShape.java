package vooga.collisions.shapes.collisionShapes;

import vooga.collisions.shapes.regularShapes.IShape;

public interface ICollisionShape extends IShape
{
	public boolean intersects(ICollisionShape s);
}
