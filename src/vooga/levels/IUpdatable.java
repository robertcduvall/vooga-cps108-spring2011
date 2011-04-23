package vooga.levels;

/**
 * Any object that can be updated. If added to a VoogaPlayField, this interface
 * is useful for ensuring that the object is updated every time VoogaPlayField
 * is updated.
 * 
 * @author Andrew Patterson
 */
public interface IUpdatable
{

    /**
     * Updates the object which implements this interface
     * 
     * @param elapsedTime
     */
    public void update (long elapsedTime);
}
