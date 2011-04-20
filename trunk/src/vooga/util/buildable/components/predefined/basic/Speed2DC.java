package vooga.util.buildable.components.predefined.basic;

public class Speed2DC extends SpeedC 
{

    public Speed2DC(double x, double y){
        super (x,y);
    }
    
    public Speed2DC(){
        this(0,0);
    }
    
    public double getYVelocity(){
        return this.myVelocities[1];
    }
    
    public double getXVelocity(){
        return this.myVelocities[0];
    }
    
}
