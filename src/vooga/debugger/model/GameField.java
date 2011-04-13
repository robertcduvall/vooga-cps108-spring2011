package vooga.debugger.model;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vooga.debugger.Debugger;
import vooga.debugger.util.MethodAction;


/**
 * Class used to display game data by storing it's Field path
 * 
 * @author Troy Ferrell
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
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new MethodAction(debug, "removeField", this));
		this.add(removeButton, BorderLayout.SOUTH);
		
		this.setMaximumSize(new Dimension(2000, 45));
	}
	
	public Field [] getPath()
	{
		return reflectionPath;
	}
	
	public Field getField()
	{
		return reflectionPath[reflectionPath.length - 1];
	}
	
	public boolean isActive()
	{
		return active;
	}
	
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
	
	public boolean areFieldsEqual(GameField gf)
	{	
		Field field1 = this.getField();
		Field field2 = gf.getField();
		
		return  field1.equals(field2);
	}
	
	
}
