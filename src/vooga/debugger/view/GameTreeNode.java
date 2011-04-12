
package vooga.debugger.view;

import java.lang.reflect.Field;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Node class used to build Field tree of game
 * Extends DefaultMutableTreeNode which is node class for JTree structure
 * 
 * @author Troy Ferrell
 */
public class GameTreeNode extends DefaultMutableTreeNode 
{
	public GameTreeNode(Field f)
	{
		super(f);
	}
	
	public String toString()
	{
		Field myField = (Field)this.getUserObject();
		return myField.getName() + " - (" + myField.getType().getSimpleName() + ")";
	}
	
	 public javax.swing.Icon getIcon() 
	 {
		 /*
		 Field f = (Field)this.getUserObject();
		 
		 ImageIcon imgIcon = new ImageIcon("src/resources/" + f.getType().getSimpleName() + "_CellImg.png");
		 if(imgIcon.getImage() == null) // if class isn't supported with image
			 imgIcon = new ImageIcon("src/resources/Default_CellImg.png");
				 
		 return imgIcon;
		 */
		 
		// for testing 
		return new ImageIcon("src/resources/GameTreeNode.gif");
	 }
}


