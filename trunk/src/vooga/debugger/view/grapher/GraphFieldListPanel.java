
package vooga.debugger.view.grapher;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import vooga.debugger.util.MethodAction;



/**
 * Component class in charge of displaying list of GraphGameFields being recorded by grapher
 * 
 * @author Troy Ferrell
 */
public class GraphFieldListPanel extends JPanel 
{
	private JPanel myFieldListPanel;
	private JPanel myFunctionPanel;
	
	private DebuggerGrapher myDebugGrapher;
	
	private Map<GraphGameField, JSeparator> myJSeparatorMap = new HashMap<GraphGameField, JSeparator>();
	
	public GraphFieldListPanel(DebuggerGrapher debugGrapher)
	{
		myDebugGrapher = debugGrapher;
		
		initFunctionPanel();
		
		myFieldListPanel = new JPanel();
		myFieldListPanel.setLayout(new BoxLayout(myFieldListPanel, BoxLayout.PAGE_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(myFieldListPanel);
		scrollPane.setPreferredSize(new Dimension(300, 400));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(scrollPane);
		this.add(myFunctionPanel);
	}
	
	/**
	 * Add field to list
	 * @param gf
	 */
	public void addField(GraphGameField gf)
	{
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		myJSeparatorMap.put(gf, separator);
		
		myFieldListPanel.add(gf.getPanel());
		myFieldListPanel.add(separator);
		
		this.revalidate();
	}
	
	/**
	 * Remove field from list
	 * @param gf
	 */
	public void removeField(GraphGameField gf)
	{
		myFieldListPanel.remove(gf.getPanel());
		myFieldListPanel.remove(myJSeparatorMap.get(gf));
		
		myJSeparatorMap.remove(gf);
		
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Set all draw parameter for all GraphGameFields in this component list
	 * @param draw - drawing state
	 */
	public void setAllDrawable(Boolean draw)
	{
		for(GraphGameField gf : myDebugGrapher.myGraphFields)
			gf.setDrawable(draw);
		
		this.myDebugGrapher.repaint();
	}
	
	/**
	 * Creates function panel for list
	 */
	private void initFunctionPanel()
	{
		myFunctionPanel = new JPanel();
		
		JButton drawAllButton = new JButton("Draw All");
		drawAllButton.addActionListener(new MethodAction(this, "setAllDrawable", new Boolean(true)));
		myFunctionPanel.add(drawAllButton);
		
		JButton drawNoneButton = new JButton("Draw None");
		drawNoneButton.addActionListener(new MethodAction(this, "setAllDrawable", false));
		myFunctionPanel.add(drawNoneButton);
	}
}
