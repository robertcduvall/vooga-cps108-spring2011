package vooga.physics.interfaces.newtonian;

import vooga.physics.util.Velocity;

public interface INewtonianMovable {
    
    public Velocity getVelocity();
    
    public void setVelocity(Velocity newVelocity);
}
