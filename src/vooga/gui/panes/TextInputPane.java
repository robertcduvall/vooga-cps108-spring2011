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
public class TextInputPane extends PopoverPane
{
    FrameWork textInputPaneFrame;
    TTextField tTextBox;
    VoogaButton acceptButton;
   
    public TextInputPane(PaneManager parent, BufferedImage acceptImage)
    {
        super(parent);
        acceptButton = new VoogaButton(acceptImage, "Accept", new Dimension(50, 100));
    }
   
    public void initResources()
    {
        /*
         * The following error is COMPLETELY INTENTIONAL. Granted, it may sound really really
         * stupid, especially at (just checked the time) 4:05 AM, but it shouldn't kill
         * anything from anywhere. Oh, and I need to figure out what replaces GameObject for
         * this case.
         */
        textInputPaneFrame = new FrameWork(myParent.getParent().bsInput, myParent.getWidth(), myParent.getHeight());
        tTextBox = new TTextField("", myParent.getWidth()/2-50, myParent.getHeight()/2+20, 110, 25);
        textInputPaneFrame.add(tTextBox);
        //test comment
    }
   
    @Override
    public void render(Graphics2D g)
    {
        super.render(g);
        tTextBox.render(g);
        acceptButton.render(g);
    }
}