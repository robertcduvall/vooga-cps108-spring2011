package vooga.physics;

import java.awt.Point;
import java.util.Collection;
import java.util.List;
import vooga.physics.util.Force;
import vooga.physics.util.VectorField;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.physics.EmptyPhysicsC;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.util.math.Angle;

/**
 * Class created to mediate between Vooga and the physics framework. This means
 * that there should be no mention of sprites in the physics framework other
 * than here.
 * 
 * @author Nathan Klug
 * 
 */
public class VoogaPhysicsMediator {

    private static boolean isPhysicsOn = true;

    /**
     * General collision method. Tells the two physical objects that a collision
     * occurred.
     * 
     * 
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     * @param coefficientOfRestitution
     */
    public static void collision(Sprite sprite1, Sprite sprite2, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        if (isOn()) {
            List<EmptyPhysicsC> physicsComponents1 = sprite1.getComponentsWhichSubclass(EmptyPhysicsC.class);
            List<EmptyPhysicsC> physicsComponents2 = sprite2.getComponentsWhichSubclass(EmptyPhysicsC.class);

            for (EmptyPhysicsC componentFromSprite1 : physicsComponents1) {
                for (EmptyPhysicsC componentFromSprite2 : physicsComponents2) {
                    componentFromSprite1.applyCollision(componentFromSprite2.getCollisionBehavior(), angleOfImpact,
                            pointOfImpact, coefficientOfRestitution);
                }
            }
        }
    }
    
    /**
     * Applies forces and fields to all of the given sprite groups
     * 
     * @param spriteGroups
     *            a collection of sprites
     * @param forces
     *            a collection of forces
     * @param lengthOfApplication
     *            the length of time the force is applied for
     */
    public static void applyPhysicsToSpriteGroups(Collection<SpriteGroup<Sprite>> spriteGroups, PhysicsManager physics, long lengthOfApplication) {
        if (isOn()) {
            applyForcesToSpriteGroups(spriteGroups, physics.getActiveWorldForces(), lengthOfApplication);
            applyFieldsToSpriteGroups(spriteGroups, physics.getActiveVectorFields(), lengthOfApplication);
        }
    }

    /**
     * Applies a collection of forces to a collection of sprite groups. Iterates over forces
     * first, so if there are none defined, saves a lot of computation.
     * 
     * @param spriteGroups
     *            a collection of sprites
     * @param forces
     *            a collection of forces
     * @param lengthOfApplication
     *            the length of time the force is applied for
     */
    public static void applyForcesToSpriteGroups(Collection<SpriteGroup<Sprite>> spriteGroups, Collection<Force> forces, long lengthOfApplication) {
        if (isOn()) {
            for (Force force : forces) {
                for (SpriteGroup<Sprite> spriteGroup : spriteGroups) {
                    for (Sprite spriteInGroup : spriteGroup.getSprites()) {
                        applyForceToSprite(spriteInGroup, force, lengthOfApplication);
                    }
                }
            }
        }
    }

    /**
     * Applies a collection of forces to a collection of sprites
     * 
     * @param sprites
     *            a collection of sprites
     * @param forces
     *            a collection of forces
     * @param lengthOfApplication
     *            the length of time the force is applied for
     */
    public static void applyForcesToSprites(Collection<Sprite> sprites, Collection<Force> forces, long lengthOfApplication) {
        if (isOn()) {
            for (Sprite sprite : sprites) {
                applyForcesToSprite(sprite, forces, lengthOfApplication);
            }
        }
    }

    /**
     * Applies a collection of forces to a sprite
     * 
     * @param sprites
     *            a collection of sprites
     * @param forces
     *            a collection of forces
     * @param lengthOfApplication
     *            the length of time the force is applied for
     */
    public static void applyForcesToSprite(Sprite sprite, Collection<Force> forces, long lengthOfApplication) {
        List<EmptyPhysicsC> physicsComponents = sprite.getComponentsWhichSubclass(EmptyPhysicsC.class);

        for (EmptyPhysicsC component : physicsComponents) {
            component.applyForces(forces, lengthOfApplication);
        }
    }
    
    /**
     * Applies a force to a sprite
     * 
     * @param sprite
     *            a sprite
     * @param force
     *            a forces
     * @param lengthOfApplication
     *            the length of time the force is applied for
     */
    public static void applyForceToSprite(Sprite sprite, Force force, long lengthOfApplication) {
        List<EmptyPhysicsC> physicsComponents = sprite.getComponentsWhichSubclass(EmptyPhysicsC.class);

        for (EmptyPhysicsC component : physicsComponents) {
            component.applyForce(force, lengthOfApplication);
        }
    }
    
    /**
     * Applies a collection of fields to a collection of sprite groups. Iterates over fields
     * first, so if there are none defined, saves a lot of computation.
     * 
     * @param spriteGroups
     *            a collection of spriteGroups
     * @param fields
     *            a collection of fields
     * @param lengthOfApplication
     *            the length of time the field is applied for
     */
    public static void applyFieldsToSpriteGroups(Collection<SpriteGroup<Sprite>> spriteGroups, Collection<VectorField> fields, long lengthOfApplication) {
        if (isOn()) {
            for (VectorField field : fields) {
                for (SpriteGroup<Sprite> spriteGroup : spriteGroups) {
                    for (Sprite spriteInGroup : spriteGroup.getSprites()) {
                        applyFieldToSprite(spriteInGroup, field, lengthOfApplication);
                    }
                }
            }
        }
    }

    /**
     * Applies a collection of fields to a collection of sprites
     * 
     * @param sprites
     *            a collection of sprites
     * @param field
     *            a collection of fields
     * @param lengthOfApplication
     *            the length of time the field is applied for
     */
    public static void applyFieldsToSprites(Collection<Sprite> sprites, Collection<VectorField> fields, long lengthOfApplication) {
        if (isOn()) {
            for (Sprite sprite : sprites) {
                applyFieldsToSprite(sprite, fields, lengthOfApplication);
            }
        }
    }

    /**
     * Applies a collection of fields to a single sprite
     * 
     * @param sprites
     *            a collection of sprites
     * @param fields
     *            a collection of fields
     * @param lengthOfApplication
     *            the length of time the field is applied for
     */
    public static void applyFieldsToSprite(Sprite sprite, Collection<VectorField> fields, long lengthOfApplication) {
        List<EmptyPhysicsC> physicsComponents = sprite.getComponentsWhichSubclass(EmptyPhysicsC.class);

        for (EmptyPhysicsC component : physicsComponents) {
            component.applyFields(fields, lengthOfApplication);
        }
    }
    
    /**
     * Applies a field to a single sprite
     * 
     * @param sprite
     *            a sprite
     * @param field
     *            a field
     * @param lengthOfApplication
     *            the length of time the field is applied for
     */
    public static void applyFieldToSprite(Sprite sprite, VectorField field, long lengthOfApplication) {
        List<EmptyPhysicsC> physicsComponents = sprite.getComponentsWhichSubclass(EmptyPhysicsC.class);

        for (EmptyPhysicsC component : physicsComponents) {
            component.applyField(field, lengthOfApplication);
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
