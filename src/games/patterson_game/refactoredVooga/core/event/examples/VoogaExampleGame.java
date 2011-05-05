package games.patterson_game.refactoredVooga.core.event.examples;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.core.event.IEventHandler;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


public abstract class VoogaExampleGame extends VoogaGame
{
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


    public VoogaExampleGame ()
    {
        super();
    }
}
