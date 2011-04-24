package vooga.arcade.view.middleFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import vooga.arcade.controller.*;

/**
 * Makes the main panel of game thumbnails (or any other type of thumbnail,
 * represented as a JPanel).
 * 
 * @author Andrea
 * @author Ethan Goh
 * 
 */

public class ThumbnailPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private ArcadeController myCont;

	public ThumbnailPanel(List<JPanel> thumbnails)
	{
		JPanel thumbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		// Size of the Frame itself (Determines scroll length)
		thumbPanel.setPreferredSize(new Dimension(620, 500));
		JScrollPane scrollPane = new JScrollPane(thumbPanel);

		// Size of the Panel inside the frame.
		scrollPane.setPreferredSize(new Dimension(640, 460));
		for (JPanel thumbnail : thumbnails)
		{
			thumbPanel.add(thumbnail);
		}
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);
	}
}
