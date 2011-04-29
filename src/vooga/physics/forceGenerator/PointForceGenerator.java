package vooga.physics.forceGenerator;

import java.awt.Point;
import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.util.Force;

public class PointForceGenerator {

    private Force myForce;
    private Point myLocation;
    
    public PointForceGenerator(Force force, Point location){
        myForce = force;
        myLocation = location;
    }
    
    public Point getLocation(){
        return myLocation;
    }
    
    public Force getForce(EmptyForceBehavior forceTarget){
        //TODO: Check if the location intersects with the target
        return myForce;
    }
    
}
