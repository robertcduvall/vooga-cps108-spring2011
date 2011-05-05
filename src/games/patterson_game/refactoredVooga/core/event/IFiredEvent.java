package games.patterson_game.refactoredVooga.core.event;

/**
 * @author Michael Ansel
 * @author Ethan Goh
 */
public interface IFiredEvent extends Runnable
{
    /**
     * Test name of fired event against a regular expression
     * 
     * @param regex regular expression to match event name agains
     * @return true iff event name matches <code>regex</code>
     */
    boolean eventNameMatches (String regex);


    /**
     * @return source context from which event was fired
     */
    Object getSource ();


    String toString ();
}
