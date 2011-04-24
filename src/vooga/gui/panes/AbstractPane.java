package vooga.gui.panes;

import java.awt.Graphics2D; 
import vooga.gui.PaneManager;

    /** 
    * An AbstractPane that has little to no functionality, but is
    * extended to form other useful classes (ie PopoverPane)
    *
    * @author Dave Crowe
    */
public abstract class AbstractPane{
    protected PaneManager myParent;
    
    /** 
    * An AbstractPane that has little to no functionality, but is
    * extended to form other useful classes (ie PopoverPane)
    */
    public AbstractPane(PaneManager parent){
        myParent=parent;
    }
    
    /** Render yourself to some graphics */
    public abstract void render(Graphics2D g);
    
    /** Update your state */
    public abstract void update(double elapsedTime);

    /** Interpret a mouseclick */
    public abstract void sendClick(double mouseX, double mouseY);
    
    /** Fire an event to your parent's EventManager*/
    public void fireEvent(String s){
        myParent.getEventManager().fireEvent(this, s);
    }

}
