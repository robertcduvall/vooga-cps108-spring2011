package games.starshipdefender.gameobjects;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class TargetingImage extends Sprite
{
    private VoogaGame myGame;
    
    public TargetingImage(VoogaGame game)
    {
        myGame = game;
        this.createEventHandler();
    }
    
    private void createEventHandler()
    {
            myGame.addEveryTurnEvent("Input.User.Target", new IEventHandler()
            {
                    @Override
                    public void handleEvent(Object o)
                    {
                        moveTarget();
                    }
            });
    }
    
    private void moveTarget()
    {
        this.moveTo(myGame.bsTimer.getTime(), myGame.getMouseX(), myGame.getMouseY());
    }
}
