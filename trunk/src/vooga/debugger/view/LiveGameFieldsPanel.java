
package vooga.debugger.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vooga.debugger.model.GameField;

/**
 * Panel element of Debugger View that contains GUI components for live Game Fields, fields that are being displayed and read from game 
 * 
 * @author Troy Ferrell
 */
public class LiveGameFieldsPanel extends JPanel 
{
	public LiveGameFieldsPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	/**
	 * Add Game Field to Panel
	 * @param gf - gamefield to add
	 */
	public void addField(GameField gf)
	{
		this.add(gf);
	}
	
	/**
	 * Remove Game Field from Panel
	 * @param gf - gamefield to remove
	 */
	public void removeField(GameField gf)
	{
		this.remove(gf);
		
		this.revalidate();
		this.repaint();
	}
}
