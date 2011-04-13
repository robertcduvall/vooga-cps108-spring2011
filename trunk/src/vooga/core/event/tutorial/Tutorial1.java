package vooga.core.event.tutorial;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;

public class Tutorial1
{
	private EventManager eventManager;

	public Tutorial1()
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

	public void sayHi()
	{
		System.out.println("Hi!");
	}

	public void doWork()
	{
		eventManager.fireEvent(this, "Message");
		eventManager.update(0);
	}

	public static void main(String[] args)
	{
		Tutorial1 t1 = new Tutorial1();
		t1.createEventHandler();
		t1.doWork();
	}
}
