package vooga.arcade.view.middleFrame;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import vooga.arcade.controller.*;

/**
 * @author Andrea
 * Makes the main panel of game thumbnails (or any other type of thumbnail, represented as
 * a JPanel).
 *
 */

public class ThumbnailPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    ArcadeController myCont;
    
    public ThumbnailPanel(List<JPanel> thumbnails)
    {
        JPanel thumbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane scrollPane = new JScrollPane(thumbPanel);
        
        for (JPanel thumbnail : thumbnails)
        {
            thumbPanel.add(thumbnail);
        }
    
        this.add(scrollPane);
    }
}
