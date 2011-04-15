package vooga.debugger.model;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.Field;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vooga.debugger.Debugger;

import vooga.debugger.util.MethodAction;


/**
 * Class that holds data for field in game as well as gui components
 * 
 * @author Troy Ferrell
 * @author Austin Benesh
 */
public class GameField extends JPanel
{
	private Field [] reflectionPath;
	private String myFieldName;
	
	public JLabel myFieldNameLabel;
	public JLabel myFieldGetLabel;
	
	private boolean active = true;
	
	public GameField(Field [] path, Debugger debug)
	{
		reflectionPath = path;
	
		myFieldName = path[path.length -1].getName();
		
		myFieldNameLabel = new JLabel(myFieldName + ": ");
		myFieldGetLabel = new JLabel("NA");
		
		this.add(myFieldNameLabel, BorderLayout.WEST);
		this.add(myFieldGetLabel, BorderLayout.EAST);
		
		JButton removeButton = new JButton();
		ImageIcon img = new ImageIcon("src/resources/CloseButton.png");
		removeButton.setIcon(img);
		removeButton.setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
		removeButton.addActionListener(new MethodAction(debug, "removeField", this));
		this.add(removeButton, BorderLayout.SOUTH);
		
		this.setMaximumSize(new Dimension(2000, 45));
	}
	
	/**
	 * Get reflection path to this field through system with Game class as root
	 * @return in order field array of path
	 */
	public Field [] getPath()
	{
		return reflectionPath;
	}
	
	/**
	 * Get Field object encapsulated in this GameField
	 * @return the field
	 */
	public Field getField()
	{
		return reflectionPath[reflectionPath.length - 1];
	}
	
	/**
	 * Is GameField being displayed live on debugger
	 * @return
	 */
	public boolean isActive()
	{
		return active;
	}
	
	/**
	 * Deactivate game field from being live
	 */
	public void deactivate()
	{	
		myFieldGetLabel.setText("NOT FOUND");
		
		active = false;
	}
	
	public void update(Object fieldInstance)
	{	
		if(active)
			myFieldGetLabel.setText(String.valueOf(fieldInstance));
	}
	
	/**
	 * Compare two GameField and check for equality
	 * @param gf - GameField to compare to
	 * @return state of equality between two GameFields
	 */
	public boolean areFieldsEqual(GameField gf)
	{
	
		Field field1 = this.getField();
		Field field2 = gf.getField();
		
		return  field1.equals(field2);
	}
	
	
}
