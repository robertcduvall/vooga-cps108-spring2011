package vooga.sprites.spritebuilder.components.physics;

import java.awt.Point;
import vooga.physics.VoogaPhysicsMediator;
import vooga.physics.newtonianProperties.INewtonianPhysical;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.ISpriteUpdater;
import vooga.util.buildable.components.BasicComponent;

/**
 * Physics Component of a Sprite.
 * 
 * @author Nathan Klug
 * 
 */
public class PhysicsC extends VelocityC implements INewtonianPhysical, ISpriteUpdater{
 
    private double myMass;

    public PhysicsC(Sprite sprite, double mass){
        this(sprite, mass, true);
    }
    
    public PhysicsC(Sprite sprite, double mass, boolean isOn){
        super(sprite, isOn);
        myMass = mass;
    }
    
    @Override
    protected int compareTo(BasicComponent o) {
        // TODO: do we use this to compare whether a component is more specific.
        // for physics than another
        return 0;
    }

    @Override
    protected Object[] getFieldValues() {
        return new Object[]{mySprite, myMass, isOn()}; //TODO: this is not going to return the field values, just the fields
    }

    @Override
    protected void setFieldValues(Object... fields) {
        super.setFieldValues(fields[0]);
        myMass = (Double) fields[1];
        if (fields.length > 2)
            turnPhysicsOnOff((Boolean) fields[2]);
        else
            turnPhysicsOnOff(true);
    }

    @Override
    public double getMass() {
        return myMass;
    }

    @Override
    public Point getCenter() {
        return new Point((int) mySprite.getCenterX(), (int) mySprite.getCenterY());
    }

    @Override
    public void update(Sprite s, long elapsedTime) {
        if (isOn())//TODO: Get access to engine
            VoogaPhysicsMediator.getInstance().getEngine(null).applyWorldForces(this, elapsedTime);
    }
}
