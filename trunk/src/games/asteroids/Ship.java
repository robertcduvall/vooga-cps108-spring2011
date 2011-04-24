package games.asteroids;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;


public class Ship extends Sprite
{
    private static final long serialVersionUID = 1L;
    private VoogaGame myGame;


    public Ship (VoogaGame game)
    {
        myGame = game;
        // TODO setImage(game.getImageLoader().getImage("ship"));
        setImage(game.getImageLoader().getImage("ship"));
        bindKeys();
        // TODO addComponent(new HealthComponent());
    }


    private void bindKeys ()
    {
        myGame.registerEventHandler("Input.User.Thrust", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                thrust();
            }
        });
        myGame.registerEventHandler("Input.User.TurnLeft", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                turnLeft();
            }
        });
        myGame.registerEventHandler("Input.User.TurnRight", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                turnRight();
            }
        });
        myGame.registerEventHandler("Input.User.Shoot", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shoot();
            }
        });
    }


    public void damage ()
    {
        // TODO getComponent(HealthComponent.class).decreaseBy(1.0);
        // TODO if(getComponent(HealthComponent.class).isDead()) game.playerDied();
    }


    public void shoot ()
    {
       Sprite bullet = myGame.getLevelManager().addArchetypeSprite("bullet", (int)getX(), (int)getY());
       // TODO set bullet velocity based on current angle
    }


    public void thrust ()
    {
        // TODO applyForce(0.01, getAngle());
    }


    public void turnLeft ()
    {
        // TODO setAngle(getAngle() - 1);
    }


    public void turnRight ()
    {
        // TODO setAngle(getAngle() + 1);
    }
}
