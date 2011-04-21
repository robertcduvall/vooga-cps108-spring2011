package vooga.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
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


    public KeyMap (ResourceBundle bundle)
    {
        myKeys = new HashMap<Integer, Entry>();
        for (String event : bundle.keySet())
        {
            String value = bundle.getString(event);

            String edgeLevelSensitive = value.split("[.]")[0];
            KeyMap.Sensitivity sensitivity = null;
            if (edgeLevelSensitive.equalsIgnoreCase("Edge")) sensitivity =
                Sensitivity.EDGE;
            if (edgeLevelSensitive.equalsIgnoreCase("Level")) sensitivity =
                Sensitivity.LEVEL;

            String keyName = value.split("[.]")[1];
            int keyCode = getKeyCode(keyName);

            if (!myKeys.containsKey(keyCode)) myKeys.put(keyCode,
                                                         new Entry(keyCode,
                                                                   sensitivity));
            myKeys.get(keyCode).events.add(event);
        }
    }


    public Set<Map.Entry<Integer, Entry>> entrySet ()
    {
        return myKeys.entrySet();
    }


    private int getKeyCode (String keyName)
    {
        return KeyStroke.getKeyStroke(keyName).getKeyCode();
    }


    public static void pollForInput (VoogaGame game)
    {
        for (Map.Entry<Integer, KeyMap.Entry> mapEntry : game.getKeyMap().entrySet())
        {
            KeyMap.Entry entry = mapEntry.getValue();
            if ((entry.sensitivity == KeyMap.Sensitivity.LEVEL && game.bsInput.isKeyDown(entry.keyCode)) ||
                (entry.sensitivity == KeyMap.Sensitivity.EDGE && game.bsInput.isKeyPressed(entry.keyCode)))
            {
                for (String eventName : entry.events)
                {
                    game.getEventManager().fireEvent(game.getKeyMap(),
                                                     eventName,
                                                     entry);
                }
            }
        }
    }


    public static void registerEventHandler (final VoogaGame game)
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
