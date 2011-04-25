
package vooga.debugger.view;

import java.lang.reflect.Field;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Class used for nodes to store Debugger specific data in Game Tree
 * 
 * @author Troy Ferrell
 */
public class GameTreeNode extends DefaultMutableTreeNode 
{
	public GameTreeNode(Field f)
	{
		super(f);
	}
	
	public Field getField()
	{
		return (Field)this.getUserObject();
	}
	
	public String toString()
	{
		Field myField = (Field)this.getUserObject();
		return myField.getName() + " - (" + myField.getType().getSimpleName() + ")";
	}
	
	/**
	 * Gets icon for this particular node to be displayed in the Game Tree
	 * @return image icon for node
	 */
	 public Icon getIcon() 
	 {
		 /* Code for extension later
		 Field f = (Field)this.getUserObject();
		 
		 ImageIcon imgIcon = new ImageIcon("src/resources/" + f.getType().getSimpleName() + "_CellImg.png");
		 if(imgIcon.getImage() == null) // if class isn't supported with image
			 imgIcon = new ImageIcon("src/resources/Field_White.png");
				 
		 return imgIcon; 
		 */
		 
		return new ImageIcon("src/vooga/debugger/resources/Field_White.png");
	 }
}


