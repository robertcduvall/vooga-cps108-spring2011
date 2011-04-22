package vooga.physics;

/**
 * Allows for standardized way of toggling physics components off and on.
 * @author Nathan Klug
 *
 */
public interface IPhysicsToggle {

    public void turnPhysicsOnOff(boolean isOn);
    
    public boolean isOn();
}
