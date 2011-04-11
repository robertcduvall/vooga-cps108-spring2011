package vooga.sprites.spritebuilder.components.basic;

import java.awt.Point;
import vooga.physics.calculators.PhysicsCalculator;
import vooga.physics.interfaces.IPhysics;
import vooga.physics.util.Velocity;
import vooga.util.buildable.components.BasicComponent;

public class PhysicsC extends BasicComponent implements IPhysics
{

    @Override
    protected int compareTo (BasicComponent o)
    {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    protected Object[] getFields ()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected void setFields (Object ... fields)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public PhysicsCalculator getCalculator ()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public double getMass ()
    {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public Point getCenter ()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Velocity getVelocity ()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void setVelocity (Velocity newVelocity)
    {
        // TODO Auto-generated method stub
        
    }


    @Override
    public boolean isOn ()
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public void setPhysicsOnOff (boolean isOn)
    {
        // TODO Auto-generated method stub
        
    }

}
