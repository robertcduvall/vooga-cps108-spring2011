package vooga.core.event.examples.duelingdan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.core.event.examples.VoogaExampleGame;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class DuelingDan extends VoogaExampleGame
{
    public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new DuelingDan(), new Dimension(480, 360), false);
        loader.start();
    }

    private SimpleMenu levelCompleteMenu;
    private List<Bubble> myBubbles;
    private Dan myDan;
    private PlayField myPlayField;
    private SimpleMenu pausedMenu;


    private void beginLevel ()
    {
    	getEventManager().setDebugMode(true);
        getEventManager().resetFilterToParentState();
        // TODO load level from file
        // TODO initialize stats, play field
        myDan.moveToCenter();
        myDan.getShotsSpriteGroup().reset();
        while(!myBubbles.isEmpty())
        {
            Bubble bubble = myBubbles.get(0);
            bubble.destroy();
            myBubbles.remove(bubble);
        }
        getBubbleSpriteGroup().reset();

        addPeriodicTimer("BubbleTimer", 1000, "User.GamePlay.Spawn.Bubble");
        // return and let all future actions be driven by input/timer events
    }


    protected void destroyBubble (Sprite bubbleSprite)
    {
        Bubble bubble = ((Bubble) bubbleSprite.getDataID());
        bubble.destroy();
        myBubbles.remove(bubble);
        if (myBubbles.isEmpty()) fireEvent(this, "User.GamePlay.LevelComplete");
    }


    private SpriteGroup getBubbleSpriteGroup ()
    {
        return myPlayField.getGroup("Bubbles");
    }


    private void initializeEventHandlers ()
    {
        registerEventHandler("Input.User.Move.Up", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                myDan.moveUp();
            }

        });
        registerEventHandler("Input.User.Move.Down", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                myDan.moveDown();
            }

        });
        registerEventHandler("Input.User.Move.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                myDan.moveLeft();
            }

        });
        registerEventHandler("Input.User.Move.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                myDan.moveRight();
            }

        });

        registerEventHandler("Input.User.FireWeapon", new IEventHandler()
        {
            public void handleEvent (Object o)
            {
                myDan.fireWeapon();
            }
        });
        registerEventHandler("Input.User.ChangeWeapon.Next",
                             new IEventHandler()
                             {
                                 public void handleEvent (Object o)
                                 {
                                     myDan.changeWeaponNext();
                                 }
                             });
        registerEventHandler("Input.User.ChangeWeapon.Previous",
                             new IEventHandler()
                             {
                                 public void handleEvent (Object o)
                                 {
                                     myDan.changeWeaponPrevious();
                                 }
                             });
        registerEventHandler("Input.User.ChangeWeapon.Exact",
                             new IEventHandler()
                             {
                                 public void handleEvent (Object o)
                                 {
                                     @SuppressWarnings("unchecked")
                                     Map.Entry<Integer, Set<String>> entry =
                                         (Entry<Integer, Set<String>>) o;
                                     myDan.changeWeapon((Integer) Integer.parseInt(KeyEvent.getKeyText(entry.getKey())));
                                 }
                             });

        forwardEvent("Input.User.Pause", "User.GamePlay.Pause");
        registerEventHandler("User.GamePlay.Pause", new IEventHandler()
        {
            public void handleEvent (Object o)
            {
                pausedMenu.show();
            }
        });

        registerEventHandler("User.GamePlay.LevelComplete", new IEventHandler()
        {
            public void handleEvent (Object o)
            {
                levelCompleteMenu.show();
            }
        });
        registerEventHandler("User.GamePlay.BeginLevel", new IEventHandler()
        {
            public void handleEvent (Object o)
            {
                beginLevel();
            }
        });

        registerEventHandler("User.GamePlay.Spawn.Bubble", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                spawnBubble();
            }
        });
    }


    private void initializeNonPlayerCharacters ()
    {
        myPlayField.addGroup(new SpriteGroup("Bubbles"));

        myPlayField.addCollisionGroup(myDan.getShotsSpriteGroup(),
                                      this.getBubbleSpriteGroup(),
                                      new BasicCollisionGroup()
                                      {
                                          public void collided (Sprite shot,
                                                                Sprite bubble)
                                          {
                                              shot.setActive(false);
                                              destroyBubble(bubble);
                                          }
                                      });
    }


    @Override
    public void initResources ()
    {
        myPlayField = new PlayField();
        myPlayField.setBackground(new ColorBackground(Color.LIGHT_GRAY,
                                                      800,
                                                      600));

        levelCompleteMenu =
            new SimpleMenu(this,
                           myPlayField,
                           getImage("resources/level_complete.png"),
                           "Input.*:User.GamePlay.Spawn.*",
                           "Input.User.Select",
                           "User.GamePlay.BeginLevel");
        pausedMenu =
            new SimpleMenu(this,
                           myPlayField,
                           getImage("resources/paused.png"),
                           "Input.*:*.User.*:User.*:EveryTurn.UpdatePlayField",
                           "Input.User.Pause",
                           "");

        myDan = new Dan(this);
        myPlayField.add(myDan.getSprite());
        myPlayField.addGroup(myDan.getShotsSpriteGroup());

        myBubbles = new ArrayList<Bubble>();

        initializeEventHandlers();
        initializeNonPlayerCharacters();

        getEventManager().pushFilter(); // Checkpoint the initial system state

        // Bootstrap the game (may be handled by Game Loop team instead of individual game builders)
        fireEvent(this, "User.GamePlay.BeginLevel");
    }


    @Override
    public void render (Graphics2D g)
    {
        myPlayField.render(g);
    }


    private Bubble spawnBubble ()
    {
        Bubble bubble = new Bubble(this);
        myBubbles.add(bubble);
        getBubbleSpriteGroup().add(bubble.getSprite());
        return bubble;

    }


    @Override
    public void updatePlayField (long elapsedTime)
    {
        for (Bubble bubble : myBubbles)
            bubble.updateMovement();
        myPlayField.update(elapsedTime);
    }

}
