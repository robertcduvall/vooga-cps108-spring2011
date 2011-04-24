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
public class ArcadeGamePanel extends ArcadePanel
{

	private static final long serialVersionUID = 1L;
	private ArcadeGameObject gameObject;

	public ArcadeGamePanel(ArcadeGameObject gameObject)
	{
		super(new ImageIcon(gameObject.getImage()), gameObject.getData("title"));
		this.gameObject = gameObject;
		addPlayButton();
	}

	private void addPlayButton()
	{
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				gameObject.start();

			}
		});
		this.add(playButton, BorderLayout.WEST);
	}
}
