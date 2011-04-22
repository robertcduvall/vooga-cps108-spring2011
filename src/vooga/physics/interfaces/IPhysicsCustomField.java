package vooga.physics.interfaces;

/**
 * Interface which should be implemented if one would like to override the default processing
 * of the application of a field.
 * 
 * @author Nathan Klug
 *
 */
public interface IPhysicsCustomField {
    
    public void applyField(IPointField f, long timeElapsed);
}
