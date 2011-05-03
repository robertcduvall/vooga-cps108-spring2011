package vooga.network.example.plantvszombie.game;

import java.util.Random;
import java.util.ResourceBundle;

import vooga.reflection.Reflection;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

public class LevelSetup
{
	private static final int OFFSET = 50;
	private final String resourceFile ="vooga.network.example.plantvszombie.resources.levelInfo";
	private ResourceBundle myResources;
	private PlantVsZombie game;
	private Timer levelTimer;
	private int levelTimeinSeconds;
	private int myLevel;
	private String[] supportedZombies;
	private double generateRate;
	private Random random;
	private int supportedLevels;
	
	public LevelSetup(PlantVsZombie game){
		myResources = ResourceBundle.getBundle(resourceFile);
		supportedLevels = myResources.keySet().size();
		this.game = game;
		random = new Random();
		myLevel = 1;
	}
	
	public void update(long elapsedTime){
		if(levelTimer.action(elapsedTime)){
			game.setState(GameState.TRANSITION);
			return;
		}
		if(random.nextDouble()<=generateRate && (levelTimer.getCurrentTick()/1000 > 10)){
			int tmpRow = random.nextInt(5);
			Reflection.createInstance(supportedZombies[random.nextInt(supportedZombies.length)], 700, tmpRow*100+OFFSET,tmpRow, game);
		}
	}
	
	public void initializeLevel(int level){
		game.clearStates();
		if(level > supportedLevels){
			game.setState(GameState.END);
			return;
		}
		myLevel = level;
		game.setSunRepository(100);
		String[] tmpStr = myResources.getString("level"+level).split(",");
		levelTimeinSeconds = Integer.valueOf(tmpStr[0]);
		supportedZombies = tmpStr[1].substring(1,tmpStr[1].length()-1).split(";");
		levelTimer = new Timer(levelTimeinSeconds*1000);
		generateRate = Double.valueOf(tmpStr[2]);
		
		game.setState(GameState.PLAYING);
	}
	
	public int getLevel(){
		return myLevel;
	}
	
	public void nextLevel(){
		initializeLevel(myLevel+1);
	}
	
	public void restartLevel(){
		initializeLevel(myLevel);
	}
	
	public int getRemainingTime(){
		return (int)((levelTimeinSeconds*1000 - levelTimer.getCurrentTick())/1000);
	}
}
