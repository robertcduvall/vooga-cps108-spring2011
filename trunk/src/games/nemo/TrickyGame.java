package games.nemo;
import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import games.nemo.model.FishSharkCollision;
import games.nemo.model.rounds.AbstractRound;
import games.nemo.model.rounds.EndRound;
import games.nemo.model.rounds.GameRound;
import games.nemo.model.rounds.NextRound;
import games.nemo.model.rounds.StartRound;

import games.nemo.spriteGroups.Nemos;
import games.nemo.spriteGroups.Sharks;
import games.nemo.util.commands.ImageReader;
import games.nemo.util.resources.ResourceManager;
import games.nemo.view.components.ErrorCatcher;

import games.nemo.characters.Nemo;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.font.BitmapFont;

/**
 * A game manager who extends Game class
 * @author Yin
 *
 */
public class TrickyGame extends Game
{
	// { distribute = true; }
	private ResourceManager myResourceManager;
	
	private String GAME_RESOURCE = "game";
	private final int FRAMES_PER_SECOND = 50;
	private final int SHARK_NUM = 5; // shark num should increase dynamically
	private final int NEMO_NUM = 1;
	private final int SPEED = 3;
	private final long LAST_TIME = 5000L;
	
	private AbstractRound myRound;
	private long myLastTime;
	private int mySpeed; // speed should increase dynamically
	private int mySharkNum;
	private GameFont myFont;
	
	private boolean myRunGame;
	private boolean myWin;
	private boolean myLoss;
	private boolean myStart;
	private boolean myNext;
	private boolean myEnd;
	
	// Keywords for locating resources
	private final String BACKGROUND_LOCATION = "background_image";
	private final String NEMO_LOCATION = "nemo_image";
	private final String SHARK_LOCATION = "shark_image";
	private final String FONT = "game_font";
	
	private final String ROUND_RESOURCE = "round";
	private final String START_BACKGROUND_LOCATION = "open_background_image";
	private final String NEXT_BACKGROUND_LOCATION = "next_round_image";
	private final String END_BACKGROUND_LOCATION = "end_round_image";
	
	/**
	 * Game initialize
	 */
	@Override
	public void initResources() 
	{
		newGame();
	}
	
	/**
	 * update method
	 */
	@Override
	public void update(long elapsedTime) 
	{	
		/**
		 * Use truth table to determine which round it is
		 */
		// Start a new game
		if (myStart)
		{
			if (keyDown(KeyEvent.VK_ENTER))
				myRound = new GameRound(NEMO_NUM, mySharkNum, mySpeed, myLastTime, ROUND_RESOURCE);
			myStart = false;
			myRunGame = true;
		}
		
		// Run a game
		if (myRunGame)
		{
			// Win one round
			if (myRound.isWin())
			{
				// Enter next round
				if (mySharkNum < 20)
				{
					mySharkNum += 4;
					mySpeed += 3;
					myLastTime += LAST_TIME*3/5;
					myRound = new NextRound(ROUND_RESOURCE, NEXT_BACKGROUND_LOCATION, myFont);
					myRunGame = false;
					myNext = true;
				}
				
				// Win the whole game!
				else
				{
					myRound = new EndRound(ROUND_RESOURCE, END_BACKGROUND_LOCATION, 
							"Congradulations! You win!\n" +
							"Press Enter to start a new game", myFont);
					myWin = true; myEnd = true;
					myRunGame = false;
				}
			}
			
			// Lose a round = lose a game!
			if (myRound.isLoss())
			{
				myRound = new EndRound(ROUND_RESOURCE, END_BACKGROUND_LOCATION, 
						"Sorry...Press Enter to start a new game", myFont);
				myLoss = true; myEnd = true;
				myRunGame = false;
			}
		}
		
		// Enter next round
		if (myNext)
		{
			if (keyDown(KeyEvent.VK_ENTER))
			{
				myRound = new GameRound(NEMO_NUM, mySharkNum, mySpeed, myLastTime, ROUND_RESOURCE);
				myNext = false;
				myRunGame = true;
			}
		}
		
		// After winning the whole game, start a new game by clicking the mouse
		if (myEnd)
		{
			if (keyDown(KeyEvent.VK_ENTER))
			{
				mySpeed = SPEED;
				mySharkNum = SHARK_NUM;
				myLastTime = LAST_TIME;
				
				myRound = new GameRound(NEMO_NUM, mySharkNum, mySpeed, myLastTime, ROUND_RESOURCE);
				
				myEnd = false; myWin = false; myLoss = false; myNext = false;
				myRunGame = true;
			}
		}
		
		// Update current round
		myRound.execute(elapsedTime, 
				keyDown(KeyEvent.VK_UP), 
				keyDown(KeyEvent.VK_DOWN), 
				keyDown(KeyEvent.VK_LEFT), 
				keyDown(KeyEvent.VK_RIGHT),
				keyDown(KeyEvent.VK_ENTER));
				//click());
		
	}
	
	/**
	 * Render method
	 */
	@Override
	public void render(Graphics2D g) 
	{
		myRound.render(g);
	}
	
	/**
	 * A protected method for initialization
	 */
	protected void newGame()
	{
		myResourceManager = ResourceManager.getManager(GAME_RESOURCE); //?
		
		// Initialize parameters
		mySpeed = SPEED;
		mySharkNum = SHARK_NUM;
		myLastTime = LAST_TIME;
		myFont = new BitmapFont(getImages(myResourceManager.getString(FONT), 8, 12),
                " !\"#$%&'()*+,-./0123456789:;<=>?" +
                "@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_" +
                "`abcdefghijklmnopqrstuvwxyz{|}~~"); 			
		
		myStart = true; myRunGame = false; myNext = false;
		myWin = false; myLoss = false; myEnd = false;
		
		myRound = new StartRound(ROUND_RESOURCE, START_BACKGROUND_LOCATION, myFont);
	}
}
