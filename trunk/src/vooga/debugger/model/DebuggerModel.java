package vooga.debugger.model;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.tree.DefaultMutableTreeNode;

import vooga.debugger.view.GameTreeNode;

import com.golden.gamedev.Game;

/**
 * Class that manages and updates Debugger system
 * 
 * @author Troy Ferrell
 */
public class DebuggerModel 
{
	private Collection<GameField> myFields;
	
	private Game myGame;
	private DebuggerParser myParser;
	
	private Field gameField;
	
	public DebuggerModel(Game game)
	{
		myGame = game;
		myParser = new XMLParser();
		myFields = new ArrayList<GameField>();
		
		Field [] fields = this.getClass().getDeclaredFields();
		for(Field f : fields)
		{
			if(f.getName().equals("myGame"))
				gameField = f;
		}
	}
	
	/**
	 * Reflect through the referenced game for fields to create a tree that maps the game
	 * 
	 * @return root of game tree
	 */
	public GameTreeNode getGameTree()
	{
		GameTreeNode gameRoot = new GameTreeNode(gameField);
		getFields(myGame.getClass(), gameRoot);
		return gameRoot;
	}
	
	/**
	 * Private helper method for getGameTree(). Recursively looks through a class and it's fields, 
	 * then treats each of those fields as classes and look for more fields
	 * @param rootClass
	 * @param root
	 */
	private void getFields(Class<?> rootClass,  GameTreeNode root)
	{
		try 
		{
			Field [] validFields = myParser.getValidFieldsFor(rootClass);
			for(int i = 0; i < validFields.length; i++)
			{
				Class<?> fieldClass = validFields[i].getType();
				GameTreeNode node = new GameTreeNode( validFields[i] );
				getFields(fieldClass, node);
				root.add(node);
			}
		  }
		  catch (Throwable e) 
		  {
		     System.err.println(e);
		  }
	}
	
	/**
	 * Return whether the passed GameField is already active in the system.
	 * Meaning is it already being watched by the user and is consequently in the Live GameFields panel
	 * @param gf 
	 * @return state of GameField in system
	 */
	public boolean isFieldAlreadyLive(GameField gf)
	{
		for(GameField gameField : myFields)
			if(gameField.areFieldsEqual(gf))
				return true;
		
		return false;
	}
	
	public void addField(GameField gf)
	{
		if(gf != null)
			myFields.add(gf);
	}
	
	public void removeField(GameField gf)
	{
		myFields.remove(gf);
	}
	
	/**
	 * Update all GameFields being monitored by system in real-time.
	 * Finds the active object being analyzed in the game by using the field tree path
	 * EX. Game->Player->Name (Fields) ==> myGame->myPlayer->myName (real-time objects)
	 */
	public void update()
	{	
		try
		{		
			for(GameField gf : myFields)
			{
				Field [] fieldPath = gf.getPath();
				Object fieldInstance = myGame;
				
				for(int i = 1; i < fieldPath.length; i++)
				{
					fieldPath[i].setAccessible(true);
					if(fieldInstance != null)
						fieldInstance = fieldPath[i].get(fieldInstance);
				}
				
				// TODO: need to add deactivation for gamefields
				
				gf.update(fieldInstance);
			}
			
		}catch(IllegalAccessException e)
		{
			// TODO: better handle exception
			e.printStackTrace();	
		}
	}
	
}
