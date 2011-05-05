package games.nemo.model.rounds;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Map;

import vooga.stats.AbstractStat;
import vooga.stats.NumDisplayCreator;
import vooga.stats.NumStat;

import games.nemo.model.FishSharkCollision;

import games.nemo.spriteGroups.Nemos;
import games.nemo.spriteGroups.Sharks;
import games.nemo.util.commands.ImageReader;
import games.nemo.util.resources.ResourceManager;

import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
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
	private NumStat<Double> myPlayTime;
	private GameFont myFont;
	
	private static final String BACKGROUND_LOCATION = "background_image";
	private static final String NEMO_LOCATION = "nemo_image";
	private static final String SHARK_LOCATION = "shark_image";
	private final int FRAMES_PER_SECOND = 50;
	
	public GameRound(int NemoNum, int SharkNum, int speed, long lastTime, GameFont font, String resFile)
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
		//System.out.println(DisplayCreator.getOperatorMap().get(DisplayCreator.getOperatorMap()));
		
		myFont = font;
		myPlayTime = new NumStat<Double>(0.0, 1.0, "REPLACE", NumDisplayCreator.getOperatorMap());
		
		
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
		
		myPlayTime.setStep(((double)(myTimer.getTime()-myStartTime))/1000.0);
		myPlayTime.update();
		super.update(elapsedTime);
	}
	
	@Override
	public void render(Graphics2D g)
	{
		super.render(g);
		
		myFont.drawString(g, "Playing Time: "+myPlayTime.toString(), 10, 10);
	}
	
	public void setFont(GameFont font)
	{
		myFont = font;
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
	
	public NumStat getPlayTime() 
	{
		return myPlayTime;
	}
}
