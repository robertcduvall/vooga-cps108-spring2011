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


public abstract class VoogaGame extends Game implements ISimpleEventManager
{
	private final static int DEFAULT = 0;
	private final static int MENU = 1;
	private final static int GAME = 2;
	
    private EventManager myEventManager;
    private ResourceManager myResourceManager;
    private KeyMap myKeyMap;
    private LevelManager myLevelManager;
    private VoogaState myStates;

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
    
    public void startLevel(int id) 
    {
    	myState = myStates.get(GAME); //FIXME
    	myLevelManager.loadLevel(id);
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
    public void render (Graphics2D g)
    {
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
    
    @Deprecated // Implementing game states which will replace this 
    public abstract void updatePlayField (long elapsedTime);

    /**
     * Use getResourceManager() and ResourceManager#getImageLoader().
     * @return
     */
    public ImageLoader getImageLoader() {
    	return myResourceManager.getImageLoader();
    }
}
