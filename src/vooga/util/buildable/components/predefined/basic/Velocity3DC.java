package vooga.util.buildable.components.predefined.basic;

public class Velocity3DC extends VelocityC
{

    public Velocity3DC(double x, double y, double z){
        super(x,y,z);
    }
    
    public Velocity3DC(){
        this(0,0,0);
    }
    
    public double getYVelocity(){
        return this.myVelocities[1];
    }
    
    public double getXVelocity(){
        return this.myVelocities[0];
    }
    
    public double getZVelocity(){
        return this.myVelocities[2];
    }
    
}
