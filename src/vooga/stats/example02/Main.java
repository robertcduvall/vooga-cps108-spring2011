package vooga.stats.example02;

import java.awt.Dimension;

import vooga.stats.example02.util.resources.ResourceManager;
import vooga.stats.example02.view.components.ErrorCatcher;

import com.golden.gamedev.GameLoader;

/**
 * Main method
 * @author Yin
 *
 */
public class Main 
{
	
	//private static int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	private static String GAME_RESOURCE = "game";
	private static String WINDOW_SIZE = "window_size";
	
	public static void main(String[] args)
	{
		
		ResourceManager resources = ResourceManager
        .getManager(GAME_RESOURCE);
		
		String[] windowSize = resources.getStringArray(WINDOW_SIZE,",");
		
		TrickyGame myGame = new TrickyGame();
		
		GameLoader myGameLoader = new GameLoader();
		
		// Run program
		// If exception is caught, user is notified via GUI
		try
		{
			myGameLoader.setup(myGame, 
			   new Dimension(Integer.parseInt(windowSize[0]),Integer.parseInt(windowSize[1])), 
			   false);
			   myGameLoader.start();
		}
		catch (Exception e)
		{
			new ErrorCatcher(e.getMessage());
		}
		
	}
	
}