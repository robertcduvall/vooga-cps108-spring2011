package vooga.physics;

import java.awt.Point;
import java.util.List;
import vooga.physics.util.IPhysicsToggle;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.physics.EmptyPhysicsC;
import vooga.util.math.Angle;

/**
 * Class created to mediate between Vooga and the physics framework. This means
 * that there should be no mention of sprites in the physics framework other
 * than here.
 * 
 * @author Nathan Klug
 * 
 */
public class VoogaPhysicsMediator{

    private static boolean isPhysicsOn = true;
    /**
     * General collision method. Tells the two physical objects that a collision
     * occurred.
     * 
     * TODO: Collisions between many types of components?
     * 
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     * @param coefficientOfRestitution
     */
    public static void collision(Sprite sprite1, Sprite sprite2, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        List<EmptyPhysicsC> physicsComponents1 = sprite1.getComponentsWhichSubclass(EmptyPhysicsC.class);
        List<EmptyPhysicsC> physicsComponents2 = sprite2.getComponentsWhichSubclass(EmptyPhysicsC.class);

        for (EmptyPhysicsC componentFromSprite1 : physicsComponents1) {
            for (EmptyPhysicsC componentFromSprite2 : physicsComponents2) {
                componentFromSprite1.applyCollision(componentFromSprite2.getCollisionBehavior(), angleOfImpact,
                        pointOfImpact, coefficientOfRestitution);
            }
        }
    }
    
    /**
     * Is physics on?
     */
    public static boolean isOn() {
        return isPhysicsOn;
    }

    /**
     * set physics on or off based on the parameter
     */
    public static void turnPhysicsOnOff(boolean isOn) {
        isPhysicsOn = isOn;
    }

    /**
     * Turns physics off
     */
    public void turnOff() {
        isPhysicsOn = false;
    }

    /**
     * Turns physics on
     */
    public void turnOn() {
        isPhysicsOn = true;
    }

    /**
     * Turns physics on if it is off and turns physics off if it is on
     */
    public void togglePhysicsOnOff() {
        isPhysicsOn = !isPhysicsOn;
    }
}
