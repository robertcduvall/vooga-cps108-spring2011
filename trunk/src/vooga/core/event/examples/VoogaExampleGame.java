package vooga.core.event.examples;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.KeyStroke;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


public abstract class VoogaExampleGame extends VoogaGame
{
    protected static class KeyMap
    {
        public class Entry
        {
            public Set<String> events;

            public int keyCode;
            public Sensitivity sensitivity;


            public Entry (int keyCode, Sensitivity sensitivity)
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
                Sensitivity sensitivity = null;
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
    }

    protected class SimpleMenu
    {
        private String myCloseOnEvent;
        private String myFilter;
        private VoogaGame myGame;
        private String myOnCloseEvent;
        private PlayField myPlayField;
        private Sprite mySprite;
        private SpriteGroup mySpriteGroup;


        public SimpleMenu (VoogaGame game,
                           PlayField playField,
                           BufferedImage image,
                           String removeEventsGlob,
                           String closeOnEvent,
                           String fireEventOnClose)
        {
            myGame = game;
            myPlayField = playField;

            mySprite = new Sprite(image);
            // put Sprite in the center of the window
            mySprite.setX(myGame.getWidth() / 2 - mySprite.getWidth() / 2);
            mySprite.setY(myGame.getHeight() / 2 - mySprite.getHeight() / 2);

            mySpriteGroup = new SpriteGroup("SimpleMenuInstance");
            mySpriteGroup.add(mySprite);

            myFilter = removeEventsGlob;

            myCloseOnEvent = closeOnEvent;
            myOnCloseEvent = fireEventOnClose;
        }


        public void hide ()
        {
            myPlayField.removeGroup(mySpriteGroup);
            myGame.getEventManager().popFilter();
            myGame.fireEvent(this, myOnCloseEvent);
        }


        public void show ()
        {
            myGame.getEventManager().pushFilter();
            for (String filter : myFilter.split(":"))
                myGame.getEventManager().removeEventHandlers(filter);
            myPlayField.addGroup(mySpriteGroup);
            myGame.registerEventHandler(myCloseOnEvent, new IEventHandler()
            {
                @Override
                public void handleEvent (Object o)
                {
                    hide();
                }
            });
        }
    }

    private KeyMap myKeyMap =
        new KeyMap(ResourceBundle.getBundle("vooga.core.event.examples.duelingdan.resources.DefaultKeyMap"));


    public VoogaExampleGame ()
    {
        super();
        initializeEventHandlers();
    }


    private void initializeEventHandlers ()
    {
        getEventManager().registerEventHandler("EveryTurn.CheckInput",
                                               new IEventHandler()
                                               {
                                                   public void handleEvent (Object o)
                                                   {
                                                       pollForInput();
                                                   }
                                               });
    }


    private void pollForInput ()
    {
        for (Map.Entry<Integer, KeyMap.Entry> mapEntry : myKeyMap.entrySet())
        {
            KeyMap.Entry entry = mapEntry.getValue();
            if ((entry.sensitivity == KeyMap.Sensitivity.LEVEL && keyDown(entry.keyCode)) ||
                (entry.sensitivity == KeyMap.Sensitivity.EDGE && keyPressed(entry.keyCode)))
            {
                for (String eventName : entry.events)
                {
                    getEventManager().fireEvent(this, eventName, entry);
                }
            }
        }
    }
}
