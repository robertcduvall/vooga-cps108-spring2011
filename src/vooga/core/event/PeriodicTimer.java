package vooga.core.event;

/**
 * @author Michael Ansel
 * @author Ethan Goh
 */
public class PeriodicTimer extends AbstractTimer
{
    private long myInterval;
    private long myNextFireTime;


    public PeriodicTimer (EventManager eventManager,
    			String timerName,
                          long interval,
                          String eventName,
                          Object arg)
    {
        super(eventManager, timerName, eventName, arg);
        myInterval = interval;
        myNextFireTime = System.currentTimeMillis() + interval;
    }


    @Override
    public void fireEvent ()
    {
        super.fireEvent();
        myNextFireTime += myInterval;
    }


    public long getInterval ()
    {
        return myInterval;
    }


    @Override
    public long getNextFireTime ()
    {
        return myNextFireTime;
    }


    @Override
    public boolean isFinished ()
    {
        return false;
    }


    @Override
    public String toString ()
    {
        return String.format("PeriodicTimer(Interval=%d ms, NextFireTime=%d)",
                             getInterval(),
                             getNextFireTime());
    }


}
