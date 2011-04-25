package vooga.physics.collisionBehavior;

import java.util.List;

import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.physics.EmptyPhysicsC;
import vooga.util.math.Angle;

/**
 * Physics behavior type for friction. Friction is a response to a collision so it
 * extends the EmptyCollisionBehavior.
 * 
 * @author Anne Weng
 *
 */
public class FrictionCollisionBehavior extends EmptyCollisionBehavior {

    private double myCoefficientOfFriction;
    private double myMass;

    /**
     * Creates a FrictionCollisionBehavior. The coefficient of friction should be
     * a number between 0 and 1, where 0 signifies that the object is frictionless.
     * @param coefficientOfFriction
     * @param mass
     */
    public FrictionCollisionBehavior(double coefficientOfFriction, double mass){
        myCoefficientOfFriction = coefficientOfFriction;
        myMass = mass;
    }

    /**
     * Returns the coefficient of friction.
     * @return
     */
    public double getCoefficientOfFriction(){
        return myCoefficientOfFriction;
    }

    /**
     * Applies friction to an object. Returns the change in velocity as a result.
     * 
     * @param physicalObject
     * @param force
     * @param surfaceTangent
     * @param elapsedTime
     */
    public Velocity applyFriction(Sprite otherObject, Force force, Angle surfaceTangent, long time) {
        //First determines if the other object has a component with friction.
        //If so, saves its coefficient of friction. If not, assumes other object is frictionless.
        double otherCoefficientOfFriction = 0;
        List<EmptyPhysicsC> components = otherObject.getComponentsWhichSubclass(EmptyPhysicsC.class);
        for (EmptyPhysicsC component : components){
            if (component.getCollisionBehavior().equals(this)){
                otherCoefficientOfFriction = ((FrictionCollisionBehavior) component.getCollisionBehavior()).getCoefficientOfFriction();
                break;
            }
        }
        double mu = 0;
        if (myCoefficientOfFriction > 0 && otherCoefficientOfFriction > 0) {
            mu = (myCoefficientOfFriction + otherCoefficientOfFriction)/2;
            //Coefficient of Friction will be average of the two if neither is frictionless.
        }

        double normalMagnitude = force.getPerpComponent(surfaceTangent);
        if (normalMagnitude < 0) {
            // Normal magnitude is negative so surfaceTangent is in
            // direction of friction
            normalMagnitude = -normalMagnitude;
        }
        else {
            // Normal magnitude is positive so surfaceTangent is in
            // direction opposite friction
            surfaceTangent.setNegativeAngle();
        }
        Force friction = new Force(normalMagnitude * mu, surfaceTangent);
        
        return new Velocity(friction.getMagnitude() * time / myMass, friction.getAngle());
    }

}
