package vooga.physics.mediators;

import java.awt.Point;
import vooga.physics.engine.BasePhysicsEngine;
import vooga.physics.interfaces.newtonian.INewtonianMovable;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.PhysicsC;
import vooga.util.buildable.components.IComponent;
import vooga.util.math.Angle;

public class VoogaPhysicsMediator {

    private BasePhysicsEngine defaultPhysics;

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
            defaultPhysics.collision(c, getBestCollisionComponent(object2), angleOfImpact, pointOfImpact, coefficientOfRestitution);
        }
        for (IComponent c : object2.getComponents()) {
            defaultPhysics.collision(c, getBestCollisionComponent(object1), angleOfImpact, pointOfImpact, coefficientOfRestitution);
        }
    }
//
//    public static Velocity getSpriteVelocity(Sprite sprite) {
//        return new Velocity(sprite.getHorizontalSpeed(), -sprite.getVerticalSpeed());
//    }
//
//    public static void setSpriteVelocity(Sprite sprite, Velocity velocity) {
//        sprite.setHorizontalSpeed(velocity.getXComponent());
//        sprite.setVerticalSpeed(-velocity.getYComponent());
//    }
    
    public INewtonianMovable getBestCollisionComponent(Sprite sprite){
        if (sprite.carriesComponent(PhysicsC.class))
            return sprite.getComponent(PhysicsC.class);
        else
            return spriteToMovable(sprite);//TODO: Will sprite be an Imovable?
    }

    public static INewtonianMovable spriteToMovable(Sprite otherObject) {
        // TODO: Replace this method with appropriate use of the mediator?
        return null;
    }
}
