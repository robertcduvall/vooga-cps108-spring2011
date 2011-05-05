package games.spaceinvaders.sprites;

import games.spaceinvaders.Commons;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class Player extends Sprite implements Commons
{
    private VoogaGame myGame;
    private int lives;

    public Player(VoogaGame game, int x, int y) 
    {
        super(game.getImageLoader().getImage(ALIEN));
        myGame = game;
        lives = PLAYER_LIVES;
        setX(x - getWidth()/2);
        setY(y - getHeight());

        game.registerEventHandler("Input.User.Left", new IEventHandler() 
        {
            @Override
            public void handleEvent(Object o) 
            {
                move(-1 * PLAYER_SPEED);
            }
        });

        game.registerEventHandler("Input.User.Right", new IEventHandler() 
        {
            @Override
            public void handleEvent(Object o) 
            {
                move(PLAYER_SPEED);
            }
        });
        
        game.registerEventHandler("Input.User.Fire", new IEventHandler() 
        {
            @Override
            public void handleEvent(Object o) 
            {
                fire(BULLET_SPEED);
            }
        });
    }
    
//    private void checkGameOver() 
//    {
//        if(lives > 0)
//        {
//            myGame.getLevelManager().addArchetypeSprite("player", getWidth() / 2, 0);
//        }
//    }

    private void move(double dx) 
    {
        this.moveX(dx);
    }
    
    private void fire(double dy)
    {
        this.moveY(dy);
    }
}