package games.patterson_game.refactoredVooga.core.event;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Map;
import java.util.regex.Pattern;
import com.golden.gamedev.Game;

/**
 * @author Michael Ansel
 * @author Ethan Goh
 */
public class EventManager implements ISimpleEventManager
{
	private static class BaseEventFilter extends EventLayer
	{
		public BaseEventFilter()
		{
			super();
		}

		public EventLayer getParentFilter()
		{
			throw new UnsupportedOperationException(
					"BaseEventFilter has no parent!");
		}
	}

	private static EventLogger DEBUG = new EventLogger(System.err);

	private static final String EVERY_TURN_GLOB = "EveryTurn.*";

	private EventLayer myCurrentFilter;

	private static long maxRuntimeInMillis = 10;

	/**
	 * Create a new, empty EventManager.
	 */
	public EventManager()
	{
		myCurrentFilter = new BaseEventFilter();

		registerEventHandler("EveryTurn.CheckTimers", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				checkTimers();
			}
		});

		registerEventHandler("EveryTurn.CheckInput", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				// TODO check for user input and fire mapped events
				// call game resource manager to poll currently mapped keys and
				// fire associated event
			}
		});
	}

	private void addEvent(IFiredEvent event)
	{
		myCurrentFilter.addEvent(event);
	}

	/**
	 * Register a new Every Turn Event.
	 * 
	 * @param eventName
	 *            Event to listen for and handle.
	 * @param eventHandler
	 *            Handler to run when event is fired.
	 */
	@Override
	public void addEveryTurnEvent(String eventName, IEventHandler eventHandler)
	{
		myCurrentFilter.registerEventHandler("EveryTurn." + eventName,
				eventHandler);
	}

	@Override
	public void addPeriodicTimer(String timerName, long interval,
			String eventName)
	{
		this.addPeriodicTimer(timerName, interval, eventName, null);
	}

	/**
	 * Add a periodic timer to the EventManager. <code>eventName</code> will be
	 * fired every <code>interval</code> milliseconds.
	 * 
	 * @param interval
	 *            Number of milliseconds to wait before firing
	 *            <code>eventName</code> again.
	 * @param eventName
	 *            Event to fire every <code>interval</code> seconds.
	 * @return PeriodicTimer state (can be used to cancel the timer)
	 */
	@Override
	public void addPeriodicTimer(String timerName, long interval,
			String eventName, Object arg)
	{
		DEBUG.addTimer(eventName, interval, arg);
		myCurrentFilter.addPeriodicTimer(this, timerName, eventName, interval,
				arg);
	}

	@Override
	public void addTimer(String timerName, long delay, String eventName)
	{
		this.addTimer(timerName, delay, eventName, null);
	}

	/**
	 * Add a one-shot timer to the EventManager. <code>eventName</code> will be
	 * fired after <code>delay</code> milliseconds.
	 * 
	 * @param delay
	 *            Number of milliseconds to wait before firing
	 *            <code>eventName</code>.
	 * @param eventName
	 *            Event to fire after <code>delay</code> seconds.
	 * @return Timer state (can be used to cancel the timer)
	 */
	@Override
	public void addTimer(String timerName, long delay, String eventName,
			Object arg)
	{
		DEBUG.addTimer(eventName, delay, arg);
		myCurrentFilter.addTimer(this, timerName, eventName, delay, arg);
	}

	/**
	 * Fire events for all expired timers.
	 */
	protected void checkTimers()
	{
		for (ITimer timer : myCurrentFilter.getExpiredTimers())
		{
			DEBUG.timerExpired(timer);
			timer.fireEvent();
			if (timer.isFinished())
				this.removeTimer(timer.getName());
		}
	}

	/**
	 * Fire <code>eventName</code> with provided <code>source</code> context.
	 * 
	 * @param source
	 *            Context from which event will be fired.
	 * @param eventName
	 *            Event to fire.
	 */
	@Override
	public void fireEvent(Object source, String eventName)
	{
		fireEvent(source, eventName, null);
	}

	/**
	 * Fire <code>eventName</code> with provided <code>source</code> context and
	 * argument.
	 * 
	 * @param source
	 *            Context from which event will be fired.
	 * @param eventName
	 *            Event to fire.
	 * @param arg
	 *            Argument to pass to event handler
	 */
	@Override
	public void fireEvent(final Object source, final String eventName,
			final Object arg)
	{
		final IEventHandler handler = myCurrentFilter
				.getEventHandler(eventName);
		if (handler == null)
		{
			DEBUG.fireNonExistentEvent(source, eventName, arg);
			return;
		}
		DEBUG.fireEvent(source, eventName, arg);
		addEvent(new IFiredEvent()
		{

			@Override
			public boolean eventNameMatches(String regex)
			{
				return Pattern.matches(regex, eventName);
			}

			@Override
			public Object getSource()
			{
				return source;
			}

			@Override
			public void run()
			{
				handler.handleEvent(arg);
			}

			@Override
			public String toString()
			{
				return String.format("Event<%s>", eventName);
			}

		});
	}

	/**
	 * Fire all events matching <code>glob</code> (pattern) with provided
	 * <code>source</code> context.
	 * 
	 * @param source
	 *            Context from which event will be fired.
	 * @param glob
	 *            Pattern to use for selecting events to fire.
	 */
	@Override
	public void fireEvents(Object source, String glob)
	{
		fireEvents(source, glob, null);
	}

	/**
	 * Fire all events matching <code>glob</code> (pattern) with provided
	 * <code>source</code> context and argument.
	 * 
	 * @param source
	 *            Context from which event will be fired.
	 * @param glob
	 *            Pattern to use for selecting events to fire.
	 * @param arg
	 *            Argument to pass to event handlers for all fired events.
	 */
	@Override
	public void fireEvents(Object source, String glob, Object arg)
	{
		for (Map.Entry<String, IEventHandler> entry : myCurrentFilter
				.getEventHandlerEntries(glob))
			fireEvent(source, entry.getKey(), arg);
	}

	/**
	 * Syntactic sugar to handle an event by immediately firing a different
	 * event. Useful for keymaps and other similar "glue" constructs.
	 * 
	 * @param eventName
	 *            Event to listen for.
	 * @param nextEventName
	 *            Event to fire.
	 */
	@Override
	public void forwardEvent(String eventName, final String nextEventName)
	{
		registerEventHandler(eventName, new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				fireEvent(this, nextEventName, o);
			}
		});
	}

	/**
	 * Pop the topmost EventFilter off the filter stack and return it.
	 * 
	 * @return Removed filter
	 */
	public EventLayer popFilter()
	{
		EventLayer oldFilter = myCurrentFilter;
		myCurrentFilter = myCurrentFilter.getParentFilter();
		return oldFilter;
	}

	/**
	 * Create a new EventFilter and push it onto the filter stack. The new
	 * filter automatically duplicates its parent's state.
	 */
	public void pushFilter()
	{
		myCurrentFilter = new EventLayer(myCurrentFilter);
	}

	/**
	 * Push the provided filter onto the filter stack. Also updates the provided
	 * filter's parent to point to the previous top of the filter stack.
	 * 
	 * @param newFilter
	 *            Filter to push onto the filter stack.
	 */
	public void pushFilter(EventLayer newFilter)
	{
		newFilter.setParentFilter(myCurrentFilter);
		myCurrentFilter = newFilter;
	}

	/**
	 * Register a new event handler.
	 * 
	 * @param eventName
	 *            Event to listen for and handle.
	 * @param eventHandler
	 *            Handler to run when event is fired.
	 */
	@Override
	public void registerEventHandler(String eventName,
			IEventHandler eventHandler)
	{
		DEBUG.registerEventHandler(eventName, eventHandler);
		myCurrentFilter.registerEventHandler(eventName, eventHandler);
	}

	/**
	 * Remove and return the event handler for the named event.
	 * 
	 * @param eventName
	 *            Name of event target handler is registered under.
	 * @return Removed event handler; null if no handler found.
	 */
	@Override
	public IEventHandler removeEventHandler(String eventName)
	{
		return myCurrentFilter.removeEventHandler(eventName);
	}

	/**
	 * Remove all event handlers for all events matching <code>glob</code>.
	 * 
	 * @param glob
	 *            Pattern to use to match events targeted for removal.
	 */
	@Override
	public void removeEventHandlers(String glob)
	{
		for (Map.Entry<String, IEventHandler> entry : myCurrentFilter
				.getEventHandlerEntries(glob))
		{
			DEBUG.removeEventHandler(entry.getKey(), entry.getValue());
			removeEventHandler(entry.getKey());
		}
	}

	/**
	 * Remove/Cancel the specified timer.
	 * 
	 * @param timer
	 *            Timer to remove.
	 */
	public void removeTimer(String timerName)
	{
		myCurrentFilter.removeTimer(timerName);
	}

	/**
	 * Revert all changes made to the current filter since its creation by
	 * clearing the filter's internal state and recopying its parent's state.
	 */
	public void resetFilterToParentState()
	{
		myCurrentFilter.resetToParentState();
	}

	/**
	 * Core reactor loop. Fires timer-based events and dispatches all queued
	 * events. Call directly from {@link Game#update(long)}.
	 * 
	 * @param elapsedTime
	 *            Time elapsed since last invocation.
	 */
	public void update(long elapsedTime)
	{
		runEveryTurnEvents(this, EVERY_TURN_GLOB, elapsedTime);

		myCurrentFilter.swapEventQueues();

		long eventManagerStartTime = System.currentTimeMillis();

		while (myCurrentFilter.hasEvents()
				&& System.currentTimeMillis() - eventManagerStartTime < maxRuntimeInMillis)
		{
			IFiredEvent event = myCurrentFilter.removeEvent();
			DEBUG.runEvent(event);
			event.run();
		}

	}

	private void runEveryTurnEvents(EventManager eventManager,
			String everyTurnGlob, long elapsedTime)
	{
		for (Map.Entry<String, IEventHandler> entry : myCurrentFilter
				.getEventHandlerEntries(everyTurnGlob))
		{
			entry.getValue().handleEvent(elapsedTime);
		}

	}

	public void setDebugMode(boolean b)
	{
		DEBUG.setDebugMode(b);
	}
}
