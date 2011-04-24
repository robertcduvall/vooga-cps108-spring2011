package vooga.gui;

import java.awt.Graphics2D; 
import java.awt.image.BufferedImage;
import java.util.*;

import vooga.core.VoogaGame;
import vooga.core.VoogaState;
import vooga.core.event.EventManager;
import vooga.gui.panes.AbstractPane;


/**
 * A manger that facilitates movement between panes, stores information
 * regarding the overall state of the panes... updating and rendering correctly
 * based on the current panel.
 * 
 * @author David Crowe
 */
 
 public abstract class PaneManager implements VoogaState {
    //State variables
        //Instantiated by default
        protected EventManager myEventManager;
        protected List<AbstractPane> myPanes;
    
        //Instantiated in constructor
        protected VoogaGame myParent;
        protected AbstractPane myActivePane;


    /**
     * Initializes relevant items and sets the active panel.
     * 
     * @param game The game to which this PaneManager is tied.
     */
    public PaneManager (VoogaGame parent) {
        myParent=parent;
        myEventManager = new EventManager();
        myPanes=new ArrayList<AbstractPane>();
        initPanes();
        createEventHandlers();
        openDefaultPane();
    }
    
    /**
    * Create your panes and add them to the myPanes list.
    * The first you add will be defined as your "default pane" for opening
    * via openDefaultPane();
    * 
    * 
    *    @example
    *    menuPane=new MenuPane(new ArrayList<VoogaButton>(), this);
    *    myPanes.add(menuPane);
    */
    protected abstract void initPanes() ;
    
    /**
     * Create your EventHandlers as specified by the Events team.
     * 
     * 
     *    @example
     *     eventManager.registerEventHandler("Message", new IEventHandler()
     *           {
     *                   public void handleEvent(Object o)
     *                   {
     *                           sayHi();
     *                   }
     *           });
     */
     protected abstract void createEventHandlers() ;

    /**
     * Loads the first pane. This is the same as calling openPane(0)
     */
    public void openDefaultPane()
    {
        openPane(0);
    }


    /**
    * Open a pane at specified index, if this index is out of range,
    * nothing will happen.
    *
    * @param paneIndex the index of the pane you want to open.
    */
    public void openPane (int paneIndex)
    {
        AbstractPane tempPane = myPanes.get(paneIndex);
        if (tempPane!=null){
            myActivePane=tempPane;
//            System.out.println("Pane switched to: "+paneIndex);
        }
    }

    /**
     * Retrieves the highest running level's id
     */
    public int getCurrentPane(){
        for (int i=0;i<myPanes.size();i++)
        {
            if (myPanes.get(i).equals(myActivePane))
            {
                return i;
            }
        }
        System.out.println("Pane not found. Returning -1.");
		return -1;
    }

    /**
     * Updates the active Pane.
     * 
     * @param elapsedTime
     */
    public void update (long elapsedTime)
    {
    	myEventManager.update(elapsedTime);
        if(myActivePane != null)
        {
            myActivePane.update(elapsedTime);
        }    
    }

    /**
     * Renders the active Pane.
     * 
     * @param g The graphics which will be used to render
     */
    public void render (Graphics2D g)
    {
        if(myActivePane != null)
        {
            myActivePane.render(g);
        }
    }
    
     /**
     * Sends a mouseclick to the active pane.
     */
    public void sendClickToActivePane() 
    {
//    	System.out.println("PaneManager: Received Click... ");
        if(myActivePane != null)
        {
            myActivePane.sendClick(getMouseX(), getMouseY());
//            System.out.println("PaneManager: Passing it along.");
        }
    }
    
    /**
    * Grabs the mouseX coordinate from the toplevel voogaGame
    *
    * @return the mouse's relative X position in the parent game
    */
    public double getMouseX(){
        return myParent.getMouseX();
    }
    
    /**
    * Grabs the mouseY coordinate from the toplevel voogaGame
    *
    * @return the mouse's relative Y position in the parent game
    */
    public double getMouseY(){
        return myParent.getMouseY();
    }
    
    /**
    * Get the width of this PaneManager's VoogaGame parent
    */
    public int getWidth()
    {
        return myParent.getWidth();
    }
    
    /**
    * Get the height of this PaneManager's VoogaGame parent
    */
    public int getHeight()
    {
        return myParent.getHeight();
    }

    /**
     * Use this object's VoogaGame parent to obtain an image from a string.
     * @param s
     * @return
     */
	public BufferedImage getImage(String s) {
		return myParent.getImage(s);
	}

	/**
	 * get the EventManager
	 * @return this PaneManager's EventManager
	 */
	public EventManager getEventManager() {
		return myEventManager;
	}
	
	/**
	 * Get this object's parent- use exclusively to initialize COMPONENTS within a Pane.
	 * 
	 * @return myParent - the VoogaGame that this PaneManager was spawned from.
	 */
	public VoogaGame getParent() {
		return myParent;
	}
	
	/**
	 * Fires an event to the Manager's parent- used to send commands that are applied
	 * to say a LevelManager.
	 * @param eventName the event string to fire.
	 */
	public void fireParentEvent(String eventName){
		myParent.getEventManager().fireEvent(eventName, eventName);
	}
}
