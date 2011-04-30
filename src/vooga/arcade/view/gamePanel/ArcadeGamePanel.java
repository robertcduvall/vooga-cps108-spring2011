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

import vooga.arcade.controller.ArcadeController;
import vooga.arcade.helper.ImageProcessor;
import vooga.arcade.html.AboutHTMLPage;
import vooga.arcade.parser.ArcadeGameObject;
import vooga.arcade.view.actions.ActionFactory;

/**
 * JPanel instance that contains an arcadeGameObject
 * 
 * @author KevinWang
 * @author Ethan Goh
 */
public class ArcadeGamePanel extends ArcadePanel
{

	private static final long serialVersionUID = 1L;
	private ArcadeGameObject gameObject;
	private ArcadeController arcadeController;

	private static int iconSize = 130;

	public ArcadeGamePanel(ArcadeGameObject gameObject, ArcadeController pc)
	{
		super(new ImageIcon(ImageProcessor.padImageToSquare(gameObject.getImage(),iconSize)), gameObject.getData("title"));
		this.gameObject = gameObject;
		this.arcadeController = pc;
		addPlayButton();
		addListenerToButton(pc);
	}

	/**
	 * add play button to the panel.
	 */
	private void addPlayButton()
	{
		JButton playButton = new JButton(title);
		playButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				gameObject.start();

			}
		});
		this.add(playButton, BorderLayout.CENTER);
	}
	
	/**
	 * add action listener to button
	 * @param pc
	 */
	private void addListenerToButton(ArcadeController pc){
	    imageButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                arcadeController.updateColumnText(AboutHTMLPage.generateHTMLFile(gameObject),0);
            }
        });
	}

	/**
	 * set icon size
	 * @param i
	 */
	private void setIconSize(int i)
	{
		iconSize = i;
	}
}
