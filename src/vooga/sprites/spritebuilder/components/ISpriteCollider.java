package vooga.sprites.spritebuilder.components;

import java.awt.Point;
import vooga.physics.interfaces.IPhysics;
import vooga.util.math.Angle;

/**
 * TODO: Make sure this gets integrate with collisions
 * @author Nathan Klug
 *
 */
public interface ISpriteCollider extends IPhysics{

    public void collisionOccurred(ISpriteCollider otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution);
}
