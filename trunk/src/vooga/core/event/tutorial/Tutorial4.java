package vooga.core.event.tutorial;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;

public class Tutorial4
{
	private EventManager eventManager;

	public Tutorial4()
	{
		eventManager = new EventManager();
	}

	public void createEventHandler()
	{
		eventManager.registerEventHandler("Message", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				sayHi();
			}
		});
	}

	public void periodicTimer()
	{
		int timeInMilli = 1000;
		eventManager.addPeriodicTimer("OneTime", timeInMilli, "Message");
	}
	
	public void sayHi()
	{
		System.out.println("Hi!");
	}

	public void doWork()
	{
		while(true)
			eventManager.update(0);
	}

	public static void main(String[] args)
	{
		Tutorial4 t1 = new Tutorial4();
		t1.createEventHandler();
		t1.periodicTimer();
		t1.doWork();
	}
}
