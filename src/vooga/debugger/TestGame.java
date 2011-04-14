package vooga.debugger;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import vooga.debugger.Debugger.DebugLevel;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Timer;


/**
 * @author Troy Ferrell
 *
 */
public class TestGame extends Game 
{
	Debugger myDebugger;
	public int dummy = 10;
	//public int demo = 128;
	public String test = "20";
	public double val = 2.0;
	public Player myPlayer;
	public ArrayList<Integer> values;
	public boolean isPaused = false;
	
	private Timer testTimer; 
	/* (non-Javadoc)
	 * @see com.golden.gamedev.Game#initResources()
	 */
	@Override
	public void initResources() 
	{
		myPlayer = new Player();
		values = new ArrayList<Integer>();
		values.add(1);
		myDebugger = new Debugger(this);
		testTimer = new Timer(1000);
		
	}

	/* (non-Javadoc)
	 * @see com.golden.gamedev.Game#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D arg0) 
	{

	}

	/* (non-Javadoc)
	 * @see com.golden.gamedev.Game#update(long)
	 */
	@Override
	public void update(long arg0) 
	{
		if(isPaused)
			return;
		dummy += 1;
		if(dummy > 200)
		{
			dummy = 0;
			//demo++;
		}
		
		
		if(testTimer.action(arg0))
		{
			myDebugger.println("Test Debug", DebugLevel.INFO);
		}
		
		
		test = String.valueOf(dummy-10);
		
		myDebugger.update();
	}
	public void startGame()
	{
		isPaused = false;
	}
	public void pauseGame()
	{
		isPaused = true;
	}
	/**
	 * Initialize Game System
	 * 
	 * @param args
	 */
    public static void main(String[] args) 
    {
   
        GameLoader game = new GameLoader();
        //game.setup(new SSOmega(), new Dimension(1280,860), false);
        game.setup(new TestGame(), new Dimension(1024,768), false);
        game.start();
        
    }
    
}
