package vooga.gui.panes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map;

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
    Map<TTextField, VoogaButton> textFieldsList;
   
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
    
    public void multipleRender(Graphics2D g)
    {
    	super.render(g);
    	for(TTextField ttf : textFieldsList.keySet())
    		ttf.render(g);
    	for(VoogaButton vb : textFieldsList.values())
    		vb.render(g);
    }
    
    /**
     * This method was added for the sake of adding multiple components as a list going down or across.
     * @param x - x coordinate of the first text field
     * @param y - y coordinate of the first text field
     * @param dx - space between the next text field (x-coordinate) thats the same between all text fields
     * @param dy - space between the next text field (y-coordinate) thats the same between all text fields
     */
    public void initMultipleResources(int x, int y, int dx, int dy)
    {
    	int deltaMultiplier = 1;
    	textInputPaneFrame = new FrameWork(myParent.getParent().bsInput, myParent.getWidth(), myParent.getHeight());
    	for(TTextField ttf : textFieldsList.keySet())
    	{
    		ttf = new TTextField("", myParent.getWidth()/2-50, myParent.getHeight()/2+20, x+(dx*deltaMultiplier), y+(dy*deltaMultiplier));
    		textInputPaneFrame.add(ttf);
    		deltaMultiplier++;
    	}
    }
    
    /**
     * Adds another text field and accompanying button with it; most useful for the buttons
     * @param textField - a Golden T TextField (see their GUI package information)
     * @param VoogaButton - a GUI component button
     */
    public void setTextFieldandButton(TTextField textField, VoogaButton button)
    {
    	textFieldsList.put(textField, button);
    }
}