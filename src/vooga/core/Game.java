package vooga.core;

import java.awt.Graphics2D;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.core.event.ISimpleEventManager;
import vooga.core.event.PeriodicTimer;
import vooga.core.event.Timer;
import com.golden.gamedev.Game;

// Shouldn't this just have the game loop in it?  <3 Andrea
public abstract class Game extends Game implements ISimpleEventManager
{
	private EventManager myEventManager;

	public Game()
	{
		super();
		myEventManager = new EventManager();
		initializeEventHandlers();
	}

	@Override
	public void addEveryTurnEvent(String eventName, IEventHandler eventHandler)
	{
		myEventManager.addEveryTurnEvent(eventName, eventHandler);
	}

	@Override
	public void addPeriodicTimer(String timerName, long interval,
			String eventName)
	{
		myEventManager.addPeriodicTimer(timerName, interval, eventName);
	}

	@Override
	public void addPeriodicTimer(String timerName, long interval,
			String eventName, Object arg)
	{
		myEventManager.addPeriodicTimer(timerName, interval, eventName, arg);
	}

	@Override
	public void addTimer(String timerName, long delay, String eventName)
	{
		myEventManager.addTimer(timerName, delay, eventName);
	}

	@Override
	public void addTimer(String timerName, long delay, String eventName,
			Object arg)
	{
		myEventManager.addTimer(timerName, delay, eventName, arg);
	}

	@Override
	public void fireEvent(Object source, String eventName)
	{
		myEventManager.fireEvent(source, eventName);
	}

	@Override
	public void fireEvent(Object source, String eventName, Object arg)
	{
		myEventManager.fireEvent(source, eventName, arg);
	}

	@Override
	public void fireEvents(Object source, String glob)
	{
		myEventManager.fireEvents(source, glob);
	}

	@Override
	public void fireEvents(Object source, String glob, Object arg)
	{
		myEventManager.fireEvents(source, glob, arg);
	}

	@Override
	public void forwardEvent(String eventName, String nextEventName)
	{
		myEventManager.forwardEvent(eventName, nextEventName);
	}

	public EventManager getEventManager()
	{
		return myEventManager;
	}

	private void initializeEventHandlers()
	{
		myEventManager.registerEventHandler("EveryTurn.CheckInput",
				new IEventHandler()
				{
					public void handleEvent(Object o)
					{
						// TODO Game Resources Team: Poll all mapped inputs
						// (keys, mouse, etc.) and fire associated events
					}
				});
		myEventManager.registerEventHandler("EveryTurn.UpdatePlayField",
				new IEventHandler()
				{
					@Override
					public void handleEvent(Object o)
					{
						long elapsedTime = (Long) o;
						updatePlayField(elapsedTime);
					}
				});
	}

	@Override
	public abstract void initResources();

	@Override
	public void registerEventHandler(String eventName,
			IEventHandler eventHandler)
	{
		myEventManager.registerEventHandler(eventName, eventHandler);
	}

	@Override
	public IEventHandler removeEventHandler(String eventName)
	{
		return myEventManager.removeEventHandler(eventName);
	}

	@Override
	public void removeEventHandlers(String glob)
	{
		myEventManager.removeEventHandlers(glob);
	}

	@Override
	public abstract void render(Graphics2D g);

	@Override
	public void update(long elapsedTime)
	{
		myEventManager.update(elapsedTime);
	}

	public abstract void updatePlayField(long elapsedTime);
}
