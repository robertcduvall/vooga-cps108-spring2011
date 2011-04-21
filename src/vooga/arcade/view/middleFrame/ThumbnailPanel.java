package vooga.arcade.view.middleFrame;
import java.util.ArrayList;
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
    IController myCont;
    
    public ThumbnailPanel(ArrayList<JPanel> thumbnails)
    {
        JPanel thumbPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(thumbPanel);
        
        // TODO: Specify the layout?
        
        for (JPanel thumbnail : thumbnails)
        {
            thumbPanel.add(thumbnail);
        }
    
        this.add(scrollPane);
    }
}
