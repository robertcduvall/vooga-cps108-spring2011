package games.breakout.sprites;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Paddle extends Sprite
{   
    private static final Double PADDLE_SPEED = 3D; 
    
    private VoogaGame game;
    private boolean hasTheBall;
    private int ballCount;
    
    public int getBallCount ()
    {
        return ballCount;
    }

    public void setBallCount (int ballCount)
    {
        this.ballCount = ballCount;
    }

    public Paddle (VoogaGame game, double x, double y)
    {
        super(game.getImageLoader().getImage("paddle"));
        
        setX(x - getWidth()/2);
        setY(y - getHeight());
        setAngle(Direction.NORTH.getAngle());
        
        this.game = game;
        ballCount = 3;
        hasTheBall = true;
        
        game.registerEventHandler("Input.User.Start", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                launchBall();
            }            
        });
        
        game.registerEventHandler("Input.User.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shift(-PADDLE_SPEED);
            }            
        });
        
        game.registerEventHandler("Input.User.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shift(PADDLE_SPEED);
            }            
        });
        
        game.registerEventHandler("Game.BallLost",  new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                prepareNewBall();
            }            
        });
    }

    protected void prepareNewBall ()
    {
        hasTheBall = true;
        
        if (ballCount == 0)
            System.exit(0);
    }

    protected void shift (double dx)
    {
        double newX = getX() + dx;
        
        if (0 < newX && newX + getWidth() < game.getWidth())
            moveX(dx);
    }

    protected void launchBall ()
    {
        if (ballCount > 0 && hasTheBall)
        {
            ballCount--;
            hasTheBall = false;
            
            game.getLevelManager().addArchetypeSprite("ball", (int) getCenterX(), (int) getY());
        }
    }


}
