package vooga.sprites.spritebuilder.components.basic;

import vooga.physics.interfaces.IPhysicsFriction;
import vooga.util.buildable.components.BasicComponent;

/**
 * Component to use if you want an object to exert frictional force.
 * 
 * TODO: how do we want this to work? does this work on update or collision?
 * @author Nathan Klug
 *
 */
public class PhysicsFrictionC extends BasicComponent implements IPhysicsFriction{

    private double coefficient = 2;
    private boolean isOn;
    
    @Override
    public double getCoefficientOfFriction() {
        return coefficient;
    }

    @Override
    public boolean isOn() {
        return isOn();
    }

    @Override
    public void setPhysicsOnOff(boolean isOn) {
        this.isOn = isOn;
        
    }

    @Override
    protected int compareTo(BasicComponent o) {
        // TODO: how do we want to handle comparisions for physics?
        return 0;
    }

    @Override
    protected Object[] getFields() {
        
        return new Object[]{coefficient, isOn};
    }

    @Override
    protected void setFields(Object... fields) {
        coefficient = (Double) fields[0];
        if (fields.length > 1)
            isOn = (Boolean) fields[1];
        else
            isOn = true;
    }
    
}
