package vooga.core;

import java.awt.Graphics2D;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.core.event.ISimpleEventManager;
import vooga.levels.LevelManager;
import vooga.resources.KeyMap;
import vooga.resources.ResourceManager;
import vooga.resources.images.ImageLoader;
import com.golden.gamedev.Game;
import vooga.view.graphics.AdvancedGraphics;
import java.awt.geom.AffineTransform;
import java.util.List;
import vooga.view.function.AbstractGraphicsFunction;

public abstract class VoogaGame extends Game implements ISimpleEventManager
{
    private EventManager myEventManager;
    private ResourceManager myResourceManager;
    private KeyMap myKeyMap;
    private LevelManager myLevelManager;
    protected AdvancedGraphics myAdvancedGraphics;

    public VoogaGame ()
    {
        super();
        myEventManager = new EventManager();
        myResourceManager = new ResourceManager(this.getClass());
        myLevelManager = new LevelManager(this);
    }
    
    @Override
    public void addEveryTurnEvent (String eventName, IEventHandler eventHandler)
    {
        myEventManager.addEveryTurnEvent(eventName, eventHandler);
    }


    @Override
    public void addPeriodicTimer (String timerName,
                                  long interval,
                                  String eventName)
    {
        myEventManager.addPeriodicTimer(timerName, interval, eventName);
    }


    @Override
    public void addPeriodicTimer (String timerName,
                                  long interval,
                                  String eventName,
                                  Object arg)
    {
        myEventManager.addPeriodicTimer(timerName, interval, eventName, arg);
    }


    @Override
    public void addTimer (String timerName, long delay, String eventName)
    {
        myEventManager.addTimer(timerName, delay, eventName);
    }


    @Override
    public void addTimer (String timerName,
                          long delay,
                          String eventName,
                          Object arg)
    {
        myEventManager.addTimer(timerName, delay, eventName, arg);
    }


    @Override
    public void fireEvent (Object source, String eventName)
    {
        myEventManager.fireEvent(source, eventName);
    }


    @Override
    public void fireEvent (Object source, String eventName, Object arg)
    {
        myEventManager.fireEvent(source, eventName, arg);
    }


    @Override
    public void fireEvents (Object source, String glob)
    {
        myEventManager.fireEvents(source, glob);
    }


    @Override
    public void fireEvents (Object source, String glob, Object arg)
    {
        myEventManager.fireEvents(source, glob, arg);
    }


    @Override
    public void forwardEvent (String eventName, String nextEventName)
    {
        myEventManager.forwardEvent(eventName, nextEventName);
    }


    public EventManager getEventManager ()
    {
        return myEventManager;
    }


    public KeyMap getKeyMap ()
    {
        return myKeyMap;
    }
    
    public LevelManager getLevelManager()
    {
        return myLevelManager;
    }
    
    public ResourceManager getResourceManager()
    {
        return myResourceManager;
    }


    
    /**
     * Creates advancedGraphics object to manage graphics state
     */
    @Override
    public void initResources()
    {
        myResourceManager.init();
        myAdvancedGraphics = new AdvancedGraphics(this);
        this.initializeResources();
    }

    /**
     * Must be implemented to initialize game resources in game that extends VoogaGame.
     */
    public abstract void initializeResources();
    

    @Override
    protected void notifyExit ()
    {
        // TODO: Deinitialize everything here
    }


    @Override
    public void registerEventHandler (String eventName,
                                      IEventHandler eventHandler)
    {
        myEventManager.registerEventHandler(eventName, eventHandler);
    }


    @Override
    public IEventHandler removeEventHandler (String eventName)
    {
        return myEventManager.removeEventHandler(eventName);
    }


    @Override
    public void removeEventHandlers (String glob)
    {
        myEventManager.removeEventHandlers(glob);
    }

    /**
     * Renders graphics based on state of advancedGraphics
     * Pass modified graphics context to LevelManager
     */
    @Override
    public void render(Graphics2D g)
    {
        if (myAdvancedGraphics.getFunctionState() != null)
        {
            AffineTransform old = g.getTransform();
            g.setTransform(myAdvancedGraphics.getFunctionState());
            transformedRender(g);
            g.setTransform(old);
        }
        myLevelManager.render(g);
    }

    public void setKeyMap (KeyMap keyMap)
    {
        if (keyMap == null) throw new NullPointerException("Must load a valid KeyMap!");
        if (myKeyMap == null) KeyMap.registerEventHandler(this);
        myKeyMap = keyMap;
    }


    @Override
    public void update (long elapsedTime)
    {
        myEventManager.update(elapsedTime);
        myLevelManager.update(elapsedTime);
        updatePlayField(elapsedTime);
    }
    
    public abstract void updatePlayField (long elapsedTime);

    /**
     * Use getResourceManager() and ResourceManager#getImageLoader().
     * @return
     */
    public ImageLoader getImageLoader() {
    	return myResourceManager.getImageLoader();
    }
    
    /**
     * User specifies what type of functions required.
     * @return List of functions maintained in advancedGraphics
     */
    public abstract List<AbstractGraphicsFunction> graphicsFunctions();
    
    /**
     * Convenience method for setting advancedGraphics state
     * @param bool
     */
    public void setGraphicsActivityState(boolean bool)
    {
        myAdvancedGraphics.setActivityState(bool);
    }
    
    /**
     * Convenience method for getting advancedGraphics activity state
     * @return boolean
     */
    public boolean getGraphicsActivityState()
    {
        return myAdvancedGraphics.getActivityState();
    }
}
