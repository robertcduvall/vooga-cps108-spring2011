/**
 * 
 */
package games.tao_rpsallstars;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	private Map<String,Set<String>> aBeatsB = new HashMap<String,Set<String>>();
	private String userChoice;
	private String CPUChoice;
	
	
	public RPSReferee(EventManager eventManager, User user){
		this.eventManager = eventManager;
		CpuPlayer cpu = new CpuPlayer(eventManager, null);
		this.user = user;
		createEventHandlers();
		
		putRelationship("Rock", "Scissors");
		putRelationship("Scissors", "Paper");
		putRelationship("Paper", "Rock");
		putRelationship("Fire", "Rock");
		putRelationship("Fire", "Scissors");
		putRelationship("Fire", "Paper");
	}
	
	public void putRelationship(String A, String B){
		if(aBeatsB.containsKey(A)){
			aBeatsB.get(A).add(B);
		} else{
			Set<String> s = new HashSet<String>();
			s.add(B);
			aBeatsB.put(A,s);
		}
	}
	
	public void judge(){
		if(aBeatsB.get(userChoice).contains(CPUChoice)){
			//TODO: user scores
		}
		else if(aBeatsB.get(CPUChoice).contains(userChoice)){
			//TODO: CPU scores
		} else{
			//TODO: tie
		}
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
                    	user.rock();
                    }
            });
            eventManager.registerEventHandler("Input.User.Paper", new IEventHandler()
            {
                    @Override
                    public void handleEvent(Object o)
                    {
                            //sayHi();
                    	//Tells CPU to throw
                    	user.paper();
                    }
            });
            eventManager.registerEventHandler("Input.User.Scissors", new IEventHandler()
            {
                    @Override
                    public void handleEvent(Object o)
                    {
                            //sayHi();
                    	//Tells CPU to throw
                    	user.scissors();
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
