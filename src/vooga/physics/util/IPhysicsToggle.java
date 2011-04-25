package vooga.physics.util;

/**
 * Allows for standardized toggling of objects which use physics. Used to toggle
 * specific objects, not physics as a whole
 * @author Nathan Klug
 *
 */
public interface IPhysicsToggle {

    public void turnPhysicsOnOff(boolean isOn);
    
    public boolean isOn();
}
