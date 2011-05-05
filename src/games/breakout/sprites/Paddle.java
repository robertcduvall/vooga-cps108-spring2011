package games.breakout.sprites;

import games.breakout.Breakout;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

/**
 * A paddle moved around by the player.
 * 
 * @author Misha
 *
 */
@SuppressWarnings("serial")
public class Paddle extends Sprite
{   
    /**
     * The paddle speed, in pixels per ms.
     */
    public static final Double PADDLE_SPEED = 5D; 
    
    private VoogaGame game;
    private boolean hasTheBall;
    private int ballCount;
    
    /**
     * @return The number of balls remaining in the supply.
     */
    public int getBallCount ()
    {
        return ballCount;
    }

    /**
     * Set the number of balls in the supply.
     * 
     * @param ballCount The new number of balls.
     */
    public void setBallCount (int ballCount)
    {
        this.ballCount = ballCount;
    }

    /**
     * Creates a new paddle on top of the given (x,y)
     * coordinate.
     * 
     * @param game The game that created this paddle.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
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
        
        game.registerEventHandler("Input.User.Cheat", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setBallCount(100);
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

    /**
     * Allow a new ball to be launched, if there 
     * are balls remaining.
     */
    public void prepareNewBall ()
    {
        hasTheBall = true;
    }

    /**
     * Move the paddle, if the screen boundaries allow for it.
     * @param dx The difference in x-coordinate.
     */
    protected void shift (double dx)
    {
        double newX = getX() + dx;
        
        if (0 < newX && newX + getWidth() < game.getWidth())
            moveX(dx);
    }

    /**
     * Launch the ball, if there is not a ball already launched.
     */
    protected void launchBall ()
    {
        if (hasTheBall)
        {
            ballCount--;
            hasTheBall = false;
            
            game.getLevelManager().addArchetypeSprite("ball", (int) getCenterX(), (int) getY());
        }
    }


}
