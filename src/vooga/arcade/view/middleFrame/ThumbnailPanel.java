package vooga.arcade.view.middleFrame;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vooga.arcade.controller.ArcadeController;

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
	    super(new CardLayout());
	    addNewCard(thumbnails, "init");
	}
    
    public void addNewCard(List<JPanel> thumbnails, String name){
        JPanel thumbPanel = new JPanel();
        thumbPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        int height = (thumbnails.size() / 3 * 250);
        
        // Size of the Frame itself (Determines scroll length)
        thumbPanel.setPreferredSize(new Dimension(620, height));
        for (JPanel thumbnail : thumbnails)
        {
            thumbPanel.add(thumbnail);
        }

        JScrollPane scrollPane = new JScrollPane(thumbPanel);
        
        // Size of the Panel inside the frame.
        scrollPane.setPreferredSize(new Dimension(640, 860));
        
        scrollPane
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, name);
    }
    
    public void show (String name){
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, name);
    }
}
