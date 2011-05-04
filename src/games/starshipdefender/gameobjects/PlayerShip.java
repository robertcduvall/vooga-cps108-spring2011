package games.starshipdefender.gameobjects;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class PlayerShip extends Sprite
{
    private VoogaGame myGame;
    private int myHealth;
    private int xpos;
    private int ypos;
    
    public PlayerShip(VoogaGame game)
    {
        myGame = game;
        this.createEventHandler();
    }
    
    public PlayerShip(VoogaGame game, int x, int y)
    {
        this(game);
        xpos = x;
        ypos = y;
    }
    
    private void createEventHandler()
    {
        myGame.registerEventHandler("Input.User.Shoot", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                Sprite shot = myGame.getLevelManager().addSpriteFromPool("torpedo");
                shot.setLocation(xpos, ypos);
                shot.moveTo(myGame.bsTimer.getTime(), myGame.getMouseX(), myGame.getMouseY());
            }
        });
    }
    
    public void updateHealth(int health)
    {
        myHealth += health;
    }
}
