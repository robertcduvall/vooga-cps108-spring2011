
package vooga.debugger.view;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Class in charge of GUI modifications of GameTree. 
 * 
 * @author Troy
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
