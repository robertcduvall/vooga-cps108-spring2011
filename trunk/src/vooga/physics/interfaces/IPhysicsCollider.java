package vooga.physics.interfaces;

import java.awt.Point;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.PhysicsC;
import vooga.util.math.Angle;

/**
 * TODO: Make sure this gets integrate with collisions
 * @author Nathan Klug
 *
 */
public interface IPhysicsCollider{

    public void collisionOccurred(Object otherSprite, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution);
}
