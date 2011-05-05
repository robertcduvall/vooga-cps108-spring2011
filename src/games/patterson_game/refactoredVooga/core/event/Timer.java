package games.patterson_game.refactoredVooga.core.event;

/**
 * @author Michael Ansel
 * @author Ethan Goh
 */
public class Timer extends AbstractTimer
{
    private long myFireTime;


    public Timer (EventManager eventManager,
    		String timerName,
                  long delay,
                  String eventName,
                  Object arg)
    {
        super(eventManager, timerName, eventName, arg);
        myFireTime = System.currentTimeMillis() + delay;
    }


    @Override
    public long getNextFireTime ()
    {
        return myFireTime;
    }


    @Override
    public boolean isFinished ()
    {
        return isReadyToFire();
    }


    @Override
    public String toString ()
    {
        return String.format("Timer(NextFireTime=%d)", getNextFireTime());
    }
}
