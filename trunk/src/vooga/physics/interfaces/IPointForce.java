package vooga.physics.interfaces;

public interface IPointForce extends IPhysics{
    
    public static double CONSTANT = 0;
    
    public double getPointMagnitude();
    
    public boolean isOn();
    
}
