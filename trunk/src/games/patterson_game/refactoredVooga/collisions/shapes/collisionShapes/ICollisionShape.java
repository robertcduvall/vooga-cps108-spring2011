package games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes;

import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.IShape;

public interface ICollisionShape extends IShape
{
	public boolean intersects(ICollisionShape s);
}
