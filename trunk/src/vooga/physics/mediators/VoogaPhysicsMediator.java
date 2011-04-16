package vooga.physics.mediators;

import java.awt.Point;
import vooga.physics.engine.AbstractPhysicsEngine;
import vooga.physics.interfaces.IMovable;
import vooga.physics.interfaces.IPhysicsCollider;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.IComponent;
import vooga.util.math.Angle;

public class VoogaPhysicsMediator {

    private AbstractPhysicsEngine myPhysics;

    /**
     * General collision method. Tells the two physical objects that a collision
     * occurred.
     * 
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     * @param coefficientOfRestitution
     */
    public void collision(Sprite object1, Sprite object2, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        for (IComponent c : object1.getComponents()) {
            myPhysics.collision(c, object2, angleOfImpact, pointOfImpact, coefficientOfRestitution);
        }
        for (IComponent c : object2.getComponents()) {
            myPhysics.collision(c, object1, angleOfImpact, pointOfImpact, coefficientOfRestitution);
        }
    }

    public static Velocity getSpriteVelocity(Sprite sprite) {
        return new Velocity(sprite.getHorizontalSpeed(), -sprite.getVerticalSpeed());
    }

    public static void setSpriteVelocity(Sprite sprite, Velocity velocity) {
        sprite.setHorizontalSpeed(velocity.getXComponent());
        sprite.setVerticalSpeed(-velocity.getYComponent());
    }
    
    //TODO: fill this in
    public static IMovable spriteToMovable(Sprite s){
        return null;
    }
}