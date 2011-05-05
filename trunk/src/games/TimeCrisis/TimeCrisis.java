package games.TimeCrisis;

import games.TimeCrisis.actors.BulletHole;
import games.TimeCrisis.managers.EnemyManager;
import games.TimeCrisis.managers.PlayerManager;
import games.TimeCrisis.managers.SceneManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;

/**
 * @author Troy Ferrell
 *
 */
public class TimeCrisis extends VoogaGame 
{ 
	 { distribute = true; }
	 
	private static final int GAME_OVER_STATE = 0;
	private static final int GAME_PLAY_STATE = 1;
	private static final int GAME_LEVEL_STATE = 2;
	private int currentState = GAME_OVER_STATE;
	private int maxLevel;
	
	private Background bg;
	
	private PlayerManager myPlayer;
	private EnemyManager myEnemyManager;
	private SceneManager mySceneManager;
	
	private SpriteGroup myBulletHoleGroup;
	private Sprite mainMenuImg;
	private Sprite levelCompleteImg;
	
	@Override
	public void initResources() 
	{
		this.hideCursor();
		
		bg = new ColorBackground(Color.BLACK);
		
		myPlayer = new PlayerManager(this);
		myEnemyManager = new EnemyManager(this); // load file data here
		mySceneManager = new SceneManager(this);
		
		myBulletHoleGroup = new SpriteGroup("Bullet Hole Group");
		mainMenuImg = new Sprite(getImageLoader().getImage("MainMenuImg"));
		levelCompleteImg = new Sprite(getImageLoader().getImage("LevelCompleteImg"));

		maxLevel = getResourceManager().getBundle().getInteger("MaxLevel");

		createEvents();
	}

	/**
	 *Register and bind event handlers for TimeCrisis 
	 */
	private void createEvents() 
	{
		getEventManager().addEveryTurnEvent("gameOverCheck", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
            	if(myPlayer.getTimeLeft() < 0 && currentState == GAME_PLAY_STATE)
            	{
            		currentState = GAME_OVER_STATE;
            		playSound("src/games/TimeCrisis/resources/sounds/UH_OH.wav");
            	}
            }
        });
		
		getEventManager().registerEventHandler("levelCompleteEvent",  new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
            	currentState = GAME_PLAY_STATE;
            }
        });
		
		getEventManager().registerEventHandler("Input.User.Fire", new IEventHandler()
		{
		    @Override
		    public void handleEvent (Object o)
		    {
		    	if(currentState == GAME_PLAY_STATE)
		    		shoot();
		    }
		});
		
		getEventManager().registerEventHandler("Input.User.Cover", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	if(currentState == GAME_PLAY_STATE)
            	{
	            	if(myPlayer.isShowing())
	            		myPlayer.hide();
	    			else
	    				myPlayer.show();
            	}
            }
        });
		
		getEventManager().registerEventHandler("Input.User.NewGame", new IEventHandler()
		{
		    @Override
		    public void handleEvent (Object o)
		    {	
		    	if(currentState == GAME_OVER_STATE)
		    	{
		    		currentState = GAME_PLAY_STATE;
		    		initLevel(0);
		    	}
		    }
		});
		
		getEventManager().registerEventHandler("Input.User.QuitGame", new IEventHandler()
		{
		    @Override
		    public void handleEvent (Object o)
		    {	
		    	finish();
		    }
		});
	}
	
	public void initLevel(int level)
	{
		bg = new ImageBackground(this.getImageLoader().getImage("SceneBGLvl" + level ));
		
		myPlayer.loadNewLevel(level);
		myEnemyManager.loadNextLevel(level);
		mySceneManager.loadNewLevel(level);
		
		myBulletHoleGroup.clear();
		
		getLevelManager().loadLevel(level);
	}
	
	public void shoot()
	{
		System.out.println(this.getMouseX() + " " + this.getMouseY());
		
    	if(myPlayer.hasAmmo() && myPlayer.isShowing())
		{
			myPlayer.fire();
			
			if(!myEnemyManager.checkCollision(this.getMouseX(), this.getMouseY()))
				myBulletHoleGroup.add(new BulletHole(this, this.getMouseX(), this.getMouseY()));
			else
				hitEnemy();
		}
	}

	/**
	 * User shot at an enemy 
	 */
	private void hitEnemy() 
	{
		// TODO: look at type of enemy and add to score based on that
		myPlayer.addScore(50);
		
		if(myEnemyManager.checkLevelCompleted())
		{
			playSound("src/games/TimeCrisis/resources/sounds/success.wav");
			
			if(getLevelManager().getCurrentLevel().getId() + 1 == maxLevel)
				currentState = GAME_OVER_STATE;
			else
			{
				currentState = GAME_LEVEL_STATE;
				initLevel(getLevelManager().getCurrentLevel().getId() + 1);
				this.addTimer("levelChangeTimer", 2000, "levelCompleteEvent");
			}
		}
	}
	
   @Override
    public void update (long elapsedTime)
    {
        getEventManager().update(elapsedTime);

        if(currentState == GAME_PLAY_STATE)
        {
			myEnemyManager.update(elapsedTime);
			
			myBulletHoleGroup.update(elapsedTime);
			
			myPlayer.update(elapsedTime);
        }
    }
	
	public void render (Graphics2D g)
	{
		bg.render(g);
	
		myEnemyManager.render(g);
		
		mySceneManager.render(g);
		
		myBulletHoleGroup.render(g);
		
		myPlayer.render(g);
		
		if(currentState == GAME_LEVEL_STATE)
			levelCompleteImg.render(g);
		else if(currentState == GAME_OVER_STATE)
			mainMenuImg.render(g);
	}

	public static void main(String[] args) 
	{
		launchGame(new TimeCrisis(), new Dimension(1024, 768), true);	
	}
	@Override
	public void updatePlayField(long elapsedTime) {}
}
