package vooga.core.event;

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
        public BaseEventFilter ()
        {
            super();
        }


        public EventLayer getParentFilter ()
        {
            throw new UnsupportedOperationException("BaseEventFilter has no parent!");
        }
    }

    protected static class Logger
    {
        private static final MessageFormat ADD_PERIODIC_TIMER_MESSAGE =
            new MessageFormat("Adding new {1} millisecond periodic timer for event \"{0}\" with argument {2}.");

        private static final MessageFormat ADD_TIMER_MESSAGE =
            new MessageFormat("Adding new {1} millisecond timer for event \"{0}\" with argument {2}.");

        private static final MessageFormat FIRE_EVENT_MESSAGE =
            new MessageFormat("Firing event \"{1}\" with argument {2} and source {0}.");

        private static final MessageFormat FIRE_NONEXISTENT_EVENT =
            new MessageFormat("Attempted to fire non-existent event \"{1}\" from {0} with argument {2}.");

        private static final boolean HIDE_EVERY_TURN_EVENTS = true;

        private static final MessageFormat REGISTER_EVENT_HANDLER_MESSAGE =
            new MessageFormat("Registering handler for event \"{0}\".");

        private static final MessageFormat REMOVE_EVENT_HANDLER_MESSAGE =
            new MessageFormat("Removing handler for event \"{0}\" ({1}).");

        private static final MessageFormat RUN_EVENT_MESSAGE =
            new MessageFormat("Running event \"{0}\" (fired by {1}).");

        private static final MessageFormat TIMER_EXPIRED_MESSAGE =
            new MessageFormat("Timer expired! ({0})");

        private PrintStream myStream;


        public Logger (PrintStream stream)
        {
            myStream = stream;
        }


        public void addPeriodicTimer (String eventName,
                                      long interval,
                                      Object arg)
        {
            printMessage(ADD_PERIODIC_TIMER_MESSAGE, eventName, interval, arg);
        }


        public void addTimer (String eventName, long delay, Object arg)
        {
            printMessage(ADD_TIMER_MESSAGE, eventName, delay, arg);
        }


        public void fireEvent (Object source, String eventName, Object arg)
        {
            if (HIDE_EVERY_TURN_EVENTS && eventName.matches("^EveryTurn[.].*$")) return;
            printMessage(FIRE_EVENT_MESSAGE, source, eventName, arg);
        }


        public void fireNonExistentEvent (Object source,
                                          String eventName,
                                          Object arg)
        {
            printMessage(FIRE_NONEXISTENT_EVENT, source, eventName, arg);
        }


        private void println (String str)
        {
            myStream.println(str);
        }


        private void printMessage (MessageFormat messageFormat,
                                   Object ... objects)
        {
            if (DEBUG_MODE) println(messageFormat.format(objects));
        }


        public void registerEventHandler (String eventName,
                                          IEventHandler eventHandler)
        {
            printMessage(REGISTER_EVENT_HANDLER_MESSAGE,
                         eventName,
                         eventHandler);
        }


        public void removeEventHandler (String eventName,
                                        IEventHandler eventHandler)
        {
            printMessage(REMOVE_EVENT_HANDLER_MESSAGE, eventName, eventHandler);
        }


        public void runEvent (IFiredEvent event)
        {
            if (HIDE_EVERY_TURN_EVENTS &&
                event.eventNameMatches("^EveryTurn[.].*$")) return;
            printMessage(RUN_EVENT_MESSAGE, event.toString(), event.getSource());
        }


        public void timerExpired (ITimer timer)
        {
            printMessage(TIMER_EXPIRED_MESSAGE, timer);
        }

    }

    private static Logger DEBUG = new Logger(System.err);

    public static boolean DEBUG_MODE = false;

    private static final String EVERY_TURN_GLOB = "EveryTurn.*";

    private EventLayer myCurrentFilter;


    /**
     * Create a new, empty EventManager.
     */
    public EventManager ()
    {
        myCurrentFilter = new BaseEventFilter();

        registerEventHandler("EveryTurn.CheckTimers", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                checkTimers();
            }
        });

        registerEventHandler("EveryTurn.CheckInput", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                // TODO check for user input and fire mapped events
                // call game resource manager to poll currently mapped keys and
                // fire associated event
            }
        });
    }


    private void addEvent (IFiredEvent event)
    {
        myCurrentFilter.addEvent(event);
    }


    /**
     * Register a new Every Turn Event.
     * 
     * @param eventName Event to listen for and handle.
     * @param eventHandler Handler to run when event is fired.
     */
    @Override
    public void addEveryTurnEvent (String eventName, IEventHandler eventHandler)
    {
        myCurrentFilter.registerEventHandler("EveryTurn." + eventName,
                                             eventHandler);
    }


    @Override
    public PeriodicTimer addPeriodicTimer (long interval, String eventName)
    {
        return this.addPeriodicTimer(interval, eventName, null);
    }


    /**
     * Add a periodic timer to the EventManager. <code>eventName</code> will be
     * fired every <code>interval</code> milliseconds.
     * 
     * @param interval Number of milliseconds to wait before firing
     *            <code>eventName</code> again.
     * @param eventName Event to fire every <code>interval</code> seconds.
     * @return PeriodicTimer state (can be used to cancel the timer)
     */
    @Override
    public PeriodicTimer addPeriodicTimer (long interval,
                                           String eventName,
                                           Object arg)
    {
        DEBUG.addTimer(eventName, interval, arg);
        return myCurrentFilter.addPeriodicTimer(this, eventName, interval, arg);
    }


    @Override
    public Timer addTimer (long delay, String eventName)
    {
        return this.addTimer(delay, eventName, null);
    }


    /**
     * Add a one-shot timer to the EventManager. <code>eventName</code> will be
     * fired after <code>delay</code> milliseconds.
     * 
     * @param delay Number of milliseconds to wait before firing
     *            <code>eventName</code>.
     * @param eventName Event to fire after <code>delay</code> seconds.
     * @return Timer state (can be used to cancel the timer)
     */
    @Override
    public Timer addTimer (long delay, String eventName, Object arg)
    {
        DEBUG.addTimer(eventName, delay, arg);
        return myCurrentFilter.addTimer(this, eventName, delay, arg);
    }


    /**
     * Fire events for all expired timers.
     */
    protected void checkTimers ()
    {
        for (ITimer timer : myCurrentFilter.getExpiredTimers())
        {
            DEBUG.timerExpired(timer);
            timer.fireEvent();
            if (timer.isFinished()) this.removeTimer(timer);
        }
    }


    /**
     * Fire <code>eventName</code> with provided <code>source</code> context.
     * 
     * @param source Context from which event will be fired.
     * @param eventName Event to fire.
     */
    @Override
    public void fireEvent (Object source, String eventName)
    {
        fireEvent(source, eventName, null);
    }


    /**
     * Fire <code>eventName</code> with provided <code>source</code> context and
     * argument.
     * 
     * @param source Context from which event will be fired.
     * @param eventName Event to fire.
     * @param arg Argument to pass to event handler
     */
    @Override
    public void fireEvent (final Object source,
                           final String eventName,
                           final Object arg)
    {
        final IEventHandler handler =
            myCurrentFilter.getEventHandler(eventName);
        if (handler == null)
        {
            DEBUG.fireNonExistentEvent(source, eventName, arg);
            return;
        }
        DEBUG.fireEvent(source, eventName, arg);
        addEvent(new IFiredEvent()
        {

            @Override
            public boolean eventNameMatches (String regex)
            {
                return Pattern.matches(regex, eventName);
            }


            @Override
            public Object getSource ()
            {
                return source;
            }


            @Override
            public void run ()
            {
                handler.handleEvent(arg);
            }


            @Override
            public String toString ()
            {
                return String.format("Event<%s>", eventName);
            }

        });
    }


    /**
     * Fire all events matching <code>glob</code> (pattern) with provided
     * <code>source</code> context.
     * 
     * @param source Context from which event will be fired.
     * @param glob Pattern to use for selecting events to fire.
     */
    @Override
    public void fireEvents (Object source, String glob)
    {
        fireEvents(source, glob, null);
    }


    /**
     * Fire all events matching <code>glob</code> (pattern) with provided
     * <code>source</code> context and argument.
     * 
     * @param source Context from which event will be fired.
     * @param glob Pattern to use for selecting events to fire.
     * @param arg Argument to pass to event handlers for all fired events.
     */
    @Override
    public void fireEvents (Object source, String glob, Object arg)
    {
        for (Map.Entry<String, IEventHandler> entry : myCurrentFilter.getEventHandlerEntries(glob))
            fireEvent(source, entry.getKey(), arg);
    }


    /**
     * Syntactic sugar to handle an event by immediately firing a different
     * event. Useful for keymaps and other similar "glue" constructs.
     * 
     * @param eventName Event to listen for.
     * @param nextEventName Event to fire.
     */
    @Override
    public void forwardEvent (String eventName, final String nextEventName)
    {
        registerEventHandler(eventName, new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
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
    public EventLayer popFilter ()
    {
        EventLayer oldFilter = myCurrentFilter;
        myCurrentFilter = myCurrentFilter.getParentFilter();
        return oldFilter;
    }


    /**
     * Create a new EventFilter and push it onto the filter stack. The new
     * filter automatically duplicates its parent's state.
     */
    public void pushFilter ()
    {
        myCurrentFilter = new EventLayer(myCurrentFilter);
    }


    /**
     * Push the provided filter onto the filter stack. Also updates the provided
     * filter's parent to point to the previous top of the filter stack.
     * 
     * @param newFilter Filter to push onto the filter stack.
     */
    public void pushFilter (EventLayer newFilter)
    {
        newFilter.setParentFilter(myCurrentFilter);
        myCurrentFilter = newFilter;
    }


    /**
     * Register a new event handler.
     * 
     * @param eventName Event to listen for and handle.
     * @param eventHandler Handler to run when event is fired.
     */
    @Override
    public void registerEventHandler (String eventName,
                                      IEventHandler eventHandler)
    {
        DEBUG.registerEventHandler(eventName, eventHandler);
        myCurrentFilter.registerEventHandler(eventName, eventHandler);
    }


    /**
     * Remove and return the event handler for the named event.
     * 
     * @param eventName Name of event target handler is registered under.
     * @return Removed event handler; null if no handler found.
     */
    @Override
    public IEventHandler removeEventHandler (String eventName)
    {
        return myCurrentFilter.removeEventHandler(eventName);
    }


    /**
     * Remove all event handlers for all events matching <code>glob</code>.
     * 
     * @param glob Pattern to use to match events targeted for removal.
     */
    @Override
    public void removeEventHandlers (String glob)
    {
        for (Map.Entry<String, IEventHandler> entry : myCurrentFilter.getEventHandlerEntries(glob))
        {
            DEBUG.removeEventHandler(entry.getKey(), entry.getValue());
            removeEventHandler(entry.getKey());
        }
    }


    /**
     * Remove/Cancel the specified timer.
     * 
     * @param timer Timer to remove.
     */
    public void removeTimer (ITimer timer)
    {
        myCurrentFilter.removeTimer(timer);
    }


    /**
     * Revert all changes made to the current filter since its creation by
     * clearing the filter's internal state and recopying its parent's state.
     */
    public void resetFilterToParentState ()
    {
        myCurrentFilter.resetToParentState();
    }


    /**
     * Core reactor loop. Fires timer-based events and dispatches all queued
     * events. Call directly from {@link Game#update(long)}.
     * 
     * @param elapsedTime Time elapsed since last invocation.
     */
    public void update (long elapsedTime)
    {
        fireEvents(this, EVERY_TURN_GLOB, elapsedTime);

        myCurrentFilter.swapEventQueues();

        while (myCurrentFilter.hasEvents())
        {
            IFiredEvent event = myCurrentFilter.removeEvent();
            DEBUG.runEvent(event);
            event.run();
        }
    }
}
