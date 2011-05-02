package vooga.sprites.spritebuilder.components.physics;

import java.awt.Point;
import java.util.Collection;
import vooga.physics.collisionBehavior.EmptyCollisionBehavior;
import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.forceGenerator.AbstractForceGenerator;
import vooga.physics.util.IPhysicsToggle;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.util.buildable.components.BasicComponent;
import vooga.util.math.Angle;

/**
 * Physics Component of a Sprite.
 * 
 * @author Nathan Klug
 * 
 */
public abstract class AbstractPhysicsC extends BasicComponent implements ISpriteUpdater, IPhysicsToggle {

    private boolean isOn;
    private Velocity deltaVelocity;

    protected EmptyForceBehavior myForceBehavior;
    protected EmptyCollisionBehavior myCollisionBehavior;

    public AbstractPhysicsC(EmptyForceBehavior forceBehavior,
            EmptyCollisionBehavior collisionBehavior) {
        this(forceBehavior, collisionBehavior, true);
    }

    public AbstractPhysicsC(EmptyForceBehavior forceBehavior,
            EmptyCollisionBehavior collisionBehavior, boolean isOn) {
        myForceBehavior = forceBehavior;
        myCollisionBehavior = collisionBehavior;
        this.isOn = isOn;
        deltaVelocity = new Velocity(0, new Angle(0));
    }

    @Override
    protected int compareTo(BasicComponent o) {
        // TODO: do we use this to compare whether a component is more specific.
        // for physics than another
        return 0;
    }

    @Override
    protected Object[] getFieldValues() {
        return new Object[] { myForceBehavior, myCollisionBehavior, isOn() };
    }

    @Override
    protected void setFieldValues(Object... fields) {
        myForceBehavior = (EmptyForceBehavior) fields[0];
        myCollisionBehavior = (EmptyCollisionBehavior) fields[2];
        if (fields.length > 2)
            turnPhysicsOnOff((Boolean) fields[3]);
        else
            turnPhysicsOnOff(true);
    }
    
    public void addToVelocityChange(Velocity otherVelocity){
        deltaVelocity.addVector(otherVelocity);
    }

    public void applyForce(AbstractForceGenerator force, long lengthOfApplication) {
        addToVelocityChange(myForceBehavior.forceToVelocityChange(force, lengthOfApplication));
    }

    public void applyForces(Collection<AbstractForceGenerator> forces, long lengthOfApplication) {
        for (AbstractForceGenerator force : forces) {
            applyForce(force, lengthOfApplication);
        }
    }

    public void applyCollision(EmptyCollisionBehavior otherCollisionBehavior, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        addToVelocityChange(myCollisionBehavior.collisionToVelocityChange(otherCollisionBehavior, angleOfImpact, pointOfImpact,
                coefficientOfRestitution));
    }

    public EmptyCollisionBehavior getCollisionBehavior() {
        return myCollisionBehavior;
    }
    
    public EmptyForceBehavior getForceBehavior() {
        return myForceBehavior;
    }
    
    @Override
    public void update(Sprite s, long elapsedTime) {
        if (s.carriesComponent(LocalForceC.class)){
            applyForces(s.getComponent(LocalForceC.class).getLocalForces(), elapsedTime);
        }
        Velocity oldVelocity = getSpriteVelocityForPhysics(s);
        oldVelocity.addVector(deltaVelocity);
        setSpriteVelocityForPhysics(s, oldVelocity);
        deltaVelocity = new Velocity(0, 0);
    }

    public Velocity getSpriteVelocityForPhysics(Sprite sprite) {
        return new Velocity(sprite.getHorizontalSpeed(), -sprite.getVerticalSpeed());
    }

    public void setSpriteVelocityForPhysics(Sprite sprite, Velocity newVelocity) {
        sprite.setHorizontalSpeed(newVelocity.getXComponent());
        sprite.setVerticalSpeed(-newVelocity.getYComponent());
    }
    
    public Velocity getDeltaVelocity(){
        return deltaVelocity;
    }

    @Override
    public void turnPhysicsOnOff(boolean isOn) {
        this.isOn = isOn;

    }

    @Override
    public boolean isOn() {
        return isOn;
    }
}
