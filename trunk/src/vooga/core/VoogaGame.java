package vooga.core;

import java.awt.Graphics2D;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.core.event.ISimpleEventManager;
import vooga.levels.LevelManager;
import vooga.resources.KeyMap;
import vooga.resources.ResourceManager;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;

import com.golden.gamedev.Game;


public abstract class VoogaGame extends Game implements ISimpleEventManager
{
    private EventManager myEventManager;
    protected ResourceManager myResourceManager;
    private KeyMap myKeyMap;
    private LevelManager myLevelManager;

    public VoogaGame ()
    {
        super();
        myEventManager = new EventManager();
//        myResourceManager = new ResourceManager(this.getClass());
        myLevelManager = new LevelManager(this);
        initializeEventHandlers();
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


    private void initializeEventHandlers ()
    {
        myEventManager.registerEventHandler("EveryTurn.CheckInput",
                                            new IEventHandler()
                                            {
                                                public void handleEvent (Object o)
                                                {
                                                    // TODO Game Resources Team: Poll all mapped inputs
                                                    // (keys, mouse, etc.) and fire associated events
                                                }
                                            });
        myEventManager.registerEventHandler("EveryTurn.UpdatePlayField",
                                            new IEventHandler()
                                            {
                                                @Override
                                                public void handleEvent (Object o)
                                                {
                                                    long elapsedTime = (Long) o;
                                                    updatePlayField(elapsedTime);
                                                }
                                            });
    }


    @Override
    public void initResources () {
    	myResourceManager.init();
    }


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


    @Override
    public abstract void render (Graphics2D g);


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
    }
    
    public abstract void updatePlayField (long elapsedTime);

    /**
     * Use getResourceManager() and ResourceManager#getImageLoader().
     * @deprecated
     * @return
     */
    public ImageLoader getImageLoader() {
    	return myResourceManager.getImageLoader();
    }
}
