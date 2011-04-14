package vooga.core.event;

import java.io.PrintStream;
import java.text.MessageFormat;

public class EventLogger
{
    public static boolean DEBUG_MODE = false;

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


    public EventLogger (PrintStream stream)
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
    	//TODO: Attach to Debugger Output
        myStream.println(str);
    }


    private void printMessage (MessageFormat messageFormat,
                               Object ... objects)
    {
    	//TODO: Attach to Debugger Output
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

    public void setDebugMode(boolean b)
    {
    	DEBUG_MODE = b;
    }
}