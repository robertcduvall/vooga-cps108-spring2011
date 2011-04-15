
package vooga.debugger.view.grapher;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * @author Troy Ferrell
 */
public class GraphField extends JPanel implements MouseListener 
{
	private ArrayList<String> dataList = new ArrayList<String>();
	
	private boolean selected = false;
	
	public GraphField()
	{
		// jlabel
		// remove button
		// 
	}

	public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected(boolean sel)
	{
		selected = sel;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		selected = !selected;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	
	
}
