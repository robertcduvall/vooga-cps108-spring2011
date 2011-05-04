package games.mariobros.sprites;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

/**
 * Mario
 * 
 * @author Ethan Goh
 *
 */

public class Mario extends Sprite
{   
    
	private static final double movementSpeed = 5D; 
    private static final double jumpPower = 5D;
    private VoogaGame game;
    private int lives;
    
    /**
     * @return The number of balls remaining in the supply.
     */
    public int getLives ()
    {
        return lives;
    }

    /**
     * Set the number of balls in the supply.
     * 
     * @param life The new number of balls.
     */
    public void setLives (int life)
    {
        this.lives = life;
    }

    /**
     * Creates a new paddle on top of the given (x,y)
     * coordinate.
     * 
     * @param game The game that created this paddle.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Mario (VoogaGame game, double x, double y)
    {
        super(game.getImageLoader().getImage("mario"));
        
        setX(x - getWidth()/2);
        setY(y - getHeight());
        setAngle(Direction.NORTH.getAngle());
        
        this.game = game;
        lives = 3;
        
        game.registerEventHandler("Input.User.Cheat", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setLives(100);
            }            
        });
        
        game.registerEventHandler("Input.User.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                translate(-movementSpeed);
            }            
        });
        
        game.registerEventHandler("Input.User.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                translate(movementSpeed);
            }            
        });
        
        game.registerEventHandler("Input.User.Up", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                jump();
            }            
        });
        
    }

    /**
     * Move the paddle, if the screen boundaries allow for it.
     * @param dx The difference in x-coordinate.
     */
    private void translate (double dx)
    {
        double newX = getX() + dx;
        
        if (0 < newX && newX + getWidth() < game.getWidth())
            moveX(dx);
    }
    
    private void jump()
    {
    	
    }


}
