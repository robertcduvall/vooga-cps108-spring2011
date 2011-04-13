package vooga.debugger;

import java.lang.reflect.Field;

import vooga.debugger.model.DebuggerModel;
import vooga.debugger.model.GameField;
import vooga.debugger.view.DebuggerView;
import vooga.debugger.view.GameTreeNode;

import com.golden.gamedev.Game;


/**
 * Core Debugger class. The Controller of the Debugger System
 * 
 * @author Troy Ferrell & Austin Benesh
 */
public class Debugger 
{
	private Game myGame;
	
	private DebuggerView myView;
	private DebuggerModel myModel;
	
	//private DebuggerGrapher myGrapher;
	
	public Debugger(Game game)
	{
		myGame = game;
		
		myModel = new DebuggerModel(game);
		myView = new DebuggerView(this, myModel);
	}
	
	public void update()
	{
		myModel.update();
	}
	
	public void playGame()
	{
		myView.playButton.setEnabled(false);
		myView.stopButton.setEnabled(true);
		
		//myGame.start();
	}
	
	public void stopGame()
	{
		myView.playButton.setEnabled(true);
		myView.stopButton.setEnabled(false);
		
		//myGame.stop();
	}
	
	public void restartGame()
	{
		// restart game
	}
	
	public GameTreeNode getGameTree()
	{
		return myModel.getGameTree();
	}
	
	/**
	 * Add a new GameField to the system
	 * @param fieldNode - node with field data
	 */
	public void addField(GameTreeNode fieldNode)
	{
		// Convert Object[] to Field[]
		Object [] objPath = fieldNode.getUserObjectPath();
		Field [] fieldPath = new Field[objPath.length];
		for(int i = 0; i < objPath.length; i++)
		{
			fieldPath[i] = (Field)objPath[i];
		}
		
		// Try adding new GameField
		GameField gf = new GameField(fieldPath, this); //DebuggerParser.createGameField(gameObj);
		if(!myModel.isFieldAlreadyLive(gf))
		{
			myView.addFieldToView(gf);
			myModel.addField(gf);
		}
	}
	
	/**
	 * Remove a field from the system
	 * @param field - field to remove
	 */
	public void removeField(GameField field)
	{
		myView.removeFieldFromView(field);
		myModel.removeField(field);

	}

}
