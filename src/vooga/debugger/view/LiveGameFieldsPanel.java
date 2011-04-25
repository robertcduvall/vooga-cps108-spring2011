
package vooga.debugger.view;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import vooga.debugger.model.GameField;
import vooga.debugger.view.grapher.GraphGameField;


/**
 * Panel element of Debugger View that contains GUI components for live Game Fields, fields that are being displayed and read from game 
 * 
 * @author Troy Ferrell
 */
public class LiveGameFieldsPanel extends JPanel 
{
	private JPanel myLiveFieldListPanel;
	
	private Map<ScopeGameField, JSeparator> myJSeparatorMap = new HashMap<ScopeGameField, JSeparator>();
	
	public LiveGameFieldsPanel()
	{	
		myLiveFieldListPanel = new JPanel();
		myLiveFieldListPanel.setLayout(new BoxLayout(myLiveFieldListPanel, BoxLayout.PAGE_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(myLiveFieldListPanel);
		scrollPane.setPreferredSize(new Dimension(300, 400));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(scrollPane);
	}
	
	/**
	 * Add Game Field to Panel
	 * @param gf - gamefield to add
	 */
	public void addField(ScopeGameField gf)
	{
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		myJSeparatorMap.put(gf, separator);
		
		myLiveFieldListPanel.add(gf.getPanel());
		myLiveFieldListPanel.add(separator);
		
		this.revalidate();
	}
	
	/**
	 * Remove Game Field from Panel
	 * @param gf - gamefield to remove
	 */
	public void removeField(ScopeGameField gf)
	{
		myLiveFieldListPanel.remove(gf.getPanel());
		myLiveFieldListPanel.remove(myJSeparatorMap.get(gf));
		
		myJSeparatorMap.remove(gf);
		
		this.revalidate();
		this.repaint();
	}
}
