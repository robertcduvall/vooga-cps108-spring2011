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
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

/**
 * To be used in a particular game object (such as a sprite) which extends
 * IPhysics. The object should call updateWithPhysics in it's update method,
 * using either a held instance of this or some subclass (probably
 * BestCalculator), or by using the default calculator, which should be held by
 * the level or game or something.
 * 
 * TODO: Tell level/game to hold an instance of this, once this is finalized.
 * 
 * @author Nathan Klug
 * 
 */
public class PhysicsCalculator {

    private static final String BEST_CALC_RESOURCES = "calculators/bestCalculator";
    static String[] bestCalculators;
    static String[] bestInterfaces;
    static Map<String, String[]> calculatorsToInterfaces;

    public PhysicsCalculator() {

        getDefaultsFromFile(BEST_CALC_RESOURCES);
    }

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

    public void updateWithPhysics(long elapsedTime, IPhysics physicalObject) {
        if (physicalObject.isOn()) {
            Collection<Force> forces = PhysicsEngine.getInstance().getWorldForces();
            for (Force f : forces) {
                applyForce(physicalObject, f, elapsedTime);
            }
        }
    }

    protected void applyForce(IPhysics physicalObject, Force force, long elapsedTime) {
        if (physicalObject.isOn()) { // Is this really necessary, since it's
                                     // currently private?
            Velocity deltaVelocity = new Velocity(force.getMagnitude() * elapsedTime / physicalObject.getMass(),
                    force.getAngle());
            Velocity spriteVelocity = physicalObject.getVelocity();
            spriteVelocity.addVector(deltaVelocity);
            physicalObject.setVelocity(spriteVelocity);
        }
    }

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
