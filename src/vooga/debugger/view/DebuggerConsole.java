
package vooga.debugger.view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class that initializes and updates console of system
 * 
 * @author Troy Ferrell
 */
public class DebuggerConsole extends JPanel 
{
	private JTextArea myConsoleTextArea;
	
	public DebuggerConsole()
	{
		myConsoleTextArea = new JTextArea();
		myConsoleTextArea.setEditable(false);
		myConsoleTextArea.setLineWrap(true);
		myConsoleTextArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(myConsoleTextArea);
		// TODO: need to get rid of hardcoded values
		scrollPane.setMinimumSize(new Dimension(150, 35));
		scrollPane.setPreferredSize(new Dimension(550, 100));
		
		this.add(scrollPane);
	}
	
	/**
	 * Clear debugger console
	 */
	public void clearConsole()
	{
		myConsoleTextArea.setText("");
	}
	
	/**
	 * print line to debugger console
	 * @param s - string to print
	 */
	public void println(String s)
	{
		myConsoleTextArea.append(s + "\n");
	}
}


