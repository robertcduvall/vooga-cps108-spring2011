package vooga.physics.engine;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import vooga.physics.interfaces.IPhysics;
import vooga.physics.interfaces.IPointForce;
import vooga.physics.util.Force;
import vooga.reflection.Reflection;
import vooga.util.math.Angle;

/**
 * I propose that we unify WorldForceManager and WorldPhysicsCalculator into
 * this one class. Is this okay? TODO: Comment here
 * 
 * @author Nathan Klug
 * 
 */
public class PhysicsEngine {

    private static Collection<Force> worldForces;
    private static Collection<IPointForce> pointForces;
    private static PhysicsEngine myInstance;
    private static boolean isOn;

    private PhysicsEngine() {
        worldForces = new HashSet<Force>();
        pointForces = new HashSet<IPointForce>();
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
    public void applyWorldForces(IPhysics physicalObject, long elapsedTime) {
        if (isOn) {
            for (Force f : worldForces) {
                physicalObject.getCalculator().applyForce(physicalObject, f, elapsedTime);
            }
            
            Collection<IPointForce> myInterfaces = Reflection.getInterfacesWhichSubclass(physicalObject,
                    IPointForce.class);

            for (IPointForce myIFace : myInterfaces) {
                String myIFaceName = myIFace.getClass().toString();
                for (IPointForce f : pointForces) {
                    Collection<IPointForce> interfaces = Reflection.getInterfacesWhichSubclass(f, IPointForce.class);

                    for (IPointForce iFace : interfaces) {
                        String interfaceName = iFace.getClass().toString();
                        if (myIFaceName.equals(interfaceName)) {
                            physicalObject.getCalculator().applyField(myIFace, iFace, elapsedTime);
                        }
                    }
                }

            }
        }
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
            object1.getCalculator().collisionOccurred(object1, object2, angleOfImpact, pointOfImpact,
                    coefficientOfRestitution);
            object2.getCalculator().collisionOccurred(object1, object2, angleOfImpact, pointOfImpact,
                    coefficientOfRestitution);
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
