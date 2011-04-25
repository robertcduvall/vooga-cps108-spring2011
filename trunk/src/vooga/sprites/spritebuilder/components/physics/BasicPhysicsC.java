package vooga.sprites.spritebuilder.components.physics;

import java.awt.Point;
import java.util.List;
import vooga.physics.VoogaPhysicsMediator;
import vooga.physics.collisionBehavior.EmptyCollisionBehavior;
import vooga.physics.collisionBehavior.PhysicalCollisionBehavior;
import vooga.physics.fieldBehavior.EmptyFieldBehavior;
import vooga.physics.fieldBehavior.NewtonianFieldBehavior;
import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.forceBehavior.NewtonianForceBehavior;
import vooga.physics.newtonianProperties.INewtonianPhysical;
import vooga.physics.util.Force;
import vooga.physics.util.IPhysicsToggle;
import vooga.physics.util.VectorField;
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
public class BasicPhysicsC extends EmptyPhysicsC{

    private boolean isOn;
    private Velocity deltaVelocity;
    
    private BasicPhysicsC() {
    }
    
    private BasicPhysicsC(boolean isOn) {
    }

    public BasicPhysicsC(Velocity velocity, double mass) {
        this(velocity, mass, true);
    }
    
    public BasicPhysicsC(Velocity velocity, double mass, boolean isOn) {
        this(new NewtonianForceBehavior(mass),new EmptyFieldBehavior(), new PhysicalCollisionBehavior(velocity, mass), isOn);
    }
    
    private BasicPhysicsC(EmptyForceBehavior forceBehavior, EmptyFieldBehavior fieldBehavior, EmptyCollisionBehavior collisionBehavior){
        this(forceBehavior, fieldBehavior, collisionBehavior, true);
    }
    
    private BasicPhysicsC(EmptyForceBehavior forceBehavior, EmptyFieldBehavior fieldBehavior, EmptyCollisionBehavior collisionBehavior, boolean isOn){
        super(forceBehavior, fieldBehavior, collisionBehavior, isOn);
    }

    @Override
    protected int compareTo(BasicComponent o) {
        // TODO: do we use this to compare whether a component is more specific.
        // for physics than another
        return 0;
    }

    @Override
    public void update(Sprite s, long elapsedTime) {
        super.update(s, elapsedTime);
        //Updates the current velocity for the collision behavior
        //collisionBehavior.updateVelocity(getSpriteVelocityForPhysics(s));
    }
}
