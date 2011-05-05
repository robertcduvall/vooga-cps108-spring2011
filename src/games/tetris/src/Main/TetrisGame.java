package games.tetris.src.Main;

//JFC

import games.tetris.src.Context.ITetrisContext;
import games.tetris.src.DrawnImages.ImageFactory;
import games.tetris.src.Helper.ResourceManager;
import games.tetris.src.Helper.SpriteLayerComparator;
import games.tetris.src.KeyActions.IKeyAction;
import games.tetris.src.TetrisComponents.ActiveMino;
import games.tetris.src.TetrisComponents.HoldPieceManager;
import games.tetris.src.TetrisComponents.LookAheadManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;

public class TetrisGame extends Game implements ITetrisContext
{
	private final ResourceManager resources = new ResourceManager(
			"games.tetris.TetrisResource");
	private final ResourceManager keypressResource = new ResourceManager(
			"games.tetris.KeypressResource");

	private Map<Integer, IKeyAction> keyMappings;

	private PlayField playfield;

	private SpriteGroup matrixGroup;
	private SpriteGroup holdGroup;
	private SpriteGroup lookAheadGroup;
	private Sprite matrixBackground;
	private Sprite matrixBorder;

	private Timer blockGravityTimer;
	private Timer blockLockTimer;
	// private SystemTimer gameTimer;
	private Timer dasTimer;
	private Timer arrTimer;
	private Timer softDropTimer;

	private int dasDelay;
	private int arrDelay;
	private int softDropDelay;

	private int blockGravityDelay;
	private int blockLockDelay;
	private int blockLockDelayTotal;

	private GameFont font;

	private ActiveMino activeMino;
	private HoldPieceManager heldMinoManager;
	private LookAheadManager lookAheadMinos;
	private TetrisMatrix tetrisMatrix;

	private boolean gameOver;

	/****************************************************************************/
	/**************************** GAME SKELETON *********************************/
	/****************************************************************************/

	@Override
	public void initResources()
	{
		this.initGameEssentials();
		this.initGameTimers();

		this.initKeyMappings();

		this.initSpriteComponents();

		tetrisMatrix = new TetrisMatrix();
		activeMino = new ActiveMino(matrixGroup, tetrisMatrix);
		this.generateNextBlock();
		gameOver = false;
	}

	public void reset()
	{

		initResources();
	}

	private void initKeyMappings()
	{
		keyMappings = new HashMap<Integer, IKeyAction>();
		for (String s : keypressResource.keySet())
		{
			int key = 0;
			try
			{
				key = Integer.parseInt(s);
			}
			catch (NumberFormatException e)
			{
				continue;
			}

			String className = keypressResource.getString(s);
			String packagePath = keypressResource.getString("PackageName");
			Class<?> c = null;
			try
			{
				c = Class.forName(packagePath + "." + className);
				Constructor<?> ctr = c.getConstructor();
				IKeyAction am = (IKeyAction) ctr.newInstance();
				keyMappings.put(key, am);
			}
			catch (Exception e)
			{
			}
		}
	}

	@Override
	public void generateNextBlock()
	{
		activeMino.setMino(lookAheadMinos.getNextBlock());
		if (!tetrisMatrix.isLegalPosition(activeMino.getCurrentMino()))
		{
			gameOver = true;
		}
	}

	private void initSpriteComponents()
	{
		holdGroup = new SpriteGroup("HoldGroup");
		matrixGroup = new SpriteGroup("BlockMatrixGroup");
		lookAheadGroup = new SpriteGroup("lookAheadGroup");

		lookAheadMinos = new LookAheadManager(lookAheadGroup);
		heldMinoManager = new HoldPieceManager(holdGroup);

		playfield.setComparator(new SpriteLayerComparator());
		playfield.addGroup(matrixGroup);
		playfield.addGroup(lookAheadGroup);
		playfield.addGroup(holdGroup);
		playfield.add(matrixBackground);
		playfield.add(matrixBorder);

	}

	private void initGameTimers()
	{
		arrDelay = resources.getInteger("DefaultArrDelay");
		dasDelay = resources.getInteger("DefaultDasDelay");
		softDropDelay = resources.getInteger("DefaultSoftDropDelay");
		blockGravityDelay = resources.getInteger("DefaultBlockGravityDelay");
		blockLockDelayTotal = resources
				.getInteger("DefaultBlockLockDelayTotal");
		blockLockDelay = resources.getInteger("DefaultBlockLockDelay");
		dasTimer = new Timer(dasDelay);
		arrTimer = new Timer(arrDelay);
		softDropTimer = new Timer(softDropDelay);
		blockGravityTimer = new Timer(blockGravityDelay);
		blockLockTimer = new Timer(blockLockDelay);
	}

	private void initGameEssentials()
	{
		playfield = new PlayField();
		Background windowBackground = new ColorBackground(Color.black,
				resources.getInteger("BackgroundWidth"),
				resources.getInteger("BackgroundHeight"));

		BufferedImage matrixImage = getImage(resources
				.getString("BackgroundImage"));
		matrixBackground = new Sprite(matrixImage,
				resources.getInteger("MatrixSpawnCenterWidth") - 10, 100);

		BufferedImage matrixBorderImage = ImageFactory.drawMatrixBorder(260,
				440);
		matrixBorder = new Sprite(matrixBorderImage,
				resources.getInteger("MatrixSpawnCenterWidth") - 41, 72);
		matrixBorder.setLayer(2);

		playfield.setBackground(windowBackground);

		font = fontManager.getFont(
				getImages(resources.getString("FontImage"), 20, 3),
				resources.getString("FontMapping"));
	}

	@Override
	public void update(long elapsedTime)
	{
		this.checkKeyPresses(elapsedTime);

		if (!gameOver)
		{
			playfield.update(elapsedTime);
			
			if (blockGravityTimer.action(elapsedTime))
			{
				boolean b = activeMino.moveY(-1, tetrisMatrix);
				blockLockDelayTotal -= blockGravityTimer.getDelay();
				if (!b && blockLockDelayTotal <= 0)
				{
					//Hard Drop
					keyMappings.get(32).performKeyPressed(this, elapsedTime);
				}
			}
		}
	}

	private void checkKeyPresses(long elapsedTime)
	{
		for (Integer i : keyMappings.keySet())
		{
			if (keyPressed(i))
			{
				keyMappings.get(i).performKeyPressed(this, elapsedTime);
			}
			if (keyDown(i))
			{
				keyMappings.get(i).performKeyDown(this, elapsedTime);
			}
		}
	}

	@Override
	public void render(Graphics2D g)
	{
		playfield.render(g);
		// draw info text
		font.drawString(g, "DELAYED AUTOSHIFT: " + Integer.toString(dasDelay),
				10, 10);
		font.drawString(g, "AUTO REPEAT RATE: " + Integer.toString(arrDelay),
				10, 30);
		font.drawString(g, "NEXT",
				resources.getInteger("MatrixSpawnCenterWidth") + 240, 90);
		font.drawString(g, "HOLD", 20, 90);
		if (gameOver)
		{
			font.drawString(g, "NOOBFACE. GAME OVER. PRESS R TO RESTART: ",
					100, 250);
		}

	}

	@Override
	protected void notifyExit()
	{

	}

	/****************************************************************************/
	/***************************** START-POINT **********************************/
	/****************************************************************************/

	public static void main(String[] args)
	{
		GameLoader game = new GameLoader();
		game.setup(new TetrisGame(), new Dimension(800, 600), false);
		game.start();
	}

	@Override
	public ActiveMino getActiveMino()
	{
		return activeMino;
	}

	@Override
	public TetrisMatrix getTetrisMatrix()
	{
		return tetrisMatrix;
	}

	@Override
	public Timer getDasTimer()
	{
		return dasTimer;
	}

	@Override
	public Timer getArrTimer()
	{
		return arrTimer;
	}

	@Override
	public Timer getBlockLockTimer()
	{
		return blockLockTimer;
	}
	
	@Override
	public void subtractBlockLockDelayTotal(int time)
	{
		blockLockDelayTotal -= time;
	}
	
	@Override
	public void resetBlockLockDelayTotal()
	{
		blockLockDelayTotal = resources.getInteger("DefaultBlockLockDelayTotal");
	}

	@Override
	public Timer getSoftDropTimer()
	{
		return softDropTimer;
	}

	@Override
	public void adjustDasBy(int i)
	{
		dasDelay += i;
		if (dasDelay < 0)
			dasDelay = 0;
		dasTimer.setDelay(dasDelay);
	}

	@Override
	public boolean holdMino()
	{
		return heldMinoManager.swapHeldMino(activeMino, this);
	}

	@Override
	public void setHoldUsed(boolean b)
	{
		heldMinoManager.setHoldUsed(b);
	}
}