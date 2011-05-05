package vooga.gui.panes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.gui.TTextField;
import com.golden.gamedev.gui.toolkit.FrameWork;

import vooga.gui.PaneManager;
import vooga.gui.util.VoogaButton;

/**
 * Type of popover that allows a user to input a value, and when 'accpt' 
 * button is pressed (customizable look) returns the input to its parent.
 * @author Dave Crowe, David Colon-Smith
 *
 */
public class TextInputPaneRefactored extends PopoverPane
{
    FrameWork textInputPaneFrame;
    TTextField tTextBox;
    VoogaButton acceptButton;
   
    public TextInputPaneRefactored(PaneManager parent, BufferedImage acceptImage)
    {
        super(parent);
        acceptButton = new VoogaButton(acceptImage, "Accept", new Dimension(50, 100));
        
    }
   
    public void initResources()
    {
        textInputPaneFrame = new FrameWork(myParent.getParent().bsInput, myParent.getWidth(), myParent.getHeight());
        tTextBox = new TTextField("", myParent.getWidth()/2-50, myParent.getHeight()/2+20, 110, 25);
        textInputPaneFrame.add(tTextBox);
    }
   
    @Override
    public void render(Graphics2D g)
    {
        super.render(g);
        tTextBox.render(g);
        acceptButton.render(g);
    }
}