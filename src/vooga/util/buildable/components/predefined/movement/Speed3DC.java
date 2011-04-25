package vooga.util.buildable.components.predefined.movement;

public class Speed3DC extends SpeedC
{

    public Speed3DC(double x, double y, double z){
        super(x,y,z);
    }
    
    public Speed3DC(){
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
