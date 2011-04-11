package vooga.sprites.improvedsprites;

import java.awt.Point;
import java.awt.image.BufferedImage;
import vooga.physics.calculators.PhysicsCalculator;
import vooga.physics.interfaces.IPhysics;
import vooga.physics.util.Velocity;

public class PhysicalSprite extends Sprite implements IPhysics
{

    PhysicsCalculator myCalculator;
    
    public PhysicalSprite ()
    {
        super();
    }

    public PhysicalSprite (BufferedImage image, double x, double y)
    {
        super(image, x, y);
    }

    public PhysicalSprite (BufferedImage image)
    {
        super(image);
    }

    public PhysicalSprite (double x, double y)
    {
        super(x, y);
    }

    
    @Override
    public PhysicsCalculator getCalculator ()
    {
        return myCalculator;
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
