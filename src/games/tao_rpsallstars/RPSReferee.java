/**
 * 
 */
package games.tao_rpsallstars;

import games.tao_rpsallstars.cpu_player.CpuPlayer;
import games.tao_rpsallstars.sprites.User;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;

/**
 * @author Kevin
 *
 * This class listens to the RPS throws of the player and the RPS throws of the CPU. Then it
 * decides who wins the round.
 */
public class RPSReferee {
	
	private EventManager eventManager;
	private CpuPlayer cpu;
	private User user;
	
	public RPSReferee(EventManager eventManager){
		this.eventManager = eventManager;
		CpuPlayer cpu = new CpuPlayer(eventManager, null);
		createEventHandlers();
	}
	
	public CpuPlayer getCPU(){
		return cpu;
	}
	
	public void createEventHandlers()
    {
            eventManager.registerEventHandler("Input.User.Rock", new IEventHandler()
            {
                    @Override
                    public void handleEvent(Object o)
                    {
                            //sayHi();
                    	//Tells CPU to throw
                    }
            });
            eventManager.registerEventHandler("Input.User.Paper", new IEventHandler()
            {
                    @Override
                    public void handleEvent(Object o)
                    {
                            //sayHi();
                    	//Tells CPU to throw
                    }
            });
            eventManager.registerEventHandler("Input.User.Scissors", new IEventHandler()
            {
                    @Override
                    public void handleEvent(Object o)
                    {
                            //sayHi();
                    	//Tells CPU to throw
                    }
            });
            eventManager.registerEventHandler("Input.CPU.Rock", new IEventHandler()
            {
                    @Override
                    public void handleEvent(Object o)
                    {
                            //sayHi();
                    	//Tells Referee to judge
                    }
            });
            eventManager.registerEventHandler("Input.CPU.Paper", new IEventHandler()
            {
                    @Override
                    public void handleEvent(Object o)
                    {
                            //sayHi();
                    	//Tells Referee to judge
                    }
            });
            eventManager.registerEventHandler("Input.CPU.Scissors", new IEventHandler()
            {
                    @Override
                    public void handleEvent(Object o)
                    {
                            //sayHi();
                    	//Tells Referee to judge
                    }
            });
    }
	
}
