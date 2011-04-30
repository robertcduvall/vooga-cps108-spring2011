/**
 * 
 */
package vooga.arcade.view.gamePanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vooga.arcade.helper.ImageProcessor;
import vooga.arcade.parser.ArcadeGameObject;
import vooga.arcade.view.actions.ActionFactory;

/**
 * @author KevinWang
 * @author Ethan Goh
 */
public abstract class ArcadePanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	protected ImageIcon icon;
	protected String title;
	protected JButton imageButton;
	
	public ArcadePanel(ImageIcon i, String t)
	{
		icon = i; 
		title = t;
		this.setLayout(new BorderLayout(10, 10));
		addImageButton();
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	private void addImageButton()
	{
	    imageButton = new JButton(icon);
	    this.add(imageButton, BorderLayout.NORTH);
	}

}
