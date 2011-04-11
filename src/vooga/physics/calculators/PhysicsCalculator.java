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
import vooga.physics.interfaces.IPhysics;
import vooga.physics.interfaces.IPointForce;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

/**
 * To be used in a particular game object (such as a sprite) which extends
 * IPhysics. The object should call updateWithPhysics in it's update method,
 * using either a held instance of this or some subclass (probably
 * BestCalculator), or by using the default calculator, which should be held by
 * the level or game or something.
 * 
 * TODO: Level team will need to change bestCalculator based on the level?
 * 
 * @author Nathan Klug
 * 
 */
public class PhysicsCalculator {

    private static final String BEST_CALC_RESOURCES = "calculators/bestCalculator";
    // bestCalculators is a list of the preferred calculator to use by default
    static String[] bestCalculators;
    // bestInterfaces is a list of the preferred interfaces to use when a Sprite implements multiple interfaces
    static String[] bestInterfaces;
    // calculatorsToInterfaces is a map from the calculators to the interfaces that can be used by them
    static Map<String, String[]> calculatorsToInterfaces;

    public PhysicsCalculator() {

        getDefaultsFromFile(BEST_CALC_RESOURCES);
    }

    /**
     * Gets the default best calculators, interfaces, and map from the defaults defined at fileName.
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
     * Used to get the best calculator, based on the preferences in the resource file and the
     * interfaces implemented by the parameter. 
     * 
     * @param physics the object who needs to know the best calculator for itself
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
        //TODO: Throw an exception that there is a problem with the resource file, because this shouldn't happen
        return null;
    }

    /**
     * Updates based on the basic physics (like gravity) in the world.
     * @param elapsedTime
     * @param physicalObject the object to update
     */
    public void updateWithPhysics(long elapsedTime, IPhysics physicalObject) {
        if (physicalObject.isOn()) {
            PhysicsEngine.getInstance().applyWorldForces(physicalObject, elapsedTime);
        }
    }

    /**
     * Applies an external force to an IPhysics object.
     * @param physicalObject
     * @param force
     * @param elapsedTime
     */
    public void applyForce(IPhysics physicalObject, Force force, long elapsedTime) {
        if (physicalObject.isOn()) { // Is this really necessary, since it's
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
     * @param physicalObject
     * @param field
     * @param elapsedTime
     */
    public void applyField(IPointForce physicalObject, IPointForce field, long elapsedTime) {
        MathVector radius = new MathVector(physicalObject.getCenter(), field.getCenter());
        double magnitude = field.getPointMagnitude() * field.CONSTANT * physicalObject.getPointMagnitude() / radius.getMagnitude();
        applyForce(physicalObject, new Force(magnitude, radius.getAngle()), elapsedTime);
    }

    /**
     * Calculates the collision based on the masses and velocities of the objects colliding
     * @param thisObject
     * @param otherObject
     * @param angleOfImpact
     * @param pointOfCollision
     * @param coefficientOfRestitution
     */
    public void collisionOccurred(IPhysics thisObject, IPhysics otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution) {
        if (thisObject.isOn()) {
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
}
