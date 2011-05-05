package games.BattleRPG;

import games.BattleRPG.util.BattleGameHandler;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.debugger.Debugger;
import vooga.resources.images.ImageLoader;
/**
 * 
 * @author Austin Benesh
 *	5 May 2011
 */
public class BattleRPG extends VoogaGame
{
	public static EventManager eventManager;
    public static ImageLoader imageLoader;
    public static BattleGameHandler gameHandler;
	private Debugger myDebugger;
	
    {distribute = true;}

	@Override
	public void updatePlayField(long elapsedTime)
	{
		myDebugger.update(elapsedTime);
	}
	@Override
	public void initResources()
	{
		eventManager = getEventManager();
	    imageLoader = getImageLoader();
	    BattleGameHandler.initHandler(this);
		myDebugger = new Debugger(this);
	    
	    getLevelManager().loadLevel(0);
	}
	
	public static void main (String[] args)
    {   
		launchGame(new BattleRPG(), new Dimension(500, 400), false);
    }

}
