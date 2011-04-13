package vooga.physics.calculators;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import vooga.reflection.Reflection;
import vooga.physics.engine.PhysicsEngine;
import vooga.physics.interfaces.INewtonianPhysics;
import vooga.physics.interfaces.IPhysics;
import vooga.physics.interfaces.IPointField;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

/** Deprecated. Moved to PhysicsEngine
 * 
 * 
 * @author Nathan Klug
 * @author Anne Weng
 * 
 */
public class PhysicsCalculator {

    private static final String BEST_CALC_RESOURCES = "calculators/bestCalculator";
    // bestCalculators is a list of the preferred calculator to use by default
    private static String[] bestCalculators;
    // bestInterfaces is a list of the preferred interfaces to use when a Sprite
    // implements multiple interfaces
    private static String[] bestInterfaces;
    // calculatorsToInterfaces is a map from the calculators to the interfaces
    // that can be used by them
    private static Map<String, String[]> calculatorsToInterfaces;
    private boolean isOn;
    private static PhysicsCalculator myInstance;

    protected PhysicsCalculator() {

        getDefaultsFromFile(BEST_CALC_RESOURCES);
        isOn = true;
    }

    public static PhysicsCalculator getInstance() {
        if (myInstance == null)
            myInstance = new PhysicsCalculator();
        return myInstance;
    }

    /**
     * Gets the default best calculators, interfaces, and map from the defaults
     * defined at fileName.
     * 
     * @param fileName
     */
    private void getDefaultsFromFile(String fileName) {
        ResourceBundle resources = ResourceBundle.getBundle(fileName);
        bestCalculators = resources.getString("bestCalculators").split(",");
        bestInterfaces = resources.getString("bestInterfaces").split(",");

        calculatorsToInterfaces = new HashMap<String, String[]>();
        for (String calc : bestCalculators) {
            calculatorsToInterfaces.put(calc, resources.getString(calc).split(","));
        }
    }

    private static Collection<String> getInterfaceNames(IPhysics physicalObject) {
        Class[] interfaces = physicalObject.getClass().getInterfaces();
        String[] interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            String[] currentInterfaceName = interfaces[i].getCanonicalName().split(".");
            interfaceNames[i] = currentInterfaceName[currentInterfaceName.length - 1];
        }
        return new HashSet<String>(Arrays.asList(interfaceNames));
    }

    /**
     * Used to get the best calculator, based on the preferences in the resource
     * file and the interfaces implemented by the parameter.
     * 
     * @param physics
     *            the object who needs to know the best calculator for itself
     * @return
     */
    public static PhysicsCalculator getBestCalcForInterface(IPhysics physics) {
        ResourceBundle resources = ResourceBundle.getBundle(BEST_CALC_RESOURCES);
        Collection<String> objectInterfaces = getInterfaceNames(physics);

        String calcDirectory = resources.getString("calculatorPath");

        for (String iFace : bestInterfaces) {
            if (objectInterfaces.contains(iFace)) {
                for (String calc : bestCalculators) {
                    if (calculatorsToInterfaces.containsValue(iFace)) {
                        return (PhysicsCalculator) Reflection.createInstance(calcDirectory + calc);
                    }
                }
            }
        }
        // TODO: Throw an exception that there is a problem with the resource
        // file, because this shouldn't happen
        return null;
    }

    /**
     * Updates based on the basic physics (like gravity) in the world.
     * 
     * @param elapsedTime
     * @param physicalObject
     *            the object to update
     */
    public void updateWithPhysics(long elapsedTime, IPhysics physicalObject) {
        if (isOn()) {
            PhysicsEngine.getInstance().applyWorldForces(physicalObject, elapsedTime);
        }
    }

    public boolean isOn() {
        return isOn;
    }

    public void setPhysicsOnOff(boolean isOn) {
        this.isOn = isOn;
    }

    /**
     * Applies an external force to an IPhysics object using the Impulse
     * Momentum Theorem. <br>
     * <br>
     * Source: <a
     * href="http://en.wikipedia.org/wiki/Impulse_momentum_theorem">Wikipedia
     * </a>
     * 
     * @param physicalObject
     * @param force
     * @param elapsedTime
     */
    public void applyForce(IPhysics physicalObject, Force force, long elapsedTime) {
        if (isOn()) { // Is this really necessary, since it's
                      // currently protected?
            Velocity deltaVelocity = new Velocity(force.getMagnitude() * elapsedTime / physicalObject.getMass(),
                    force.getAngle());
            Velocity spriteVelocity = physicalObject.getVelocity();
            spriteVelocity.addVector(deltaVelocity);
            physicalObject.setVelocity(spriteVelocity);
        }
    }

    /**
     * Applies an external field to an IPhysics object.
     * 
     * @param physicalObject
     * @param field
     * @param elapsedTime
     */
    public void applyField(IPointField physicalObject, IPointField field, long elapsedTime) {
        if (isOn()) {
            MathVector radius = new MathVector(physicalObject.getCenter(), field.getCenter());
            double magnitude = field.getPointMagnitude() * field.CONSTANT * physicalObject.getPointMagnitude()
                    / radius.getMagnitude();
            applyForce(physicalObject, new Force(magnitude, radius.getAngle()), elapsedTime);
        }
    }

    private void applyForce(IPointField physicalObject, Force force, long elapsedTime) {
        if (isOn()) {
            Velocity deltaVelocity = new Velocity(force.getMagnitude() * elapsedTime
                    / physicalObject.getPointMagnitude(), force.getAngle());
            Velocity spriteVelocity = physicalObject.getVelocity();
            spriteVelocity.addVector(deltaVelocity);
            physicalObject.setVelocity(spriteVelocity);
        }

    }

    /**
     * Calculates the collision based on the masses and velocities of the
     * objects colliding. <br>
     * <br>
     * Source: <a href=
     * "http://en.wikipedia.org/wiki/Coefficient_of_restitution#Speeds_after_impact"
     * >Wikipedia</a>
     * 
     * @param thisObject
     * @param otherObject
     * @param angleOfImpact
     * @param pointOfCollision
     * @param coefficientOfRestitution
     */
    public void basicCollisionOccurred(IPhysics thisObject, IPhysics otherObject, Angle angleOfImpact, double coefficientOfRestitution) {
        if (isOn()) {
            double myParallel = thisObject.getVelocity().getParallelComponent(angleOfImpact);
            double myPerp = thisObject.getVelocity().getPerpComponent(angleOfImpact);
            double otherParallel = otherObject.getVelocity().getParallelComponent(angleOfImpact);
            double otherPerp = otherObject.getVelocity().getPerpComponent(angleOfImpact);

            double parallelNumerator = thisObject.getMass() * myParallel + otherObject.getMass() * otherParallel
                    + otherObject.getMass() * coefficientOfRestitution * (otherParallel - myParallel);
            double perpNumerator = thisObject.getMass() * myPerp + otherObject.getMass() * otherPerp
                    + otherObject.getMass() * coefficientOfRestitution * (otherPerp - myPerp);
            double denominator = thisObject.getMass() + otherObject.getMass();

            Velocity newVelocity = new Velocity(perpNumerator / denominator, parallelNumerator / denominator,
                    angleOfImpact);
            thisObject.setVelocity(newVelocity);
        }
    }

    /**
     * Applies a force which causes rotation. <br>
     * <br>
     * Applies the following equation to determine the change in angular
     * velocity. <br>
     * <img src=
     * "http://vooga-cps108-spring2011.googlecode.com/svn/trunk/src/vooga/physics/util/angularvelocity.PNG"
     * >
     * 
     * @param physicalObject
     * @param force
     * @param pointOfApplication
     * @param elapsedTime
     */
    public void applyRotationalForce(INewtonianPhysics physicalObject, Force force, Point pointOfApplication, long elapsedTime) {
        if (isOn()) {
            MathVector radius = new MathVector(physicalObject.getCenter(), pointOfApplication);
            Angle theta = radius.getVectorAngle(force);
            double deltaOmega = force.getMagnitude() * theta.sin() * elapsedTime / physicalObject.getMass()
                    / radius.getMagnitude();
            physicalObject.setRotationalVelocity(physicalObject.getRotationalVelocity() + deltaOmega);
        }
    }

    /**
     * Applies friction to an object.
     * 
     * @param physicalObject
     * @param force
     * @param surfaceTangent
     * @param elapsedTime
     */
    public void applyFriction(INewtonianPhysics physicalObject, Force force, Angle surfaceTangent, long elapsedTime) {
        if (isOn()) {
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
            Force friction = new Force(normalMagnitude * physicalObject.getCoefficientOfFriction(), surfaceTangent);
            applyForce(physicalObject, friction, elapsedTime);
        }
    }
}
