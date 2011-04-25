package vooga.gui.panes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import vooga.core.event.IEventHandler;
import vooga.gui.PaneManager;
import vooga.gui.util.ScrollingSpriteDisplay;

/**
 * Type of popover that allows a user to select from a group of displayed sprites. Uses
 * the ScrollingSpriteDisplay to do this, and displays a neat header on top. Extends PopoverPane
 * so keep in mind back and menu are enabled by default.
 * @author Dave Crowe, David Colon-Smith
 *
 */
public class ChooserPane extends PopoverPane
{   
    int lastIndex;
    ScrollingSpriteDisplay<Sprite> myChooserDisplay;
    Sprite myHeader;
    String myName;
   
    public ChooserPane(PaneManager parent, BufferedImage[] optionSlides, BufferedImage header)
    {
        super(parent);
        myHeader = new Sprite(header, 5, 5);
        myChooserDisplay = new ScrollingSpriteDisplay<Sprite>(40, header.getHeight() + 10, parent.getWidth() - 85,
                parent.getHeight() - header.getHeight() - 10, 5, parent.getParent());
        if(optionSlides != null && optionSlides.length > 0)
            for(int i = 0; i < optionSlides.length; i++)
                myChooserDisplay.addSprite(new Sprite(optionSlides[i]));
        lastIndex = optionSlides.length - 1;
        initResetAction();
    }
   
    public void initResetAction()
    {
    	myName = "Default"; // move method to top level later
        myParent.getEventManager().registerEventHandler("ResetInfoPane."+myName, new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                reset();
            }
           
        });
    }
   
    /**
     * Returns the currently seleted sprite in the scrolling sprite display thing
     */
    public Sprite getActiveSprite()
    {
        return myChooserDisplay.getActiveSprite();
    }
   
    /**
     * Renders the chooser pane. Call this at the end of the parent's draw method to paint over what
     * was visible before.
     */
    @Override
    public void render(Graphics2D g)
    {
        super.render(g);
        myHeader.render(g);
        myChooserDisplay.render(g);
    }
   
    /**
     * Sets the chooser/palette back to the first set. It's best to do it right after the "show" boolean
     * has been set to false.
     */
    public void reset()
    {
        myChooserDisplay.setStart(0);
    }
   
    /**
     * NEEDS TO BE IN THE UPDATE METHOD OF THIS OBJECT'S PARENT. [ ie:
     * if (click()) sendClick(); ] This is how the panel responds to user input.
     */
    public void sendClick(double mouseX, double mouseY)
    {
        super.sendClick(mouseX, mouseY);
        myChooserDisplay.sendClick(mouseX, mouseY);
        /*for(Sprite option : myChooserDisplay.getActiveSprite())
        {
           
        }*/
    }

}