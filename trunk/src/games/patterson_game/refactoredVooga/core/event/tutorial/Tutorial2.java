package games.patterson_game.refactoredVooga.core.event.tutorial;
import games.patterson_game.refactoredVooga.core.event.EventManager;
import games.patterson_game.refactoredVooga.core.event.IEventHandler;

public class Tutorial2
{
	private EventManager eventManager;

	public Tutorial2()
	{
		eventManager = new EventManager();
	}

	public void everyTurnEventHandler()
	{
		eventManager.addEveryTurnEvent("Message", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				sayHi();
			}
		});
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
		Tutorial2 t1 = new Tutorial2();
		t1.everyTurnEventHandler();
		t1.doWork();
	}
}
