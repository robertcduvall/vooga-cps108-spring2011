package vooga.physics.engine;

import java.awt.Point;
import java.util.Collection;
import java.util.HashSet;
import vooga.physics.interfaces.IPhysics;
import vooga.util.math.Angle;
import vooga.util.physics.Force;

/**
 * I propose that we unify WorldForceManager and WorldPhysicsCalculator into this one class. Is this okay?
 * TODO: Comment here
 * 
 * @author Nathan Klug
 *
 */
public class PhysicsEngine {

    private static Collection<Force> worldForces;
    private static PhysicsEngine myInstance;
    private static boolean isOn;

    private PhysicsEngine() {
        worldForces = new HashSet<Force>();
        isOn = true;
    }

    /**
     * Returns an instance of WorldForceManager.
     * 
     * @return
     */
    public static PhysicsEngine getInstance() {
        if (myInstance == null) {
            myInstance = new PhysicsEngine();
        }
        return myInstance;
    }

    /**
     * Adds a force to the collection of worldwide forces.
     * 
     * @param force
     */
    public void addGlobalForce(Force force) {
        worldForces.add(force);
    }

    /**
     * Removes a force from the collection of worldwide forces.
     * 
     * @param force
     */
    public void removeGlobalForce(Force force) {
        worldForces.remove(force);
    }

    /**
     * Applies all of the worldwide forces to something with a physics nature.
     * 
     * @param physics
     * @param elapsedTime
     */
    public Collection<Force> getWorldForces() {
        if (isOn) {
            return worldForces;
        }
        return new HashSet<Force>();
    }
    
    /**
     * Elastic collision method. Coefficient of Restitution is set to 1.
     * 
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     */
    public void elasticCollision(IPhysics object1, IPhysics object2, Angle angleOfImpact, Point pointOfImpact) {
        collision(object1, object2, angleOfImpact, pointOfImpact, 1);
    }

    /**
     * Inelastic collision method. Coefficient of Restitution is set to 0.
     * 
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     */
    public void inelasticCollision(IPhysics object1, IPhysics object2, Angle angleOfImpact, Point pointOfImpact) {
        collision(object1, object2, angleOfImpact, pointOfImpact, 0);
    }

    /**
     * TODO: DESIGN DECISION/fill in more pros/cons
     * 
     * Basically, should IPhysics objects know their calculator?
     * Pros: We wouldn't have to be passing around IPhysics objects and calculators
     * 
     * Cons: Another method to implement in the IPhysics interface
     *       Too many interdependencies?
     *       
     * Nathan decided that objects should know their calculators
     * 
     * General collision method. Tells the two physical objects that a collision
     * occurred.
     * 
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     * @param coefficientOfRestitution
     */
    public void collision(IPhysics object1, IPhysics object2, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        if (isOn) {
            object1.getCalculator().collisionOccurred(object1, object2, angleOfImpact, pointOfImpact, coefficientOfRestitution);
            object2.getCalculator().collisionOccurred(object1, object2, angleOfImpact, pointOfImpact, coefficientOfRestitution);
        }
    }

    /**
     * If no coefficient of restitution is given, default is elastic collision.
     * 
     * @param object1
     * @param object2
     * @param angleOfImpact
     * @param pointOfImpact
     */
    public void collision(IPhysics object1, IPhysics object2, Angle angleOfImpact, Point pointOfImpact) {
        elasticCollision(object1, object2, angleOfImpact, pointOfImpact);
    }

    /**
     * Returns a boolean value representing if physics is on
     * 
     * @return
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Turns physics off
     */
    public void turnOff() {
        isOn = false;
    }

    /**
     * Turns physics on
     */
    public void turnOn() {
        isOn = true;
    }

    /**
     * Sets the physics on/off based on the input boolean
     * 
     * @param newState
     */
    public void setPhysicsOnOff(boolean newState) {
        isOn = newState;
    }

    /**
     * Turns physics on if it is off and turns physics off if it is on
     */
    public void togglePhysicsOnOff() {
        isOn = !isOn;
    }

}
