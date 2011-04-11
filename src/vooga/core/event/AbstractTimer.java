package vooga.core.event;

/**
 * @author Michael Ansel
 * @author Ethan Goh
 */
public abstract class AbstractTimer implements ITimer
{
    private Object myArgument;
    private EventManager myEventManager;
    private String myEventName;


    protected AbstractTimer (EventManager eventManager,
                             String eventName,
                             Object arg)
    {
        myEventManager = eventManager;
        myEventName = eventName;
        myArgument = arg;
    }


    @Override
    public void cancel ()
    {
        myEventManager.removeTimer(this);
    }


    @Override
    public int compareTo (ITimer other)
    {
        if (other == null) throw new NullPointerException();

        return (int) Math.signum(this.getNextFireTime() -
                                 other.getNextFireTime());
    }


    @Override
    public boolean equals (Object o)
    {
        if (this.getClass() == o.getClass()) return this.compareTo((ITimer) o) == 0;
        else return false;
    }


    @Override
    public void fireEvent ()
    {
        myEventManager.fireEvent(this, myEventName, myArgument);
    }


    @Override
    public int hashCode ()
    {
        return ((Long) this.getNextFireTime()).hashCode();
    }


    @Override
    public boolean isReadyToFire ()
    {
        return getNextFireTime() <= System.currentTimeMillis();
    }
}
