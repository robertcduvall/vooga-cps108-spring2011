package vooga.core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.core.event.ISimpleEventManager;
import vooga.levels.LevelManager;
import vooga.resources.ResourceManager;
import vooga.resources.images.ImageLoader;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;


public abstract class VoogaGame extends Game implements ISimpleEventManager
{
    public static void launchGame(VoogaGame game, Dimension dimension, boolean fullScreen)
    {
        GameLoader loader = new GameLoader();
        loader.setup(game, dimension, fullScreen);
        loader.start();
    }
    
	private final static int DEFAULT = 0;
	private final static int MENU = 1;
	private final static int GAME = 2;
	
    private ResourceManager myResourceManager;
    private LevelManager myLevelManager;
    private VoogaState myState;
    private Map<Integer, VoogaState> myStates;
    
    @Override
    protected void initEngine()
    {
        super.initEngine();
        
        myResourceManager = new ResourceManager(this);
        myLevelManager = new LevelManager(this);
        myResourceManager.parse();
        myStates = new HashMap<Integer, VoogaState>();
        myState = new VoogaState()
        {
            private EventManager myEventManager = new EventManager();
            @Override
            public void update (long elapsedTime)
            {
                return;
            }
            
        
            @Override
            public void render (Graphics2D g)
            {
                return;
            }
            
        
            @Override
            public EventManager getEventManager ()
            {
                return myEventManager;
            }
        };
        myStates.put(DEFAULT, myState);
        myStates.put(GAME, myLevelManager);
        // Register keyboard input checking event handler.
        myResourceManager.getKeyMap().registerEventHandler(this);
    }
    
    @Override
    public void addEveryTurnEvent (String eventName, IEventHandler eventHandler)
    {
        getEventManager().addEveryTurnEvent(eventName, eventHandler);
    }


    @Override
    public void addPeriodicTimer (String timerName,
                                  long interval,
                                  String eventName)
    {
        getEventManager().addPeriodicTimer(timerName, interval, eventName);
    }


    @Override
    public void addPeriodicTimer (String timerName,
                                  long interval,
                                  String eventName,
                                  Object arg)
    {
        getEventManager().addPeriodicTimer(timerName, interval, eventName, arg);
    }


    @Override
    public void addTimer (String timerName, long delay, String eventName)
    {
        getEventManager().addTimer(timerName, delay, eventName);
    }


    @Override
    public void addTimer (String timerName,
                          long delay,
                          String eventName,
                          Object arg)
    {
        getEventManager().addTimer(timerName, delay, eventName, arg);
    }


    @Override
    public void fireEvent (Object source, String eventName)
    {
        getEventManager().fireEvent(source, eventName);
    }


    @Override
    public void fireEvent (Object source, String eventName, Object arg)
    {
        getEventManager().fireEvent(source, eventName, arg);
    }


    @Override
    public void fireEvents (Object source, String glob)
    {
        getEventManager().fireEvents(source, glob);
    }


    @Override
    public void fireEvents (Object source, String glob, Object arg)
    {
        getEventManager().fireEvents(source, glob, arg);
    }


    @Override
    public void forwardEvent (String eventName, String nextEventName)
    {
        getEventManager().forwardEvent(eventName, nextEventName);
    }


    public EventManager getEventManager ()
    {
        return myState.getEventManager();
    }


    /**
     * Use getResourceManager() and ResourceManager#getImageLoader().
     * @return
     */
    public ImageLoader getImageLoader() {
    	return myResourceManager.getImageLoader();
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
    	myState = myStates.get(GAME);
    	myLevelManager.loadLevel(id);
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
        getEventManager().registerEventHandler(eventName, eventHandler);
    }


    @Override
    public IEventHandler removeEventHandler (String eventName)
    {
        return getEventManager().removeEventHandler(eventName);
    }


    @Override
    public void removeEventHandlers (String glob)
    {
        getEventManager().removeEventHandlers(glob);
    }


    @Override
    public void render (Graphics2D g)
    {
        myLevelManager.render(g);
    }


    @Override
    public void update (long elapsedTime)
    {
        getEventManager().update(elapsedTime);
        myLevelManager.update(elapsedTime);
        updatePlayField(elapsedTime);
    }

    @Deprecated // Implementing game states which will replace this 
    public abstract void updatePlayField (long elapsedTime);
}
