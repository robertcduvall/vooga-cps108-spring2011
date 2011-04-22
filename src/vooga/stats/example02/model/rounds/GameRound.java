package vooga.stats.example02.model.rounds;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import vooga.stats.example02.model.FishSharkCollision;

import vooga.stats.example02.spriteGroups.Nemos;
import vooga.stats.example02.spriteGroups.Sharks;
import vooga.stats.example02.util.commands.ImageReader;
import vooga.stats.example02.util.resources.ResourceManager;

import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * The main game is run in this playfield
 * @author Yin
 *
 */
public class GameRound extends AbstractRound
{
	private ResourceManager myResourceManager;
	
	private int myNemoNum;
	private int mySharkNum;
	private int mySpeed;
	private long myStartTime;
	private long myLastTime;
	private static boolean myWin;
	private static boolean myLoss;
	private boolean myInGame;
	
	private ImageBackground myBackground;
	private BufferedImage myNemoImage;
	private BufferedImage mySharkImage;
	
	private Nemos myNemos;
	private Sharks mySharks;
	private CollisionManager myCollisions;
	private SystemTimer myTimer;
	
	private static final String BACKGROUND_LOCATION = "background_image";
	private static final String NEMO_LOCATION = "nemo_image";
	private static final String SHARK_LOCATION = "shark_image";
	private final int FRAMES_PER_SECOND = 50;
	
	public GameRound(int NemoNum, int SharkNum, int speed, long lastTime, String resFile)
	{
		myResourceManager = ResourceManager.getManager(resFile);
		
		myNemoNum = NemoNum;
		mySharkNum = SharkNum;
		mySpeed = speed;
		myLastTime = lastTime;
		
		myInGame = true;
		myWin = false;
		myLoss = false;
		
		myBackground = new ImageBackground(ImageReader.readImage(myResourceManager.getString(BACKGROUND_LOCATION)));
		myNemoImage = ImageReader.readImage(myResourceManager.getString(NEMO_LOCATION));
		mySharkImage = ImageReader.readImage(myResourceManager.getString(SHARK_LOCATION));
		
		myNemos = new Nemos("Nemo", myNemoNum, myNemoImage);
		mySharks = new Sharks("Sharks", mySharkNum, mySharkImage, mySpeed);
		myCollisions = new FishSharkCollision(myNemos, mySharks);
		
		myTimer = new SystemTimer();
		myTimer.setFPS(FRAMES_PER_SECOND);
		myTimer.startTimer();
		myStartTime = myTimer.getTime();
		
		this.setBackground(myBackground);
		this.addGroup(myNemos);
		this.addGroup(mySharks);
		this.addCollisionGroup(myNemos, mySharks, myCollisions);
	}
	
	/**
	 * A method for updating
	 * Control the operation for nemo group
	 * If player wins current game round, the game status is modified
	 */
	@Override
	public void execute(long elapsedTime, 
			boolean up, boolean down, 
			boolean left, boolean right, 
			boolean click)
	{
		myNemos.execute(elapsedTime, up, down, left, right);
		myCollisions.checkCollision();
		
		if (myTimer.getTime()-myStartTime>=myLastTime)
		{
			myInGame = false;
			myWin = true;
		}
		
		super.update(elapsedTime);
	}
	
	/**
	 * Set loss status
	 */
	public static void setLoss()
	{
		myLoss = true;
		myWin = false;
	}
	
	/**
	 * @return current win status
	 */
	public boolean isWin()
	{
		return myWin;
	}
	
	/**
	 * @return current loss status
	 */
	public boolean isLoss()
	{
		return myLoss;
	}
	
	/**
	 * 
	 * @return whether time is up
	 */
	public boolean isTimesUp()
	{
		return !myInGame;
	}
	
}
