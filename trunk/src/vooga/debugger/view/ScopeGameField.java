
package vooga.debugger.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.Field;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vooga.debugger.Debugger;
import vooga.debugger.model.GameField;
import vooga.debugger.util.MethodAction;

/**
 * Extends class GameField by adding GUI functionality for reading or "scoping" field in the game
 * 
 * @author Troy Ferrell
 */
public class ScopeGameField extends GameField 
{
	public JPanel myFieldPanel;
	
	public JLabel myFieldNameLabel;
	public JLabel myFieldGetLabel;
	
	public ScopeGameField(Field [] path, Debugger debug)
	{
		super(path);
		
		myFieldPanel = new JPanel();
		myFieldPanel.setLayout(new BoxLayout(myFieldPanel, BoxLayout.LINE_AXIS));
		myFieldPanel.setMaximumSize(new Dimension(2000, 45));
		
		myFieldPanel.add(createLabelPanel());
		myFieldPanel.add(createRemoveButtonPanel(debug));
	}

	/**
	 * Creates and returns panel with name of field and value of field labels
	 * 
	 * @return panels with components described
	 */
	private JPanel createLabelPanel() 
	{
		JPanel labelPanel = new JPanel();
		
		myFieldNameLabel = new JLabel("Field Name: " + myFieldName);
		myFieldNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		myFieldGetLabel = new JLabel("Value: NA");
		myFieldGetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		labelPanel.setLayout(new GridLayout(1, 2));
		labelPanel.setPreferredSize(new Dimension(300, 50));
		
		labelPanel.add(myFieldNameLabel);
		labelPanel.add(myFieldGetLabel);
		
		return labelPanel;
	}

	/**
	 * Creates panel that has the remove button
	 * 
	 * @param debug - reference to controller
	 * @return panel as described 
	 */
	private JPanel createRemoveButtonPanel(Debugger debug) 
	{
		JPanel panel = new JPanel();
		
		JButton removeButton = new JButton(new ImageIcon("src/vooga/debugger/resources/CloseButton.png"));
		removeButton.addActionListener(new MethodAction(this, "removeButtonPressed", debug));
		
		panel.add(removeButton);
		
		return panel;
	}
	
	/**
	 * Update this gui with this field's new values
	 */
	protected void updateField(long deltaTime, Object fieldInstance)
	{	
		if(this.active)
			myFieldGetLabel.setText("Value: " + String.valueOf(fieldInstance));
	}
	
	public JPanel getPanel()
	{
		return myFieldPanel;
	}
	
	/**
	 * Action Event Method - calls controller class to remove this GameField from the system
	 * @param debug - controller class
	 */
	private void removeButtonPressed(Debugger debug)
	{
		debug.removeField(this);
	}
}
