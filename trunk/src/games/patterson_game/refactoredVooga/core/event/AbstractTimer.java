package games.patterson_game.refactoredVooga.core.event;

/**
 * @author Michael Ansel
 * @author Ethan Goh
 */
public abstract class AbstractTimer implements ITimer
{
	private Object myArgument;
	private EventManager myEventManager;
	private String myEventName;
	private String myTimerName;

	protected AbstractTimer(EventManager eventManager, String timerName,
			String eventName, Object arg)
	{
		myEventManager = eventManager;
		myEventName = eventName;
		myTimerName = timerName;
		myArgument = arg;
	}

	@Override
	public String getName()
	{
		return myTimerName;
	}

	@Override
	public int compareTo(ITimer other)
	{
		if (other == null)
			throw new NullPointerException();

		return (int) Math.signum(this.getNextFireTime()
				- other.getNextFireTime());
	}

	@Override
	public boolean equals(Object o)
	{
		if (this.getClass() == o.getClass())
			return this.compareTo((ITimer) o) == 0;
		else
			return false;
	}

	@Override
	public void fireEvent()
	{
		myEventManager.fireEvent(this, myEventName, myArgument);
	}

	@Override
	public int hashCode()
	{
		return ((Long) this.getNextFireTime()).hashCode();
	}

	@Override
	public boolean isReadyToFire()
	{
		return getNextFireTime() <= System.currentTimeMillis();
	}
}
