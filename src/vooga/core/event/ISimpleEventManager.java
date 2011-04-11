package vooga.core.event;

public interface ISimpleEventManager
{
    public void addEveryTurnEvent (String eventName, IEventHandler eventHandler);


    public PeriodicTimer addPeriodicTimer (long interval, String eventName);


    public PeriodicTimer addPeriodicTimer (long interval,
                                           String eventName,
                                           Object arg);


    public Timer addTimer (long delay, String eventName);


    public Timer addTimer (long delay, String eventName, Object arg);


    public void fireEvent (Object source, String eventName);


    public void fireEvent (Object source, String eventName, Object arg);


    public void fireEvents (Object source, String glob);


    public void fireEvents (Object source, String glob, Object arg);


    public void forwardEvent (String eventName, String nextEventName);


    public void registerEventHandler (String eventName,
                                      IEventHandler eventHandler);


    public IEventHandler removeEventHandler (String eventName);


    public void removeEventHandlers (String glob);
}
