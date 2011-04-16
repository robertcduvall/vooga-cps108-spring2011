package vooga.physics.interfaces;

import java.awt.Point;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.PhysicsC;
import vooga.util.math.Angle;

/**
 * @author Nathan Klug
 *
 */
public interface IPhysicsCollider{

    public void collisionOccurred(Object otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution);
}
