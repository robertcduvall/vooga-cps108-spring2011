package games.patterson_game.refactoredVooga.levelsRefactored;

/**
 * Any object that can be updated. If added to a <code>VoogaPlayField</code>,
 * this interface is useful for ensuring that the object is updated every time
 * <code>VoogaPlayField</code> is updated.
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
