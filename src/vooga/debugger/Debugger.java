package vooga.debugger;

import java.lang.reflect.Field;

import javax.swing.tree.DefaultMutableTreeNode;

import vooga.debugger.model.DebuggerModel;
import vooga.debugger.model.GameField;
import vooga.debugger.view.DebuggerView;
import vooga.debugger.view.GameTreeNode;


import com.golden.gamedev.Game;

/**
 * 
 * 
 * @author Troy Ferrell & Austin Benesh & Ethan Goh
 */
public class Debugger
{
	private Game myGame;

	private DebuggerView myView;
	private DebuggerModel myModel;

	private Debugger.DebugLevel debugLevel;

	public enum DebugLevel
	{
		ALL, TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF
	}

	// private DebuggerGrapher myGrapher;

	public Debugger(Game game)
	{
		myGame = game;
		myModel = new DebuggerModel(game);
		myView = new DebuggerView(this, myModel);
		debugLevel = DebugLevel.ALL;
	}

	public void update()
	{
		myModel.update();
	}

	private static String getMethodCaller()
	{
		StackTraceElement[] stackTrace = Thread.currentThread()
				.getStackTrace();
		return stackTrace[1] + ":";
	}

	public void println(String s, DebugLevel d)
	{
		if (debugLevel.compareTo(d) < 0)
			myView.println(getMethodCaller() + " " + s);
	}

	public void playGame()
	{
		myView.playButton.setEnabled(false);
		myView.stopButton.setEnabled(true);

		// myGame.start();
	}

	public void stopGame()
	{
		myView.playButton.setEnabled(true);
		myView.stopButton.setEnabled(false);

		// myGame.stop();
	}

	public void restartGame()
	{
		// restart game
	}

	public GameTreeNode getGameTree()
	{
		return myModel.getGameTree();
	}

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
