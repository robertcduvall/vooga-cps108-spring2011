package vooga.physics.mediators;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import vooga.core.VoogaGame;
import vooga.physics.engine.BasePhysicsEngine;
import vooga.physics.engine.NewtonianPhysicsEngine;
import vooga.physics.interfaces.newtonian.INewtonianMovable;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.PhysicsC;
import vooga.sprites.spritebuilder.components.basic.VelocityC;
import vooga.util.buildable.components.IComponent;
import vooga.util.math.Angle;

public class VoogaPhysicsMediator {

    private static VoogaPhysicsMediator myInstance;
    private static Map<VoogaGame, BasePhysicsEngine> myEngines;
    private BasePhysicsEngine getDefaultEngine(){
        return new NewtonianPhysicsEngine();
    }
    
    private VoogaPhysicsMediator(){
        myEngines = new HashMap<VoogaGame, BasePhysicsEngine>();
    }
    
    public static VoogaPhysicsMediator getInstance(){
        if (myInstance == null)
            myInstance = new VoogaPhysicsMediator();
        return myInstance;
    }
    
    public BasePhysicsEngine getEngine(VoogaGame game){
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
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     * @param coefficientOfRestitution
     */
    public void collision(VoogaGame game, Sprite object1, Sprite object2, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        for (IComponent c : object1.getComponents()) {
            myEngines.get(game).collision(c, getBestCollisionComponent(object2), angleOfImpact, pointOfImpact, coefficientOfRestitution);
        }
        for (IComponent c : object2.getComponents()) {
            myEngines.get(game).collision(c, getBestCollisionComponent(object1), angleOfImpact, pointOfImpact, coefficientOfRestitution);
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
            return sprite.getComponent(VelocityC.class);
    }

    public static INewtonianMovable spriteToMovable(Sprite otherObject) {
        // TODO: Replace this method with appropriate use of the mediator?
        return null;
    }
}
