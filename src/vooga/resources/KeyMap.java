package vooga.resources;

import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.KeyStroke;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;


public class KeyMap
{
    public class Entry
    {
        public Set<String> events;

        public int keyCode;
        public KeyMap.Sensitivity sensitivity;


        public Entry (int keyCode, KeyMap.Sensitivity sensitivity)
        {
            this.keyCode = keyCode;
            this.sensitivity = sensitivity;
            this.events = new HashSet<String>();
        }
    }

    public enum Sensitivity
    {
        EDGE(true), LEVEL(true), MOUSE_EDGE(false), MOUSE_LEVEL(false);
        
        private boolean amKey;
        Sensitivity(Boolean amkey){
        	amKey = amkey;
        }
        
        public boolean isKey(){
        	return amKey;
        }
    }

    private Map<Integer, Entry> myKeys;


    public KeyMap ()
    {
        myKeys = new HashMap<Integer, Entry>();
    }


    public void addKeyEvent (String event,
                             String edgeLevelSensitive,
                             String keyName)
    {
        KeyMap.Sensitivity sensitivity = null;
        if (edgeLevelSensitive.equalsIgnoreCase("Edge")) sensitivity =
            Sensitivity.EDGE;
        if (edgeLevelSensitive.equalsIgnoreCase("Level")) sensitivity =
            Sensitivity.LEVEL;
        if (edgeLevelSensitive.equalsIgnoreCase("MouseEdge")) sensitivity =
            Sensitivity.MOUSE_EDGE;
        if (edgeLevelSensitive.equalsIgnoreCase("MouseLevel")) sensitivity =
            Sensitivity.MOUSE_LEVEL;

        int keyCode = 0;
        if (sensitivity.isKey()) keyCode = getKeyCode(keyName);
        else keyCode = getMouseCode(keyName);

        if (!myKeys.containsKey(keyCode)) myKeys.put(keyCode,
                                                     new Entry(keyCode,
                                                               sensitivity));
        // TODO 2 entries, same key, different sensitivities
        myKeys.get(keyCode).events.add(event);
    }


    private int getMouseCode(String keyName) {
    	if (keyName.equalsIgnoreCase("Button1")) return MouseEvent.BUTTON1;
    	else if (keyName.equalsIgnoreCase("Button2")) return MouseEvent.BUTTON2;
    	else return MouseEvent.BUTTON3;
	}


	private int getKeyCode (String keyName)
    {
    	
        return KeyStroke.getKeyStroke(keyName).getKeyCode();
    }


    public void pollForInput (VoogaGame game)
    {
        for (Map.Entry<Integer, KeyMap.Entry> mapEntry : myKeys.entrySet())
        {
            KeyMap.Entry entry = mapEntry.getValue();
            if ((entry.sensitivity == KeyMap.Sensitivity.LEVEL && game.bsInput.isKeyDown(entry.keyCode)) ||
                (entry.sensitivity == KeyMap.Sensitivity.EDGE && game.bsInput.isKeyPressed(entry.keyCode)) ||
                (entry.sensitivity == KeyMap.Sensitivity.MOUSE_LEVEL && game.bsInput.isMouseDown(entry.keyCode)) ||
                (entry.sensitivity == KeyMap.Sensitivity.MOUSE_EDGE && game.bsInput.isMousePressed(entry.keyCode)))
            {
                for (String eventName : entry.events)
                {
                    game.getEventManager().fireEvent(this, eventName, entry);
                }
            }
        }
    }


    public void registerEventHandler (final VoogaGame game)
    {
        game.getEventManager().registerEventHandler("EveryTurn.CheckInput",
                                                    new IEventHandler()
                                                    {
                                                        public void handleEvent (Object o)
                                                        {
                                                            // Poll all mapped inputs (keys, mouse, etc.) and fire associated events
                                                            pollForInput(game);
                                                        }
                                                    });
    }
}
