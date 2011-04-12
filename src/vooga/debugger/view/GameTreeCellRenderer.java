
package vooga.debugger.view;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Class that update look of JTree used for displaying hierarchical view of fields in the game
 * 
 * @author Troy Ferrell
 * @author usman - http://usmansaleem.blogspot.com/2006/08/setting-custom-icon-for-individual.html
 */

public class GameTreeCellRenderer extends DefaultTreeCellRenderer 
{
	 public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) 
	 {
		 if((value instanceof GameTreeNode) && (value != null)) 
		 {
			 setIcon(((GameTreeNode)value).getIcon());
		 }

		//we can not call super.getTreeCellRendererComponent method, since it overrides our setIcon call and cause rendering of labels to '...' when node expansion is done

		//so, we copy (and modify logic little bit) from super class method:

		 String stringValue = tree.convertValueToText(value, sel, expanded, leaf, row, hasFocus);

		 this.hasFocus = hasFocus;
		 setText(stringValue);
		 if(sel)
		   setForeground(getTextSelectionColor());
		 else
		   setForeground(getTextNonSelectionColor());

		 if (!tree.isEnabled()) 
		 {
		   setEnabled(false);
		 }
		 else 
		 {
		   setEnabled(true);
		 }
		 
		 setComponentOrientation(tree.getComponentOrientation());
		 selected = sel;
		 
		 return this;
	}
}
