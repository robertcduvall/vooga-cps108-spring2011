package vooga.resources;

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
        EDGE, LEVEL
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

        int keyCode = getKeyCode(keyName);

        if (!myKeys.containsKey(keyCode)) myKeys.put(keyCode,
                                                     new Entry(keyCode,
                                                               sensitivity));
        // TODO 2 entries, same key, different sensitivities
        myKeys.get(keyCode).events.add(event);
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
                (entry.sensitivity == KeyMap.Sensitivity.EDGE && game.bsInput.isKeyPressed(entry.keyCode)))
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
