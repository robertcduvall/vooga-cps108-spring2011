package games.patterson_game.refactoredVooga.core.event.tutorial;
import games.patterson_game.refactoredVooga.core.event.EventManager;
import games.patterson_game.refactoredVooga.core.event.IEventHandler;

public class Tutorial3
{
	private EventManager eventManager;

	public Tutorial3()
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

	public void oneShotTimer()
	{
		int timeInMilli = 1000;
		eventManager.addTimer("OneTime", timeInMilli, "Message");
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
		Tutorial3 t1 = new Tutorial3();
		t1.createEventHandler();
		t1.oneShotTimer();
		t1.doWork();
	}
}
