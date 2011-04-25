package vooga.util.buildable.components.predefined.movement;

public class Speed2DC extends SpeedC 
{

    public Speed2DC(Double x, Double y){
        super (x,y);
    }
    
    public Speed2DC(){
        this(0.0,0.0);
    }
    
    public double getYVelocity(){
        return this.myVelocities[1];
    }
    
    public double getXVelocity(){
        return this.myVelocities[0];
    }

	public void setSpeed(double newSpeed, double direction, double max) {
		if (newSpeed > max) newSpeed = max;
		myVelocities[0]= newSpeed*Math.cos(Math.toRadians(direction));
		myVelocities[1]= newSpeed*Math.cos(Math.toRadians(direction));
	}

	public void setXYSpeed(double vx, double vy) {
		myVelocities[0] = vx;
		myVelocities[1] = vy;
	}
    
}
