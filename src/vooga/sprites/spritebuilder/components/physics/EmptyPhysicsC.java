package vooga.sprites.spritebuilder.components.physics;

import java.awt.Point;
import java.util.Collection;
import vooga.physics.collisionBehavior.EmptyCollisionBehavior;
import vooga.physics.fieldBehavior.EmptyFieldBehavior;
import vooga.physics.forceBehavior.EmptyForceBehavior;
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
public class EmptyPhysicsC extends BasicComponent implements ISpriteUpdater, IPhysicsToggle {

    protected boolean isOn;
    protected Velocity deltaVelocity;

    protected EmptyForceBehavior myForceBehavior;
    protected EmptyFieldBehavior myFieldBehavior;
    protected EmptyCollisionBehavior myCollisionBehavior;

    public EmptyPhysicsC() {
        this(true);
    }

    public EmptyPhysicsC(boolean isOn) {
        this(new EmptyForceBehavior(), new EmptyFieldBehavior(), new EmptyCollisionBehavior(), isOn);

    }

    public EmptyPhysicsC(EmptyForceBehavior forceBehavior, EmptyFieldBehavior fieldBehavior,
            EmptyCollisionBehavior collisionBehavior) {
        this(forceBehavior, fieldBehavior, collisionBehavior, true);
    }

    public EmptyPhysicsC(EmptyForceBehavior forceBehavior, EmptyFieldBehavior fieldBehavior,
            EmptyCollisionBehavior collisionBehavior, boolean isOn) {
        myForceBehavior = forceBehavior;
        myFieldBehavior = fieldBehavior;
        myCollisionBehavior = collisionBehavior;
        this.isOn = isOn;
    }

    @Override
    protected int compareTo(BasicComponent o) {
        // TODO: do we use this to compare whether a component is more specific.
        // for physics than another
        return 0;
    }

    @Override
    protected Object[] getFieldValues() {
        return new Object[] { myForceBehavior, myFieldBehavior, myCollisionBehavior, isOn() };
    }

    @Override
    protected void setFieldValues(Object... fields) {
        myForceBehavior = (EmptyForceBehavior) fields[0];
        myFieldBehavior = (EmptyFieldBehavior) fields[1];
        myCollisionBehavior = (EmptyCollisionBehavior) fields[2];
        if (fields.length > 3)
            turnPhysicsOnOff((Boolean) fields[3]);
        else
            turnPhysicsOnOff(true);
    }

    public void applyForce(Force force, long lengthOfApplication) {
        deltaVelocity.addVector(myForceBehavior.forceToVelocityChange(force, lengthOfApplication));
    }

    public void applyForces(Collection<Force> forces, long lengthOfApplication) {
        for (Force force : forces) {
            applyForce(force, lengthOfApplication);
        }
    }

    public void applyField(VectorField field, long lengthOfApplication) {
        deltaVelocity.addVector(myFieldBehavior.fieldToVelocityChange(field, lengthOfApplication));
    }

    public void applyFields(Collection<VectorField> fields, long lengthOfApplication) {
        for (VectorField field : fields) {
            applyField(field, lengthOfApplication);
        }
    }

    public EmptyCollisionBehavior getCollisionBehavior() {
        return myCollisionBehavior;
    }

    public void applyCollision(EmptyCollisionBehavior otherCollisionBehavior, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        myCollisionBehavior.collisionToVelocityChange(otherCollisionBehavior, angleOfImpact, pointOfImpact,
                coefficientOfRestitution);
    }

    @Override
    public void update(Sprite s, long elapsedTime) {
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

    @Override
    public void turnPhysicsOnOff(boolean isOn) {
        this.isOn = isOn;

    }

    @Override
    public boolean isOn() {
        return isOn;
    }
}
