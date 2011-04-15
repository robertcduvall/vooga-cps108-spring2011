package vooga.debugger;

import java.lang.reflect.Field;

import javax.swing.tree.DefaultMutableTreeNode;

import vooga.debugger.model.DebuggerModel;
import vooga.debugger.model.GameField;
import vooga.debugger.view.DebuggerToolbar;
import vooga.debugger.view.DebuggerView;
import vooga.debugger.view.GameTreeNode;

import com.golden.gamedev.Game;

/**
 * Core class of Debugger system functioning as controller for MVC architecture
 * 
 * @author Troy Ferrell 
 * @author Austin Benesh
 * @author Ethan Goh
 */
public class Debugger
{
	// TODO: need to load in some values from resources for init sizes of components
	
	private Game myGame;

	private DebuggerView myView;
	private DebuggerModel myModel;
	
	private boolean showAll;

	private Debugger.DebugLevel debugLevel;

	public enum DebugLevel
	{
		ALL, TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF
	}

	public Debugger(Game game)
	{
		showAll = false;
		myGame = game;
		myModel = new DebuggerModel(game);
		myView = new DebuggerView(this, myModel);
		debugLevel = DebugLevel.ALL;
	}

	/**
	 * Update the debugger system. Includes updating the game fields being displayed in real-time.
	 */
	public void update()
	{
		myModel.update();
	}

	// Test method
	private static String getMethodCaller()
	{
		StackTraceElement[] stackTrace = Thread.currentThread()
				.getStackTrace();
		return stackTrace[1] + ":";
	}
	
	/**
	 *  Print to the debugger console
	 * @param s - string to output
	 * @param d - debug level of printing
	 */
	public void println(String s, DebugLevel d)
	{
		if (debugLevel.compareTo(d) < 0)
			myView.myHistoryPanel.println(getMethodCaller() + " " + s);
	}

	public void playGame()
	{
		myView.myToolbar.playButton.setEnabled(false);
		myView.myToolbar.stopButton.setEnabled(true);

		// myGame.start();
	}

	public void stopGame()
	{
		myView.myToolbar.playButton.setEnabled(true);
		myView.myToolbar.stopButton.setEnabled(false);

		// myGame.stop();
	}

	public void restartGame()
	{
		// restart game
	}

	/**
	 * Switch property for showing all fields in the Game Tree
	 */
	public void showAllFields()
	{
		showAll = !showAll;
		
		myView.myGameTreePanel.updateTree();
	}
	
	/**
	 * Get Game Tree of game system. Lays out fields of game classes in hierarchical structure
	 * 
	 * @return root node of Game Tree
	 */
	public GameTreeNode getGameTree()
	{
		return myModel.getGameTree(showAll);
	}

	/**
	 * Add Game Field to system. Takes particular field and updates GUI with values of corresponding object in system
	 * @param fieldNode - tree node with data of member field
	 */
	public void addField(GameTreeNode fieldNode)
	{
		// Convert Object[] to Field[]
		Object[] objPath = fieldNode.getUserObjectPath();
		Field[] fieldPath = new Field[objPath.length];
		for (int i = 0; i < objPath.length; i++)
		{
			fieldPath[i] = (Field) objPath[i];
		}

		// Try adding new GameField
		GameField gf = new GameField(fieldPath, this); // DebuggerParser.createGameField(gameObj);
		if (!myModel.isFieldAlreadyLive(gf))
		{
			myView.addFieldToView(gf);
			myModel.addField(gf);
		}
	}

	/**
	 * Remove a Game Field from the system
	 * @param field - GameField to be removed
	 */
	public void removeField(GameField field)
	{
		myView.removeFieldFromView(field);
		myModel.removeField(field);

	}

	public DebugLevel getDebugLevel()
	{
		return debugLevel;
	}

	public void setDebugLevel(DebugLevel debugLevel)
	{
		this.debugLevel = debugLevel;
	}
}
