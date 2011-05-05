package games.TimeCrisis.managers;

import games.TimeCrisis.actors.EnemyTarget;
import games.TimeCrisis.actors.SpritePath;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import vooga.core.VoogaGame;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * @author Troy Ferrell
 *
 */
public class EnemyManager 
{
	private static final String SCANNER_DELIMITTER = "\\s+";
	
	private Map<SpritePath, EnemyTarget> myEnemyPathMap = new HashMap<SpritePath, EnemyTarget>();
	
	private int totalKills = 0;
	private int killsToCompleteLevel = 0;
	
	VoogaGame myGame;

	public EnemyManager(VoogaGame game)
	{	
		myGame = game;
	}
	
	public void loadNextLevel(int lvl)
	{
		myEnemyPathMap.clear();
		
		totalKills = 0;
		killsToCompleteLevel = myGame.getResourceManager().getBundle().getInteger("EnemyKillLimit" + lvl );
		
		String filePath = myGame.getResourceManager().getBundle().getString("EnemyPathFile" + lvl );
		
		try
		{
			Scanner reader = new Scanner(new File(filePath)).useDelimiter(SCANNER_DELIMITTER);
			
			while(reader.hasNext())
			{
				int x1 = Integer.parseInt(reader.next());
				int y1 = Integer.parseInt(reader.next());
				
				int x2 = Integer.parseInt(reader.next());
				int y2 = Integer.parseInt(reader.next());
				
				double scale = Double.parseDouble(reader.next());
				
				myEnemyPathMap.put(new SpritePath(x1, y1, x2, y2, scale), null);
			}
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean checkCollision(int x, int y)
	{
		for(SpritePath path : myEnemyPathMap.keySet())
		{
			EnemyTarget enemyTarget = (EnemyTarget)(myEnemyPathMap.get(path));
			if(enemyTarget != null)
			{
				if(enemyTarget.isShowing() && enemyTarget.getBounds().contains(x, y))
				{
					// TODO; look at type of player gun and use that as damage value
					enemyTarget.damage(25, x, y);
					
					if(!enemyTarget.isAlive())	
						killEnemy(path);
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean checkLevelCompleted()
	{
		return totalKills >= killsToCompleteLevel;
	}
	
	public void update(long deltaTime)
	{	
		for(SpritePath path : myEnemyPathMap.keySet())
		{
			EnemyTarget et = (EnemyTarget)(myEnemyPathMap.get(path));
			if(et != null)
			{
				if(et.isAlive())
					et.update(deltaTime);
				else
					killEnemy(path);
			}
		}
		
		for(SpritePath path : myEnemyPathMap.keySet())
		{
			EnemyTarget et = (EnemyTarget)(myEnemyPathMap.get(path));
			if(et == null)
			{
				boolean addEnemy = (Math.random() > 0.85);
				if(addEnemy)
				{
					myEnemyPathMap.put(path, new EnemyTarget(myGame, 
							myGame.getLevelManager().getCurrentLevel().getId(), path));
					break;
				}
			}
		}//end for
	}

	/**
	 * @param path
	 */
	private void killEnemy(SpritePath path) 
	{
		myGame.playSound("games/TimeCrisis/resources/sounds/deathBeep.wav");
		
		totalKills++;
		path.reset();
		myEnemyPathMap.put(path, null);
	}
	
	public void render(Graphics2D g2d)
	{
		for(SpritePath path : myEnemyPathMap.keySet())
		{
			EnemyTarget et = (EnemyTarget)(myEnemyPathMap.get(path));
			if(et != null)
				et.render(g2d);
		}
	}
}
