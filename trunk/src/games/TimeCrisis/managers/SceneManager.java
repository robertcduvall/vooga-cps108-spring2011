
package games.TimeCrisis.managers;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import vooga.core.VoogaGame;

import com.golden.gamedev.object.Sprite;

/**
 * @author Troy Ferrell
 *
 */
public class SceneManager 
{
	private static final String SCANNER_DELIMITTER = "\\s+";
	
	private ArrayList<Sprite> mySceneObjects = new ArrayList<Sprite>();
	
	private VoogaGame myGame;
	
	public SceneManager(VoogaGame game)
	{
		myGame = game;
	}
	
	public void loadNewLevel(int lvl)
	{
		mySceneObjects.clear();
		
		String filePath = myGame.getResourceManager().getBundle().getString("SceneLevelFile" + lvl );
		
		try
		{
			Scanner reader = new Scanner(new File(filePath)).useDelimiter(SCANNER_DELIMITTER);
			
			while(reader.hasNext())
			{
				String img = reader.next();
				int x = Integer.parseInt(reader.next());
				int y = Integer.parseInt(reader.next());
				
				mySceneObjects.add( new Sprite(myGame.getImageLoader().getImage(img), x, y));
			}
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void render(Graphics2D g)
	{
		for(Sprite s : mySceneObjects)
			s.render(g);
	}
}
