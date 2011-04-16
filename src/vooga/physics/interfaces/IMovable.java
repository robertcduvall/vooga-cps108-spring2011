package vooga.physics.interfaces;

import vooga.physics.util.Velocity;

public interface IMovable {
    public Velocity getVelocity();
    
    public void setVelocity(Velocity newVelocity);
}
