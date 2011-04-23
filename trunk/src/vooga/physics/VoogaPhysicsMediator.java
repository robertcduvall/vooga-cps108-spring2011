package vooga.physics;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import vooga.core.VoogaGame;
import vooga.physics.newtonianProperties.INewtonianMovable;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.physics.PhysicsC;
import vooga.sprites.spritebuilder.components.physics.VelocityC;
import vooga.util.buildable.components.IComponent;
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

    private static VoogaPhysicsMediator myInstance;
    private static Map<VoogaGame, BasePhysicsEngine> myEngines;

    private BasePhysicsEngine getDefaultEngine() {
        return new NewtonianPhysicsEngine();
    }

    private VoogaPhysicsMediator() {
        myEngines = new HashMap<VoogaGame, BasePhysicsEngine>();
    }

    public static VoogaPhysicsMediator getInstance() {
        if (myInstance == null)
            myInstance = new VoogaPhysicsMediator();
        return myInstance;
    }

    /**
     * Implements engines as psuedo-singletons. This will likely change.
     * @param game the game corresponding to the desired engine.
     * @return
     */
    public BasePhysicsEngine getEngine(VoogaGame game) {
        if (!myEngines.containsKey(game))
            myEngines.put(game, getDefaultEngine());
        return myEngines.get(game);
    }

    public static void addEngine(VoogaGame currentGame, BasePhysicsEngine desiredEngine) {
        myEngines.put(currentGame, desiredEngine);
    }

    public static void removeEngine(VoogaGame currentGame, BasePhysicsEngine desiredEngine) {
        myEngines.remove(currentGame);
    }

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
    public void collision(VoogaGame game, Sprite object1, Sprite object2, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        for (IComponent c : object1.getComponents()) {
            myEngines.get(game).collision(c, getBestCollisionComponent(object2), angleOfImpact, pointOfImpact,
                    coefficientOfRestitution);
        }
        for (IComponent c : object2.getComponents()) {
            myEngines.get(game).collision(c, getBestCollisionComponent(object1), angleOfImpact, pointOfImpact,
                    coefficientOfRestitution);
        }
    }

    private INewtonianMovable getBestCollisionComponent(Sprite sprite) {
        if (sprite.carriesComponent(PhysicsC.class))
            return sprite.getComponent(PhysicsC.class);
        else
            return sprite.getComponent(VelocityC.class);
    }
}
