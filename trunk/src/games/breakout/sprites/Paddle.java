package games.breakout.sprites;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.predefined.movement.Speed2DC;

@SuppressWarnings("serial")
public class Paddle extends Sprite
{   
    private static final Double PADDLE_SPEED = 1D; 
    
    private VoogaGame game;
    private int ballCount;
    
    public Paddle (VoogaGame game, double x, double y)
    {
        super(game.getImageLoader().getImage("paddle"));
        
        setX(x - getWidth()/2);
        setY(y - getHeight());
        
        this.game = game;
        ballCount = 3;
        
        game.registerEventHandler("Input.Start", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                launchBall();
            }            
        });
        
        game.registerEventHandler("Input.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shift(-PADDLE_SPEED);
            }            
        });
        
        game.registerEventHandler("Input.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shift(PADDLE_SPEED);
            }            
        });
    }

    protected void shift (double dx)
    {
        double newX = getX() + dx;
        
        if (0 < newX && newX + getWidth() < game.getWidth())
            setX(newX);
    }

    protected void launchBall ()
    {
        if (ballCount > 0)
        {
            ballCount--;
            
            game.getLevelManager().addArchetypeSprite("ball", (int) getCenterX(), (int) getY(),
                                                      new Speed2DC(new Double(1), new Double(-1)));
        }
    }

}
