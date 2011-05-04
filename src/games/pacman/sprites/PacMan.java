package games.pacman.sprites;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.collisions.shapes.collisionShapes.CollisionQuadrilateral;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

/**
 * PacMan, controllable by arrows keys.
 * 
 * @author DJ Sharkey
 *
 */
@SuppressWarnings("serial")
public class PacMan extends Sprite
{   
    /**
     * The pacman speed, in pixels per ms.
     */
    public static final Double PACMAN_SPEED = 5D; 
    
    private VoogaGame game;
    private int numLives;
    
    /**
     * @return The number of balls remaining in the supply.
     */
    public int getNumLives ()
    {
        return numLives;
    }

    /**
     * Set the number of balls in the supply.
     * 
     * @param ballCount The new number of balls.
     */
    public void setNumLives (int numLives)
    {
        this.numLives = numLives;
    }

    /**
     * Creates a new pacman at (x,y)
     * 
     * @param game The game that created this paddle.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public PacMan (VoogaGame game, double x, double y)
    {
        super(game.getImageLoader().getImage("pacman"));
        
        setX(x - getWidth()/2);
        setY(y - getHeight());
        setAngle(Direction.NORTH.getAngle());
        this.game = game;
        this.numLives = 3;
        int width = this.getWidth();
      //  this.addComponents(new CollisionPolygonC(new CollisionQuadrilateral(new Vertex(0,0), new Vertex(0,this.getWidth()), new Vertex(this.getHeight(),0), new Vertex(this.getWidth(),this.getHeight()))));
        
        game.registerEventHandler("Input.User.Start", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            		//TODO
            }            
        });
        
        game.registerEventHandler("Input.User.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setAngle(Direction.SOUTH.getAngle());
                shift(-PACMAN_SPEED,0);
            }            
        });
        
        game.registerEventHandler("Input.User.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setAngle(Direction.NORTH.getAngle());
                shift(PACMAN_SPEED,0);
            }            
        });
        
        game.registerEventHandler("Input.User.Up", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setAngle(Direction.EAST.getAngle());
                shift(0,-PACMAN_SPEED);
            }            
        });
        
        game.registerEventHandler("Input.User.Down", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setAngle(Direction.WEST.getAngle());
                shift(0,PACMAN_SPEED);
            }            
        });
        
        game.registerEventHandler("Game.LifeLost",  new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                //TODO
            }            
        });
    }
    
    /**
     * Move paddle, if the screen boundaries/walls allow.
     * @param dx The difference in x-coordinate.
     */
    protected void shift (double dx,double dy)
    {
        double newX = getX() + dx;
        double newY=getY()+dy;
        if (0 < newX && newX + getWidth() < game.getWidth())
            moveX(dx);
        if (0 < newY && newY + getHeight() < game.getHeight())
            moveY(dy);
    }

    
 //   System.exit(0);

  //  game.getLevelManager().addArchetypeSprite("ball", (int) getCenterX(), (int) getY());

}
