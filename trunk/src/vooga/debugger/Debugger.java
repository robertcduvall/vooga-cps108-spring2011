package vooga.debugger;

import java.lang.reflect.Field;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import vooga.debugger.model.DebuggerModel;
import vooga.debugger.model.GameField;
import vooga.debugger.view.DebuggerToolbar;
import vooga.debugger.view.DebuggerView;
import vooga.debugger.view.GameTreeNode;
import vooga.debugger.view.ScopeGameField;
import vooga.debugger.view.grapher.GraphGameField;


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
	private Game myGame;

	private DebuggerView myView;
	public DebuggerModel myModel;
	
	public boolean showAll;

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
	public void update(long deltaTime)
	{
		myModel.update(deltaTime);
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

	/**
	 * Start the vooga game
	 */
	public void playGame()
	{
		myView.myToolbar.playButton.setEnabled(false);
		myView.myToolbar.stopButton.setEnabled(true);
	
		// myGame.start();
	}

	/**
	 * Stop the vooga game
	 */
	public void stopGame()
	{
		myView.myToolbar.playButton.setEnabled(true);
		myView.myToolbar.stopButton.setEnabled(false);

		//myGame.stop();
	}

	/**
	 * Restart the vooga game
	 */
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
	 * Sets Vooga Grapher frame visibility to true
	 */
	public void showGrapher()
	{
		myView.myGrapher.setVisible(true);
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
	 * Add a field to the grapher
	 * 
	 * @param fieldNode
	 */
	public void recordField(GameTreeNode fieldNode)
	{
		Field [] pathToGameField = castObjectPath(fieldNode.getUserObjectPath());
		
		Field gameField = pathToGameField[pathToGameField.length - 1];
		if(gameField.getType().isPrimitive())
			this.addField(new GraphGameField(pathToGameField, this, myView.myGrapher));
		else
			println("Could not record Field: " + gameField.getName() + 
					". Vooga Grapher cannot record fields that are not primitives.", DebugLevel.INFO); 
	}
	
	/**
	 * 
	 * @param fieldNode
	 * @param viewOption
	 */
	public void setViewPreference(GameTreeNode fieldNode, String viewOption)
	{
		String className = fieldNode.getField().getType().getName();
		if(myModel.myParser.fieldMap.containsKey(className))
		{
			Element eField = (Element)myModel.myParser.fieldMap.get(className);
			eField.setAttribute("show", viewOption);
			myModel.myParser.fieldMap.put(className,(Node)eField);
		}
		myModel.getFields(fieldNode.getField().getType(), fieldNode, showAll, true);
		DefaultTreeModel treeModel = (DefaultTreeModel)myView.myGameTreePanel.getTree().getModel();
		treeModel.reload(fieldNode);
	}
	
	/**
	 * 
	 * @param fieldNode
	 */
	public void scopeField(GameTreeNode fieldNode)
	{
		Field [] pathToGameField = castObjectPath(fieldNode.getUserObjectPath());
		
		this.addField(new ScopeGameField(pathToGameField, this));
	}
	
	/**
	 * Add Game Field to system. Takes particular field and updates GUI with values of corresponding object in system
	 * @param fieldNode - tree node with data of member field
	 */
	public void addField(GameField gf)
	{
		// Try adding new GameField
		if (!myModel.doesGameFieldExist(gf))
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
	
	/**
	 * @param fieldNode
	 */
	private Field[] castObjectPath(Object [] objPath) 
	{
		Field[] fieldPath = new Field[objPath.length];
		for (int i = 0; i < objPath.length; i++)
		{
			fieldPath[i] = (Field) objPath[i];
		}
		
		return fieldPath;
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
