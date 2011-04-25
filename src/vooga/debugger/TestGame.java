package vooga.debugger;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

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
	public boolean testBool = false;
	public double counter = 0;
	public double sinVal = 0;
	
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
		
		counter += 0.1;
		sinVal = Math.sin(counter);
		
		if(testTimer.action(arg0))
		{
			myDebugger.println("Test Debug", DebugLevel.INFO);
		}
		
		testBool = !testBool;
		
		test = String.valueOf(dummy-10);
		
		myDebugger.update(arg0);
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
