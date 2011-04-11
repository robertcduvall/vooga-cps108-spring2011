package vooga.util.buildable.components.predefined.basic;

public class Velocity2DC extends VelocityC
{

    public Velocity2DC(double x, double y){
        super (x,y);
    }
    
    public Velocity2DC(){
        this(0,0);
    }
    
    public double getYVelocity(){
        return this.myVelocities[1];
    }
    
    public double getXVelocity(){
        return this.myVelocities[0];
    }
    
}
