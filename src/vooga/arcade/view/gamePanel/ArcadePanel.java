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

import vooga.arcade.parser.ArcadeGameObject;

/**
 * @author KevinWang
 * @author Ethan Goh
 */
public abstract class ArcadePanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private ImageIcon icon;
	private String title;
	
	public ArcadePanel(ImageIcon i, String t)
	{
		icon = i; 
		title = t;
		this.setLayout(new BorderLayout(10, 10));
		addIcon();
		addTitle();
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	private void addIcon()
	{
		this.add(new JLabel(icon), BorderLayout.NORTH);
	}
	
	private void addTitle()
	{
		this.add(new JLabel(title), BorderLayout.EAST);
	}
}
