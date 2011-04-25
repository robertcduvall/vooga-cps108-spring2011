package games.asteroids;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.predefined.basic.HealthC;
import vooga.util.buildable.components.predefined.movement.Speed2DC;


public class Ship extends Sprite
{
    private static final long serialVersionUID = 1L;
    private VoogaGame myGame;


    public Ship (VoogaGame game)
    {
    	super(game.getImageLoader().getImage("ship"));
        myGame = game;
        // TODO setImage(game.getImageLoader().getImage("ship"));
        bindKeys();
        this.addComponent(new HealthC(42.0));
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
        getComponent(HealthC.class).decrease(1.0);
        if(getComponent(HealthC.class).isDead()) {
//        	myGame.playerDied();
        }
    }


    public void shoot ()
    {
       Sprite bullet = myGame.getLevelManager().addArchetypeSprite("bullet", (int)getX(), (int)getY());
       // TODO set bullet velocity based on current angle
    }


    public void thrust ()
    {
        super.accelerate(-.01, 10);
        //TODO PHYSICS!
    }


    public void turnLeft ()
    {
       this.setAngle(getAngle() + 1);
    }


    public void turnRight ()
    {
        setAngle(getAngle() - 1);
    }
}
